/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

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
     * Sets the dialog's title text when displayed in the user screen.
     * @param title 
     */
    public void setTitle(String title) {
        _title=title;
    }
    
}
