/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.threading;

import javax.swing.SwingWorker;

/**
 * Mimics VB.Net Func delegate
 * @author seph
 */
public class Func <T> {
    
    private Returnable<T> _returnable=null;
    private Invokable _callback=null;
    private SwingWorker _worker=null;
    private Invokable _progress=null;
    
    /**
     * Creates a new instance of Func.
     * @param returnable Encapsulated method that will return the expected value of type this class has been instantiated.
     */
     public Func(Returnable<T> returnable) {
         this(returnable, null);
     }
    
    /**
     * Creates a new instance of Func.
     * @param returnable Encapsulated method that will return the expected value of type this class has been instantiated.
     * @param callback Encapsulated method that will be executed when synchronization has been finished. 
     */
    public Func(Returnable<T> returnable, Invokable callback) {
        _returnable=returnable; _callback=callback;
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
    
    T _result=null;
            
    /**
     * Executes and invokes the initialized Invokable method asynchronously and returns the value from the encapsulated Returnable interface.
     * @return Result from the encapsulated Returnable interface instantiated along with this instance of the class.
     */
    public T invoke() {
        if (_worker!=null) throw new RuntimeException("Thread is already running.");
        
        _worker=new SwingWorker<T, Void>() {
            
            @Override
            protected T doInBackground() throws Exception {
                _result=_returnable.returns();
                return  _result;
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
                    if (_worker!=null) {
                        while (!_worker.isDone() &&
                               !_worker.isCancelled()) _progress.invoke();
                    }
                }
            });
            _progressdelegate.invoke();
        }
      
        Thread _thread =new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (!_worker.isDone() &&
                           !_worker.isCancelled()) Thread.sleep(100);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }         
            }
        });
        
        _thread.start();
       
        
      return _result;
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
