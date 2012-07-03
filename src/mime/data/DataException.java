/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown relative to table data error.
 * @author seph
 */
public class DataException extends RuntimeException {
    
    /**
     * Creates a new instance of DataException.
     */
    public DataException() {
        super();
    }
    
    /**
     * Creates a new instance of DataException.
     * @param message Error message.
     */
    public DataException(String message) {
        super(message);
    }
    
}
