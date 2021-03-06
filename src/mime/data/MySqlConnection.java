/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mime.Converter;


/**
 * Mimics VB.Net MySql.Data.MySqlClient.MySqlConnection class.
 * @author seph
 */
public class MySqlConnection {
    
    /**
     * MySQL JDBC default driver.
     */
    public final String driver="com.mysql.jdbc.Driver";
    
    private String _server="";
    private String _database="";
    private String _userid="";
    private String _password="";
    private int _port=0;
    
    private boolean  _isdisposed=false;
    private Connection _connection=null;
    
    /**
     * Creates a new instance of MySqlConnection.
     * @param connectionstring  OLEDB formatted MySql database connection string.
     */
    public MySqlConnection(String connectionstring) {
        String _base="[\\w]+;";
        String server=getMatch("(S|s)(E|e)(R|r)(V|v)(E|e)(R|r)[\\n\\r\\t =]+" + _base, connectionstring).replace(";", "");
        String database=getMatch("(D|d)(A|a)(T|t)(A|a)(B|b)(A|a)(S|s)(E|e)[\\n\\r\\t =]+" + _base, connectionstring).replace(";", "");
        String uid=getMatch("(U|u)(I|i)(D|d)[\\n\\r\\t =]+" + _base, connectionstring).replace(";", "");
        String pwd=getMatch("(P|p)(W|w)(D|d)[\\n\\r\\t =]+" + _base, connectionstring).replace(";", "");
        String port=getMatch("(P|p)(O|o)(R|r)(T|t)[\\n\\r\\t =]+[0-9]+", connectionstring).replace(";", "");
        
        _server=matchAndReplace("(S|s)(E|e)(R|r)(V|v)(E|e)(R|r)[\\n\\r\\t =]+", server, ""); 
        _database=matchAndReplace("(D|d)(A|a)(T|t)(A|a)(B|b)(A|a)(S|s)(E|e)[\\n\\r\\t =]+", database, ""); 
        _userid=matchAndReplace("(U|u)(I|i)(D|d)[\\n\\r\\t =]+", uid, ""); 
        _password=matchAndReplace("(P|p)(W|w)(D|d)[\\n\\r\\t =]+", pwd, ""); 
        
        if (port=="") _port=3306;
        else _port=Converter.toInt(matchAndReplace("(P|p)(O|o)(R|r)(T|t)[\\n\\r\\t =]+", port, ""));
    }
    
    /**
     * Creates a new instance of MySqlConnection.
     * @param server Database server host name or IP address.
     * @param database Database catalog name
     * @param userid Database server log on id.
     * @param password Database server log on password.
     */
    public MySqlConnection(String server, String database, String userid, String password) {
        this(server, database, userid, password, 3306);
    }
    
    /**
     * Creates a new instance of MySqlConnection.
     * @param server Database server host name or IP address.
     * @param database Database catalog name
     * @param userid Database server log on id.
     * @param password Database server log on password.
     * @param port Database server usable port number.
     */
    public MySqlConnection(String server, String database, String userid, String password, int port) {
        _server=server; _database=database; _userid=userid; _password=password; _port=port;
    }
    
    /**
     * Starts a new database transaction and set a current SQL save point with the connected database.
     * @return 
     */
    public MySqlTransaction beginTransaction() {
        MySqlTransaction _transaction = null;
        
        try {
            Savepoint _savepoint =_connection.setSavepoint("Save01");
            _transaction=new MySqlTransaction(this, _connection.setSavepoint());
        }
        catch (Exception ex) {
            _transaction=null;
            throw new MySqlTransactionException(ex.getMessage());
        }
        
        return _transaction;
    }
    
