/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.util.ArrayList;
import mime.ItemNotExistException;

/**
 * Mimics VB.Net System.Data.DataTableCollection class.
 * @author seph
 */
public class DataTableCollection extends ArrayList<DataTable> {
    
    private DataSet _owner=null;
    
    /**
     * Creates a new instance of DataTableCollection.
     * @param owner 
     */
    public DataTableCollection(DataSet owner) {
        _owner=owner;
    }
    
    /**
     * Adds a new DataTable into the collection.
     * @return Returns the newly added table, otherwise returns null of table adding failed.
     */
    public DataTable add() {
        DataTable dt=new DataTable(this);
        if (super.add(dt)) return super.get(super.size()-1);
        else return null;
    }
    
    @Override
    public boolean add(DataTable table) {
        boolean _added=super.add(table);
        if (_added) table.setOwner(this);
        return _added;
    }
    
    /**
     * Returns whether a DataTable with the specified table name already exists within the collection or not.
     * @param tablename Table name.
     * @return True if there is an existing table with the specified name, otherwise false if there is nothing.
     */
    public boolean  contains(String tablename) {
        return (boolean) (getTableByName(tablename)!=null);
    }
        
    /**
     * Returns the current owner DataSet for the current table collection.
     * @return 
     */
    public DataSet dataSet() {
        return _owner;
    }
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current collection already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            for (DataTable dt:this) {
                dt.dispose(); dt=null;
            }
            
            finalize();
            
            DataTableCollection _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Returns a DataTable with the specified table name from the collection.
     * @param tablename Table name.
     * @return Returns a table that matches the specified name. May throw ItemNotExistException if there is no matching table with the specified name.
     */
    public DataTable get(String tablename) {
        DataTable dt=getTableByName(tablename);
        if (dt==null) throw new ItemNotExistException("Table : " + tablename + " does not exists.");
        return dt;
    }
    
    private DataTable getTableByName(String tablename) {
        DataTable _table=null;
        
        for (DataTable dt: this) {
            if (dt.getTableName().toLowerCase().equals(tablename.toLowerCase())) {
                _table=dt; break;
            }
        }
        
        return _table;
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
     * Removes a DataTable with the specified table name from the collection.
     * @param tablename Table name.
     */
    public void remove(String tablename) {
       DataTable dt=get(tablename);
       super.remove(dt);
    }
    
}
