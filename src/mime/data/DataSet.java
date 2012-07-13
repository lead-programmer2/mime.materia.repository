/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Mimics VB.Net System.Data.DataSet class.
 * @author seph
 */
public class DataSet {
   
    private DataTableCollection _tables=new DataTableCollection(this);
    
    /**
     * Creates a new instance of DataSet.
     */
    public DataSet() {
        
    }
   
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current class already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            _tables.dispose(); _tables=null;
            finalize();
            DataSet _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    private String _datasetname="";
    
    /**
     * Returns the name associated with the current DataSet object.
     * @return 
     */
    public String getDataSetName() {
        return _datasetname;
    }
    
    /**
     * Sets the name associated with the current DataSet.
     * @param datasetname Assigned name.
     */
    public void setDataSetName(String datasetname) {
        _datasetname=datasetname;
    }
    
    private boolean _isdisposed=false;
    
    /**
     * Returns whether the current class has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Returns the collection of tables the current DataSet contains.
     * @return 
     */
   public DataTableCollection tables() {
       return _tables;
   }
   
   @Override
   public String toString() {
       return _datasetname;
   }
   
}
