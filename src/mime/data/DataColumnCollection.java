/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.util.ArrayList;
import mime.CollectionExistException;
import mime.ItemNotExistException;

/**
 * Mimics VB.Net System.Data.DataColumnCollection class.
 * @author seph
 */
public class DataColumnCollection extends ArrayList<DataColumn> {
    
    
    /**
     * Adds a new DataColumn into the collection.
     * @return New DataColumn added into the collection, otherwise null if adding failed.
     */
    public DataColumn add() {
        DataColumn dc=new DataColumn(this);
        if (super.add(dc)) return super.get(super.size()-1);
        else  return null;
    }
    
    /**
     * Adds a new DataColumn into the collection.
     * @param columnname Name to associate the new DataColumn.
     * @return New DataColumn added into the collection, otherwise null if adding failed.
     */
    public DataColumn add(String columnname) {
        return add(columnname, Object.class);
    }
  
    /**
     * Adds a new DataColumn into the collection.
     * @param columnname Name to associate the new DataColumn.
     * @param datatype Supported data type.
     * @return New DataColumn added into the collection, otherwise null if adding failed.
     */
    public DataColumn add(String columnname, Class datatype) {
        DataColumn dc=new DataColumn(this, columnname, datatype);
        if (super.add(dc)) return super.get(super.size()-1);
        else  return null;
    }
    
    @Override
    public boolean add(DataColumn column) {
        boolean _added=super.add(column);
        if (_added) column.setOwner(this);
        return _added;
    }
    
    /**
     * Returns whether a DataColumn with the specified column name exists within the collection or not.
     * @param columnname Name associated withe the DataColumn.
     * @return True if there is an existing column, otherwise false if there is none.
     */
    public boolean  contains(String columnname) {
        return (boolean)(get(columnname)!=null); 
    }

    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current collection already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            for (DataColumn col:this) {
                col=null;
            }
            super.finalize();
            
            DataColumnCollection _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Returns a DataColumn with the specified name from the collection otherwise returns null if nothing has been found. 
     * May throw a DataColumnException when there is no column with the specified name can be accessed.
     * @param columnname Name associated withe the DataColumn.
     * @return 
     */
    public DataColumn get(String columnname) {
        return getColumnByName(columnname);
    }
    
    private DataColumn getColumnByName(String columnname) {
        DataColumn dc=null;
        
        for (DataColumn col:this) {
            if (col.getColumnName().toLowerCase().equals(columnname.toLowerCase())) {
               dc=col; break; 
            }
        }
        
        if (dc==null) throw new ItemNotExistException("Column : " + columnname + " does not exists.");
        
        return dc;
    }
    
    private boolean _isdisposed=false;
    
    /**
     * Returns whether the current collection has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Creates a new instance of DataColumnCollection. May throw a CollectionExistException if instantiated to a
     * table that already have a DataColumnCollection attached into it.
     * @param owner Hosted DataTable object.
     */
    public DataColumnCollection(DataTable owner) {
       if (owner.columns()!=null) throw new CollectionExistException("Table already have DataColumnCollection attached into it.");
        _table=owner;
    }
    
    /**
     * Removes a DataColumn with the specified column name from the collection.Returns True if DataColumn removal
     * succeeds or if there is an existing column that has been removed, otherwise false.
     * @param columnname Name associated with the DataColumn.
     * @return True if a DataColumn has been removed, otherwise false.
     */
    public boolean remove(String columnname) {
        DataColumn dc=get(columnname);
        if (dc!=null) return remove(dc);
        else return false;
    }
    
    private DataTable _table=null;
    
    /**
     * Returns the owner DataTable object for the current collection.
     * @return 
     */
    public DataTable table() {
        return _table;
    }
    
}
