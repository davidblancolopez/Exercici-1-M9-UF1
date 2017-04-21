package exercici.pkg1.m9.uf1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Exercici1M9UF1 {

    public static void main(String[] args) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, FileNotFoundException, IllegalBlockSizeException, BadPaddingException {
        int keySize = 0;
        String key;
        while (keySize != 128 && keySize != 192 && keySize != 256) {
            System.out.println("Introdueix el tamany de la clau (128, 192 o 256): ");
            Scanner sc = new Scanner(System.in);
            keySize = sc.nextInt();
        }

        System.out.println("Introdueix la clau del algoritme SHA: ");
        Scanner sc = new Scanner(System.in);
        key = sc.next();

        SeguretatAES sae = new SeguretatAES(keySize);
        
        SecretKey clau = sae.generarClau(key, keySize);
        
        sae.xifrarFitxer("texto.txt", clau);
        
        sae.desxifrarFitxer("mensajeCifrado.txt", clau, "mensajeDescifrado.txt");
      
    }
}
