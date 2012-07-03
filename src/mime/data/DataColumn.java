/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.util.ArrayList;
import mime.Converter;
import mime.InvalidCastException;

/**
 * Mimics VB.Net System.Data.DataColumn class.
 * @author seph
 */
public class DataColumn {
    
    private DataColumnCollection _owner=null;
    
    /**
     * Creates a new instance of DataColumn.
     * @param owner Owner DataColumnCollection for the current DataColumn.
     */
    public DataColumn(DataColumnCollection owner) {
       _owner=owner; _ordinal=owner.size();
    }
    
    /**
     * Creates a new instance of DataColumn.
     * @param owner Owner DataColumnCollection for the current DataColumn.
     * @param columnname Name associated with the current DataColumn.
     */
    public DataColumn(DataColumnCollection owner, String columnname) {
        if (owner.table().rows().size()>0) throw new DataColumnException("Can't add a new column. Table already have loaded data.");
        _owner=owner; setName(columnname); _ordinal=owner.size();
    }
    
    /**
     * Creates a new instance of DataColumn.
     * @param owner Owner DataColumnCollection for the current DataColumn.
     * @param columnname Name associated with the current DataColumn.
     * @param datatype Column supported data type.
     */
    public DataColumn(DataColumnCollection owner, String columnname, Class datatype) {
        if (owner.table().rows().size()>0) throw new DataColumnException("Can't add a new column. Table already have loaded data.");
        _owner=owner; setName(columnname); 
        _datatype=datatype; _ordinal=owner.size();
    }
     
    private boolean  _allownull=true;
    
    /**
     * Returns whether the DataColumn would allow null values or not. Default value is True.
     * @return True if null values are allowed, otherwise false.
     */
    public boolean getAllowNull() {
        return _allownull;
    }
    
    /**
     * Sets whether the DataColumn should allow null values or not.
     * @param allownull True if null values are allowed, otherwise false.
     */
    public void setAllowNull(boolean allownull) {
        _allownull=allownull;
    }
    
    private boolean _autoincrement=false;
    
    /**
     * Returns whether the DataColumn is a auto-incremented column or not.
     * @return True if DataColumn is marked as an auto-incremented column, otherwise false.
     */
    public boolean getAutoIncrement() {
        return _autoincrement;
    }
    
    /**
     * Sets whether the DataColumn is an auto-incremented column or not.
     * Settings the DataColumn as an auto-incremented field will set the current DataColumn as primary key
     * column. Throws DataColumnConstraintException if primary key column activation for this column has failed.
     * @param autoincrement  True if DataColumn is marked as an auto-incremented column, otherwise false.
     */
    public void setAutoIncrement(boolean autoincrement) {
        if (autoincrement) {
            if (!typeSupportsAutoIncrement(_datatype)) throw new DataColumnConstraintException("Column type does not support auto-incrementation.");
            setUnique(autoincrement);
        }
        
      _autoincrement=autoincrement;
    }
    
    private long _autoincrementseed=0;
    
    /**
     * Returns the starting value when the current column is set as auto-incremented field.
     * @return 
     */
    public long getAutoIncrementSeed() {
        return _autoincrementseed;
    }
    
    /**
     * Sets the starting value referenced when the current column is set as auto-incremented field.
     * @param autoincrementseed 
     */
    public void setAutoIncrementSeed(long autoincrementseed) {
        _autoincrementseed=autoincrementseed;
    }
    
    private int _autoincrementstep=1;
    
    /**
     * Returns the incrementation value of the current column when the current column is set as auto-incremented field.
     * @return 
     */
    public int getAutoIncrementStep() {
        return _autoincrementstep;
    }
    
    /**
     * Sets the incrementation value of the current column when the current column is set as auto-incremented field.
     * @param autoincrementstep 
     */
    public void setAutoIncrementStep(int autoincrementstep) {
        _autoincrementstep=autoincrementstep;
    }
    
    private String _caption="";
    
    /**
     * Returns the alias assigned to the current column.
     * @return 
     */
    public String getCaption() {
        return _caption;
    }
    
    /**
     * Sets the alias assigned to the current column. 
     * @param caption 
     */
    public void setCaption(String caption) {
        _caption=caption;
    }
    
    private String _columnname="";
    
    /**
     * Returns the name associated with this DataColumn.
     * @return 
     */
    public String getColumnName() {
        return _columnname;
    }
    
    private void setName(String columnname) {
        DataColumn _col=null;
        
        try {
            _col= _owner.get(columnname);
        }
        catch (Exception ex) {
            _col=null;
        }
        
        if (_col!=null) {
            if (!_col.equals(this)) throw new DataColumnException("Column : " + columnname + " already exists.");
        }
        
        _columnname=columnname;
        if (_caption.isEmpty()) _caption=_columnname;
    }
    
