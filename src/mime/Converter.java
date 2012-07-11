/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.text.SimpleDateFormat;

/**
 * Primitive data-typed value converter.
 * @author seph
 */
public class Converter {
    
    /**
     * Converts the specified value into a data of type specified.
     * @param datatype Output data type 
     * @param value Value to convert
     * @return Returns a data of type specified, otherwise returns null if the specified value can't be converted to the specified type.
     */
    public static Object convert(Class datatype, Object value) {
        Object _value=null;
        
        if (!isNull(datatype) &&
            !isNull(value)) {
          String _datatype=datatype.getName();
          
          try {
                  
              if (_datatype.equals(int.class.getName()) ||
                  _datatype.equals(Integer.class.getName())) _value=toInt(value);
              else if (_datatype.equals(long.class.getName()) ||
                       _datatype.equals(Long.class.getName())) _value=toLong(value);
              else if (_datatype.equals(short.class.getName()) ||
                       _datatype.equals(Short.class.getName())) _value=toShort(value);
              else if (_datatype.equals(float.class.getName()) ||
                       _datatype.equals(Float.class.getName())) _value=toFloat(value);
              else if (_datatype.equals(double.class.getName()) ||
                       _datatype.equals(Double.class.getName())) _value=toDouble(value);
              else if (_datatype.equals(boolean.class.getName()) ||
                       _datatype.equals(Boolean.class.getName())) _value=toBoolean(value);
              else if (_datatype.equals(String.class.getName())) _value=toString(value);
              else if (_datatype.equals(java.util.Date.class.getName())) _value=toDate(value);
              else _value=datatype.cast(value);
          }
          catch (Exception ex) {
              _value=null;
          }
        }
        
        return _value;
    }
    
    /**
     * Returns whether the specified value is null.
     * @param value Value to evaluate.
     * @return True if value is equal to null, otherwise false.
     */
    public static boolean  isNull(Object value) {
        return (boolean) (value==null);
    }
    
    /**
     * Converts the specified value into boolean data. May throw a InvalidCastException if value can't be converted.
     * @param value Value to convert.
     * @return Returns the boolean representation of the specified value.
     */
    public static boolean  toBoolean(Object value) {
        try {
            return (boolean) Boolean.valueOf(value.toString());
        }
        catch (Exception ex) {
            throw new InvalidCastException(ex.getMessage());
        }
    }
    
    /**
     * Converts the specified value into Date data. May throw a InvalidCastException if value can't be converted.
     * @param value Value to convert.
     * @return Returns the Date representation of the specified value.
     */
    public static java.util.Date toDate(Object value) {
        SimpleDateFormat _formatter=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        try {
            return _formatter.parse(value.toString());
        }
        catch (Exception ex) {
            _formatter=new SimpleDateFormat("dd-MM-yyyy");
            try {
                return _formatter.parse(value.toString());
            }
            catch (Exception ex2) {
                throw new InvalidCastException(ex2.getMessage());
            }   
        }
    }
    
    /**
     * Converts the specified value into double floating point numeric data. May throw a InvalidCastException if value can't be converted.
     * @param value Value to convert.
     * @return Returns the double floating point representation of the specified value.
     */
    public static double toDouble(Object value) {
        try {
            return (double) Double.valueOf(value.toString());
        }
        catch (Exception ex) {
            throw new InvalidCastException(ex.getMessage());
        }
    }
    
   
    /**
     * Converts the specified value into floating point numeric data. May throw a InvalidCastException if value can't be converted.
     * @param value Value to convert.
     * @return Returns the floating point representation of the specified value.
     */
    public static float toFloat(Object value) {
        try {
            return (float) Float.valueOf(value.toString());
        }
        catch (Exception ex) {
            throw new InvalidCastException(ex.getMessage());
        }
    }
    
    /**
     * Converts the specified value into integer numeric data. May throw a InvalidCastException if value can't be converted.
     * @param value Value to convert.
     * @return Returns the integer representation of the specified value.
     */
    public static int toInt(Object value) {
        try
        {
            return (int) Integer.valueOf(value.toString());
        }
        catch (Exception ex) {
            throw new InvalidCastException(ex.getMessage());
        }
    }
    
    /**
     * Converts the specified value into the long numeric data. May throw a InvalidCastException if value can't be converted.
     * @param value Value to convert.
     * @return Returns the long representation of the specified value.
     */
    public static long toLong(Object value) {
        try
        {
            return (long) Long.valueOf(value.toString());
        }
        catch (Exception ex) {
            throw new InvalidCastException(ex.getMessage());
        }
    }
    
    /**
     * Converts the specified value into a short numeric data. May throw a InvalidCastException if value can't be converted.
     * @param value Value to convert.
     * @return Returns the short numeric representation of the specified value.
     */
    public static short toShort(Object value) {
        try {
            return Short.valueOf(value.toString());
        }
        catch (Exception ex) {
            throw new InvalidCastException(ex.getMessage());
        }
    }
    
    /**
     * Converts the specified value into String data. May throw a InvalidCastException if value can't be converted.
     * @param value Value to convert.
     * @return Returns the String representation of the specified value.
     */
    public static String toString(Object value) {
        try {
            return value.toString();
        }
        catch (Exception ex) {
            throw new InvalidCastException(ex.getMessage());
        }
    }
    
    /**
     * Converts the specified string value into its SQL-qualified string representation.
     * @param value Value to convert
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(String value) {
        return value.replace("'", "''").replace("\\", "\\\\");
    }
    
    
    
}
