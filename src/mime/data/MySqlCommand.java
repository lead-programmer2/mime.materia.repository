/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Mimics VB.Net MySql.Data.MySqlClient.MySqlCommand class.
 * @author seph
 */
public class MySqlCommand {
    
    /**
     * Creates a new instance of MySqlCommand.
     */
    public MySqlCommand() {
        this(null);
    }
    
    /**
     * Creates a new instance of MySqlCommand.
     * @param connection MySqlConnection associated with the current database command.
     */
    public MySqlCommand(MySqlConnection connection) {
        this(connection, "");
    }
    
    /**
     * Creates a new instance of MySqlCommand.
     * @param connection MySqlConnection associated with the current database command.
     * @param sql SQL command statement
     */
    public MySqlCommand(MySqlConnection connection, String sql) {
        _connection=connection; _commandtext=sql;
    }
    
    /**
     * Creates a new instance of MySqlCommand.
     * @param transaction Database transaction associated with the current command.
     * @param sql SQL command statement
     */
    public MySqlCommand(MySqlTransaction transaction, String sql) {
        _transaction=transaction;
        _connection=transaction.connection();
        _commandtext=sql;
    }
    
    private String _commandtext="";
    private int _commandtimeout=0;
    private MySqlConnection _connection=null;
    private MySqlTransaction _transaction=null;
    private final MySqlParameterCollection _parameters=new MySqlParameterCollection(this);
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current class already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            _parameters.dispose();
            if (_transaction!=null) _transaction=null;
            finalize();
            MySqlCommand _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Executes the current database command SQL statement. 
     * @return Number of database records affected by the command execution. 
     */
    public int executeNonQuery() {
        int _affected=-1;
        
        if (parameters().size() > 0) {
            PreparedStatement _statement=_connection.prepareStatement(_commandtext);
           
            for (MySqlParameter _params:_parameters) {
                try {
                    _statement.setObject(_params.index()+1, _params.getValue());
                }
                catch (Exception ex) {
                    throw new MySqlParameterException(ex.getMessage());
                }
            }
            
            try {
                _statement.setQueryTimeout(_commandtimeout);
                _statement.execute(_commandtext);
                _affected=_statement.getUpdateCount();
            }
            catch (Exception ex) {
                throw new MySqlCommandException(ex.getMessage());
            }
        }
        else {
            Statement _statement=_connection.prepareStatement();
            MySqlQueryParser _parser=new MySqlQueryParser(_commandtext);
            Object[] _queries=_parser.statements();
            System.out.println(_queries.length + " sql statements");
            
            if (_queries.length > 0) {
                _affected=0;
                try {
                    for (Object query:_queries) {
                        _statement.setQueryTimeout(_commandtimeout);
                        _statement.execute(query.toString());
                        _affected+=_statement.getUpdateCount();
                    }
                }
                catch (Exception ex) {
                    _affected=-1; _parser=null;
                    throw new MySqlCommandException(ex.getMessage());
                }
            }
            else { 
                try {
                    _statement.setQueryTimeout(_commandtimeout);
                    _statement.execute(_commandtext);
                    _affected=_statement.getUpdateCount();
                }
                catch (Exception ex) {
                    _parser=null;
                    throw new MySqlCommandException(ex.getMessage());
                } 
            }
            _parser=null;
        }
        
        return _affected;
    }
    
    /**
     * Retrieves data relative to the current database command SQL statement and parameters.
     * @return ResultSet gathered from the command execution. 
     */
    public ResultSet executeReader() {
        ResultSet _resultset=null;
        
        if (_parameters.size() > 0) {
            PreparedStatement _statement=_connection.prepareStatement(_commandtext);
            try { 
               _statement.setQueryTimeout(_commandtimeout);
            }
            catch (Exception ex) {
                throw new MySqlCommandException(ex.getMessage());
            }
            
            for (MySqlParameter _params:_parameters) {
                try {
                    _statement.setObject(_params.index()+1, _params.getValue());
                }
                catch (Exception ex) {
                    throw new MySqlParameterException(ex.getMessage());
                }
            }
            
            try {
                _resultset=_statement.executeQuery();
            }
            catch (Exception ex) {
                _resultset=null; throw new MySqlCommandException(ex.getMessage());
            }
        }
        else {
            Statement _statement=_connection.prepareStatement();
            try {
                _statement.setQueryTimeout(_commandtimeout);
                _resultset=_statement.executeQuery(_commandtext);
            }
            catch (Exception ex) {
                throw new MySqlCommandException(ex.getMessage());
            }
        }
        
        return _resultset;
    }
    
    /**
     * Gets the SQL command statement assigned to the current MySqlCommand.
     * @return SQL command statement.
     */
    public String getCommandText() {
        return _commandtext;
    }
   
    /**
     * Gets the SQL statement time out (in seconds). 
     * @return Time out seconds.
     */
    public int getCommandTimeOut() {
        return _commandtimeout;
    }
    
    /**
     * Gets the MySqlConnection associated with the current command.
     * @return  Returns the MySqlConnection object associated with the current command.
     */
    public MySqlConnection getConnection() {
        return _connection; 
    }
    
    /**
     * Gets the MySqlTransaction associated with the current database command.
     * @return MySqlTransaction object associated with the current command.
     */
    public MySqlTransaction getTransaction() {
        return _transaction;
    }
    
    private boolean _isdisposed=false;
    
    /**
     * Returns whether the current class has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean  isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Gets the collection of parameters for the current command.
     * @return Collection of command parameters.
     */
    public MySqlParameterCollection parameters() {
        return _parameters;
    }
    
    /**
     * Gets the stored parameter value at the specified index of the collection.
     * @param index Parameter index
     * @return Returns parameter stored value.
     */
    public Object parameters(int index) {
        return _parameters.get(index);
    }
    
    /**
     * Sets the SQL command statement to the current MySqlCommand.
     * @param commandtext SQL command statement.
     */
    public void setCommandText(String commandtext) {
        _commandtext=commandtext;
    }
    
    /**
     * Sets the SQL command execution time out (in seconds).
     * @param commandtimeout Time out seconds.
     */
    public void setCommandTimeOut(int commandtimeout) {
        _commandtimeout=commandtimeout;
    }
    
    /**
     * Sets the MySqlConnection object associated with the current command.
     * @param connection 
     */                                           
    public void setConnection(MySqlConnection connection) {
        _connection=connection;
    }
    
    /**
     * Sets the MySqlTransaction associated with the current database command.
     * @param transaction MySqlTransaction instantiated from a database connection.
     */
    public void setTransaction(MySqlTransaction transaction) {
        _connection=transaction.connection();
        _transaction=transaction;
    }
    
}
