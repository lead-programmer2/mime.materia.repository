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
    private String _title="";
    
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
    
}
