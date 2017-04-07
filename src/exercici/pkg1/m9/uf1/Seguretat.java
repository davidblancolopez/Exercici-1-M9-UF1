/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercici.pkg1.m9.uf1;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Seguretat {

    private final int keySize;
    private final String ALGORITME = "AES";
    private final String ALGORITME2 = "SHA-256";

    public Seguretat(int keySize) {
        this.keySize = keySize;
    }

    public SecretKey keygenKeyGeneration(int keySize) {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance(ALGORITME);
                kgen.init(keySize);
                sKey = kgen.generateKey();
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible.");
            }
        }
        return sKey;
    }

    public SecretKey passwordKeyGeneration(String text, int keySize) {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                //Convertim la cadena en Array de bytes
                byte[] data = text.getBytes("UTF-8");
                //Creem objecte MEssageDisgest amb algortime SHA-256
                MessageDigest md = MessageDigest.getInstance(ALGORITME2);
                //Generem contrasenya de 32 bytes
                byte[] hash = md.digest(data);
                //Retallem la contrasenya al nombre de bytes solicitats a keySize
                byte[] key = Arrays.copyOf(hash, keySize / 8);
                //Imprimim el Array de bytes de la cadena
                System.out.println(Arrays.toString(data));
                //Imprimim el Array de 32 bytes generat
                System.out.println(Arrays.toString(hash));
                //Imprimim el Array de bytes retallat a keySize/8
                System.out.println(Arrays.toString(key));
                //creem el SecretKey amb la clau obtinguda i el algoritme AES
                sKey = new SecretKeySpec(key, ALGORITME);
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                System.err.println("Error generant la clau:" + ex);
            }
        }
        return sKey;
    }
}