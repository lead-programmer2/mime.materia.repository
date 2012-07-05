/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime;

/**
 * Mimics VB.Net System.Diagnostics.Process class.
 * @author seph
 */
public class Process {
    
    public static void start(String filename) {
        java.lang.Process p=null;
        
        try {
            if (Computer.osName().trim().toLowerCase().contains("mac")) p = Runtime.getRuntime().exec(new String[]{"open", filename});
            else p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/C", filename});
        }
        catch (Exception ex) {
            if (p!=null) {
                try {
                    p.destroy();
                }
                catch (Exception ex2) {
                }
                finally {
                    p=null; System.gc();
                }
            }
            ex.printStackTrace();
        }
    }
        
}
