/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Data formatting class.
 * @author seph
 */
public final class Format {
    
      
    private static String getMatch(String searchpattern, String target) {
        String _match="";
        Pattern _pattern=Pattern.compile(searchpattern, Pattern.CASE_INSENSITIVE);
        Matcher _matcher=_pattern.matcher(target);
        boolean _matches=_matcher.find();
        if (_matches) _match=_matcher.group();
        return _match;
    }
    
    /**
     * Formats the specified numeric value depending on the given string pattern.
     * @param value Value to be formatted
     * @param format Pattern string
     * @return String value of the specified number that is formatted into the specified pattern.
     */
    public static String valueOf(byte value, String format) {
        return valueOf(Converter.toDouble(value), format);
    }
    
    /**
     * Formats the specified numeric value depending on the given string pattern.
     * @param value Value to be formatted
     * @param format Pattern string
     * @return String value of the specified number that is formatted into the specified pattern.
     */
    public static String valueOf(short value, String format) {
        return valueOf(Converter.toDouble(value), format);
    }
    
    /**
     * Formats the specified numeric value depending on the given string pattern.
     * @param value Value to be formatted
     * @param format Pattern string
     * @return String value of the specified number that is formatted into the specified pattern.
     */
    public static String valueOf(int value, String format) {
        return valueOf(Converter.toDouble(value), format);
    }
    
    /**
     * Formats the specified numeric value depending on the given string pattern.
     * @param value Value to be formatted
     * @param format Pattern string
     * @return String value of the specified number that is formatted into the specified pattern.
     */
    public static String valueOf(long value, String format) {
        return valueOf(Converter.toDouble(value), format);
    }
    
    /**
     * Formats the specified numeric value depending on the given string pattern.
     * @param value Value to be formatted
     * @param format Pattern string
     * @return String value of the specified number that is formatted into the specified pattern.
     */
    public static String valueOf(float value, String format) {
        return valueOf(Converter.toDouble(value), format);
    }
    
    /**
     * Formats the specified numeric value depending on the given string pattern.
     * @param value Value to be formatted
     * @param format Pattern string
     * @return String value of the specified number that is formatted into the specified pattern.
     */
    public static String valueOf(double value, String format) {
        String _value=Converter.toString(value);
        String _format=format;
        
        String _fixedpattern="(F|f)[0-9]+";
        String _nonfixedpattern="(N|n)[0-9]+";
        
        String _matches=getMatch(_fixedpattern, format);
        if (!_matches.equals("")) {
            String _decimals=getMatch("[0-9]+", _matches);
            String _zeros="";
            
            if (!_decimals.equals("")) {
                int _counter=Converter.toInt(_decimals);
                for (int i=0; i<= (_counter-1); i++) _zeros += "0";
            }
            else _zeros="00";
            
            _format="#######." + _zeros;
        }
        else {
            _matches=getMatch(_nonfixedpattern, format);
            if (!_matches.equals("")) {
                String _decimals=getMatch("[0-9]+", _matches);
                String _zeros="";
            
                if (!_decimals.equals("")) {    
                    int _counter=Converter.toInt(_decimals);
                    for (int i=0; i<= _counter-2; i++) _zeros += "0";
                    _zeros+="0";
                }
                else _zeros="00";
                
                _format="###,###,###,###,###." + _zeros;
            }
        }
        
        DecimalFormat _formatter=new DecimalFormat(_format);
        _value=_formatter.format(value);
        _formatter=null; System.gc();
        
        return _value;
    }
    
    /**
     * Formats the date specified depending on the given string pattern.
     * @param date Date to be formatted.
     * @param format Pattern string
     * @return String value of the specified date that is formatted into the specified pattern.
     */
    public static String valueOf(java.sql.Date date, String format) {
         String _value=Converter.toString(date);
        
        SimpleDateFormat _formatter=new SimpleDateFormat(format);
        _value=_formatter.format(date);
        _formatter=null; System.gc();
        
        return _value;
    }
    
    /**
     * Formats the date specified depends on a given string pattern.
     * @param date Date to be formatted.
     * @param format Pattern string
     * @return String value of the specified date that is formatted into the specified pattern.
     */
    public static String valueOf(Date date, String format) {
        String _value="";
        
        SimpleDateFormat _formatter=new SimpleDateFormat(format);
        _value=_formatter.format(date);
        _formatter=null; System.gc();
        
        return _value;
    } 
    
}
