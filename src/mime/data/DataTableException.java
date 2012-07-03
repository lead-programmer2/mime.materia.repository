/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown relative to DataTable error.
 * @author seph
 */
public class DataTableException extends RuntimeException {
    
    /**
     * Creates a new instance of DataTableException.
     */
    public DataTableException() {
        super();
    }
    
    /**
     * Creates a new instance of DataTableException.
     * @param message Error message.
     */
    public DataTableException(String message) {
        super(message);
    }
    
}
