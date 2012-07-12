/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.util.ArrayList;
import mime.Converter;

/**
 * Mimics VB.Net System.Data.DataRow class.
 * @author seph
 */
public class DataRow {
    
    private DataRowCollection _owner=null;
    private final ArrayList<Object> _previousvalues=new ArrayList<Object>();
    
    /**
     * Creates a new instance of DataRow. 
     * @param owner Owner table row collection.
     */
    public DataRow(DataRowCollection owner) {
        _owner=owner; 
        DataTable _table=owner.table();
        Object values[] =new Object[_table.columns().size()];
        addRow(values);
    }
    
    /**
     * Creates a new instance of DataRow. 
     * @param owner Owner table row collection.
     * @param values Array of values to be added.
     */
    public DataRow(DataRowCollection owner, Object values[]) {
      _owner=owner;  addRow(values);
    }
    
    /**
     * Accepts the changes made to the values of the current row. If the current row is deleted, changed will take effect after calling the owner table acceptChanges method.
     */
    public void acceptChanges() {
        if (_rowState!=DataRowState.Deleted &&
            _rowState!=DataRowState.Detached) {
             _previousvalues.clear(); _rowState= DataRowState.Unchanged;
        }
    }
    
    private void addRow(Object values[]) {
        if (values.length != table().columns().size()) throw new DataRowException("Value count doesn't match column count.");
        
        int _columncount=table().columns().size();
        
        for (int i=0; i<= _columncount-1; i++) {
            DataColumn _column=table().columns(i);
            Object _curvalue=values[i];
            Class _datatype=_column.getDataType();
            Object _value=Converter.convert(_datatype, _curvalue);
            if (Converter.isNull(_value) &&
                !Converter.isNull(_curvalue)) throw new DataException("Value : " + _curvalue + " can't be converted to type : " + _datatype.getSimpleName() + ".");
            
            if (_column.getAutoIncrement()) {
                if (Converter.isNull(_value)) {
                    long _seed=_column.getLastInsertId();
                    if (_seed < _column.getAutoIncrementSeed()) _seed=_column.getAutoIncrementSeed();
                    long _counter=(_seed<=0? 0:_seed) + _column.getAutoIncrementStep();
                    _value=_counter; 
                    _column.setLastInsertId(_counter);
                }
            }
            
            if (!_column.getAllowNull() &&
                Converter.isNull(_value)) throw new DataException("Column : " + _column.getColumnName() + " does not allow null values.");
            
            if (Converter.isNull(_value)) _value=_column.getDefaultValue();
            
            if (_column.getUnique()) {
                if (_column.values().contains(_value)) throw new DuplicateKeyException("Duplicate key field value : " + _value + ".");
            }
            
            _column.values().add(_value);
        }
        
        _index=_owner.size();
        _previousvalues.clear();
        _rowState= DataRowState.Added;
    }
    
    /**
     * Deletes the current row temporarily. To have the row to be removed permanently, call the owner table acceptChanges method. 
     */
    public void delete() {
        if (_rowState==DataRowState.Deleted ||
            _rowState==DataRowState.Detached) throw new DataRowException("Can't access deleted row.");
         
        _previousvalues.clear();
        
        for (DataColumn col:table().columns()) {
            _previousvalues.add(col.values().get(_index));
        }
        
        _rowState= DataRowState.Deleted;
    }
    
    /**
     * Returns the value at the specified column index of the current row.
     * @param index Column index.
     * @return Returns the current value of current version of the row.
     */
    public Object getItem(int index) {
        return getItem(index, DataRowVersion.Current);
    }
    
    /**
     * Returns the value at the specified column index of the current row.
     * @param index Column index.
     * @param version Row version.
     * @return Returns the current values of the row if version is DataRowVersion.Current otherwise returns version of the row during the row was added or 
     * the last call of acceptChanges method. May throw DataRowException if there is no original version available if requested row version is 
     * DataRowVersion.Original. May also throw DataRowException if current row is deleted and requested row version is DataRowVersion.Current.
     */
    public Object getItem(int index, DataRowVersion version) {
        DataColumn dc=table().columns(index);
        return getItem(dc, version);
    }
        
    /**
     * Returns the value of a column with the specified column name at the current row.
     * @param columnname Column name.
     * @return Returns the current value of current version of the row.
     */
    public Object getItem(String columnname) {
        return getItem(columnname, DataRowVersion.Current);
    }
    
    /**
     * Returns the value of a column with the specified column name at the current row.
     * @param columnname Column name.
     * @param version Row version.
     * @return Returns the current values of the row if version is DataRowVersion.Current otherwise returns version of the row during the row was added or 
     * the last call of acceptChanges method. May throw DataRowException if there is no original version available if requested row version is 
     * DataRowVersion.Original. May also throw DataRowException if current row is deleted and requested row version is DataRowVersion.Current.
     */
    public Object getItem(String columnname, DataRowVersion version) {
        DataColumn dc=table().columns(columnname);
        return getItem(dc, version);  
    }
    /**
     * Returns the value at the specified column at the current row.
     * @param column Table column.
     * @return Returns the current value of current version of the row.
     */
    public Object getItem(DataColumn column) {
        return getItem(column, DataRowVersion.Current);
    }
    
