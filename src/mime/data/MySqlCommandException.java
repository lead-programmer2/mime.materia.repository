/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown relative to MySqlCommand error.
 * @author seph
 */
public class MySqlCommandException extends RuntimeException {
    
    /**
     * Creates a new instance of MySqlCommandException.
     */
    public MySqlCommandException() {
        super();
    }
    
    /**
     * Creates a new instance of MySqlCommandException.
     * @param message Error message
     */
    public MySqlCommandException(String message) {
        super(message);
    }
    
}
