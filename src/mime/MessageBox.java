/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Mimics VB.Net System.Windows.Forms.MessageBox class.
 * @author seph
 */
public final class MessageBox {
    
    /**
     * Abort option
     */
    public static final String abort="Abort";
    /**
     * Cancel option
     */
    public static final String cancel="Cancel";
    /**
     * Ignore option
     */
    public static final String ignore="Ignore";
    /**
     * No option
     */
    public static final String no="No";
    /**
     * OK option
     */
    public static final String ok="OK";
    /**
     * Retry option
     */
    public static final String retry="Retry";
    /**
     * Yes option
     */
    public static final String yes="Yes";
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param message Dialog display message
     * @return Dialog button selected.
     */
    public static DialogResult show(String message) {
        return show(message, "Notification");
    }
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param message Dialog display message
     * @param title Dialog title
     * @return Dialog button selected.
     */
    public static DialogResult show(String message, String title) {
        return show(message, title, MessageBoxButton.OKOnly);
    }
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param message Dialog display message
     * @param title Dialog title
     * @param buttons Dialog message selection buttons
     * @return Dialog button selected.
     */
    public static DialogResult show(String message, String title, MessageBoxButton buttons) {
        return show(message, title, buttons, MessageBoxIcon.Default);
    }
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param message Dialog display message
     * @param title Dialog title
     * @param buttons Dialog message selection buttons
     * @param icon Dialog message icon
     * @return Dialog button selected.
     */
    public static DialogResult show(String message, String title, MessageBoxButton buttons, MessageBoxIcon icon) {
        return show(message, title, buttons, icon, MessageBoxDefaultButton.DefaultButton1);
    }
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param message Dialog display message
     * @param title Dialog title
     * @param buttons Dialog message selection buttons
     * @param icon Dialog message icon
     * @param defaultbutton Default focused button
     * @return Dialog button selected.
     */
    public static DialogResult show(String message, String title, MessageBoxButton buttons, MessageBoxIcon icon, MessageBoxDefaultButton defaultbutton) {
       return show(null, message, title, buttons, icon, defaultbutton);   
    }
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param owner Owner component
     * @param message Dialog display message
     * @return Dialog button selected.
     */
    public static DialogResult show(Component owner, String message) {
        return show(owner, message, "Notification");
    }
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param owner Owner component
     * @param message Dialog display message
     * @param title Dialog title
     * @return Dialog button selected.
     */
    public static DialogResult show(Component owner, String message, String title) {
        return show(owner, message, title, MessageBoxButton.OKOnly);
    }
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param owner Owner component
     * @param message Dialog display message
     * @param title Dialog title
     * @param buttons Dialog message selection buttons
     * @return Dialog button selected.
     */
    public static DialogResult show(Component owner, String message, String title, MessageBoxButton buttons) {
        return show(owner, message, title, buttons, MessageBoxIcon.Default);
    }
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param owner Owner component
     * @param message Dialog display message
     * @param title Dialog title
     * @param buttons Dialog message selection buttons
     * @param icon Dialog message icon
     * @return Dialog button selected.
     */
    public static DialogResult show(Component owner, String message, String title, MessageBoxButton buttons, MessageBoxIcon icon) {
        return show(owner, message, title, buttons, icon, MessageBoxDefaultButton.DefaultButton1);
    }
    
