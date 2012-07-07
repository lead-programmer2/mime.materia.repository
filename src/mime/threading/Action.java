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
    private Invokable _progress=null;
    
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
     * Cancels the process of the currently running thread.
     */
    public void cancel() {
        if (_worker!=null) {
            try {
                _worker.cancel(true);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
           
            try {
                _worker=null; System.gc();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
        
    
    /**
     * Gets the encapsulated method that will be executed synchronously along with the assigned invoking method.
     * @return Encapsulated method that is executed upon thread execution.
     */
    public Invokable getProgressEvent() {
        return _progress;
    }
    
    /**
     * Executes and invokes the initialized Invokable method asynchronously.
     */
    public void invoke() {
        if (_worker!=null) throw new RuntimeException("Thread is already running.");
        _worker=new SwingWorker<Void, Integer>() {
            
            @Override
            protected Void doInBackground() throws Exception {
                _invokable.invoke(); return null;
            }
            
            @Override
            public void done() {
                if (_callback!=null) _callback.invoke();
           
                try {
                    finalize(); System.gc();
                }
                catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
           
        };
           
        _worker.execute();
          
        if (_progress!=null) {
            Action _progressdelegate=new Action(new Invokable() {

                @Override
                public void invoke() {
                    while (!_worker.isDone() &&
                           !_worker.isCancelled()) _progress.invoke();
                }
            });
            _progressdelegate.invoke();
        }
    }
    
    /**
     * Gets whether the current thread is already canceled or not.
     * @return True if process has been canceled, otherwise false.
     */
    public boolean isCancelled() {
        if (_worker!=null) return _worker.isCancelled();
        else return false;
    }
    
    /**
     * Gets whether the current thread is already complete or not.
     * @return True if thread is completed, otherwise false.
     */
    public boolean  isDone() {
        if (_worker!=null) return _worker.isDone();
        else return true;
    }
    
    /**
     * Sets the encapsulated invokable method which will be executed alongside the thread execution.
     * @param progressevent 
     */
    public void setProgressEvent(Invokable progressevent) {
        _progress=progressevent;
    }
    
}
