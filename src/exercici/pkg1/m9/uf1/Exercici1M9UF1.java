package exercici.pkg1.m9.uf1;

import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.SecretKey;

public class Exercici1M9UF1 {

    public static void main(String[] args) {
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

        Seguretat sg = new Seguretat(keySize);

        SecretKey sk = sg.keygenKeyGeneration(keySize);
        System.out.println(sk.getAlgorithm());
        System.out.println(Arrays.toString(sk.getEncoded()));
        System.out.println(Arrays.toString(sk.getEncoded()).length());

        System.out.println("****************************************");

        SecretKey sk2 = sg.passwordKeyGeneration(key, keySize);
        System.out.println(sk2.getAlgorithm());
        System.out.println(Arrays.toString(sk2.getEncoded()));
        System.out.println(Arrays.toString(sk2.getEncoded()).length());

    }
}
