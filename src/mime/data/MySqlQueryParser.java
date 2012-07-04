/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL statement parsing class. This distinguish number of SQL statement iterated in a string value.
 * @author seph
 */
public class MySqlQueryParser {
    
    private String _sql ="";
    private String _sqlstartings[]=new String[]{"SELECT", "INSERT", "UPDATE", "DELETE",
                                                "ANALYZE", "OPTIMIZE", "CHECK","REPAIR",
                                                "TRUNCATE", "SET", "ALTER", "DROP", "CREATE", "CALL"};
    private ArrayList<String> _regexes=new ArrayList<String>();
    
    /**
     * Creates a new instance of MySqlQueryParser.
     * @param sql SQL command statement to evaluate
     */
    public MySqlQueryParser(String sql) {
        _sql=sql; initRegExes();
    }
    
    private ArrayList<String> getStatements() {
        ArrayList<String> _statements=new ArrayList<String>();
        
        String _sections[]=_sql.split(";");
        
        if (_sections.length > 0) {
            String _currentstament="";
            
            for (String section:_sections) {
                if (isSql(section.trim())) {
                    if (_statements.size() > 0) {
                       int _index=_statements.size()-1;
                       _currentstament=_statements.get(_index);
                       if (withUnclosedBlock(_currentstament)) _statements.set(_index, _currentstament + section + ";");
                       else _statements.add(section + ";");
                    }
                    else _statements.add(section + ";");
                }
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
    
    private void initRegExes() {
        _regexes.add("(S|s)(E|e)(L|l)(E|e)(C|c)(T|t)[\\n\\r\\ta-zA-Z0-9~`!@#\\$%\\^&\\*\\(\\)-_\\+=\\{\\}\\[\\]|\\\\:;\"'<>,\\.\\?/\\| ]+(F|f)(R|r)(O|o)(M|m)[\\n\\r\\ta-zA-Z0-9_`\\[\\] ]+");
        _regexes.add("(U|u)(P|p)(D|d)(A|a)(T|t)(E|e)[\\n\\r\\ta-zA-Z0-9_`\\[\\] ]+(S|s)(E|e)(T|t)[\\n\\r\\ta-zA-Z0-9~`!@#\\$%\\^&\\*\\(\\)-_\\+={}\\[\\]|\\\\:;\"'<>,\\.\\?/\\| ]+");
        _regexes.add("(I|i)(N|n)(S|s)(E|e)(R|r)(T|t)[\\n\\r\\t ]+(I|i)(N|n)(T|t)(O|o)[\\n\\r\\t\\(\\)a-zA-Z0-9`\\[\\] ]+(V|v)(A|a)(L|l)(U|u)(E|e)(S|s)[\\n\\r\\ta-zA-Z0-9~`!@#\\$%\\^&\\*\\(\\)-_\\+={}\\[\\]|\\\\:;\"'<>,\\.\\?/\\| ]+");
        _regexes.add("(D|d)(E|e)(L|l)(E|e)(T|t)(E|e)[\\n\\r\\t ]+(F|f)(R|r)(O|o)(M|m)[\\n\\r\\ta-zA-Z0-9~`!@#\\$%\\^&\\*\\(\\)-_\\+={}\\[\\]|\\\\:;\"'<>,\\.\\?/\\| ]+");
        _regexes.add("(D|d)(R|r)(O|o)(P|p)[\\n\\r\\t ]+[(T|a)(A|a)(B|b)(L|l)(E|e)|(C|c)(O|o)(L|l)(U|u)(M|m)(N|n)|(D|d)(A|a)(T|t)(A|a)(B|b)(A|a)(S|s)(E|e)|(F|f)(U|u)(N|n)(T|t)(I|i)(O|o)(N|n)|(P|p)(R|r)(O|o)(C|c)(E|e)(D|d)(U|u)(R|r)(E|e)|(V|v)(I|i)(E|e)(W|w)]+[\\n\\r\\t ]+[(I|i)(F|f)]+[\\n\\r\\t ]+[(E|e)(X|x)(I|i)(S|s)(T|t)]+[\\n\\r\\t ]+[\\n\\r\\ta-zA-Z0-9_`\\[\\] ]+");
        _regexes.add("(C|c)(R|r)(E|e)(A|a)(T|t)(E|e)[\\n\\r\\t ]+[(T|a)(A|a)(B|b)(L|l)(E|e)|(C|c)(O|o)(L|l)(U|u)(M|m)(N|n)|(D|d)(A|a)(T|t)(A|a)(B|b)(A|a)(S|s)(E|e)|(F|f)(U|u)(N|n)(T|t)(I|i)(O|o)(N|n)|(P|p)(R|r)(O|o)(C|c)(E|e)(D|d)(U|u)(R|r)(E|e)|(V|v)(I|i)(E|e)(W|w)]+[\\n\\r\\t ][a-zA-Z0-9_`\\[\\] ]+");
        _regexes.add("(A|a)(L|l)(T|t)(E|e)(R|r)[\\n\\r\\t ]+[(T|a)(A|a)(B|b)(L|l)(E|e)|(C|c)(O|o)(L|l)(U|u)(M|m)(N|n)|(D|d)(A|a)(T|t)(A|a)(B|b)(A|a)(S|s)(E|e)|(F|f)(U|u)(N|n)(T|t)(I|i)(O|o)(N|n)|(P|p)(R|r)(O|o)(C|c)(E|e)(D|d)(U|u)(R|r)(E|e)|(V|v)(I|i)(E|e)(W|w)]+[\\n\\r\\t ]+[a-zA-Z0-9`\\[\\]_]+");
        _regexes.add("(T|t)(R|r)(U|u)(N|n)(C|c)(A|a)(T|t)(E|e)[\\n\\r\\t ]+(T|t)(A|a)(B|b)(L|l)(E|e)[\\n\\r\\t ]+[a-zA-Z0-9`\\[\\]_]+");
        _regexes.add("(C|c)(A|a)(L|l)(L|l)[\\n\\r\\t ]+[a-zA-Z0-9`\\[\\]_]+[\\n\\r\\t \\(]+");
        _regexes.add("(S|s)(E|e)(T|t)[\\n\\r\\t (G|g)(L|l)(O|o)(B|b)(A|a)(L|l)]+[a-zA-Z0-9@`_\\[\\]]+[\\n\\r\\t =]+[a-zA-Z0-9_'\\(\\)\\*\\+-/]+;");
        _regexes.add("(A|a)(N|n)(A|a)(L|l)(Y|y)(Z|z)(E|e)[\\n\\r\\t a-zA-Z_]+(T|t)(A|a)(B|b)(L|l)(E|e)[\\n\\r\\t ]+[a-zA-Z0-9`\\[\\]_]+");
        _regexes.add("(C|c)(H|h)(E|e)(C|c)(K|k)[\\n\\r\\t a-zA-Z_]+(T|t)(A|a)(B|b)(L|l)(E|e)[\\n\\r\\t ]+[a-zA-Z0-9`\\[\\]_]+");
        _regexes.add("(O|o)(P|p)(T|t)(I|i)(M|m)(I|i)(Z|z)(E|e)[\\n\\r\\t a-zA-Z_]+(T|t)(A|a)(B|b)(L|l)(E|e)[\\n\\r\\t ]+[a-zA-Z0-9`\\[\\]_]+");
        _regexes.add("(R|r)(E|e)(P|p)(A|a)(I|i)(R|r)[\\n\\r\\t a-zA-Z_]+(T|t)(A|a)(B|b)(L|l)(E|e)[\\n\\r\\t ]+[a-zA-Z0-9`\\[\\]_]+");
    }
    
    private boolean isSql(String statement) {
        boolean _issql=false;
 
        for (String _sqlpattern:_regexes) {
            Pattern _pattern=Pattern.compile(_sqlpattern);
            Matcher _matcher=_pattern.matcher(statement);
            _issql = (_issql || _matcher.find());
            if (_issql) break;
        }
        
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

    /**
     * Gets the parsed list of SQL statements from the initialized command text.
     * @return Array of SQL statement presented in the initialized command text.
     */
    public Object[] statements() {
        ArrayList<String> _statements=getStatements();
        String sqls[]=new String[_statements.size()];
        return _statements.toArray();
    }
    
    private boolean withUnclosedBlock(String statement) {
        boolean _withunclosed=false;
        
        String _patternstring="=[\\n\\r\\t ]+'[\\n\\r\\t a-zA-Z0-9`~!@#\\$%\\^&\\*\\(\\)-_\\+=\\{\\}\\[\\]\\|\\\\:;\"'<>,\\.\\?/]+";
        Pattern _pattern=Pattern.compile(_patternstring);
        Matcher _matcher=_pattern.matcher(statement);
        String _currentmatch="";
        while (_matcher.find()) {
            _currentmatch=_matcher.group();
            if (statement.endsWith(_currentmatch) &&
                !_currentmatch.trim().endsWith("'") &&
                !withWhereClause(_currentmatch) &&
                !withGroupByClause(_currentmatch) &&
                !withOrderByClause(_currentmatch) &&
                !withLimitClause(_currentmatch)) {
                _withunclosed=true; break;
            }
        }
        
        return _withunclosed;
    }
    
    private boolean withGroupByClause(String value) {
        boolean _withgroupby=false;
        
        String _patternstring="(G|g)(R|r)(O|o)(U|u)(P|p)[\\n\\r\\t ]+(B|b)(Y|y)[\\n\\r\\t ]+";
        Pattern _pattern=Pattern.compile(_patternstring);
        Matcher _matcher=_pattern.matcher(value);
        _withgroupby=_matcher.find();
        _pattern=null; _matcher=null;
        
        return _withgroupby;
    }
    
    private boolean withLimitClause(String value) {
         boolean _withlimit=false;
        
        String _patternstring="(L|l)(I|i)(M|m)(I|i)(T|t)[\\n\\r\\t ]+[0-9]+[\\n\\r\\t ,0-9]+";
        Pattern _pattern=Pattern.compile(_patternstring);
        Matcher _matcher=_pattern.matcher(value);
        _withlimit=_matcher.find();
        _pattern=null; _matcher=null;
        
        return _withlimit;
    }
    
    private boolean  withOrderByClause(String value) {
        boolean _withorderby=false;
        
        String _patternstring="(O|o)(R|r)(D|d)(E|d)(E|r)[\\n\\r\\t ]+(B|b)(Y|y)[\\n\\r\\t ]+";
        Pattern _pattern=Pattern.compile(_patternstring);
        Matcher _matcher=_pattern.matcher(value);
        _withorderby=_matcher.find();
        _pattern=null; _matcher=null;
        
        return _withorderby;
    }
    
    private boolean withWhereClause(String value) {
        boolean _withwhere=false;
        
        String _patternstring="(W|w)(H|h)(E|e)(R|r)(E|e)[\\n\\r\\t ]+";
        Pattern _pattern=Pattern.compile(_patternstring);
        Matcher _matcher=_pattern.matcher(value);
        _withwhere=_matcher.find();
        _pattern=null; _matcher=null;
        
        return _withwhere;
    }
}
