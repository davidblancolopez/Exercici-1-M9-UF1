package exercici.pkg1.m9.uf1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import static sun.security.krb5.internal.ktab.KeyTabConstants.keySize;

public class SeguretatAES {

    
    private final int keySize;
    private final String ALGORITME = "AES";

    public SeguretatAES(int keySize) {
        this.keySize = keySize;
    }

    /**
     * Metodo para generar la clave.
     *
     * @param contrasenya
     * @param tamany
     * @return
     */
    public SecretKey generarClau(String contrasenya, int tamany) throws UnsupportedEncodingException {
        SecretKey clau = null;

        if ((tamany == 128) || (tamany == 192) || (tamany == 256)) {
            try {
                //Es pasa la contrasenya a un array de bytes.
                byte[] data = contrasenya.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, tamany / 8);
                clau = new SecretKeySpec(key, "AES");
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible.");
            }
        }
        return clau;
    }

    /**
     * Metodo para cifrar un fichero.
     *
     * @param fitxer
     * @param clau
     */
    public void xifrarFitxer(String fitxer, SecretKey clau) throws FileNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, IllegalBlockSizeException, BadPaddingException {
        FileInputStream fis = new FileInputStream(fitxer);
        
        Cipher ciph = Cipher.getInstance("AES");
        ciph.init(Cipher.ENCRYPT_MODE, clau);
        
        
        byte[] buffer = new byte[1000];
        while(fis.read(buffer, 0, buffer.length)!=-1){
            ciph.update(buffer, 0, buffer.length);
        }
        FileOutputStream fos = new FileOutputStream("mensajeCifrado.txt");
        fos.write(ciph.doFinal());
    }

    /**
     * Metodo para descifrar un fichero.
     *
     * @param fitxerEncriptat
     * @param clave
     * @param fitxerDesencriptat
     */
    public void desxifrarFitxer(String fitxerEncriptat, SecretKey clave, String fitxerDesencriptat) {

    }

}
