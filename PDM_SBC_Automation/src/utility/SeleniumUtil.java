package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SeleniumUtil 
{
	private static SeleniumUtil seleniumUtil;
	private Properties properties;
	private Logger logger = Logger.getLogger(SeleniumUtil.class);
	
	private SeleniumUtil()
	{
		try 
		{
			File fLog4j = new File("config/log4j.properties");//loading log4j properties
			PropertyConfigurator.configure(fLog4j.getAbsolutePath());//configuring log4j properties by using absolute path
			logger.info("Log4j properties file has been loaded successfully.");
			properties = new Properties();
			properties.load(new FileInputStream("config/selenium.properties"));//loading selenium.properties
			logger.info("selenium properties file has been loaded successfully.");	
		}
		catch (Exception exception) 
		{
			logger.error("SeleniumUtil.SeleniumUtil() : ", exception);
		}
	}
	 
	/*To instantiate SeleniumUtils.java*/
	public static SeleniumUtil getSeleniumUtil()
	{
		if(seleniumUtil == null)
			seleniumUtil = new SeleniumUtil();//creating instance for SeleniumUtils.java, to make it as singleton class
		return seleniumUtil;
	}
	
	//Method to get all the properties which we loaded	
	public Properties getProperties()
	{
		return properties;
	}	
	
}
