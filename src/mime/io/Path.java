/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Mimics VB.Net System.IO.Path class
 * @author seph
 */
public class Path {
   
    /**
     * Gets the extension name of the specified file. 
     * @param filename Filename to evaluate
     * @return File extension name
     */
    public static String getExtension(String filename) {
        String _extension="";
        
        java.io.File _file=new java.io.File(filename);
        if (_file.exists() &&
            _file.isFile()) {
            Pattern _pattern=Pattern.compile("\\.[a-zA-Z0-9]+");
            Matcher _matcher=_pattern.matcher(filename);
            while (_matcher.find()) {
                String _value=_matcher.group();
                if (filename.endsWith(_value)) {
                    _extension=_value; break;
                }
            }
            _pattern=null; _matcher=null;
        }
        else {
            _file=null; System.gc();
            throw new FileUnavailableException("File is not available.");
        }
       
        _file=null; System.gc();
        
        return _extension;
    }
    
}
