
package utility;

import org.apache.log4j.Logger;
//import utility.Constant;
import java.util.Properties;


/**
 * Environment to read {@code config.properties} and set temporary 
 * environment properties for the test suite.
 * 
 * @author Vijaya Ankireddypalli
 */
public class Env {
    private static final Logger log = Logger.getLogger("utility.Env");
    
    private static Properties variables = null;
    
    /**
     * Gets the root path where the application package is stored.
     */
    public static String getPackageDir() {
        return FileUtil.getPackagePath();
    }
    
    /**
     * Gets the execution directory where data is stored in (logs, reports, execution data, etc)
     * @return  the execution directory
     */
    public static String getExecutionDir() {
        return Env.getPropertyAsString("execution.path");
    }
    /**
     * Sets a property key given a String.
     * 
     * <p>If property key does not exist, it will be created.
     * <br>If property exists, its value will be overwritten.
     * 
     * @param key	the key of the property value
     * @param value	the value to save
     */
    public static void setProperty(String key, String value) {
        variables.setProperty(key, value);
    }
    
    /**
     * Sets a property key given an Integer.
     * 
     * <p>If property key does not exist, it will be created.
     * <br>If property exists, its value will be overwritten.
     * 
     * @param key	the key of the property value
     * @param value	the value to save
     */
    public static void setProperty(String key, int value) {
        variables.setProperty(key, Integer.toString(value));
    }
    
    /**
     * Sets a property key given a Boolean.
     * 
     * <p>If property key does not exist, it will be created.
     * <br>If property exists, its value will be overwritten.
     * 
     * @param key	the key of the property value
     * @param value	the value to save
     */
    public static void setProperty(String key, boolean value) {
        variables.setProperty(key, Boolean.toString(value));
    }
    
    
    /**
     * Gets the value of a property given a key.
     * 
     * <p>Pass a default value in the case the key has 
     * not been set, then the default value is returned.
     * 
     * @param key			the key to read, if exists
     * @param defaultValue	the default value to return in case the key does not exists
     * @return				the value as an Integer; {@code defaultValue} if key not set
     */
    public static int getPropertyAsInt(String key, int defaultValue) {
        String value = variables.getProperty(key);
        if (value == null) {
            return defaultValue;
        } else {
            int n = defaultValue;
            try {
                n = Integer.parseInt(value);
            } catch (NumberFormatException ex) {}
            return n;
        }
    }
    
    /**
     * Gets the value of a property given a key.
     * 
     * <p>If key has not been set, warning message will be logged.
     * 
     * @param key			the key to read, if exists
     * @return				the value as an Integer, {@code 0} if key not set
     */
    public static int getPropertyAsInt(String key) {
        String value = variables.getProperty(key);
        if (value == null) {
            log.warn("Enviroment property '"+ key +"' has not been set");
            return 0;
        } else {
            int n = 0;
            try {
                n = Integer.parseInt(value);
            } catch (NumberFormatException ex) {}
            return n;
        }
    }
    
    /**
     * Gets the value of a property given a key.
     * 
     * <p>Pass a default value in the case the key has 
     * not been set, then the default value is returned.
     * 
     * @param key			the key to read, if exists
     * @param defaultValue	the default value to return in case the key does not exists
     * @return				the value as a String; {@code defaultValue} if key not set
     */
    public static String getPropertyAsString(String key, String defaultValue) {
        return variables.getProperty(key, defaultValue);
    }
    
    /**
     * Gets the value of a property given a key.
     * 
     * <p>If key has not been set, warning message will be logged.
     * 
     * @param key			the key to read, if exists
     * @return				the value as a String; empty String if key not set
     */
    public static String getPropertyAsString(String key) {
        String value = variables.getProperty(key);
        if (value == null) {
            log.warn("Enviroment property '"+ key +"' has not been set");
            return "";
        } else {
            return value;
        }
    }
    
    /**
     * Gets the value of a property given a key.
     * 
     * <p>If key has not been set, warning message will be logged.
     * 
     * @param key	the key to read, if exists
     * @return		the value as a Boolean; {@code false} if key does not exist.
     */
    public static boolean getPropertyAsBoolean(String key) {
        String value = variables.getProperty(key);
        if (value == null) {
            log.warn("Enviroment property '"+ key +"' has not been set");
            return false;
        } else {
            boolean n = Boolean.parseBoolean(value);
            return n;
        }
    }
    
    /**
     * Get the file system slash used.
     * @return  the slash type used in the file system, e.g. {@code /=Lunix or \=windows}
     */
    public static String getSlash() {
        return System.getProperty("file.separator");
    }
}

