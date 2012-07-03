/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown relevant to database connectivity error.
 * @author seph
 */
public class DatabaseConnectionException extends RuntimeException {
    
    /**
     * Creates a new instance of DatabaseConnectionException.
     */
    public DatabaseConnectionException() {
        super();
    }
    
    /**
     * Creates a new instance of DatabaseConnectionException.
     * @param message Error message
     */
    public DatabaseConnectionException(String message) {
        super(message);
    }
}
