/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.io;

import java.io.InputStream;
import java.io.StringWriter;
import mime.Application;

/**
 * File / directory compression and decompression class.
 * @author seph
 */
public class Archiver {
    
    /**
     * Creates a new instance of Archiver.
     */
    public Archiver() {
        
    }
    
    /**
     * Performs file compression into a certain file or directory and have it inside the specified destination file path.
     * @param pathforarchive Directory path or filename to compress
     * @return File information of the output file.
     */
    public java.io.File archive(String pathforarchive) {
        return archive(new java.io.File(pathforarchive));
    }
    
    /**
     * Performs file compression into a certain file or directory and have it inside the specified destination file path.
     * @param forarchive Directory or file to compress
     * @return File information of the output file.
     */
    public java.io.File archive(java.io.File forarchive) {
        String _path=forarchive.getPath();
        if (_path.startsWith("/")) _path=forarchive.getPath().substring(1);
        String _name=Path.getFileNameWithoutExtension(_path);
        java.io.File _file=null;
        
        if (!_name.equals("")) {
            String _currentdirectory=forarchive.getParent();
            if (_currentdirectory.startsWith("/")) _currentdirectory=forarchive.getParent().substring(1);
            String _destination=_currentdirectory + "\\" + _name + ".7z";
            _file = archive(forarchive, _destination);
        }
        
        return _file;
    }
    
    /**
     * Performs file compression into a certain file or directory and have it inside the specified destination file path.
     * @param pathforarchive Directory path or filename to compress
     * @param destination Output file destination
     * @return File information of the output file.
     */
    public java.io.File archive(String pathforarchive, String destination) {
        return archive(new java.io.File(pathforarchive), destination);
    }
    
    /**
     * Performs file compression into a certain file or directory and have it inside the specified destination file path.
     * @param forarchive Directory or file to compress
     * @param destination Output file destination.
     * @return File information of the output file.
     */
    public java.io.File archive(java.io.File forarchive, String destination) {
        java.io.File _file=null;
        
        String _batchfilename=Application.startUpPath() + "\\archiver.bat";
        String _zip=mime.Mime.resources.sevenZip().getPath();
        if (_zip.startsWith("/")) _zip=mime.Mime.resources.sevenZip().getPath().substring(1);
        
        String _path=forarchive.getPath();
        if (_path.startsWith("/")) _path=forarchive.getPath().substring(1);
        
        String _contents="\"" + _zip + "\" a \"" + destination + "\" " + _path + "\"";
        java.io.File _batchfile=null;
        
        StreamWriter _sw=new StreamWriter(_batchfilename);
        try {
            _sw.write(_contents); _batchfile=new java.io.File(_batchfilename);
        }
        catch (Exception ex) {
            _batchfile=null; ex.printStackTrace();
        }
        finally {
            _sw.close(); _sw.dispose();
        }
        
        if (_batchfile!=null) {
            java.lang.Process p=null;
            
            try {
                p=Runtime.getRuntime().exec(new String[]{"cmd.exe", "/C", _batchfilename});
                p.waitFor();
                InputStream _errorstream = p.getErrorStream();
                String _error="";
                
                if (_errorstream!=null) {
                     int _bytecount=_errorstream.available();
                    if (_bytecount > 0) {     
                        StringWriter _writer = new StringWriter();
                    
                        try {
                            byte[] _bytes=new byte[_bytecount];
                            _errorstream.read(_bytes);
                            _error=new String(_bytes);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }                    
                        finally {
                            _writer.close(); _writer=null; System.gc();
                        }
                    }
                }
                
                _file=new java.io.File(destination);
                if (!_file.exists()) {
                    _file=null; System.gc();
                }
            }
            catch (Exception ex) {   
                _file=null; ex.printStackTrace();
            }
            finally {    
                if (p!=null) p.destroy();
                p=null; System.gc();
            }
            
            try {
                _batchfile.delete();
            }
            catch (Exception ex) {
            }
            finally {
                _batchfile=null; System.gc();
            }
        }
        
        return _file;
    }
    
