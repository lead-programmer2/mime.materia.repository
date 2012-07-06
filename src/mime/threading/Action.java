/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.threading;

import javax.swing.SwingWorker;


/**
 * Mimics VB.Net Action class.
 * @author seph
 */
public class Action {

    private SwingWorker _worker=null;
    private Invokable _invokable=null;
    private Invokable _callback=null;
    
    /**
     * Creates a new instance of Action.
     * @param  invokingmethod Method to be invoked.
     */
    public Action(Invokable invokingmethod) {
        this(invokingmethod, null);
    }
    
    /**
    * Creates a new instance of Action.
     * @param  invokingmethod Method to be invoked.
     * @param callback Callback method that will execute the synchronization is done.
     */
    public Action(Invokable invokingmethod, Invokable callback) {
        _invokable=invokingmethod; _callback=callback;
    }
    /**
     * Executes and invokes the initialized Invokable method asynchronously.
     */
    public void invoke() {
        if (_worker!=null) throw new RuntimeException("Thread is already running.");
        _worker=new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                _invokable.invoke(); return null;
            }
            
            @Override
            public void done() {
                if (_callback!=null) _callback.invoke();
                
                try {
                    _worker=null;
                    finalize(); System.gc();
                }
                catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        };
        _worker.execute();
    }
    
    
}
