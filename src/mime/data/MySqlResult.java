/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * MySql input file and SQL statement execution result class.
 * @author seph
 */
public class MySqlResult {
    
     /**
     * Creates a new instance of MySqlResult
     * @param inputfile SQL input file
     * @param scripts Executable application contents
     */
    public MySqlResult(String inputfile, String scripts) {
        this(inputfile, scripts, "");
    }
    
    /**
     * Creates a new instance of MySqlResult
     * @param inputfile SQL input file
     * @param scripts Executable application contents
     * @param errormessage Error message
     */
    public MySqlResult(String inputfile, String scripts, String errormessage) {
        _inputpath=inputfile; _script=scripts; _error=errormessage;
    }
    
    private String _error="";
    
    /**
     * Gets the dump execution error message.
     * @return Error message from the dump console, otherwise a blank string if it doesn't have any.
     */
    public String error() {
        return _error;
    }
    
    private String _inputpath="";
    
    /**
     * Gets the dump output file path.
     * @return Output file path
     */
    public String inputPath() {
        return _inputpath;
    }
    
    private String _script="";
    
    /**
     * Gets the dump executable script contents.
     * @return Dump executable script file contents.
     */
    public String script() {
        return _script;
    }
    
}
