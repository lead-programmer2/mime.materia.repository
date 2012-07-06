/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threading;


/**
 * Mimics VB.Net Action class.
 * @author seph
 */
public abstract class Action implements Runnable {

    private Invokable _invokable=null;
    
    /**
     * Creates a new instance of Action.
     */
    public Action(Invokable invokingmethod) {
        _invokable=invokingmethod;
    }
    
    @Override
    public void run() {
        _invokable.invoke();
    }
    
}
