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
    
    /**
     * Executes or opens the file specified.
     * @param filename File path to be opened.
     * @return Process associated to the execution of the specified file, otherwise null if something went wrong.
     */
    public static java.lang.Process start(String filename) {
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
                    ex2.printStackTrace();
                }
                finally {
                    p=null; System.gc();
                }
            }
            ex.printStackTrace();
        }
        
        return p;
    }
        
}
