/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import mime.io.File;
import mime.io.Path;

/**
 * Mimics VB.Net System,Windows.Forms.SaveFileDialog class.
 * @author seph
 */
public class SaveFileDialog {
    
    /**
     * Creates a new instance of SaveFileDialog.
     */
    public SaveFileDialog() {
        
    }
    
    private boolean _checkexistingfile=true;
    private boolean  _checkexistingdirectory=true;
    private String _defaultextension="";
    private String _filename="";
    private String _filter="All Files (*.*)|*.*";
    private String _initialdirectory="";
    private boolean  _isdisposed=false;
    private boolean  _multiselect=false;
    private String _title="";
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current collection already called this method, check isDispose
     * method.
     */
    public void dispose() {
        _isdisposed=true;
        try {
            finalize();
            SaveFileDialog _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
        
    
    /**
     * Gets whether the dialog will validate if the selected file's directory is existing or not.
     * @return Returns true if dialog is set to validate selected file's directory, otherwise false.
     */
    public boolean getCheckExistingDirectory() {
        return _checkexistingdirectory;
    }
       
    /**
     * Gets whether the dialog will validate if the selected file is existing or not.
     * @return Returns true if dialog is set to validate selected file, otherwise false.
     */
    public boolean  getCheckExistingFile() {
        return _checkexistingfile;
    }
    
    /**
     * Gets the currently assigned default file extension name.
     * @return Default assigned dialog file extension.
     */
    public String getDefaultExtension() {
        return _defaultextension;
    }
    
   /**
    * Gets the currently selected file path.
    * @return Currently selected file path.
    */
    public String getFilename() {
        return _filename;
    }
    
    /**
     * Gets the currently assigned file filters.
     * @return Assigned dialog file filter.
     */
    public String getFilter() {
        return _filter;
    }
    
    /**
     * Gets the currently assigned default directory when the dialog shows into the user screen.
     * @return Default directory path when the dialog shows into the user screen.
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
     * Sets whether the dialog will validate if the selected file's directory exists or not.
     * @param check 
     */
    public void setCheckExistingDirectory(boolean check) {
        _checkexistingdirectory=check;
    }
    
    /**
     * Sets whether the dialog will validate if the selected file exists or not.
     * @param check 
     */
    public void setCheckExistingFile(boolean check) {
        _checkexistingfile=check;
    }
    
    /**
     * Sets the dialog's default file extension name
     * @param extension 
     */
    public void setDefaultExtension(String extension) {
        _defaultextension=extension;
    }
    
    /**
     * Sets the dialog's default selected file.
     * @param filename 
     */
    public void setFilename(String filename) {
         _filename=filename;
    }
    
    /**
     * Sets the dialog's associated file filters.
     * @param filter 
     */
    public void setFilter(String filter) {
        _filter=filter;
    }
    
    /**
     * Sets the dialog's assigned default directory when shown in the user screen.
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
    
    public DialogResult showDialog() {
        DialogResult _result=DialogResult.None;
        
        JFileChooser _dialog=new JFileChooser(_initialdirectory);
        _dialog.setDialogTitle(_title);
        
        if (_filter.trim()=="") _filter="All Files (*.*)|*.*";
        String[] _filtersections=_filter.split("\\|");
        String _currentname="";
        String _currentexts[];
       
        int _len=_filtersections.length;
       
        for (int i=0; i<=_len-1; i++) {
            if (i%2==0) _currentname=_filtersections[i];
            else {
                String[] _exts=_filtersections[i].trim().split(";");
                _currentexts=new String[_exts.length];
                for (int ctr=0; ctr<= _exts.length-1; ctr++) {
                    _currentexts[ctr]=_exts[ctr];
                }
                System.out.println(_currentname);
                FileNameExtensionFilter _filter=new FileNameExtensionFilter(_currentname, _currentexts);
                _dialog.addChoosableFileFilter(_filter);
                _dialog.setFileFilter(_filter);
            }
        } 
        
        if (File.exists(_filename)) _dialog.setSelectedFile(new java.io.File(_filename));
        _dialog.setMultiSelectionEnabled(_multiselect);
        
        int _dresult=_dialog.showSaveDialog(null);
        switch (_dresult) {
            case JFileChooser.APPROVE_OPTION:
                _result=DialogResult.OK; 
                _filename=_dialog.getSelectedFile().getPath();  
                try {
                    if (Path.getExtension(_filename).equals("")) {
                       FileNameExtensionFilter _currentfilter=(FileNameExtensionFilter) _dialog.getFileFilter();
                       String[] exts=_currentfilter.getExtensions();
                       if (exts.length > 0 ) _filename+=exts[0].replace("*", "");
                       else {
                           if (!_defaultextension.trim().equals("")) _filename+="."+_defaultextension;
                       }
                    }
                }
                catch (Exception ex) { 
                    FileNameExtensionFilter _currentfilter=(FileNameExtensionFilter) _dialog.getFileFilter();   
                    String[] exts=_currentfilter.getExtensions();   
                    if (exts.length > 0 ) _filename+=exts[0].replace("*", "");
                    else {
                           if (!_defaultextension.trim().equals("")) _filename+="."+_defaultextension;
                    }
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                _result=DialogResult.Cancel; break;
            case JFileChooser.ERROR_OPTION:
                _result=DialogResult.Abort; break;
            default: break;
        }
        
        return _result;
    }
        
}
