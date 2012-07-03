/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

/**
 * Mimics VB.Net MySql.Data.MySqlClient.MySqlParameter class.
 * @author seph
 */
public class MySqlParameter {
   
    private int _index=-1;
    private Object _value=null;
    private MySqlParameterCollection _owner;
    
    /**
     * Creates a new instance of MySqlParameter.
     * @param owner Owner collection
     */
    public MySqlParameter(MySqlParameterCollection owner) {
        this(owner, null);
    }
    
    /**
     * Creates a new instance of MySqlParameter.
     * @param owner Owner collection
     * @param value Parameter value.
     */
    public MySqlParameter(MySqlParameterCollection owner, Object value) {
        _owner=owner; _index=owner.size(); _value=value;
    }
    
    /**
     * Gets the owner database command for the current parameter.
     * @return Owner MySqlCommand object.
     */
    public MySqlCommand command() {
        return _owner.command();
    }
    
    /**
     * Gets the current value of the parameter.
     * @return Parameter value
     */
    public Object getValue() {
        return _value;
    }
    
    /**
     * Returns the index position of the current parameter.
     * @return 
     */
    public int index() {
        return _index;
    }
    
    /**
     * Sets the value stored in the current parameter.
     * @param value Parameter value.
     */
    public void setValue(Object value) {
        _value=value;
    }
}