    /**
     * Shows a modal message box dialog in the current user screen.
     * @param owner Owner component
     * @param message Dialog display message
     * @param title Dialog title
     * @param buttons Dialog message selection buttons
     * @param icon Dialog message icon
     * @param defaultbutton Default focused button
     * @return Dialog button selected.
     */
    public static DialogResult show(Component owner, String message, String title, MessageBoxButton buttons, MessageBoxIcon icon, MessageBoxDefaultButton defaultbutton) {
        DialogResult _dialogresult=DialogResult.None;
        int _result=JOptionPane.CLOSED_OPTION; ImageIcon _icon=null;
        int _option=JOptionPane.DEFAULT_OPTION;
        int _messagetype=JOptionPane.PLAIN_MESSAGE;
        Object _options[]=null;
        Object _defaultvalue=JOptionPane.OK_OPTION;
        
        switch (icon) {
            case Critical : 
                _messagetype=JOptionPane.ERROR_MESSAGE;
                _icon=new ImageIcon(Mime.resources.criticalImageURL()); break;
            case Exclamation:
                _messagetype=JOptionPane.WARNING_MESSAGE;
                _icon=new ImageIcon(Mime.resources.exclamationImageURL()); break;
            case Question:
                _messagetype=JOptionPane.QUESTION_MESSAGE;
                _icon=new ImageIcon(Mime.resources.questionImageURL()); break;
            case Information:
                _messagetype=JOptionPane.INFORMATION_MESSAGE;
                _icon=new ImageIcon(Mime.resources.informationImageURL()); break;  
            default: break;
        }
        
        switch (buttons) {
            case YesNo:
                _options=new Object[]{yes, no};
                _option=JOptionPane.YES_NO_OPTION;
                if (buttons==MessageBoxButton.YesNoCancel) _option=JOptionPane.YES_NO_CANCEL_OPTION;
                break;
                
            case YesNoCancel:
                _options=new Object[]{yes, no, cancel};
                _option=JOptionPane.YES_NO_CANCEL_OPTION;
                break;
                
            case AbortRetryIgnore:
                _options=new Object[]{abort, retry, ignore};
                _option=JOptionPane.YES_NO_CANCEL_OPTION;
                break;
                
            case RetryCancel:
                _options=new Object[]{retry, cancel};
                _option=JOptionPane.OK_CANCEL_OPTION;
                break;
                
            case OKCancel:
                _options=new Object[]{ok, cancel};
                _option=JOptionPane.OK_CANCEL_OPTION;
                break;
                    
            default:
                _options=new Object[]{ok};
                _option=JOptionPane.DEFAULT_OPTION;
                break;
        }
        
    
        switch (defaultbutton) {
            case DefaultButton1:
                switch (buttons) {
                    case YesNo:
                    case YesNoCancel:
                        _defaultvalue=yes; break;
                    case AbortRetryIgnore:
                        _defaultvalue=abort; break;
                    case RetryCancel:
                        _defaultvalue=retry; break;
                    default:
                        _defaultvalue=ok; break;
                }
                break;
                
            case DefaultButton2:
                 switch (buttons) {
                    case YesNo:
                    case YesNoCancel:
                        _defaultvalue=no; break;
                    case AbortRetryIgnore:
                        _defaultvalue=retry; break;
                    case RetryCancel:
                        _defaultvalue=cancel; break;
                    case OKCancel:
                        _defaultvalue=cancel; break;
                    default:
                        _defaultvalue=ok; break;
                }
                break;
                
            case DefaultButton3:
                switch (buttons) {
                    case YesNo:
                        _defaultvalue=no; break;
                    case YesNoCancel:
                        _defaultvalue=cancel; break;
                    case AbortRetryIgnore:
                        _defaultvalue=ignore; break;
                    case RetryCancel:
                        _defaultvalue=cancel; break;
                    case OKCancel:
                        _defaultvalue=cancel; break;
                    default:
                        _defaultvalue=ok; break;
                }
                break;
                
            default: break;
        }
        
       _result=JOptionPane.showOptionDialog(owner, message, title, _option, _messagetype, _icon, _options, _defaultvalue);
       
       if (_result!=JOptionPane.CLOSED_OPTION) {
           switch (_result) {
               case 2:
                   switch (buttons) {
                       case AbortRetryIgnore:
                           _dialogresult= DialogResult.Ignore; break;
                       case YesNoCancel:
                           _dialogresult= DialogResult.Cancel; break;
                       default: break;
                   }
                   break;
                   
               case 1:
                   switch (buttons) {
                       case AbortRetryIgnore:
                           _dialogresult=DialogResult.Retry; break;
                       case OKCancel: 
                       case RetryCancel:
                           _dialogresult= DialogResult.Cancel; break;
                       case YesNo:
                       case YesNoCancel:
                           _dialogresult=DialogResult.No; break;
                       default: break;
                  
                   }
                   break;
                   
               case 0:
                   switch (buttons) {
                       case AbortRetryIgnore:
                           _dialogresult=DialogResult.Abort; break;
                       case OKCancel:
                       case OKOnly:
                           _dialogresult=DialogResult.OK; break;
                       case RetryCancel:
                           _dialogresult=DialogResult.Retry; break;
                       case YesNo:
                       case YesNoCancel:
                           _dialogresult=DialogResult.Yes; break;
                       default: break;
                   }
                   break;
                   
              default: break;
           }
       }
       
        return _dialogresult;
    }
    
    
}
