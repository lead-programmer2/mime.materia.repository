/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import mime.io.Directory;

/**
 * Mimics VB.Net System.Windows.Forms.FolderBrowserDialog class.
 * @author seph
 */
public class FolderBrowserDialog {
    
    /**
     * Creates a new instance of FolderBrowserDialog.
     */
    public FolderBrowserDialog() {
        
    }
    
    private String _initialdirectory="";        
    private boolean  _isdisposed=false;
    private boolean  _multiselect=false;
    private String _title="";
    private String _selecteddirectory="";
    private String[] _selecteddirectories;
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current class already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            finalize();
            FolderBrowserDialog _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Gets the assigned initial directory when the dialog is shown in the user screen.
     * @return Directory assigned to the dialog that is set when the dialog is shown in the user screen.
     */
    public String getInitialDirectory() {
        return _initialdirectory;
    }
      
    /**
     * Gets whether the dialog accepts multi-selection or not.
     * @return True if the dialog accepts multi-selection, otherwise false.
     */
    public boolean getMultiSelect() {
        return _multiselect;
    }
    
    /**
     * Gets the title text associated with the dialog.
     * @return Title of the dialog
     */
    public String getTitle() {
        return _title;
    }
    
   /**
    * Returns whether the current class already invokes its dispose method and has been collected by the garbage collection or not.
    * @return True if class already called its dispose method, otherwise false.
    */
    public boolean isDisposed() {
        return _isdisposed;
    }
    
    /**
     * Gets the current selected directory in the dialog.
     * @return Selected directory path.
     */
    public String selectedDirectory() {
        return _selecteddirectory;
    }
        
    /**
     * Gets the current selected directories in the dialog.
     * @return Array of selected directory path.
     */
    public String[] selectedDirectories() {
        return _selecteddirectories;
    }
    
    /**
     * Sets the initial directory displayed when the dialog is shown in the user screen.
     * @param directory 
     */
    public void setInitialDirectory(String directory) {
        _initialdirectory=directory;
    }
    
     /**
     * Sets whether the dialog accepts multi-selection or not.
     * @param multiselect 
     */
    public void setMultiSelect(boolean multiselect) {
        _multiselect=multiselect;
    }
    
    /**
     * Sets the dialog's title text when displayed in the user screen.
     * @param title 
     */
    public void setTitle(String title) {
        _title=title;
    }
    
    /**
     * Displays a Folder browser common dialog in the user screen.
     * @return Dialog result button that the user choose. 
     */
    public DialogResult showDialog() {
        DialogResult _result=DialogResult.None;
        
        JFileChooser _dialog=new JFileChooser(_initialdirectory);
        _dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        _dialog.setDialogTitle(_title);
        _dialog.setMultiSelectionEnabled(_multiselect);
        _dialog.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter _filter=new FileNameExtensionFilter("Directories", "*");
        _dialog.addChoosableFileFilter(_filter);
        _dialog.setApproveButtonToolTipText("Select specified path");

        JFrame _form=new JFrame();
        ImageIcon _icon=new ImageIcon(Mime.resources.openFolderImageURL());
        _form.setIconImage(_icon.getImage());
        Object _dlgresult=_dialog.showOpenDialog(_form);
        if (_dlgresult==null) _dlgresult=-1; 
        int _dialogresult=Converter.toInt(_dlgresult);
        
        switch (_dialogresult) {
            case JFileChooser.APPROVE_OPTION:
                _selecteddirectory=_dialog.getSelectedFile().getPath();
                while (!Directory.exists(_selecteddirectory)) {
                    MessageBox.show("Selected directory does not exists!", "Directory Unavailable", MessageBoxButton.OKOnly, MessageBoxIcon.Exclamation);
                    _dlgresult=_dialog.showOpenDialog(_form);    
                    if (_dlgresult==null) _dlgresult=-1; 
                    _dialogresult=Converter.toInt(_dlgresult);     
                    switch (_dialogresult) {     
                        case JFileChooser.APPROVE_OPTION:      
                            _selecteddirectory=_dialog.getSelectedFile().getPath(); break;     
                        case JFileChooser.CANCEL_OPTION:    
                            _dialog=null; _form.dispose();    
                            _result=DialogResult.Cancel; return _result;     
                        default:     
                            _dialog=null; _form.dispose();    
                            _result=DialogResult.None; return _result;      
                    } 
                }
                
                File[] _files=_dialog.getSelectedFiles();
                _selecteddirectories=new String[_files.length];
                for (int i=0; i<=_files.length-1; i++) {
                    _selecteddirectories[i]=_files[i].getPath();
                }
                _result=DialogResult.OK; break;
                
            case JFileChooser.CANCEL_OPTION:
                _result=DialogResult.Cancel; break;
            default: break;
        }
        
        _dialog=null; _form.dispose();
        return _result;
    }
        
    
}