    /**
     * Sets the name associated with the current DataColumn. May throw a DataColumnException if assigned name
     * already exists within its parent table.
     * @param columnname Name to associate the DataColumn.
     */
    public void setColumnName(String columnname) {
        setName(columnname);
    }
    
    private Class _datatype=Object.class;
    
    /**
     * Returns the data type the current DataColum only supports and accepts.
     * @return 
     */
    public Class getDataType() {
        return _datatype;
    }
    
    /**
     * Sets the supported data type for the current DataColumn.
     * @param datatype 
     */
    public void setDataType(Class datatype) {
        if (_values.size() > 0) throw new DataColumnException("Can't update column data type. Table already have data.");
        
        if (_autoincrement) _autoincrement=typeSupportsAutoIncrement(datatype);
        
        _datatype=datatype;
    }
    
    private boolean typeSupportsAutoIncrement(Class datatype) {
        return (boolean) (datatype.equals(int.class) ||
                          datatype.equals(long.class) ||
                          datatype.equals(Integer.class) ||
                          datatype.equals(Long.class));
    }
    
    private Object _defaultvalue=null;
    
    /**
     * Returns the default new row value assigned to the current DataColumn.
     * @return 
     */
    public Object getDefaultValue() {
        return _defaultvalue;
    }
    
    /**
     * Sets the default new row value for the current DataColumn.
     * @param defaultvalue Any object of the current supported type of the current DataColumn.
     */
    public void setDefaultValue(Object defaultvalue) {
        if (defaultvalue!=null) {
            Object _value=Converter.convert(_datatype, defaultvalue);
            if (Converter.isNull(_value)) throw new InvalidCastException("Default value : " + defaultvalue.toString() + " can't be converted to type : " + _datatype.getName() + ".");
        }
        
        _defaultvalue=defaultvalue;
    }
    
    private long _lastinsertid=0;
    
    /**
     * Returns the last auto-incrementation counter generated id for the current column.
     * @return 
     */
    public long getLastInsertId() {
        return _lastinsertid;
    }
    
    /**
     * Sets the last auto-incrementation counter for the current column.
     * @param lastinsertid Current counter.
     */
    public void setLastInsertId(long lastinsertid) {
        _lastinsertid=lastinsertid;
    }
    
    
    private int _maxlength=-1;
    
    /**
     * Returns the maximum character length allowed for the current DataColumn.
     * @return 
     */
    public int getMaxLength() {
        return _maxlength;
    }
    
    /**
     * Sets the maximum character length allowed for the current DataColumn.
     * @param maxlength 
     */
    public void setMaxLength(int maxlength) {
       if (table().rows().size() > 0) throw new DataColumnException("Can't update column maxlength. Table already have data.");
       
        if (maxlength>=0) {
           if (!_datatype.getName().equals(String.class.getName())) throw new DataColumnException("Maxlength assignment not applicable to the current DataColumn.");
       } 
       
        _maxlength=maxlength;
    }
        
   /**
    * Sets the owner of the DataColumn explicitly.
    * @param owner Owning collection for the current DataColumn.
    */
    public void setOwner(DataColumnCollection owner) {
        if (owner==null) throw new DataColumnException("Column must have a parent collection.");
        else {
            if (owner.contains(this)) _owner=owner;
            else throw new DataColumnException("Column already have a different owner.");
        }
    }
    
    private int _ordinal=-1;
   
    /**
     * Returns the index position of the specified DataColumn into the owner table column collection.
     * @return 
     */
    public int ordinal() {
        return _ordinal;
    }
    
    private boolean _readonly=false;
    
    /**
     * Returns whether the DataColumn is marked as read-only and therefore contained values in the current column can be updated or not. 
     * @return 
     */
    public boolean  getReadOnly(){
        return _readonly;
    }
    
    /**
     * Sets whether the DataColumn is marked as read-only and values contained in the current column can be updated or not.
     * @param readonly 
     */
    public void setReadOnly(boolean readonly) {
        _readonly=readonly;
    }
    
    /**
     * Returns the current owner DataTable object for the current DataColumn.
     * @return 
     */
    public DataTable table() {
        return _owner.table();
    }
    
    private final ArrayList<Object> _values =new ArrayList<Object>();
    
    /**
     * Returns the lists of values this column contains for each table rows.
     * @return 
     */
    public ArrayList<Object> values() {
        return _values;
    }
    
    private boolean _unique=false;
    
    /**
     * Returns whether the current DataColumn is marked as unique field. If current column is unique, column is also considered as 
     * the primary key field of the owning table. 
     * @return 
     */
    public boolean getUnique() {
        return _unique;
    }
 
    public void setUnique(boolean unique) {
        if (unique) setAllowNull(false);
        _unique=unique;
    }

    @Override
    public String toString() {
        return _columnname;
    }
}
