/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.util.ArrayList;

/**
 * SQL statement parsing class. This distinguish number of SQL statement iterated in a string value.
 * @author seph
 */
public class MySqlQueryParser {
    
    private String _sql ="";
    private String _sqlstartings[]=new String[]{"SELECT", "INSERT", "UPDATE", "DELETE",
                                                "TRUNCATE", "SET", "ALTER", "DROP", "CREATE", "CALL"};
    /**
     * Creates a new instance of MySqlQueryParser.
     * @param sql SQL command statement to evaluate
     */
    public MySqlQueryParser(String sql) {
        _sql=sql;
    }
    
    private ArrayList<String> getStatements() {
        ArrayList<String> _statements=new ArrayList<String>();
        
        String _sections[]=_sql.split(";");
        
        if (_sections.length > 0) {
            String _currentstament="";
            
            for (String section:_sections) {
                if (startsWithSqlStartings(section)) _statements.add(_sql + ";");
                else {
                    if (_statements.size() > 0) {
                        int _index=_statements.size()-1;
                        _currentstament=_statements.get(_index);
                        _statements.set(_index, _currentstament + section + ";");
                    } 
                }
            }
        }
        
        return _statements;
    }
    
    private boolean isSql(String statement) {
        boolean _issql=false;
                
        return _issql;
    }
    
    private boolean startsWithSqlStartings(String statement) {
        boolean _startswith=false;
        
        for (String s:_sqlstartings) {
            if (statement.toUpperCase().trim().startsWith(s)) {
                _startswith=true; break;
            }
        }
        
        return _startswith;
    }
    
}
