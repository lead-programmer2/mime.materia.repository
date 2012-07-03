/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.util.ArrayList;
import mime.CollectionExistException;

/**
 * Mimics VB.Net System.Data.DataRowCollection class.
 * @author seph
 */
public class DataRowCollection extends ArrayList<DataRow> {

    private DataTable _owner=null;
    
    /**
     * Creates a new instance of DataRowCollection.
     * @param table Owner DataTable object.
     */
    public DataRowCollection(DataTable table) {
        if (table.rows()!=null) throw new CollectionExistException("Row collection is already present at the specified table.");
        _owner=table;
    }
    
    /**
     * Adds a new table row into the current collection.
     * @return Newly added table row, otherwise null if row adding failed. 
     */
    public DataRow add() {
        DataRow rw=new DataRow(this);
        if (super.add(rw)) return super.get(super.size()-1);
        else return null;
    }
    
    /**
     * Adds a new table row into the current collection.
     * @param values Values assigned to each of the columns of the additional row.
     * @return Newly added table row, otherwise null if row adding failed. 
     */
    public DataRow add(Object values[]) {
        DataRow rw=new DataRow(this, values);
        if (super.add(rw)) return super.get(super.size()-1);
        else return null;
    }
    
    @Override
    public boolean add(DataRow row) {
        boolean _added=super.add(row);
        if (_added) row.setOwner(this);
        return _added;
    }
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current collection already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            super.finalize();
            DataRowCollection _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    private boolean _isdisposed=false;
    
    /**
     * Returns whether the current collection has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean  isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Returns the owning DataTable object for the current row collection.
     * @return 
     */
    public DataTable table() {
        return _owner;
    }
    
}
