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
 * Encrypted text decryption class.
 * @author seph
 */
public final class Decrypt {
    
    private static Cipher _cipher;

    private static void init(SecretKey key) {
        try {
            _cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            _cipher.init(Cipher.DECRYPT_MODE, key);

        } catch (javax.crypto.NoSuchPaddingException ex) { ex.printStackTrace();
        } catch (java.security.NoSuchAlgorithmException ex) { ex.printStackTrace();
        } catch (java.security.InvalidKeyException ex) { ex.printStackTrace();
        }
    }
    
    /**
     * Deciphers the specified encrypted text and interprets its original plain text value. 
     * @param value Encrypted text to be deciphered.
     * @return Original plain text value of the specified encrypted text
     */
    public static String valueOf(String value) {
        return valueOf(value, "mimemateria");
    }
    
    /**
     * Deciphers the specified encrypted text and interprets its original plain text value. 
     * @param value Encrypted text to be deciphered.
     * @param key Decryption key string pattern.
     * @return Original plain text value of the specified encrypted text
     */
    public static String valueOf(String value, String key) { 
        try {
            String _key=key;
            if (_key.length() < 20) _key=key + "additionalkeystring";
            
            DESKeySpec _keyspec = new DESKeySpec(_key.getBytes("UTF8"));  
            SecretKeyFactory _keyfactory=SecretKeyFactory.getInstance("DES");
                
            init(_keyfactory.generateSecret(_keyspec));
           	  
            byte[] _raw = Base64.decodeBase64(value);
	  
            byte[] _bytes = _cipher.doFinal(_raw);
   
            String _plain = new String(_bytes, "UTF8");  
            return _plain;
        } catch (javax.crypto.BadPaddingException ex) { ex.printStackTrace();
        } catch (IllegalBlockSizeException ex) { ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) { ex.printStackTrace();
        } catch (java.io.IOException ex) { ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) { ex.printStackTrace();
        } catch (InvalidKeyException ex) { ex.printStackTrace();
        } catch (InvalidKeySpecException ex) { ex.printStackTrace();
        }
        
        return null;
    }
    
}
