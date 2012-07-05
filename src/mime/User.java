/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

/**
 * Mimics VB.Net My.User class.
 * @author seph
 */
public class User {
    
     
    /**
     * Mimics VB.Net My.User.Name class property and returns the currently logged workstation user name.
     * @return Logged workstation user name.
     */
    public static String name() {
        String _username="";
        Object _name=null;
        
        try {
            _name=System.getProperty("user.name"); 
        }
        catch (Exception ex) {
            _name=null;
        }
        
        if (_name!=null) _username=_name.toString();
        return _username;
    }
    
}
