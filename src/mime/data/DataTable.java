/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Mimics VB.Net System.Data.DataTable.
 * @author seph
 */
public class DataTable {
    
    private DataTableCollection _owner=null;
    
    /**
     * Creates a new instance of DataTable.
     */
    public DataTable() {
        
    }
    
    /**
     * Creates a new instance of DataTable.
     * @param owner Owner DataSet table collection.
     */
    public DataTable(DataTableCollection owner) {
        _owner=owner;
    }
    
    /**
     * Accepts the updates and changes made with the current table rows.
     */
    public void acceptChanges() {
        Iterator<DataRow> _iterator = rows().iterator();
        
        while (_iterator.hasNext()) {
            DataRow rw=_iterator.next();
            
            if (rw.rowState()==DataRowState.Deleted ||
                rw.rowState()==DataRowState.Detached) _iterator.remove();
            else rw.acceptChanges();
        }
    }
    
    @Override
    public DataTable clone() {
        DataTable dt=new DataTable();
        dt.setTableName(_tablename);
        for (DataColumn col:_columns) {
           DataColumn _column=dt.columns().add(col.getColumnName(), col.getDataType());
           _column.setAllowNull(col.getAllowNull());
           _column.setUnique(col.getUnique());
           _column.setAutoIncrement(col.getAutoIncrement());
           _column.setAutoIncrementStep(col.getAutoIncrementStep());
           _column.setCaption(col.getCaption());
           _column.setDefaultValue(col.getDefaultValue());
           _column.setMaxLength(col.getMaxLength());
           _column.setReadOnly(col.getReadOnly());
        }
        return dt;
    }
    
    private DataColumnCollection _columns=new DataColumnCollection(this);
    
    /**
     * Returns the collection of DataColumn associated with the current table.
     * @return 
     */
    public DataColumnCollection columns() {
        return _columns;
    }
    
    /**
     * Returns the DataColumn at the specified index of the table column collection.
     * @param index Column index.
     * @return 
     */
    public DataColumn columns(int index) {
        return _columns.get(index);
    }
    
    /**
     * Returns a DataColumn with the specified column name from the table column collection.
     * @param columnname Column name
     * @return 
     */
    public DataColumn columns(String columnname) {
        return _columns.get(columnname);
    }
    
    /**
     * Return the owner DataSet for the current table.
     * @return 
     */
    public DataSet dataSet() {
        if (_owner!=null) return _owner.dataSet();
        else return null;
    }
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current collection already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            _columns.dispose();  _columns=null;
            _rows.dispose(); _rows=null;
            finalize(); 
            DataTable _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Returns a list of DataRow with the specified table column value.
     * @param index Column index.
     * @param value Value to search.
     * @return 
     */
    public ArrayList<DataRow> find(int index, Object value) {
        DataColumn dc=_columns.get(index);
        return find(dc, value);
    }
    
    /**
     * Returns a list of DataRow with the specified table column value.
     * @param columnname Column name.
     * @param value Value to search.
     * @return 
     */
    public ArrayList<DataRow> find(String columnname, Object value) {
        DataColumn dc=_columns.get(columnname);
        return find(dc, value);
    }
    
    /**
     * Returns a list of DataRow with the specified table column value.
     * @param column Table column to be searched.
     * @param value Value to search.
     * @return 
     */
    public ArrayList<DataRow> find(DataColumn column, Object value) {
        if (!column.table().equals(this)) throw new DataColumnException("Column is of difference owner table.");
        ArrayList<DataRow> rws= new ArrayList<DataRow>();
        ArrayList<Object> values=column.values();
        int _startindex=values.indexOf(value);
        
        if (_startindex>=0) {
            int _endindex=values.lastIndexOf(value);
            for (int i=_startindex; i<=_endindex; i++) {
                Object _value=values.get(i);
                if (_value.equals(value)) rws.add(rows(i));
            }
        }
        
        return rws;
    }
    
    private boolean _isdisposed=false;
    
