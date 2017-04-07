package exercici.pkg1.m9.uf1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import static sun.security.krb5.internal.ktab.KeyTabConstants.keySize;

public class SeguretatAES {
    private final int keySize;
    private final String ALGORITME = "AES";

    
    
    public SeguretatAES(int keySize) {
        this.keySize = keySize;
    }
    
    
    public SecretKey generarClau() {
        SecretKey clau = null;

        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance(ALGORITME);
                kgen.init(keySize);
                clau = kgen.generateKey();
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible.");
            }
        }
        return clau;
    }
    
    
    public void xifrarFitxer(String fitxer, SecretKey clau) {

    }

    public void desxifrarFitxer(String fitxerEncriptat, SecretKey clave, String fitxerDesencriptat) {

    }

}
