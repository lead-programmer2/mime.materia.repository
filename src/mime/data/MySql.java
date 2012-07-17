/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import mime.Application;
import mime.io.File;
import mime.io.StreamWriter;
import org.apache.commons.io.IOUtils;

/**
 * MySql class for database backup and restoration.
 * @author seph
 */
public class MySql {
    
   // <editor-fold defaultstate="collapsed" desc="Constants">
    
    /**
     * MySQL dump parameter : --add-drop-database
     */
    public static final String ADD_DROP_DATABASE="--add-drop-database";
    /**
     * MySQL dump parameter : --add-drop-table
     */
    public static final String ADD_DROP_TABLE="--add-drop-table";
    /**
     * MySQL dump parameter : --add-locks
     */
    public static final String ADD_LOCKS="--add-locks";
    /**
     * MySQL dump parameter : --all-databases
     */
    public static final String ALL_DATABASES="--all-databases";
    /**
     * MySQL dump parameter : --allow-keywords
     */
    public static final String ALLOW_KEYWORDS="--allow-keywords";
    /**
     * MySQL application parameter : --auto-rehash
     */
    public static  final String AUTO_REHASH="--auto-rehash";
    /**
     * MySQL application parameter : --batch
     */
    public static final String BATCH="--batch";
    /**
     * MySQL application parameter : --column-names
     */
    public static  final String COLUMN_NAMES="--column-names";
    /**
     * MySQL application / dump parameter : --comments
     */
    public static final String COMMENTS="--comments";
    /**
     * MySQL dump parameter : --compact
     */
    public static final String COMPACT="--compact";
    /**
     * MySQL application / dump parameter : --compress
     */
    public static final String COMPRESS="--compress";
    /**
     * MySQL dump parameter : --complete-insert
     */
    public static final String COMPLETE_INSERT="--complete-insert";
    /**
     * MySQL dump parameter : --create-options
     */
    public static final String CREATE_OPTIONS="--create-options";
    /**
     * MySQL application parameter : --debug-info
     */
    public static  final String DEBUG_INFO="--debug-info";
    /**
     * MySQL dump parameter : --delayed-insert
     */
    public static final String DELAYED_INSERT="--delayed-insert" ;
    /**
     * MySQL dump parameter : --delete-master-logs
     */
    public static final String DELETE_MASTER_LOGS="--delete-master-logs";
    /**
     * MySQL dump parameter : --disable-keys
     */
    public static final String DISABLE_KEYS="--disable-keys";
    /**
     * MySQL dump parameter : --dump-date
     */
    public static final String DUMP_DATE="--dump-date";
    /**
     * MySQL dump parameter : --extended-insert
     */
    public static final String EXTENDED_INSERT="--extended-insert";
    /**
     * MySQL dump parameter : --flush-logs
     */
    public static final String FLUSH_LOGS="--flush-logs";
    /**
     * MySQL dump parameter : --flush-privileges
     */
    public static final String FLUSH_PRIVILEGES="--flush-privileges";
    /**
     * MySQL application / dump parameter : --force
     */
    public static final String FORCE="--force";
    /**
     * MySQL application parameter : --help
     */
    public static  final String HELP="--help";
    /**
     * MySQL dump parameter : --hex-blob
     */
    public static final String HEX_BLOB="--hex-blob";
    /**
     * MySQL application parameter : --html
     */
    public static  final String HTML="--html";
    /**
     * MySQL application parameter : --ignore-spaces
     */
    public static final String IGNORE_SPACES="--ignore-spaces";
    /**
     * MySQL dump parameter : --insert-ignore
     */
    public static final String INSERT_IGNORE="--insert-ignore";
    /**
     * MySQL application parameter : --line-numbers
     */
    public static final String LINE_NUMBERS="--line-numbers";
    /**
     * MySQL dump parameter : --lock-all-tables
     */
    public static final String LOCK_ALL_TABLES="--lock-all-tables";
    /**
     * MySQL dump parameter : --lock-tables
     */
    public static  final String LOCK_TABLES="--lock-tables";
    /**
     * MySQL application parameter : --named-commands
     */
    public static  final String NAMED_COMMANDS="--named-commands";
    /**
     * MySQL dump parameter : --no-auto-commit
     */
    public static  final String NO_AUTO_COMMIT="--no-auto-commit";
    /**
     * MySQL application parameter : --no-auto-rehash
     */
    public static  final  String NO_AUTO_REHASH="--no-auto-rehash";
    /**
     * MySQL application parameter : --no-beep
     */
    public static  final String NO_BEEP="--no-beep";
    /**
     * MySQL dump parameter : --no-create-db
     */
    public static  final String NO_CREATE_DB="--no-create-db";
    /**
     * MySQL dump parameter : --no-create-info
     */
    public static final String NO_CREATE_INFO="--no-create-info";
    /**
     * MySQL dump parameter : --no-data
     */
    public static  final String NO_DATA="--no-data";
    /**
     * MySQL application parameter : --no-named-commands
     */
    public static final String NO_NAMED_COMMANDS="--no-named-commands";
    /**
     * MySQL application parameter : --no-pager
     */
    public static  final String NO_PAGER="--no-pager";
    /**
     * MySQL dump parameter : --no-set-names
     */
    public static  final String NO_SET_NAMES="--no-set-names";
    /**
     * MySQL application parameter : --no-tee
     */
    public static  final String NO_TEE="--no-tee";
    /**
     * MySQL application parameter : --one-database
     */
    public static final  String ONE_DATABASE="--one-database";
    /**
     * MySQL dump parameter : --opt
     */
    public static  final String OPT="--opt";
    /**
     * MySQL dump parameter : --order-by-primary
     */
    public static  final String ORDER_BY_PRIMARY="--order-by-primary";
    /**
     * MySQL application / dump parameter : --quick
     */
    public static  final String QUICK="--quick";
    /**
     * MySQL dump parameter : --quote-names
     */
    public static  final String QUOTE_NAMES="--quote-names";
    /**
     * MySQL application parameter : --raw
     */
    public static  final String RAW="--raw";
    /**
     * MySQL application parameter : --reconnect
     */
    public static  final  String RECONNECT="--reconnect";
    /**
     * MySQL dump parameter : --routines
     */
    public static final String ROUTINES="--routines";
    /**
     * MySQL application parameter : --safe-updates
     */
    public static  final String SAFE_UPDATES="--safe-updates";
    /**
     * MySQL application parameter : --secure-auth
     */
    public static  final String SECURE_AUTH="--secure-auth";
    /**
     * MySQL dump parameter : --set-charset
     */
    public static final String SET_CHARSET="--set-charset";
    /**
     * MySQL application parameter : --show-warnings
     */
    public static  final String SHOW_WARNINGS="--show-warnings";
    /**
     * MySQL application parameter : --sigint-ignore
     */
    public static final String SIGINT_IGNORE="--sigint-ignore";
    /**
     * MySQL application parameter : --silent
     */
    public static  final String SILENT="--silent";
    /**
     * MySQL dump parameter : --single-transaction
     */
    public static  final String SINGLE_TRANSACTION="--single-transaction";
    /**
     * MySQL dump parameter : --skip-add-drop-tables
     */
    public static final String SKIP_ADD_DROP_TABLES="--skip-add-drop-tables";
    /**
     * MySQL dump parameter : --skip-add-locks
     */
    public static  final String SKIP_ADD_LOCKS="--skip-add-locks";
    /**
     * MySQL application parameter : --skip-column-names
     */
    public static  final String SKIP_COLUMN_NAMES="--skip-column-names";
    /**
     * MySQL dump parameter : --skip-comments
     */
    public static  final  String SKIP_COMMENTS="--skip-comments";
    /**
     * MySQL dump parameter : --skip-disable-keys
     */
    public static  final String SKIP_DISABLE_KEYS="--skip-disable-keys";
    /**
     * MySQL dump parameter : --skip-dump-date
     */
    public static final String SKIP_DUMP_DATE="--skip-dump-date";
    /**
     * MySQL application parameter : --skip-line-numbers
     */
    public static  final String SKIP_LINE_NUMBERS="--skip-line-numbers";
    /**
     * MySQL dump parameter : --skip-opt
     */
    public static  final String SKIP_OPT="--skip-opt";
    /**
     * MySQL application parameter : --skip-pager
     */
    public static  final String SKIP_PAGER="--skip-pager";
    /**
     * MySQL dump parameter : --skip-set-charset
     */
    public static  final String SKIP_SET_CHARSET="--skip-set-charset";
    /**
     * MySQL dump parameter : --skip-triggers
     */
    public static  final String SKIP_TRIGGERS="--skip-triggers";
    /**
     * MySQL dump parameter : --skip-tz-utc
     */
    public static final String SKIP_TZ_UTC="--skip-tz-utc";
    /**
     * MySQL application parameter : --table
     */
    public static  final String TABLE="--table";
    /**
     * MySQL dump parameter : --tables
     */
    public static final String TABLES="--tables";
    /**
     * MySQL dump parameter : --triggers
     */
    public static  final String TRIGGERS="--triggers";
    /**
     * MySQL dump parameter : --tz-utc
     */
    public static final String TZ_UTC="--tz-utc";
    /**
     * MySQL application parameter : --unbuffered
     */
    public static  final String UNBUFFERED="--unbuffered";
    /**
     * MySQL application / dump parameter : --verbose
     */
    public static final String VERBOSE="--verbose";
    /**
     * MySQL application / dump parameter : --version
     */
    public static final String VERSION="--version";
    /**
     * MySQL application parameter : --vertical
     */
    public static final String VERTICAL="--vertical";
    /**
     * MySQL application parameter : --wait
     */
    public static final String WAIT="--wait";
    
