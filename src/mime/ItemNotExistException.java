/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

/**
 * Exception thrown when no items match with the current searched item in a certain collection.
 * @author seph
 */
public class ItemNotExistException extends RuntimeException {
    
    /**
     * Creates a new instance of ItemNotExistException.
     */
    public ItemNotExistException() {
        super();
    }
    
    /**
     * Creates a new instance of ItemNotExistException.
     * @param message Error message.
     */
    public ItemNotExistException(String message) {
        super(message);
    }
}
