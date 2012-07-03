/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Exception throw relative to MySqlParameter error.
 * @author seph
 */
public class MySqlParameterException extends RuntimeException {
    
    /**
     * Creates a new instance of MySqlParameterException.
     */
    public MySqlParameterException() {
        super();
    }
    
    /**
     * Creates a new instance of MySqlParameterException.
     * @param message Error message
     */
    public MySqlParameterException(String message) {
        super(message);
    }
}