  // </editor-fold>
    
    private MySqlConnection _connection=null;
    private boolean  _isdisposed=false;
    
    /**
     * Creates a new instance of MySql
     * @param connectionstring MySQL-valid database connection string.
     */
    public MySql(String connectionstring) {
        _connection=new MySqlConnection(connectionstring);
    }
    
    /**
     * Creates a new instance of MySql
     * @param server Server host name / IP address
     * @param database Database catalog name
     * @param uid Database server log on account user id.
     * @param pwd Database server log on account password.
     */
    public MySql(String server, String database, String uid, String pwd) {
        _connection=new MySqlConnection(server, database, uid, pwd);
    }
    
    
     /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current class already called this method, check isDispose
     * method.
     */
    public void dispose() {
         _isdisposed=true;
        try {
            _connection.dispose();
            finalize();
            MySql _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Performs a database backup procedure of the current connected database into the specified output file path.
     * @param outputfile Database backup output file
     * @return MySql dump procedure result information, otherwise null if something went wrong.
     */
    public MySqlDumpResult dump(java.io.File outputfile) {
         String _outputfilename=outputfile.getPath();
         if (_outputfilename.startsWith("/")) _outputfilename=outputfile.getPath().substring(1);
         return dump(_outputfilename);
    }
    
    /**
     * Performs a database backup procedure of the current connected database into the specified output file path.
     * @param outputfilename Database backup output file path
     * @return MySql dump procedure result information, otherwise null if something went wrong.
     */
    public MySqlDumpResult dump(String outputfilename) {
        MySqlApplicationParameterCollection _params=new MySqlApplicationParameterCollection();
        _params.add(HEX_BLOB);
        _params.add(QUICK);
        _params.add(ROUTINES);
        return dump(outputfilename, _params);
    }
    
    /**
     * Performs a database backup procedure of the current connected database into the specified output file path.
     * @param outputfile Database backup output file.
     * @param parameters MySql dump application valid parameters.
     * @return MySql dump procedure result information, otherwise null if something went wrong.
     */
     public MySqlDumpResult dump(java.io.File outputfile, MySqlApplicationParameterCollection parameters) {
         String _outputfilename=outputfile.getPath();
         if (_outputfilename.startsWith("/")) _outputfilename=outputfile.getPath().substring(1);
         return dump(_outputfilename, parameters);
     }
     
    /**
     * Performs a database backup procedure of the current connected database into the specified output file path.
     * @param outputfilename Database backup output file path
     * @param parameters MySql dump application valid parameters.
     * @return MySql dump procedure result information, otherwise null if something went wrong.
     */
    public MySqlDumpResult dump(String outputfilename, MySqlApplicationParameterCollection parameters) {
        MySqlDumpResult _result=null;
        
        String _tables=""; String _parameters="";
        URL _mysqldump=mime.Mime.resources.mySqlDump();
        ArrayList<String> _tablelist=_connection.getTables();
        for (String table:_tablelist) _tables += ((!_tables.trim().equals(""))? " ":"") + table;
        for (String param:parameters) _parameters += ((!_parameters.trim().equals(""))? " ":"") + param;
        String _dumppath=_mysqldump.getPath();
        if (_dumppath.trim().startsWith("/")) _dumppath=_mysqldump.getPath().substring(1);
        String _contents="\"" + _dumppath + "\" --host=" + _connection.getServer() + " --user=" + _connection.getUserId() + " --password=" + _connection.getPassword() + " " + _connection.getDatabase() + " " + _tables + (!_parameters.equals("")? " ":"") + _parameters + ((!_parameters.equals("") || !_tables.equals(""))? " ":"") + "> \"" + outputfilename.replace("\\", "/") + "\"";
        String _batchfilename=Application.startUpPath() + "\\dump.bat";
        System.out.println("Creating : " + _batchfilename + " with contents " + _contents + "...");
        java.io.File _batchfile=initBatchFile(_batchfilename, _contents);
        
        if (_batchfile!=null) {
            java.lang.Process p=null;
            
            try {
                p=Runtime.getRuntime().exec(new String[]{"cmd.exe", "/C", _batchfilename});
                p.waitFor();
                InputStream _errorstream = p.getErrorStream();
                String _error="";
                
                if (_errorstream!=null) {
                    int _bytecount=_errorstream.available();
                    if (_bytecount > 0) {     
                        StringWriter _writer = new StringWriter();
                    
                        try {
                            byte[] _bytes=new byte[_bytecount];
                            _errorstream.read(_bytes);
                            _error=new String(_bytes);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }                    
                        finally {
                            _writer.close(); _writer=null; System.gc();
                        }
                    }
                }
                
                _result=new MySqlDumpResult(outputfilename, _contents, _error);
            }
            catch (Exception ex) {
                _result=new MySqlDumpResult(outputfilename, _contents, ex.getMessage());
                ex.printStackTrace();
            }
            finally {
                if (p!=null) p.destroy();
                p=null; System.gc();
            }
            
            try {
                File.delete(_batchfilename);
            }
            catch (Exception ex) {
               ex.printStackTrace();
            }
        }
        
        return _result;
    }
    
    /**
     * Executes the specified SQL statements directly into the current connected MySql database.
     * @param sql SQL statements
     * @return MySql execution result information, otherwise null if something went wrong.
     */
    public MySqlResult execute(String sql) {          
        MySqlApplicationParameterCollection _params=new MySqlApplicationParameterCollection();   
        return execute(sql, _params);    
    }
    
    /**
     * Executes the SQL statements inside the specified input file into the current connected MySql database.
     * @param inputfilename Input file to where the SQL statements will be red.
     * @return MySql execution result information, otherwise null if something went wrong.
     */
    public MySqlResult execute(java.io.File inputfile) {
        MySqlApplicationParameterCollection _params=new MySqlApplicationParameterCollection();
        return execute(inputfile, _params);
    }
    
    /**
     * Executes the specified SQL statements directly into the current connected MySql database.
     * @param sql SQL statements
     * @param parameters MySql application valid parameters
     * @return MySql execution result information, otherwise null if something went wrong.
     */
     public MySqlResult execute(String sql, MySqlApplicationParameterCollection parameters) {
         return execute(sql, parameters, 500);
     }
    
    /**
     * Executes the SQL statements inside the specified input file into the current connected MySql database.
     * @param inputfilename Input file to where the SQL statements will be red.
     * @param parameters MySql application valid parameters
     * @return MySql execution result information, otherwise null if something went wrong.
     */
    public MySqlResult execute(java.io.File inputfile, MySqlApplicationParameterCollection parameters) {
        return execute(inputfile, parameters, 500);
    }
      
    /**
     * Executes the specified SQL statements directly into the current connected MySql database.
     * @param sql SQL statements
     * @param parameters MySql application valid parameters
     * @param maxallowedpackets Max allowed packets (in MB) alloted to accept and execute large SQL scripts.
     * @return MySql execution result information, otherwise null if something went wrong.
     */
    public MySqlResult execute(String sql, MySqlApplicationParameterCollection parameters, int maxallowedpackets) {
        java.io.File _file=null;
        String _filename=Application.startUpPath() + "\\executables.sql";
        StreamWriter _sw=new StreamWriter(_filename);
        
        try {
            _sw.write(sql); _file =new java.io.File(_filename);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            _sw.close(); _sw.dispose();
        }
        
        MySqlResult _result=null;
        if (_file!=null) _result=execute(_file, parameters, maxallowedpackets);
        
        if (_file!=null) {
            try {
                _file.delete();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }     
        }
        
        return _result;
    }
    
    /**
     * Executes the SQL statements inside the specified input file into the current connected MySql database.
     * @param inputfile Input file to where the SQL statements will be red.
     * @param parameters MySql application valid parameters
     * @param maxallowedpackets Max allowed packets (in MB) alloted to accept and execute large file.
     * @return MySql execution result information, otherwise null if something went wrong.
     */
    public MySqlResult execute(java.io.File inputfile, MySqlApplicationParameterCollection parameters, int maxallowedpackets) {
        MySqlResult _result=null;
        
        String _inputfilename=inputfile.getPath();
        if (_inputfilename.startsWith("/")) _inputfilename=inputfile.getPath().substring(1);
        String _batchfilename=Application.startUpPath() + "\\execute.bat";
        String _mysqlpath=mime.Mime.resources.mySql().getPath();
        String _parameters=""; 
        for (String param:parameters) _parameters += ((!_parameters.trim().equals(""))? " ":"") + param;
         
        if (_mysqlpath.startsWith("/")) _mysqlpath=mime.Mime.resources.mySql().getPath().substring(1);
        String _contents="\"" +  _mysqlpath + "\" -h " + _connection.getServer() + " -u " + _connection.getUserId() + " -p" + _connection.getPassword() + " " + _connection.getDatabase() + (_parameters.equals("")? "":" ") + _parameters + " --max_allowed_packet=" + maxallowedpackets + "M --default-character-set=utf8 < \"" + _inputfilename + "\"";
        java.io.File _batchfile=initBatchFile(_batchfilename, _contents);
        
        if (_batchfile!=null) {
            java.lang.Process p=null;
            
            try {
                p=Runtime.getRuntime().exec(new String[]{"cmd.exe", "/C", _batchfilename});
                p.waitFor();
                InputStream _errorstream = p.getErrorStream();
                String _error="";
                
                if (_errorstream!=null) {
                     int _bytecount=_errorstream.available();
                    if (_bytecount > 0) {     
                        StringWriter _writer = new StringWriter();
                    
                        try {
                            byte[] _bytes=new byte[_bytecount];
                            _errorstream.read(_bytes);
                            _error=new String(_bytes);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }                    
                        finally {
                            _writer.close(); _writer=null; System.gc();
                        }
                    }
                }
                
                _result=new MySqlResult(_inputfilename, _contents, _error);
            }
            catch (Exception ex) {   
                _result=new MySqlResult(_inputfilename, _contents, ex.getMessage());
                ex.printStackTrace();
            }
            finally {    
                if (p!=null) p.destroy();
                p=null; System.gc();
            }
              
            try {
                File.delete(_batchfilename);
            }
            catch (Exception ex) {
               ex.printStackTrace();
            }
        }
        
        return _result;
    }
    
    /**
     * Initializes a batch file into a certain path with the specified contents.
     * @param filename Batch file path
     * @param contents Batch file executable contents.
     * @return 
     */
    private java.io.File initBatchFile(String filename, String contents) {
        java.io.File _batchfile=new java.io.File(filename);
        
        StreamWriter sw=new StreamWriter(_batchfile);    
        try {    
            sw.write(contents);    
        }    
        catch (Exception ex) {    
            ex.printStackTrace();    
        }    
        finally {    
            sw.close(); sw.dispose();    
        } 
        
        return _batchfile;
    }
    
    /**
     * Returns whether the current table has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean isDisposed() {
        return _isdisposed;
    }
}
