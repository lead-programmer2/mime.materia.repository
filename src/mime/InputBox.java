/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Mimics VB.Net InputBox method.
 * @author seph
 */
public final class InputBox {
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param prompt Dialog message
     * @return User input value.
     */
    public static String show(String prompt) {
        Component _owner=null;
        return show(_owner, prompt);
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param owner Dialog owner
     * @param prompt Dialog message
     * @return User input value.
     */
    public static String show(Component owner, String prompt) {
        return show(owner, prompt, MessageBoxIcon.Default);
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param prompt Dialog owner
     * @param icon Dialog message
     * @return User input value.
     */
    public static String show(String prompt, MessageBoxIcon icon)  {
       Component _owner=null;
        return show(_owner, prompt, icon);
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param owner Dialog owner
     * @param prompt Dialog message
     * @param icon Dialog message icon
     * @return User input value.
     */
    public static String show(Component owner, String prompt, MessageBoxIcon icon)  {
        return show(owner, prompt, "Provide Information", icon);
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param prompt Dialog message
     * @param title Dialog title
     * @return User input value.
     */
    public static String show(String prompt, String title) {
        Component _owner=null;
        return show(_owner, prompt, title);
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param owner Dialog owner
     * @param prompt Dialog message
     * @param title Dialog title
     * @return User input value.
     */
    public static String show(Component owner, String prompt, String title) {
        return show(owner, prompt, title, "");
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param prompt Dialog message
     * @param title Dialog title
     * @param icon Dialog message icon
     * @return User input value.
     */
    public static String show(String prompt, String title,  MessageBoxIcon icon) {
        Component _owner=null;
        return show(_owner, prompt, title, icon);
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param owner Dialog owner
     * @param prompt Dialog message
     * @param title Dialog title
     * @param icon Dialog message icon
     * @return User input value.
     */
    public static String show(Component owner, String prompt, String title,  MessageBoxIcon icon) {
        return show(owner, prompt, title, "", icon);
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param prompt Dialog message
     * @param title Dialog title
     * @param defaultvalue Default value
     * @return User input value.
     */
    public static String show(String prompt, String title, String defaultvalue) {
        return show(null, prompt, title, defaultvalue);
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param owner Dialog owner
     * @param prompt Dialog message
     * @param title Dialog title
     * @param defaultvalue Default value
     * @return User input value.
     */
    public static String show(Component owner, String prompt, String title, String defaultvalue) {
        return show(owner, prompt, title, defaultvalue, MessageBoxIcon.Default);     
    }
     
    /**
     * Shows a modal dialog input box into the user screen.
     * @param prompt Dialog message
     * @param title Dialog title
     * @param defaultvalue Default value
     * @param icon Dialog message icon
     * @return User input value.
     */
    public static String show(String prompt, String title, String defaultvalue, MessageBoxIcon icon) { 
        return show(null, prompt, title, defaultvalue, icon);
    }
    
    /**
     * Shows a modal dialog input box into the user screen.
     * @param owner Dialog owner
     * @param prompt Dialog message
     * @param title Dialog title
     * @param defaultvalue Default value
     * @param icon Dialog message icon
     * @return User input value.
     */
    public static String show(Component owner, String prompt, String title, String defaultvalue, MessageBoxIcon icon) {
        String _result="";
        ImageIcon _icon=null;
        int _messagetype=JOptionPane.PLAIN_MESSAGE;
        
        switch(icon) {
            case Critical: 
                _messagetype=JOptionPane.ERROR_MESSAGE;
                _icon=new ImageIcon(Mime.resources.criticalImageURL()); break;
            case Exclamation:
                _messagetype=JOptionPane.WARNING_MESSAGE;
                _icon=new ImageIcon(Mime.resources.exclamationImageURL()); break;
            case Information:
                _messagetype=JOptionPane.INFORMATION_MESSAGE;
                _icon=new ImageIcon(Mime.resources.informationImageURL()); break;
            case Question:
                _messagetype=JOptionPane.QUESTION_MESSAGE;
                _icon=new ImageIcon(Mime.resources.questionImageURL()); break;
            default: break;
        }
        
        Object _input=JOptionPane.showInputDialog(owner, prompt, title, _messagetype, _icon, null, defaultvalue);
        if (_input!=null) _result=_input.toString(); 
       
        return _result;
    }
    
}
