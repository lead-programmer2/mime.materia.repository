/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.resources;

import java.io.InputStream;
import java.net.URL;

/**
 * Library resource manager.
 * @author seph
 */
public class ResourceManager {
    
    /**
     * Creates a new instance of ResourceManager.
     */
    public ResourceManager() {
        
    }
    
    /**
     * Gets the URL of the critical image used in MessageBox class.
     * @return URL of the critical.png resource.
     */
    public URL criticalImageURL() {
        return getResource("/mime/resources/critical.png");
    }
    
     /**
     * Gets the URL of the exclamation image used in MessageBox class.
     * @return URL of the exclamation.png resource.
     */
    public URL exclamationImageURL() {
        return getResource("/mime/resources/exclamation.png");
    }
    
    /**
     * Returns the URL of the specified library resource.
     * @param name
     * @return URL of the specified resource.
     */
    public URL getResource(String name) {
        return getClass().getResource(name);
    }
    
    /**
     * Returns the stream representation of the specified library resource.
     * @param name
     * @return Stream representation of the specified resource.
     */
    public InputStream getResourceAsStream(String name) {
        return getClass().getResourceAsStream(name);
    }
    
    /**
     * Gets the URL of the information image used in MessageBox class.
     * @return URL of the information.png resource.
     */
    public URL informationImageURL() {
        return getResource("/mime/resources/information.png");
    }
    
    /**
     * Gets the URL of the embedded MySQLDump application resource.
     * @return URL of the mysql.exe resource.
     */
    public URL mySql() {
        return getResource("/mime/resources/mysql.exe");
    }
    
    /**
     * Gets the URL of the embedded MySQLDump application resource.
     * @return URL of the mysqldump.exe resource.
     */
    public URL mySqlDump() {
        return getResource("/mime/resources/mysqldump.exe");
    }
    
    /**
     * Gets the URL of the open file image used in OpenFileDialog class.
     * @return URL of the openfiledialog.png resource.
     */
    public URL openFileImageURL() {
        return getResource("/mime/resources/openfiledialog.png");
    }
    
    /**
     * Gets the URL of the open file image used in FolderBrowserDialog class.
     * @return URL of the folderbrowserdialog.png resource.
     */
    public URL openFolderImageURL() {
        return getResource("/mime/resources/folderbrowserdialog.png");
    }
    
     /**
     * Gets the URL of the question image used in MessageBox class.
     * @return URL of the question.png resource.
     */
    public URL questionImageURL() {
        return getResource("/mime/resources/question.png");
    }
    
     /**
     * Gets the URL of the save file image used in SaveFileDialog class.
     * @return URL of the savefiledialog.png resource.
     */
    public URL saveFileImageURL() {
        return getResource("/mime/resources/savefiledialog.png");
    }
    
    /**
     * Gets the URL of the embedded 7z archiving tool application resource.
     * @return 
     */
    public URL sevenZip() {
        return getResource("/mime/resources/7z.exe");
    }
    
}