    /**
     * Closes the establish connection with a certain database.
     */
    public void close() {
        try {
            _connection.close();
        } 
        catch (Exception ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }
       
    /**
     * Creates a new MySqlCommand object from the current database connection.
     * @return Newly created MySqlCommand object.
     */
    public MySqlCommand createCommand() {
        return new MySqlCommand(this, "");
    }
    
    private void createConnection() {
        try {    
            String _url="jdbc:mysql://" + _server + ":" + _port + "/";   
            Class.forName(driver).newInstance();    
            _connection=DriverManager.getConnection(_url + _database, _userid, _password);    
        }    
        catch (Exception ex) {   
            _connection=null;    
            throw new DatabaseConnectionException(ex.getMessage());    
        }
    }
   
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current class already called this method, check isDispose
     * method.
     */
    public void dispose() {
         _isdisposed=true;
        try {
            if (_connection!=null) {
                try {
                    if (!_connection.isClosed()) _connection.close();;
                }
                catch (Exception ex) {
                }
                finally {
                   _connection=null;
                }
            }
            
            finalize();
            MySqlConnection _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Gets the current connection database catalog name.
     * @return Returns database catalog name.
     */
    public String getDatabase() {
        return _database;
    }
    
    /**
     * Gets the actual implemented database connection used by the current MySqlConnection object.
     * @return Implemented connection interface.
     */
    public Connection getInterface() {
        return _connection;
    }
    
    private String getMatch(String searchpattern, String target) {
        String _match="";
        Pattern _pattern=Pattern.compile(searchpattern);
        Matcher _matcher=_pattern.matcher(target);
        boolean _matches=_matcher.find();
        if (_matches) _match=_matcher.group();
        return _match;
    }
    
    
    /**
     * Gets the current connection database server log on password.
     * @return Returns database server log on account password.
     */
    public String getPassword() {
        return _password;
    }
    
    /**
     * Gets the current connection server port number.
     * @return Port number used to communicate with the database.
     */
    public int getPort() {
        return _port;
    }
    
    /**
     * Gets the database schema information of the current connected database.
     * @return 
     */
    public DatabaseMetaData getSchema() {
        if (_connection!=null) 
            try {
                return _connection.getMetaData();
            }
            catch (Exception ex) {
                throw new DatabaseConnectionException(ex.getMessage());
            }
        else return null;
    }
    
    /**
     * Gets the current connection database server host name or IP address.
     * @return Returns host name or IP address of the database server.
     */
    public String getServer() {
        return _server;
    }
    
    /**
     * Gets the lists of database tables inside the connected database.
     * @return List of database table names inside the connected database.
     */
    public ArrayList<String> getTables() {
        ArrayList<String> _tables=new ArrayList<String>();
        
        String _query= "SELECT\n" + 
                       "`tables`.`TABLE_NAME`\n" +
                       "FROM\n" + 
                       "`TABLES` AS `tables`\n" +
                       "WHERE\n" +
                       "`tables`.`TABLE_SCHEMA` LIKE '" + Converter.toSqlValidString(_database) + "' AND\n" +
                       "`tables`.`TABLE_COMMENT` NOT LIKE 'VIEW'\n" + 
                       "ORDER BY\n" +
                       "`TABLE_NAME`";
        
        MySqlConnection con=new MySqlConnection(_server, "information_schema", _userid, _password, _port);
        
        MySqlCommand cmd=con.createCommand();
        cmd.setCommandText(_query);
        boolean _isclosed=(!con.isOpen());
        if (_isclosed) con.open();
        DataTable _table=new DataTable();
        _table.load(cmd.executeReader());
        cmd.dispose(); 
        for (DataRow rw:_table.rows()) _tables.add(rw.getItem("TABLE_NAME").toString());
        _table.dispose();
        if (_isclosed && 
            isOpen()) con.close();
        
        con.dispose();
        
        return _tables;
    }
    
    /**
     * Gets the current connection database server log on user id.
     * @return Returns database server log on account user id.
     */
    public String getUserId() {
        return _userid;
    }
    
    /**
     * Returns whether the current table has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Gets whether the current class is connected to a database or not.
     * @return True if current connection is connected, otherwise false.
     */
    public boolean isOpen() {
        if (_connection==null) return false;
        else {
            try {
                return (!_connection.isClosed());
            }
            catch (Exception ex) {
                return false;
            }
        }
    }
    
    private String matchAndReplace(String searchpattern, String target, String replacement) {    
        Pattern _pattern=Pattern.compile(searchpattern);
        Matcher _matcher=_pattern.matcher(target);
        StringBuffer _buffer = new StringBuffer(target.length());
        boolean _matches=_matcher.find();
        if (_matches) {
            _matcher.appendReplacement(_buffer, Matcher.quoteReplacement(replacement));
            _matcher.appendTail(_buffer);
        }
        return _buffer.toString();
    }
    
    /**
     * Attempt to establish a database connection using the supplied database information.
     */
    public void open() {
        if (_connection==null) createConnection();
        else {
            if (isOpen()) throw new DatabaseConnectionException("Connection is already open.");
            else createConnection();
        }
    }
    
    /**
     * Initiates the connection for possible query execution.
     * @return Blank connection statement. My throw DatabaseConnectionException when connection is not open or other failure that may encounter during
     * statement preparation.
     */
    public Statement prepareStatement() {
        if (!isOpen()) throw new DatabaseConnectionException("No database connection has been opened yet.");
        try {
            return _connection.createStatement();
        }
        catch (Exception ex) {
           throw new DatabaseConnectionException(ex.getMessage());
        }
     }
     
    /**
     * Initiates the connection for possible query execution.
     * @param sql SQL statement to initialize.
     * @return Connection statement that can accept parameters. My throw DatabaseConnectionException when connection is not open or other failure that may encounter during
     * statement preparation.
     */
     public PreparedStatement prepareStatement(String sql) { 
         if (!isOpen()) throw new DatabaseConnectionException("No database connection has been opened yet.");
         try {
             return _connection.prepareStatement(sql);
         }
         catch (Exception ex) {
             throw new DatabaseConnectionException(ex.getMessage());
         }
     }
     
     private String replace(String searchpattern, String target, String replacewith) { 
         String _replaced=target;
         Pattern _pattern=Pattern.compile(searchpattern, Pattern.CASE_INSENSITIVE);
         Matcher _matcher=_pattern.matcher(target);
        if (_matcher.find()) _replaced = _matcher.replaceAll(replacewith);
        return _replaced;
     }
     
    /**
     * Sets the database catalog name of the current connection.
     * @param database Database catalog name.
     */
    public void setDatabase(String database) {
        if (isOpen()) throw new DatabaseConnectionException("Connection is currently open.");
        _database=database;
    }
    
    /**
     * Sets the database log on account password for the current connection.
     * @param password Database log on account password.
     */
    public void setPassword(String password) {
        if (isOpen()) throw new DatabaseConnectionException("Connection is currently open.");
        _password=password;
    }
    
    /**
     * Sets the database port number for the current connection.
     * @param port Database port number.
     */
    public void setPort(int port) {
        if (isOpen()) throw new DatabaseConnectionException("Connection is currently open.");
        _port=port;
    }
    
    /**
     * Sets the server host name or IP address for the current connection.
     * @param server Database server host name or IP address.
     */
    public void setServer(String server) {
        if (isOpen()) throw new DatabaseConnectionException("Connection is currently open.");
        _server=server;
    }
    
    /**
     * Sets the server log on user id for the current connection.
     * @param userid Database log on account user id.
     */
    public void setUserId(String userid) {
        if (isOpen()) throw new DatabaseConnectionException("Connection is currently open.");
        _userid=userid;
    }
    
}
