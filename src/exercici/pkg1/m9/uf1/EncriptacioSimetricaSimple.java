/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercici.pkg1.m9.uf1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncriptacioSimetricaSimple {

    public static byte[] cifrarFichero(String texto, SecretKey clave) throws NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.ENCRYPT_MODE, clave);

        byte[] buffer = texto.getBytes();
        byte[] bufferCifrado;
        System.out.println("Texto sin cifrar en bytes");
        System.out.println(Arrays.toString(buffer));
        
        bufferCifrado = cifrador.doFinal(buffer); //Completa el cifrado
        System.out.println("Texto cifrado en bytes");
        System.out.println(Arrays.toString(bufferCifrado));
        System.out.println("Texto cifrado");
        System.out.println(new String(bufferCifrado));
        
        return bufferCifrado;
    }

    private static void descifrarFichero(byte[] bufferCifrado, SecretKey clave) throws NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
       
        Cipher descifrador = Cipher.getInstance("AES");
        descifrador.init(Cipher.DECRYPT_MODE, clave);

        byte[] bufferClaro;
        bufferClaro = descifrador.doFinal(bufferCifrado); //Completa el descifrado
        System.out.println("Texto descifrado");
        System.out.println(new String(bufferClaro));
    }

    public static void main(String[] Args) {
        //Recursos que utilizaremos
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cadena;

        //Realizamos la lectura del usuario y password y realizamos la encriptación y desencriptación
        try {
            System.out.println("Introduce la cadena: ");
            cadena = reader.readLine();

            int keySize = 256;
            while (true) {
                System.out.println("Introdueix el tamany de la clau (128, 192 o 256): ");
                keySize = Integer.parseInt(reader.readLine());
                if (keySize == 128 || keySize == 192 || keySize == 256) {
                    break;
                }
            }

            //Generamos la clave a partir del usuario y el password
            KeyGenerator key_gen = KeyGenerator.getInstance("AES");
            SecureRandom secure = new SecureRandom();
            secure.setSeed(cadena.getBytes());
            key_gen.init(keySize, secure);
//            key_gen.init(keySize);

            // Generamos la clave.
            SecretKey key = key_gen.generateKey();

            descifrarFichero(cifrarFichero("Hola Mundo", key), key);
            
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println(e.getMessage());
        }
    }
}