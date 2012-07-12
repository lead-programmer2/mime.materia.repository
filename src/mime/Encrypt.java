/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * Text encryption class
 * @author seph
 */
public final class Encrypt {
    
    private static Cipher _cipher;

    private static void init(SecretKey key) {
        try {
            _cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            _cipher.init(Cipher.ENCRYPT_MODE, key);

        } catch (javax.crypto.NoSuchPaddingException ex) { ex.printStackTrace();
        } catch (java.security.NoSuchAlgorithmException ex) { ex.printStackTrace();
        } catch (java.security.InvalidKeyException ex) { ex.printStackTrace();
        }
    }
    
    /**
     * Generates the encrypted value of the specified text. 
     * @param value Plain text to be encrypted 
     * @return Triple-DES-encrypted text representation of the specified value.
     */
    public static String valueOf(String value) {
        return valueOf(value, "mimemateria");
    }
    
    /**
     * Generates the encrypted value of the specified text. 
     * @param value Plain text to be encrypted 
     * @param key Encryption key pattern string.
     * @return Triple-DES-encrypted text representation of the specified value.
     */
    public static String valueOf(String value, String key) { 
        try {
             
            String _key=key;
            if (_key.length() < 20) _key=key + "additionalkeystring";
             
            DESKeySpec _keyspec = new DESKeySpec(_key.getBytes("UTF8"));   
            SecretKeyFactory _keyfactory=SecretKeyFactory.getInstance("DES");
              
            init(_keyfactory.generateSecret(_keyspec));
              
            byte[] _utf8 = value.getBytes("UTF8");
            byte[] _encrypted = _cipher.doFinal(_utf8);

            return Base64.encodeBase64String(_encrypted);
        } catch (javax.crypto.BadPaddingException ex) { ex.printStackTrace();
        } catch (IllegalBlockSizeException ex) { ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) { ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) { ex.printStackTrace();
        } catch (InvalidKeySpecException ex) { ex.printStackTrace();
        } catch (InvalidKeyException ex) { ex.printStackTrace();
        }
        
        return null;
    }
}
