/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

/**
 * Exception thrown related to existing collection attached to a certain object error.
 * @author seph
 */
public class CollectionExistException extends RuntimeException {
    
    /**
     * Creates a new instance of CollectionExistException.
     */
    public CollectionExistException() {
        super();
    }
    
    /**
     * Creates a new instance of CollectionExistException.
     * @param message Error message.
     */
    public CollectionExistException(String message) {
       super(message);
   }
    
}