    /**
     * Returns whether the current table has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Loads the data stored in the specified ResultSet into the current DataTable.
     * @param resultset Database result set object.
     */
    public void load(ResultSet resultset) {
        ResultSetMetaData _metadata=null;
        Connection connection=null;
        
        try {
            _metadata=resultset.getMetaData();
            connection=resultset.getStatement().getConnection();
        }
        catch (Exception ex) {    
            _metadata=null; connection=null;
            throw new RuntimeException(ex.getMessage());
        }
        
       if (_metadata!=null &&
           connection!=null) {
           String _pkcolumnname="";
           DatabaseMetaData _dbmetadata=null;
           
           try {
               _dbmetadata=connection.getMetaData();
           }
           catch (Exception ex) {
               _dbmetadata=null;
               throw new RuntimeException(ex.getMessage());
           }
           
           if (_dbmetadata!=null) {
               ResultSet _pks=null;
               
               try {
                   _pks=_dbmetadata.getPrimaryKeys(_metadata.getCatalogName(1), _metadata.getSchemaName(1), _metadata.getTableName(1));
               }
               catch (Exception ex) {
                   _pks=null;
               }
               
               if (_pks!=null) {
                   boolean _moved=false;
                   
                   try {
                       _moved=_pks.first();
                   }
                   catch (Exception ex) {
                       _moved=false;
                   }
                   
                   if (_moved) {
                       try {
                          _pkcolumnname=_pks.getString("COLUMN_NAME");
                       }
                       catch (Exception ex) {
                         throw new RuntimeException(ex.getMessage());   
                       }
               
                   }
                   
                   try {
                       if (!_pks.isClosed()) _pks.close();
                   }
                   catch (Exception ex) {
                   }
                   finally {
                       _pks=null;
                   }
               }
           }
           
            try {
                setTableName(_metadata.getTableName(1));
                int _columncount=_metadata.getColumnCount();
                _columns.clear();
                
                for (int i=1; i<=_columncount; i++) {
                   String _columnname=_metadata.getColumnName(i);
                   String _columncaption=_metadata.getColumnLabel(i);
                   Class _datatype=Object.class;
                   int _type=_metadata.getColumnType(i);
                   
                   switch (_type) {
                       case java.sql.Types.VARCHAR:
                       case java.sql.Types.LONGNVARCHAR:
                       case java.sql.Types.LONGVARCHAR:
                       case java.sql.Types.NVARCHAR:
                           _datatype=String.class; break;
                       case java.sql.Types.CHAR:
                       case java.sql.Types.NCHAR:
                           _datatype=char.class; break;
                       case java.sql.Types.BIGINT:
                       case java.sql.Types.INTEGER:
                           _datatype=long.class; break;
                       case java.sql.Types.NUMERIC:
                       case java.sql.Types.SMALLINT:
                       case java.sql.Types.TINYINT:
                           _datatype=int.class; break;
                       case java.sql.Types.BIT:
                           _datatype=boolean.class; break;
                       case java.sql.Types.DECIMAL:
                       case java.sql.Types.FLOAT:
                       case java.sql.Types.REAL:
                           _datatype=float.class; break;
                       case java.sql.Types.DOUBLE:
                           _datatype=double.class; break;
                       case java.sql.Types.DATE:
                       case java.sql.Types.TIME:
                       case java.sql.Types.TIMESTAMP:
                           _datatype=java.util.Date.class; break;
                       case java.sql.Types.BLOB: 
                       case java.sql.Types.BINARY:
                       case java.sql.Types.LONGVARBINARY:
                       case java.sql.Types.VARBINARY:
                           _datatype=byte[].class; break;
                       default: break;
                   }
                   
                   DataColumn _column=_columns.add(_columnname, _datatype);
                   if (!_columncaption.trim().equals("")) _column.setCaption(_columncaption);
                   if (_pkcolumnname.trim().equals(_columnname)) _column.setUnique(true);
                   _column.setAutoIncrement(_metadata.isAutoIncrement(i));
                   if (_datatype.equals(String.class)) _column.setMaxLength(_metadata.getPrecision(i));
                   if (_metadata.isNullable(i)==1) _column.setAllowNull(true);
                   _column.setReadOnly(_metadata.isReadOnly(i));
                }
                
                while (resultset.next()) {    
                    Object values[]=new Object[_columncount];    
                    for (DataColumn col:_columns) {    
                        if (!col.getDataType().getName().equals(byte[].class.getName())) values[col.ordinal()]=resultset.getString(col.getColumnName());    
                        else  values[col.ordinal()]=resultset.getBytes(col.getColumnName()); 
                    }    
                   DataRow rw= _rows.add(values); rw.acceptChanges();
                }
                
            }
            catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
       }
       else throw new RuntimeException("Can't retrieve table definition from the resultset.");
        
    }
    
