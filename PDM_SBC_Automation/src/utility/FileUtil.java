package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;
import utility.Selenium;

/**
 * File utility for handing file and directory operations.
 * @author Vijaya Ankireddypall
 */
public class FileUtil {
    private static final Logger log = Logger.getLogger("com.m1.ems.util.FileUtil");
    
    /**
     * Gets the Package (Application) root path.
     * @return  the applications root path
     */
    public static String getPackagePath() {
        String path = FileUtil.class.getResource("").getFile().replaceAll("%20", " ");
        //log.debug("Path1: "+ path);
        
        // Get Full Path
        String packageName = FileUtil.class.getPackage().getName().replace(".", "/");
        // Remove package name from path
        path = path.substring(0, path.indexOf(packageName) - 1);
        //log.debug("Page2: "+ path);

        // If Jar file
        if (path.indexOf('!') != -1) {
            path = path.substring(0, path.lastIndexOf('/'));
        }
        //log.debug("Path3: "+ path);
        
        //  Remove source "src", "bin", or "dist"
        if (path.endsWith("src") || path.endsWith("bin") || path.endsWith("dist")) {
            path = path.substring(0, path.lastIndexOf('/'));
        }
        //log.debug("Path4: "+ path);
        
        // In case console variant
        if (path.indexOf("file:") != -1) {
            path = path.substring(5, path.length());
        }
        //log.debug("Path5: "+ path);
        
        // Windows box
        if (path.startsWith("/C:/", 0)) {
            path = path.substring(1, path.length());
        }
        log.debug("Return path: "+ path);
        return path;
    }
    
    /**
     * Write to a file in CSV format.
     * <p>If mode is 'w' (write) then header is printed (if applied) and file 
     * is truncated if exists.
     * <br>Else if mode is 'a' (append) then only content is added to the file.
     * 
     * @param filename  the filename of the CSV file
     * @param table     the two dimensional table with all the content
     * @param header    a header which must match the number of columns; {@code null} 
     *                  or empty array if not set
     * @param mode      {@code 'w'} for write, or {@code 'a'} for append
     */
    public static void writeToCSV(String filename, String[][] table, String[] header, char mode) {
        log.debug("Write to CSV file - Filename: "+ filename);
        int numColumns;
        String quote = "\"";
        String comma = ",";
        String br    = "\n";
        
        if (table.length < 1)
            return;
        
        /*
         *  Determine number of columns
         */
        if (header != null && header.length > 0) {
            numColumns = header.length;
            if (numColumns != table[0].length) {
                log.error("Table columns does not match header columns");
                return;
            }
        } else {
            header = null;
            numColumns = table[0].length;
        }
        
        String content = "";
        
        /*
         * Get header
         */
        if (header != null && mode == 'w') {
            String line = "";
            for (int i=0; i < numColumns; i++) {
                line += quote + header[i] + quote + comma;
            }
            line = line.substring(0, line.length()-1);
            content = line + br; 
        }
        
        /*
         * Get Content
         */
        for (int n=0; n < table.length; n++) {
            String line = "";
            for (int i=0; i<numColumns; i++) {
                String value = table[n][i];
                if (value == null)
                    value = "null";
                line += quote + value.replace("\"", "").replace(",", "") + quote + comma;
            }
            line = line.substring(0, line.length()-1);
            content += line + br;
        }
        
        if (mode == 'w')
            FileUtil.writeFile(filename, content, 'w');
        if (mode == 'a')
            FileUtil.writeFile(filename, content, 'a');
    }
    
    /**
     * Read a file relative to a class in a java package.&nbsp;
     * Supports files in a Jar. 
     * 
     * @param clazz     the class object as the base path to the file
     * @param filename  the file path relative to the class (do not include a leading slash)
     * @return          a List of the file's contents
     */
    public static List<String> readFileInClassPackage(Class<?> clazz, String filename) {
        log.debug("Reading file='"+ filename +"' in package relative to class='"+ clazz.getName() +"'");
        
        InputStream inStream = null;
        BufferedReader bufRead = null;
        String line;
        List<String> list = new ArrayList<String>();

        try {
            inStream = clazz.getResourceAsStream(filename);
            bufRead = new BufferedReader(new InputStreamReader(inStream));
            while ( (line = bufRead.readLine()) != null ) {
                list.add(line);
            }
            log.debug("Successfully read file='"+ filename +"' in package");
        } catch (NullPointerException ex) {
            Selenium.failTest("File was not found (relative to class='"+ clazz.getName() +"') :: "+ filename, false);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            Selenium.failTest("Unable to read file (relative to class='"+ clazz.getName() +"') :: "+ filename, false);
        }
        
        finally {
            try {
                if (bufRead != null) bufRead.close();
                if (inStream != null) inStream.close();
            } catch (IOException ex) {
                log.debug(ex.getMessage());
            }
        }
        return list;
    }
    

