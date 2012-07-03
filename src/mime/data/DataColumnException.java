/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown related to ordinary DataColumn errors.
 * @author seph
 */
public class DataColumnException extends RuntimeException {
    
    /**
     * Creates a new instance of DataColumnException.
     */
    public DataColumnException() {
        super();
    }
    
    /**
     * Creates a new instance of DataColumnException.
     * @param message Error message.
     */
    public DataColumnException(String message) {
        super(message);
    }
}
