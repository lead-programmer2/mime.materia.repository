/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

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
     * Converts the specified file into an array of byte.
     * @param filename Path of the file to convert
     * @return Byte array representation of the specified file.
     */
    public static byte[] toByteArray(String filename) {
        return toByteArray(new File(filename));
    }
    
    /**
     * Converts the specified file into an array of byte.
     * @param file File to convert.
     * @return Byte array representation of the specified file.
     */
    public static byte[] toByteArray(File file) {
        byte[] _bytes=null;
        
        try {
            _bytes=FileUtils.readFileToByteArray(file);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
            
        return _bytes;
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
     * Converts the specified file into its corresponding hexadecimal string representation.
     * @param filename Path of the file to be converted.
     * @return Hexadecimal string representation of the specified file.
     */
    public static String toHexadecimalString(String filename) {
        return toHexadecimalString(new File(filename));
    }
    
    /**
     * Converts the specified file object into its corresponding hexadecimal string representation.
     * @param file File to be converted
     * @return Hexadecimal string representation of the specified file.
     */
    public static String toHexadecimalString(File file) {
        return toHexadecimalString(toByteArray(file));
    }
    
    /**
     * Converts the specified byte array into corresponding hexadecimal string representation.
     * @param bytes Byte array to convert
     * @return Hexadecimal string representation of the specified byte array.
     */
    public static String toHexadecimalString(byte[] bytes) {
        return Hex.encodeHexString(bytes);
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
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(byte value) {
        return toSqlValidString(value, 0);
    }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @param decimals Decimal places.
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(byte value, int decimals) {
        return toSqlValidString(Converter.toDouble(value), decimals);
    }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @return SQL-qualified string representation of the specified value.
     */
     public static String toSqlValidString(int value) {
         return toSqlValidString(value, 0);
     }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @param decimals Decimal places.
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(int value, int decimals) {
        return toSqlValidString(Converter.toDouble(value), decimals);
    }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(long value) {
        return toSqlValidString(value, 0);
    }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @param decimals Decimal places.
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(long value, int decimals) {
        return toSqlValidString(Converter.toDouble(value), decimals);
    }
        
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(short value) {
        return toSqlValidString(value, 0);
    }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @param decimals Decimal places.
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(short value, int decimals) {
        return toSqlValidString(Converter.toDouble(value), decimals);
    }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(float value) {
        return toSqlValidString(value, 2);
    }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @param decimals Decimal places.
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(float value, int decimals) {
        return toSqlValidString(Converter.toDouble(value), decimals);
    }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(double value) {
        return toSqlValidString(value, 2);
    }
    
    /**
     * Converts the specified numeric value into its SQL-qualified string representation.
     * @param value Value to convert
     * @param decimals Decimal places.
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(double value, int decimals) {
        return Format.valueOf(value, "F" + decimals);
    }
    
    /**
    * Converts the specified date value into its SQL-qualified string representation.
     * @param value Value to convert
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(Date value) {
        return toSqlValidString(value, false);
    }
    
    /**
     * Converts the specified date value into its SQL-qualified string representation.
     * @param value Value to convert
     * @param withhours Determines whether result should also include time part.
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(Date value, boolean withhours) {
        String _value=value.toString();
        String _format="yyyy-MM-dd";
        
        if (withhours) _format += " HH:mm:ss";
        _value=Format.valueOf(value, _format);
        
        return _value;
    }
    
    /**
    * Converts the specified date value into its SQL-qualified string representation.
     * @param value Value to convert
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(java.sql.Date value) {
        return toSqlValidString(value, false);
    }
    
    /**
     * Converts the specified date value into its SQL-qualified string representation.
     * @param value Value to convert
     * @param withhours Determines whether result should also include time part.
     * @return SQL-qualified string representation of the specified value.
     */
    public static String toSqlValidString(java.sql.Date value, boolean withhours) {
        String _value=value.toString();
        String _format="yyyy-MM-dd";
        
        if (withhours) _format += " HH:mm:ss";
        _value=Format.valueOf(value, _format);
        
        return _value;
    }
    
}