    /**
     * Read a Property File and return it as a java.util.Properties.
     * 
     * @param filename  the file name of the property file
     * @return          the file as a {@code java.util.Properties}
     */
    public static Properties readProperties(String filename) {
        String propfile = FileUtil.getAbsolutePath(filename);
        Properties sets = new Properties();
        try {
            log.debug("Reading properties file :: "+ propfile);
            FileInputStream fis = new FileInputStream(propfile);
            sets.load(fis);
            fis.close();
        } catch (IOException e) {
            Selenium.failTest("Unable to open properties file :: "+ propfile, false);
        }
        return sets;
    }
    
    
    /**
     * Write Properties to File.
     * @param filename  the file name of the property file
     */
    public static void writeProperties(String filename, Properties properties, String comments) {
        String propfile = FileUtil.getAbsolutePath(filename);
        try {
            log.debug("Writing to properties file :: "+ propfile);
            FileOutputStream fos = new FileOutputStream(propfile);
            properties.store(fos, comments);
            fos.close();
        } catch (IOException e) {
            Selenium.failTest("Unable to write to properties file :: "+ propfile, false);
        }
    }
    
    
    /**
     * Writes content to a file.
     * <p>Content is provided as a single string, therefore use of this 
     * method is not suggested for large amounts of data.
     * @param filename  the filename to create or append to
     * @param content   the content as a String
     * @param mode      {@code 'a'} for append or {@code 'w'} to overwrite if exists
     */
    public static void writeFile(String filename, String content, char mode) {
        log.debug("Write file - Filename: "+ filename);
        String filePath = FileUtil.getAbsolutePath(filename);
        File file = new File(filePath);
        
        /*
         * Verify file path
         */
        String dirPath = file.getParent();
        if (file.isDirectory())
            Selenium.failTest("Write file - Filename is a directory: "+ filePath, false);
        if (dirPath == null)
            Selenium.failTest("Write file - The directory must exists to write file: "+ filePath, false);
        File dir = new File(dirPath);
        if (!dir.canWrite())
            Selenium.failTest("Write file - Path is not writable: "+ dirPath, false);
        
        /*
         * Verify mode
         */
        if (mode != 'a' && mode != 'w')
            Selenium.failTest("Write file - Append mode is invalid, must be 'a' or 'w': "+ dirPath, false);
        
        /*
         * Write file
         */
        FileWriter fWriter = null;
        BufferedWriter bufWrite = null;
        boolean failed = false;
        try {
            if (mode == 'w')
                fWriter = new FileWriter(filePath);
            if (mode == 'a')
                fWriter = new FileWriter(filePath, true);
            bufWrite = new BufferedWriter(fWriter);
            bufWrite.write(content);
        } catch (IOException ex) {
            failed = true;
        } finally {
            try {
                if (bufWrite != null) {
                    bufWrite.flush();
                    bufWrite.close();
                }
            } catch (IOException ex) {
                log.warn("Write file - Filed to close file buffer: "+ filePath);
            }
        }
        
        if (failed) {
            Selenium.failTest("Write file - Filed to write content to file: "+ filePath, false);
        }
    }
    
    /**
     * Reads a file and return a String.
     * 
     * @param filename  the file to read (relative or absolute)
     * @return          the string of the entire file; empty string on error
     */
    public static String readToString(String filename) {
        String content = "";
        File file = new File(filename);
        
        String filePath = FileUtil.getAbsolutePath(filename);
        log.debug("Opening file: "+ filePath);
        
        FileReader fstream = null;
        BufferedReader bstream  = null;
        
        try {
            fstream = new FileReader(file);
            bstream = new BufferedReader(fstream);
            
            String line;
            while ( (line = bstream.readLine()) != null) {
                content += line +"\n";
            }
            
            bstream.close();
            fstream.close();

        } catch (FileNotFoundException ex) {
            Selenium.failTest("File was not found :: "+ filePath, false);
        } catch (IOException ex) {
            log.error(ex.getMessage());
            Selenium.failTest("Unable to read file :: "+ filePath, false);
        }
        return content;
    }
    
    /**
     * Reads a file and returns a List line delimited.
     * 
     * @param filename  the file to read (relative or absolute)
     * @return          List<String> of entire file.
     *                  Empty list on IO error. 
     */
    public static List<String> readToList(String filename) {
        List<String> list = new ArrayList<String>();
        File file = new File(filename);
        
        String filePath = FileUtil.getAbsolutePath(filename);
        log.debug("Opening file: "+ filePath);
        
        FileReader fstream = null;
        BufferedReader bstream  = null;
        String line;
        
        try {
            fstream = new FileReader(file);
            bstream = new BufferedReader(fstream);
            
            while ( (line = bstream.readLine()) != null) {
                list.add(line);
            }
            
            fstream.close();
            bstream.close();
            
        } catch (FileNotFoundException ex) {
            Selenium.failTest("File was not found :: "+ filePath, false);
        } catch (IOException ex) {
            log.error(ex.getMessage());
            Selenium.failTest("Unable to read file :: "+ filePath, false);
        }
        return list;
    }
    