    /**
     * Dispose off any resources used by the class to free up memory space. May throw a RunTimeException especially in cases
     * of multiple call of this method. To validate whether the current class already called this method, check isDispose
     * method.
     */
    public void dispose() {
         _isdisposed=true;
        try {
            finalize();
            Archiver _current=this;
            _current=null; System.gc();
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Performs file extraction for a certain compressed file and have it inside the specified output directory destination path.
     * @param compressedfilepath Compressed file path to extract.
     * @return File information regarding the output file destination, otherwise null if something went wrong.
     */
    public java.io.File extract(String compressedfilepath) {
        return extract(new java.io.File(compressedfilepath));
    }
    
    /**
     * Performs file extraction for a certain compressed file and have it inside the specified output directory destination path.
     * @param compressedfile Compressed file to extract
     * @return File information regarding the output file destination, otherwise null if something went wrong.
     */
    public java.io.File extract(java.io.File compressedfile) {
         String _path=compressedfile.getPath();
        if (_path.startsWith("/")) _path=compressedfile.getPath().substring(1);
        String _name=Path.getFileNameWithoutExtension(_path);
        java.io.File _file=null;
        
        if (!_name.equals("")) {
            String _currentdirectory=compressedfile.getParent();
            if (_currentdirectory.startsWith("/")) _currentdirectory=compressedfile.getParent().substring(1);
            String _destination=_currentdirectory + "\\" + _name;
            _file = extract(compressedfile, _destination);
        }
        
        return _file;
    }
    
    /**
     * Performs file extraction for a certain compressed file and have it inside the specified output directory destination path.
     * @param compressedfilepath Compressed file path to extract.
     * @param destination Output path destination
     * @return File information regarding the output file destination, otherwise null if something went wrong.
     */
    public java.io.File extract(String compressedfilepath, String destination) {
        return extract(new java.io.File(compressedfilepath), destination);
    }
    
    /**
     * Performs file extraction for a certain compressed file and have it inside the specified output directory destination path.
     * @param compressedfile Compressed file to extract
     * @param destination Output path destination
     * @return File information regarding the output file destination, otherwise null if something went wrong.
     */
    public java.io.File extract(java.io.File compressedfile, String destination) {
        java.io.File _file=null;
         
        String _batchfilename=Application.startUpPath() + "\\extractor.bat";  
        String _zip=mime.Mime.resources.sevenZip().getPath();
        if (_zip.startsWith("/")) _zip=mime.Mime.resources.sevenZip().getPath().substring(1);
        
        String _path=compressedfile.getPath();
        if (_path.startsWith("/")) _path=compressedfile.getPath().substring(1);
        
        String _contents="\"" + _zip + "\" e \"" + _path + "\" -o\""  + destination + "\" *.* -r";
        java.io.File _batchfile=null;
        
        StreamWriter _sw=new StreamWriter(_batchfilename);
        try {
            _sw.write(_contents); _batchfile=new java.io.File(_batchfilename);
        }
        catch (Exception ex) {
            _batchfile=null; ex.printStackTrace();
        }
        finally {
            _sw.close(); _sw.dispose();
        }
        
        if (_batchfile!=null) {
             java.lang.Process p=null;
             
             try {
                p=Runtime.getRuntime().exec(new String[]{"cmd.exe", "/C", _batchfilename});
                p.waitFor();
                InputStream _errorstream = p.getErrorStream();
                String _error="";
                
                if (_errorstream!=null) {
                     int _bytecount=_errorstream.available();
                    if (_bytecount > 0) {     
                        StringWriter _writer = new StringWriter();
                    
                        try {
                            byte[] _bytes=new byte[_bytecount];
                            _errorstream.read(_bytes);
                            _error=new String(_bytes);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }                    
                        finally {
                            _writer.close(); _writer=null; System.gc();
                        }
                    }
                }
                
                _file=new java.io.File(destination);
                if (!_file.exists()) {
                    _file=null; System.gc();
                }
            }
            catch (Exception ex) {   
                _file=null; ex.printStackTrace();
            }
            finally {    
                if (p!=null) p.destroy();
                p=null; System.gc();
            }
            
            try {
                _batchfile.delete();
            }
            catch (Exception ex) {
            }
            finally {
                _batchfile=null; System.gc();
            }
        }
        
        return _file;
    }
    
    private boolean _isdisposed=false;
    
    /**
     * Returns whether the current class has already called its finalized method so that the current class will be 
     * swiped out by the garbage collection or not.
     * @return Returns True if the current class already called the dispose method, otherwise false.
     */
    public boolean isDisposed() {
        return _isdisposed;
    }
    
    
}
