/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.util.ArrayList;
import mime.CollectionExistException;

/**
 * Mimics VB.Net MySql.Data.MySqlClient.MySqlParameterCollection class.
 * @author seph
 */
public class MySqlParameterCollection extends ArrayList<MySqlParameter> {
    
    private MySqlCommand _owner=null;
    
    /**
     * Creates a new instance of MySqlParameterCollection.
     * @param owner Owner MySqlCommand object for the current collection.
     */
    public MySqlParameterCollection(MySqlCommand owner) {
        if (owner.parameters()!=null) throw new CollectionExistException("Parameter collection already exists in the specified owner.");
        _owner=owner;
    }
    
    /**
     * Adds a new parameter into the collection.
     * @return Newly added MySqlParameter object.
     */
    public MySqlParameter add() {
        return addParameter(null);
    }
    
    /**
     * Adds a new parameter into the collection.
     * @param  value Value of the parameter.
     * @return Newly added MySqlParameter object.
     */
    public MySqlParameter addParameter(Object value) {
        MySqlParameter _parameter=new MySqlParameter(this, value);
        if (super.add(_parameter)) return super.get(super.size()-1);
        else return null;
    }
    
    @Override
    public boolean add(MySqlParameter parameter) {
        boolean _added=false;
        if (super.contains(parameter)) throw new MySqlParameterException("Parameter can't be added multiple times.");
        if (!parameter.command().parameters().equals(this)) throw new MySqlParameterException("Parameter was already a member of another collection.");        
        _added=super.add(parameter);
        return _added;
    }
    
    private MySqlParameter getParameterByValue(Object value) {
        MySqlParameter _parameter=null;
        
        for (MySqlParameter param:this) {
            if (param.getValue().equals(value)) {
                _parameter=param; break;
            }
        }
        
        return _parameter;
    }
    
    /**
     * Gets the owner database command for the current collection.
     * @return Owner MySqlCommand object.
     */
    public MySqlCommand command() {
        return _owner;
    }
    
    /**
     * Gets the database connection owning the current collection.
     * @return Owner database connection.
     */
    public MySqlConnection connection() {
        return _owner.getConnection();
    }
    
    /**
     * Returns whether a parameter with the specified value already exists within the collection or not.
     * @param value Value to find.
     * @return True if there is matches found, otherwise false.
     */
    public boolean containsValue(Object value) {
        return (boolean) (getParameterByValue(value)!=null);
    }
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current collection already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            for (MySqlParameter p:this) {
                p=null;
            }
            
            super.finalize();
            
            MySqlParameterCollection _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    private boolean _isdisposed=false;
    
    /**
     * Returns whether the current collection has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean  isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Removes a parameter with the specified value from the collection.
     * @param value Parameter value.
     */
    public void removeValue(Object value) {
        MySqlParameter _parameter=getParameterByValue(value);
        if (_parameter!=null) super.remove(_parameter);
    }
    
}