    /**
     * Returns the value at the specified column at the current row.
     * @param column Table column.
     * @param version Row version.
     * @return Returns the current values of the row if version is DataRowVersion.Current otherwise returns version of the row during the row was added or 
     * the last call of acceptChanges method. May throw DataRowException if there is no original version available if requested row version is 
     * DataRowVersion.Original. May also throw DataRowException if current row is deleted and requested row version is DataRowVersion.Current.
     */
    public Object getItem(DataColumn column, DataRowVersion version) {
       if (!column.table().equals(table())) throw new DataColumnException("Column is not of the current table.");
       
       if (version==DataRowVersion.Current) {
           if (_rowState==DataRowState.Deleted ||
               _rowState==DataRowState.Detached) throw new DataRowException("Can't access deleted row.");
           
           return column.values().get(_index);
       }
       else {
           if (_previousvalues.size() <=0) throw new DataRowException("No original version available.");
           return _previousvalues.get(column.ordinal());
       }
    }
    
    /**
     * Sets the value at the specified column index of the current row.
     * @param index Column index
     * @param value Value to assign.
     */
    public void setItem(int index, Object value) {
        DataColumn dc=table().columns(index);
        setItem(dc, value);
    }
    
    /**
     * Sets the value at the specified column with the specified column name at the current row.
     * @param columnname Column name.
     * @param value Value to assign.
     */
    public void setItem(String columnname, Object value) {
        DataColumn dc=table().columns(columnname);
        setItem(dc, value);
    }
       
    /**
     * Sets the value at the specified column of the current row.
     * @param column Table column.
     * @param value Value to assign.
     */
    public void setItem(DataColumn column, Object value) {
        if (!column.table().equals(table())) throw new DataColumnException("Column is not of the current table.");
        
        if (_rowState==DataRowState.Deleted ||
            _rowState==DataRowState.Detached) throw new DataRowException("Can't access deleted row.");
        
        Object _curvalue=value;    
        Class _datatype=column.getDataType();    
        Object _value=Converter.convert(_datatype, _curvalue);    
        if (Converter.isNull(_value) &&
            !Converter.isNull(_curvalue)) throw new DataException("Value : " + _curvalue + " can't be converted to type : " + _datatype.getSimpleName() + ".");
                     
        if (!column.getAllowNull() &&
            Converter.isNull(_value)) throw new DataException("Column : " + column.getColumnName() + " does not allow null values.");
                
        if (column.getUnique()) { 
            if (column.values().contains(_value)) {
                if (column.values().indexOf(_value)!=_index) throw new DuplicateKeyException("Duplicate key field value : " + _value + ".");    
            }
        }
       
        Object _previousvalue=column.values().get(_index);
        
        if (!_previousvalue.equals(_value)) {    
            if (_rowState==DataRowState.Added ||
                _rowState==DataRowState.Unchanged) {
                _previousvalues.clear();
                for (DataColumn col:table().columns()) {
                    _previousvalues.add(col.values().get(_index));
                }
            }
       
            column.values().set(_index, _value);
            _rowState= DataRowState.Modified;
        }
    }
    
    private int _index=-1;
    
    /**
     * Returns the current index position of the current row within the table row collection.
     * @return 
     */
    public int index() {
        return _index;
    }
       
    /**
     * Rejects the changes and updates made to current row. May throw a DataRowException when there is no original version of the
     * current row.
     */
    public void rejectChanges() {
        if (_previousvalues.size() <=0) throw new DataRowException("No original version available.");
        
        for (DataColumn col:table().columns()) {
            col.values().set(_index, _previousvalues.get(col.ordinal()));
        }
        
        _previousvalues.clear();
        if (_rowState!=DataRowState.Added) _rowState=DataRowState.Unchanged;
    }
    
    private DataRowState _rowState=DataRowState.Unchanged;
    
    /**
     * Returns the current DataRow state.
     * @return 
     */
    public DataRowState rowState()  {
        return _rowState;
    }
      
    /**
     * Sets the owner collection of the row explicitly.
     * @param owner 
     */
    public void setOwner(DataRowCollection owner) {
        if (owner==null) throw new DataRowException("Row must have a owner collection.");
        else {
            if (owner.contains(this)) _owner=owner;
            else throw new DataRowException("Row already have a different owner.");
        }
    }
    
    /**
     * Returns the owner table for the current DataRow.
     * @return 
     */
    public DataTable table() {
        return _owner.table();
    }
    
    /**
     * Returns the array representation of the current table row.
     * @return Array of Object for each row columns.
     */
    public Object[] toArray() {
        int _columns=table().columns().size();
        Object values[] =new Object[_columns];
        for (int i=0; i<=_columns-1; i++) values[i]=getItem(i);
        return values;
    }
    
}
