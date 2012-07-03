/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown relative to primary DataColumn key value multiple instance attempts.
 * @author seph
 */
public class DuplicateKeyException extends RuntimeException {
    
    /**
     * Creates a new instance of DuplicateKeyException.
     */
    public DuplicateKeyException() {
        super();
    }
    
    /**
     * Creates a new instance of DuplicateKeyException.
     * @param message Error message.
     */
    public DuplicateKeyException(String message) {
        super(message);
    }
}
