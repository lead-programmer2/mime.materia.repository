/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

/**
 * Exception thrown relative to type casting errors.
 * @author seph
 */
public class InvalidCastException extends RuntimeException {
    
    /**
     * Create a new instance of InvalidCastException.
     */
    public InvalidCastException() {
        super();
    }
    
    /**
     * Create a new instance of InvalidCastException.
     * @param message Error message.
     */
    public InvalidCastException(String message) {
        super(message);
    }
}
