/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.net.InetAddress;

/**
 * Mimics VB.Net My.Computer class.
 * @author seph
 */
public final class Computer {
    
        /**
     * Mimics VB.Net My.Computer.Name class property and returns the current workstation host name.
     * @return Current workstation host name.
     */
    public static String name() {
        String _computername="";
        InetAddress _address=null;
        
        try {
            _address=InetAddress.getLocalHost();
            _computername=_address.getHostName();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
       
        if (_address!=null) {
            _address=null; System.gc();
        }
        
        return _computername;
    }
    
   /**
    * Gets the current workstation IP address. 
    * @return Current workstation IP address.
    */
    public static String ipAddress() {
        String _ipaddress="";
        InetAddress _address=null;
                
        try {
            _address=InetAddress.getLocalHost();
            _ipaddress=_address.getHostAddress();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        if (_address!=null) {
            _address=null; System.gc();
        }
        
        return _ipaddress;
    }
 
    /**
     * Gets the current workstation OS name.
     * @return Workstation OS name.
     */
    public static String osName() {
        Object _osname=null;
        
        try {
            _osname=System.getProperty("os.name");
        }
        catch (Exception ex) {
            _osname=null;
            ex.printStackTrace();
        }
        
        String _name="";
        if (_osname!=null) _name=_osname.toString();
        
        return _name;
    }
    
}
