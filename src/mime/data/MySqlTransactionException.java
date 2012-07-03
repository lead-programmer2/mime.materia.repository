/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown relative to database transaction error.
 * @author seph
 */
public class MySqlTransactionException extends RuntimeException {
    
    /**
     * Creates a new instance of MySqlTransactionException
     */
    public MySqlTransactionException() {
        super();
    }
    
    /**
     * Creates a new instance of MySqlTransactionException.
     * @param message Error message.
     */
    public MySqlTransactionException(String message) {
        super(message);
    }
    
}