    /**
     * Given a relative path resolves the absolute path.
     * 
     * @param filename  the file with relative page to resolve
     * @return          the absolute path to a file (including file)
     */
    public static String getAbsolutePath(String filename) {
        File file = new File(filename);
        String filePath = filename;
        try {
            filePath = file.getCanonicalPath();
        } catch (Exception e) {
            log.warn("Unable to capture file path", e);
        }
        return filePath;
    }
    
    /**
     * Determines if file exists and is not empty.
     * 
     * @param filename  the name of the file to check
     * @return          {@code true} if file exists, otherwise false
     */
    public static boolean existAndNotEmpty(String filename) {
        File file = new File(filename);
        if ( file.exists() && file.length() > 0 ) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Determines if file exists.
     * @param fileOrDir the name of the file to check
     * @return          {@code true} if file exists
     */
    public static boolean exists(String fileOrDir) {
        File f = new File(fileOrDir);
        if (f.exists()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Copy a single file to target directory or file path.
     * <p>Fails test if copy fails.
     * @param sourceFile    the source filename
     * @param targetFile    the target filename or directory
     */
    public static void copyFile(String sourceFile, String targetFile){
        log.debug("Copy source file='"+ sourceFile +"' to target='"+ targetFile +"'");

        File fileSource = new File(sourceFile);
        File fileTarget = new File(targetFile);
        
        if (!fileSource.exists())
            Selenium.failTest("File copy - no such source file: "+ sourceFile, false);
        if (!fileSource.isFile())
            Selenium.failTest("File copy - source file is not a file: "+ sourceFile, false);
        if (!fileSource.canRead())
            Selenium.failTest("File copy - source file is unreadable: "+ sourceFile, false);
        
        if (fileTarget.isDirectory())
            fileTarget = new File(fileTarget, fileSource.getName());
        
        if (fileTarget.exists()) {
            Selenium.failTest("File copy - target file already exists: "+ targetFile, false);
            /*
            if (!toFile.canWrite())
                Selenium.failTest("File copy - destination file is unwriteable: "+ targetFile, false);
            */
        }
         
        String parent = fileTarget.getParent();
        if (parent == null)
            parent = System.getProperty("user.dir");
        File dir = new File(parent);
        if (!dir.exists())
            Selenium.failTest("File copy - destination directory doesn't exist: "+ parent, false);
        if (dir.isFile())
            Selenium.failTest("File copy - destination is not a directory: "+ parent, false);
        if (!dir.canWrite())
            Selenium.failTest("File copy - destination directory is unwriteable: "+ parent, false);
        
        FileInputStream  fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(fileSource);
            fos = new FileOutputStream(fileTarget);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1)
                fos.write(buffer, 0, bytesRead);
            log.debug("Successfully copied file="+ targetFile);
        } catch (Exception ex) {
            Selenium.failTest(ex.getMessage(), false);
        }
        
        finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException ex) {}
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {}
        }
    }
    
    /**
     * Copy a Directory (recursive) to target directory.
     * <p>Fails test if copy fails.
     * @param source    the source directory name
     * @param target    the target directory name
     */
    public static void copyDir(String source, String target){
        log.debug("Directory copy - source='"+ source +"' to target='"+ target +"'");

        File dirSource = new File(source);
        File dirTarget = new File(target);
        String fSep = System.getProperty("file.separator");

        if (!dirSource.exists())
            Selenium.failTest("Directory copy - no such source directory: "+ source, false);
        
        if (dirSource.isDirectory()) {
            if (!dirSource.canRead())
                Selenium.failTest("Directory copy - source directory is unreadable: "+ source, false);

            if (!dirTarget.exists()) {
                log.debug("Directory copy - target directory does not exist (creating...): "+ target);
                dirTarget.mkdirs();
            } else
                log.debug("Directory copy - target directory exists: "+ target);
        
            if (!dirTarget.canWrite())
                Selenium.failTest("Directory copy - destination directory is unwriteable: "+ target, false);
            
            String[] children = dirSource.list();
            for(int i=0; i<children.length; i++) {
                copyDir(source +fSep+ children[i], target +fSep+ children[i]);
            }
        } else if (dirSource.isFile()) {
            copyFile(source, target);
        } else {
            log.error("Directory copy - source is not a directory or a file: "+ source);
        }
    }
    
    /**
     * Delete entire directory tree.
     * @param directory the relative or absolute directory path
     */
    public static void deleteDir(String directory) {
        File dir = new File(getAbsolutePath(directory));
        try {
            FileUtils.deleteDirectory(dir);
        } catch (IOException ex) {
            log.error("Failed to delete directory="+ getAbsolutePath(directory));
        }
    }
  
}
