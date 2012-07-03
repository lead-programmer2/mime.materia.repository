/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown relative to DataRow errors.
 * @author seph
 */
public class DataRowException extends RuntimeException {
    
    /**
     * Creates a new instance of DataRowException.
     */
    public DataRowException() {
        super();
    }
    
    /**
     * Creates a new instance of DataRowException.
     * @param message Error message.
     */
    public DataRowException(String message) {
        super(message);
    }
    
}