    /**
     * Merges the specified DataTable values into the current table.
     * @param table DataTable object to merge.
     */
    public void merge(DataTable table) {
       if (table.columns().size()!=_columns.size()) throw new DataTableException("Merging table doesn't match column count to current table."); 
       DataColumn _pkcolumn=getPrimaryKey();
       if (_pkcolumn==null) throw new DataTableException("No reference key column found for the current table.");
       if (!table.columns().contains(_pkcolumn.getColumnName())) throw new DataTableException("No reference key column found in the merging table.");
       
       Iterator<DataRow> _iterator=table.rows().iterator();
       int _columncount=_columns.size();
       
       while (_iterator.hasNext()) {
           DataRow rw=_iterator.next();
           
           if (rw.rowState()!=DataRowState.Deleted &&
               rw.rowState()!=DataRowState.Detached) {
               Object _pkvalue=rw.getItem(_pkcolumn.getColumnName());
               ArrayList<DataRow> rws=find(_pkcolumn, _pkvalue);
               if (rws.size()<=0) _rows.add(rw.toArray());
               else {
                   DataRow dr=rws.get(0);
                   for (int i=0; i<=_columncount-1; i++) {
                       if (!_pkcolumn.getColumnName().toLowerCase().equals(table.columns(i).getColumnName())) dr.setItem(i, rw.getItem(i));
                   }
               }
           }
       }
    }
    
    /**
     * Sets the current owning table collection for the current table.
     * @param owner DataSet table collection
     */
    public void setOwner(DataTableCollection owner) {
       if (owner==null) _owner=owner;
       else {
           if (owner.contains(this)) _owner=owner;
       }
    }
    
    /**
     * Returns the primary key field for the current table. 
     * @return Returns a DataColumn that is set as the primary key field, otherwise returns null if there is no primary key field specified.
     */
    public DataColumn getPrimaryKey() {
        DataColumn pk=null;
        
        for (DataColumn dc:_columns) {
            if (dc.getUnique()) {
                pk=dc; break;
            }
        }
           
        return pk;        
    }
    
    /**
     * Sets a DataColumn at the specified index of the table column collection as the primary key field of the table.
     * @param index Column index
     */
    public void setPrimaryKey(int index) {
        DataColumn dc=columns().get(index);
        setPrimaryKey(dc);
    }
    
    /**
     * Sets a DataColumn of the table with the specified column name as the primary key field of the table.
     * @param columnname Column name
     */
    public void setPrimaryKey(String columnname) {
        DataColumn dc=columns().get(columnname);
        setPrimaryKey(dc);
    }
    
   /**
    * Sets the specified DataColumn as the primary key field of the table.
    * @param column Table column
    */
    public void setPrimaryKey(DataColumn column) {
        if (!column.table().equals(this)) throw new DataColumnConstraintException("Column is not of the current table.");
        column.setUnique(true);
    }
    
    /**
     * Rejects the updates and changes made to all of the table rows.
     */
    public void rejectChanges() {
        for (DataRow rw:rows()) {
           if (rw.rowState()!=DataRowState.Unchanged) rw.rejectChanges();
        }
    }
    
    private DataRowCollection _rows=new DataRowCollection(this);
    
    /**
     * Returns the collection of DataRow the current table contains.
     * @return 
     */
    public DataRowCollection rows() {
        return _rows;
    }
    
    /**
     * Returns the table row at the specified index of the current table row collection.
     * @param index Row index.
     * @return 
     */
    public DataRow rows(int index) {
        return _rows.get(index);
    }
    
    private String _tablename="";
    
    /**
     * Returns the table name associated with the current DataTable object.
     * @return 
     */
    public String getTableName() {
        return _tablename;
    }
    
    /**
     * Sets the table name associated with the current DataTable object. May throw DataTableException when the current table is a member of a DataSet and
     * assigned name already exists within the owner DataSet table collection.
     * @param tablename Table name
     */
    public void setTableName(String tablename) {
        if (!tablename.trim().equals("")) {  
            if (_owner!=null) {
                for (DataTable dt:_owner) {
                    if (dt.getTableName().toLowerCase().equals(tablename) &&
                        !dt.equals(this)) throw new DataTableException("Table : " + tablename + " already exists.");
                }
            }
       
        }
        
        _tablename=tablename;
    }
    
    @Override
    public String toString() {
        return _tablename;
    }
    
}
