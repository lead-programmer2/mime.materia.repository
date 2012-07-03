/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown when an error regarding primary key column has been encountered.
 * @author seph
 */
public class DataColumnConstraintException extends RuntimeException {
    
    /**
     * Creates a new instance of DataColumnConstraintException.
     */
    public DataColumnConstraintException() {
        super();
    }
    
    /**
     * Creates a new instance of DataColumnConstraintException.
     * @param message Error message.
     */
    public DataColumnConstraintException(String message) {
        super(message);
    }
}
