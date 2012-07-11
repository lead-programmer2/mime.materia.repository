/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * Mimics VB.Net SpecialDirectories class.
 * @author seph
 */
public final class SpecialDirectories {
    
      /**
       * All Users > Desktop
       */
      public static final String ALLUSERSDESKTOP_DIRECTORY    = "AllUsersDesktop";
      /**
       * All Users > Start Menu
       */
      public static final String ALLUSERSSTARTMENU_DIRECTORY  = "AllUsersStartMenu";
      /**
       * All Users > Programs
       */
      public static final String ALLUSERSPROGRAMS_DIRECTORY   = "AllUsersPrograms";  
      /**
       * All Users > Startup
       */
      public static final String ALLUSERSSTARTUP_DIRECTORY    = "AllUsersStartup";  
      /**
       * Current User > Desktop
       */
      public static final String DESKTOP_DIRECTORY            = "Desktop";  
      /**
       * Current User > Favorites
       */
      public static final String FAVORITES_DIRECTORY          = "Favorites";  
      /**
       * Current User > My Documents
       */
      public static final String MYDOCUMENT_DIRECTORY         = "MyDocuments";  
      /**
       * Current User > Programs
       */
      public static final String PROGRAMS_DIRECTORY           = "Programs";
      /**
       * Current User > Recent
       */
      public static final String RECENT_DIRECTORY             = "Recent";  
      /**
       * Current User > Send To
       */
      public static final String SENDTO_DIRECTORY             = "SendTo";  
      /**
       * Current User > Start Menu
       */
      public static final String STARTMENU_DIRECTORY          = "StartMenu";  
      /**
       * Current User > Startup
       */
      public static final String STARTUP_DIRECTORY            = "Startup";
    
      /**
       * Gets the Windows special directory : All Users\Desktop
       * @return All Users\Desktop directory path
       */
      public static String allUsersDesktop() {
          return getSpecialDirectory(ALLUSERSDESKTOP_DIRECTORY);
      }
      
      /**
       * Gets the Windows special directory : All Users\Programs Menu
       * @return All Users\Programs directory path
       */
      public static String allUsersPrograms() {
          return getSpecialDirectory(ALLUSERSPROGRAMS_DIRECTORY);
      }
      
       /**
       * Gets the Windows special directory : All Users\Start Menu
       * @return All Users\Start Menu directory path
       */
      public static String allUsersStartMenu() {
          return getSpecialDirectory(ALLUSERSSTARTMENU_DIRECTORY);
      }
      
       /**
       * Gets the Windows special directory : All Users\Startup
       * @return All Users\Startup directory path
       */
      public static String allUsersStartup() {
          return getSpecialDirectory(ALLUSERSSTARTUP_DIRECTORY);
      }
      
      /**
       * Gets the Windows special directory : Current User\Desktop
       * @return Current User\Desktop directory path
       */
      public static String desktop() {
          return getSpecialDirectory(DESKTOP_DIRECTORY);
      }
      
       /**
       * Gets the Windows special directory : Current User\Favorites
       * @return Current User\Favorites directory path
       */
      public static String favorites() {
          return getSpecialDirectory(FAVORITES_DIRECTORY);
      }
      
      public static String getSpecialDirectory(String folder) {
          String result = "";
    
          try {
              File file = File.createTempFile("dirgetter",".vbs");        
              file.deleteOnExit();
              FileWriter fw = new java.io.FileWriter(file);

        
              String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
                           + "wscript.echo WshShell.SpecialFolders(\"" + folder + "\")\n"            
                           + "Set WSHShell = Nothing\n";

              fw.write(vbs);
              fw.close();
      
              java.lang.Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
              BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
              
              result = input.readLine();
              input.close();
          }
          catch(Exception ex){
              ex.printStackTrace();
          }
    
          return result;
      }
      
      /**
       * Gets the Windows special directory : Current User\My Documents
       * @return Current User\My Documents directory path
       */
      public static String myDocuments() {
          return getSpecialDirectory(MYDOCUMENT_DIRECTORY);
      }
      
      /**
       * Gets the Windows special directory : Current User\Programs
       * @return Current User\Programs directory path
       */
      public static String programs() {
          return getSpecialDirectory(PROGRAMS_DIRECTORY);
      }
      
       /**
       * Gets the Windows special directory : Current User\Recent
       * @return Current User\Recent directory path
       */
      public static String recent() {
          return getSpecialDirectory(RECENT_DIRECTORY);
      }
      
       /**
       * Gets the Windows special directory : Current User\Send To
       * @return Current User\Send To directory path
       */
      public static String sendTo() {
          return getSpecialDirectory(SENDTO_DIRECTORY);
      }
      
      /**
       * Gets the Windows special directory : Current User\Start Menu
       * @return Current User\Start Menu directory path
       */
      public static String startMenu() {
          return getSpecialDirectory(STARTMENU_DIRECTORY);
      }
      
      /**
       * Gets the Windows special directory : Current User\Startup
       * @return Current User\Startup directory path
       */
      public static String startup() {
          return getSpecialDirectory(STARTUP_DIRECTORY);
      }
      
}
