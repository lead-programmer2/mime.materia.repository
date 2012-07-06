/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.threading;

/**
 * Interface to call on certain method and have it encapsulated.
 * @author seph
 */
public interface Invokable {
    
    /**
     * Calls and executes the encapsulated code blocks.
     */
    public void invoke();
    
}
