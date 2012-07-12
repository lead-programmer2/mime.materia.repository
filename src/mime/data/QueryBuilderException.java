/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception thrown relative to QueryBuilder error.
 * @author seph
 */
public class QueryBuilderException extends RuntimeException {
    
    /**
     * Creates a new instance of QueryBuilderException
     */
    public QueryBuilderException() {
        super();
    }
    
    /**
     * Creates a new instance of QueryBuilderException
     * @param message Error message
     */
    public QueryBuilderException(String message) {
        super(message);
    }
    
}
