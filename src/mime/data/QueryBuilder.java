/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mime.Converter;

/**
 * SQL statement builder for DataTable object.
 * @author seph
 */
public class QueryBuilder {
    
    private DataTable _table=null;
    
    /**
     * Creates a new instance of QueryBuilder
     * @param table DataTable object to refer the SQL statement to be generated.
     */
    public QueryBuilder(DataTable sourcetable) {
        _table=sourcetable;
    }
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current collection already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            finalize(); 
            QueryBuilder _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Generates SQL statement from the initialized DataTable object
     * @return SQL statements generated based on the initialized DataTable object.
     */
    public String generate() {
        StringBuilder _builder=new StringBuilder();
       
        if (_table!=null) {
           if (_table.rows().size() > 0) {
               String _tablename=_table.getTableName();
               if (_tablename.equals("")) _tablename="table";
              
               String _updatecolumns="";
               String _insertcolumns="";
               String _pkcolumn="";
               
               DataColumn _pkcol=_table.getPrimaryKey();
               if (_pkcol!=null) _pkcolumn=_pkcol.getColumnName();
               else throw new QueryBuilderException("Table does not have a reference primary key column.");
               
               for (DataColumn col:_table.columns()) {
                   if (!col.getAutoIncrement()) {
                       _insertcolumns += (_insertcolumns.equals("")? "":", ") + "`" + col.getColumnName() + "`";
                       _updatecolumns += (_updatecolumns.equals("")? "":", ") + "`" + col.getColumnName() + "` = @" + col.getColumnName();
                   }
               }
               
               for (DataRow rw:_table.rows()) {
                   String _sqlline="";
                    
                   String _pkvalue="";
                   Object _pkobject=rw.getItem(_pkcolumn);
                            
                   if (_pkobject==null) _pkvalue="NULL";        
                   else _pkvalue="'" + Converter.toSqlValidString(_pkobject.toString()) + "'";
                                              
                   switch (rw.rowState()) {
                       case Added:
                           _sqlline = "INSERT INTO `" + _tablename + "` \n" +
                                      "(" + _insertcolumns + ")\n" + 
                                      "VALUES" +
                                      "(";
                           String _insertentries="";
                           
                           for (DataColumn col:_table.columns()) {
                               Object _value=rw.getItem(_tablename);
                               if (_value==null) _insertentries += (_insertentries.equals("")? "":", ") + "NULL";
                               else {
                                   String _type=col.getDataType().getName();
                                   
                                   if (_type.equals(String.class.getName())) _insertentries += (_insertentries.equals("")? "":", ") + "'" + Converter.toSqlValidString(_value.toString()) + "'";
                                   else if (_type.equals(java.util.Date.class.getName()) ||
                                            _type.equals(java.sql.Date.class.getName())) _insertentries += (_insertentries.equals("")? "":", ") + "'" + Converter.toSqlValidString(Converter.toDate(_value), true) + "'";
                                   else if (_type.equals(float.class.getName()) ||
                                            _type.equals(double.class.getName()) ||
                                            _type.equals(Double.class.getName()) ||
                                            _type.equals(Float.class.getName())) _insertentries += (_insertentries.equals("")? "":", ") + Converter.toSqlValidString(Converter.toDouble(_value), 4);
                                   else if (_type.equals(byte[].class.getName())) {
                                       String _hex="";
                                       try {
                                           _hex=Converter.toHexadecimalString((byte[]) _value);
                                       }
                                       catch (Exception ex) {
                                           _hex="";
                                           ex.printStackTrace();
                                       }
                                       
                                       if (_hex.equals("")) _insertentries += (_insertentries.equals("")? "":", ") + "NULL";
                                       else _insertentries += (_insertentries.equals("")? "":", ") + "x'" + Converter.toSqlValidString(_hex) + "'";
                                   }
                                   else if (_type.equals(boolean.class.getName()) ||
                                            _type.equals(Boolean.class.getName())) _insertentries += (_insertentries.equals("")? "":", ") + "'" + (Converter.toBoolean(_value)? "1":"0") + "'";
                                   else _insertentries += (_insertentries.equals("")? "":", ") + _value.toString(); 
                               }
                           }
                           
                           _sqlline += _insertentries + ");"; break;
                           
                       case Modified:
                           _sqlline = "UPDATE `" + _tablename + "` SET\n" +
                                      _updatecolumns;
                           
                           for (DataColumn col:_table.columns()) { 
                               Object _value=rw.getItem(_tablename); 
                               String _finalizedvalue="";
                               
                               if (_value==null) _finalizedvalue = "NULL";
                               else {
                                   String _type=col.getDataType().getName();
                                   
                                   if (_type.equals(String.class.getName())) _finalizedvalue="'" + Converter.toSqlValidString(_value.toString()) + "'";
                                   else if (_type.equals(java.util.Date.class.getName()) ||
                                            _type.equals(java.sql.Date.class.getName())) _finalizedvalue="'" + Converter.toSqlValidString(Converter.toDate(_value), true) + "'";
                                   else if (_type.equals(float.class.getName()) ||
                                            _type.equals(double.class.getName()) ||
                                            _type.equals(Double.class.getName()) ||
                                            _type.equals(Float.class.getName())) _finalizedvalue=Converter.toSqlValidString(Converter.toDouble(_value), 4);
                                   else if (_type.equals(byte[].class.getName())) {
                                       String _hex="";   
                                       try {
                                           _hex=Converter.toHexadecimalString((byte[]) _value);
                                       }
                                       catch (Exception ex) {
                                           _hex="";
                                           ex.printStackTrace();
                                       }
                                       
                                       if (_hex.equals("")) _finalizedvalue="NULL";
                                       else _finalizedvalue = "x'" + Converter.toSqlValidString(_hex) + "'";
                                   }
                                   else if (_type.equals(boolean.class.getName()) ||
                                            _type.equals(Boolean.class.getName())) _finalizedvalue="'" + (Converter.toBoolean(_value)? "1":"0") + "'";
                                   else _finalizedvalue = "'" + _value.toString() + "'";
                               }
                               
                               Pattern _pattern=Pattern.compile("@" + col.getColumnName());
                               Matcher _matcher=_pattern.matcher(_sqlline);
                               if (_matcher.find()) _matcher.replaceFirst(_finalizedvalue);
                               _matcher=null; _pattern=null; System.gc();
                           }
                           
                           _sqlline += "\nWHERE (`" + _pkcolumn + "` = " + _pkvalue + ");"; break;
                           
                       case Deleted:
                       case Detached:
                            _sqlline="DELETE FROM `" + _tablename + "` WHERE (`" + _pkcolumn + "` = " + _pkvalue + ");"; break;
                           
                       default: break;
                   }
                   
                   if (!_sqlline.equals("")) _builder.append(_sqlline);
               }
           }
        }
        else throw new QueryBuilderException("No table is initialized.");
        
        return _builder.toString();
    }
    
    private boolean _isdisposed=false;
    
    /**
     * Returns whether the current object has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Gets the initialized source table to where the SQL statement should be referred into.
     * @return 
     */
    public DataTable table() {
        return _table;
    }
    
    
}
