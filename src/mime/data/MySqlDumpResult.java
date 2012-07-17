/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * MySql dump execution result class.
 * @author seph
 */
public class MySqlDumpResult {
    
    /**
     * Creates a new instance of MySqlDumpResult
     * @param outputfile Dump output file
     * @param scripts Executable dump contents
     */
    public MySqlDumpResult(String outputfile, String scripts) {
        this(outputfile, scripts, "");
    }
    
    /**
     * Creates a new instance of MySqlDumpResult
     * @param outputfile Dump output file
     * @param scripts Executable dump contents
     * @param errormessage Error message
     */
    public MySqlDumpResult(String outputfile, String scripts, String errormessage) {
        _outputpath=outputfile; _script=scripts; _error=errormessage;
    }
    
    private String _error="";
    
    /**
     * Gets the dump execution error message.
     * @return Error message from the dump console, otherwise a blank string if it doesn't have any.
     */
    public String error() {
        return _error;
    }
    
    private String _outputpath="";
    
    /**
     * Gets the dump output file path.
     * @return Output file path
     */
    public String outputPath() {
        return _outputpath;
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
