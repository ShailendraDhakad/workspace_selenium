
package utility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static org.testng.Assert.fail;

import org.testng.Reporter;
import org.testng.SkipException;

import com.aventstack.extentreports.MediaEntityBuilder;


/**
 * Main driver class for setting up Web UI testing, running the webpages, and tearing down testing.
 * <p>If a wrapper method exists for a driver method it is recommend to use wrapper method instead.
 * <p>For direct access to driver methods use driver().
 * @author Vijaya Ankireddypalli
 */
public class Selenium {
    
    
    public static boolean isElePresent = false;
	public static boolean sElementChecked=false;
	public static WebDriver driver;
	public static WebElement wElement;
	public static WebDriverWait waiting;
	public static String sTestCaseName;

    public static WebDriver openApplication(String sBaseURL, String sBrowserType)
	{
    	System.setProperty("webdriver.gecko.driver","C://SVN//Functional_Automation//JAVA//Libraries//Selenium//geckodriver.exe");
		if (sBrowserType.equalsIgnoreCase("firefox"))
		{
			Log.info("Firefox browser");
			DesiredCapabilities browserCapabilities = DesiredCapabilities.firefox();
			browserCapabilities.setCapability("marionette",true);
		//	FirefoxBinary ffbinary = new FirefoxBinary(new File("C:\\Users\\FirefoxPortableESR\\Firefox.exe"));
			FirefoxProfile ffprofile = new FirefoxProfile();
		//	driver = new FirefoxDriver(ffbinary, ffprofile, browserCapabilities);
			driver = new FirefoxDriver(browserCapabilities);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		else if (sBrowserType.equalsIgnoreCase("IE"))
		{
			Log.info("IE browser");
		    System.setProperty("webdriver.ie.driver","C://SVN//Functional_Automation//JAVA//Libraries//Selenium//IEDriverServer.exe"); 
		   // DesiredCapabilities cap = new DesiredCapabilities();
		    //cap.setCapability("nativeEvents",false);
		    //cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		    driver = new InternetExplorerDriver();	
		}
		else if (sBrowserType.equalsIgnoreCase("chrome"))
		{
			Log.info("Chrome browser");
			System.setProperty("webdriver.chrome.driver","C://SVN//Functional_Automation//JAVA//Libraries//Selenium//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-data-dir=F:/Resources/TestData/Retail/profile");
		    driver = new ChromeDriver();
		//	driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		
		driver.get(sBaseURL);
		return driver;
	}  
    
    /*
     * #######################################################################################
     * ##                                Test Controls                                      ##
     * #######################################################################################
     */
    
    
    /**
     * Skip the running test which is currently being executed.&nbsp; 
     * Avoids a failing a test if not testable.
     * 
     * <p>Log level: WARN
     * 
     * @see #skipTest(String, boolean, Exception)
     * @param message the message to pass to the logger
     * @throws Exception 
     */
    public static void skipTest(String message)  {
        skipTest(message,true, null);
    }
    
    /**
     * Skip the running test which is currently being executed.&nbsp; 
     * Avoids a failing a test if not testable.
     * 
     * <p>If {@code takeScreenCapture} is {@code true}, than captures the
     * current window and saves to a PNG file.
     * 
     * <p>Log level: WARN
     * 
     * @see #skipTest(String, boolean, Exception)
     * @param message the message to pass to the logger
     * @param takeScreenCapture set to {@code true} if want to take a screen 
     *                          capture, otherwise {@code false}
     * @throws Exception 
     */
    public static void skipTest(String message, boolean takeScreenCapture) {
        skipTest(message, takeScreenCapture, null);
    }
    
    /**
     * Skip the running test which is currently being executed.&nbsp; 
     * Avoids a failing a test if not testable.
     * 
     * <p>If {@code takeScreenCapture} is {@code true}, than captures the
     * current window and saves to a PNG file.
     * 
     * <p>Log level: WARN
     * 
     * @param message the message to pass to the logger
     * @param takeScreenCapture set to {@code true} if want to take a screen 
     *                          capture, otherwise {@code false}
     * @param exception exception to log, otherwise {@code null}
     * @throws Exception 
     */
    public static void skipTest(String message,boolean takeScreenCapture, Exception exception)  {
       String link;
       try{
        if (takeScreenCapture) {
            link = Utils.takeScreenshot(driver, sTestCaseName);
            if (link == null)
                link = "";
        }
        if (exception == null)
            Log.warn(message);
        else
            Log.warn(message + exception);
        throw new SkipException(message);
       }
       catch(Exception e){
    	   Log.error("Exception:"+e.getMessage());
       }
    }
    
    /**
     * Fails the running test with a specific error message.
     * 
     * <p>Log level: ERROR
     * 
     * @see #failTest(String, boolean, Exception)
     * @param message   the error message to display when failing the test
     * @throws Exception 
     */
    public static void failTest(String message)  {
        failTest(message,true, null);
    }
    
    /**
     * Fails the running test with a specific error message.
     * 
     * <p>If {@code takeScreenCapture} is {@code true}, than captures the
     * current window and saves to a PNG file.
     * 
     * <p>Log level: ERROR
     * 
     * @see #failTest(String, boolean, Exception) 
     * @param message   the error message to display when failing the test
     * @param takeScreenCapture set to {@code true} if want to take a screen 
     *                          capture, otherwise {@code false}
     * @throws Exception 
     */
    public static void failTest(String message,boolean takeScreenCapture) {
        failTest(message, takeScreenCapture, null);
    }
    
    /**
     * Fails the running test with a specific error message.
     * 
     * <p>If {@code takeScreenCapture} is {@code true}, than captures the
     * current window and saves to a PNG file.
     * 
     * <p>Log level: ERROR
     * 
     * @param message   the error message to display when failing the test
     * @param takeScreenCapture set to {@code true} if want to take a screen 
     *                          capture, otherwise {@code false}
     * @param exception exception to log, otherwise {@code null}
     * @throws Exception 
     */
    public static void failTest(String message, boolean takeScreenCapture, Exception exception)  {
        String link = "";
        try{
        if (takeScreenCapture) {
            link = Utils.takeScreenshot(driver, sTestCaseName);
            if (link == null)
                link = "";
            else
            	AdvanceReporting.getTest().addScreenCaptureFromPath(link);
        }
        if (exception == null){
            Log.error(message + link);
        	AdvanceReporting.getTest().fail(message);//.log(LogStatus.ERROR, message);
        }
        else{
            Log.error(message + link + exception);
            AdvanceReporting.getTest().fail(message + "Exception: " + exception, MediaEntityBuilder.createScreenCaptureFromPath(link).build());
            
        }	
        fail(message);
        }
        catch(Exception e)
        {
        	Log.error("Excpetion" + e.getMessage());
        }
    }
    
    /**
     * Passes the running test with a specific message.
     * 
     * <p>Log level: INFO
     * 
     * @see #passTest(String, boolean)
     * @param message   the message to display when passing the test
     * @throws Exception 
     */
    public static void passTest(String message)  {
        passTest(message, false);
    }
    
    /**
     * Passes the running test with a specific message.
     * 
     * <p>If {@code takeScreenCapture} is {@code true}, than captures the
     * current window and saves to a PNG file.
     * 
     * <p>Log level: INFO
     * 
     * @param message   the message to display when passing the test
     * @param takeScreenCapture set to {@code true} if want to take a screen 
     *                          capture, otherwise {@code false}
     * @throws Exception 
     */
    public static void passTest(String message, boolean takeScreenCapture)  {
        String link = "";
        try{
        if (takeScreenCapture) {
            link = Utils.takeScreenshot(Selenium.driver, sTestCaseName);
            if (link == null)
                link = "";

            AdvanceReporting.getTest().pass(message , MediaEntityBuilder.createScreenCaptureFromPath(link ).build());
        }
        else{
	        Log.info("PASSED - "+ message + " " + link);
	        AdvanceReporting.getTest().pass(message);
        }
        }
        catch(Exception e){
        	Log.error("Exception:"+e.getMessage());
        }
    }
    
    /*
     * #######################################################################################
     * ##                           driver Driver Library                                 ##
     * #######################################################################################
     */

  
    /*
	 * type(), wrapper method for selenium type command
	 */
    
    public static void type(String sLocator, String sValue) 
    {
    	String sObjectName = sLocator.substring(sLocator.lastIndexOf('.') + 1);
    	type(sObjectName, sLocator, sValue);
    }
	
   
	 public static void type(String sObjectName, String sLocator, String sValue) 	    
	    {	
		 	type(sObjectName, sLocator, sValue, true) ;
//		 	try
//		 	{	
//				wElement = getWebElement(sLocator,sObjectName);
//				if (wElement != null)
//				{
//					Log.debug("Type event is occuring at " + sObjectName);	 
//					wElement.clear();
//		    		wElement.sendKeys(sValue);
//		    		
//		    		if (wElement.getAttribute("value").equalsIgnoreCase(sValue))
//			    	Log.info("Type event has been succeeded at " + sObjectName);
//		    		else
//		    		Log.error("Type event has not been succeeded at " + sObjectName);
//				}
//				else
//				{
//					Log.error(sObjectName + " element is not present");
//				}
//		    }
//			catch(Exception ex)
//		    {
//		    	Log.error("Exception in type():" + ex.getMessage());
//		   	}
	    }
	 
	 public static void type(String sObjectName, String sLocator, String sValue, boolean bValidateType) 	    
	    {	    
		 	try
		 	{	
				wElement = getWebElement(sLocator,sObjectName);
				if (wElement != null)
				{
					Log.debug("Type event is occuring at " + sObjectName);	 
					wElement.clear();			
		    		wElement.sendKeys(sValue);
		    		if (bValidateType){
		    			String textValue=wElement.getAttribute("value");
		    			textValue=textValue.replace("(", "").replace(")", "").replace("-", "").replace(" ", "").replace("_", "");
			    		if (textValue.equalsIgnoreCase(sValue))
				    	Log.info("Type event has been succeeded at " + sObjectName);
			    		else
			    		Log.error("Type event has not been succeeded at " + sObjectName);
		    		}
				}
				else
				{
					Log.error(sObjectName + " element is not present");
				}
		    }
			catch(Exception ex)
		    {
		    	Log.error("Exception in type():" + ex.getMessage());
		   	}
	    }
	 
	 
	 public static void typeKey(String sObjectName, String sLocator, Keys sValue) 	    
	    {	    
		 	try
		 	{	
				wElement = getWebElement(sLocator,sObjectName);
				if (wElement != null)
				{
					Log.debug("Type event is occuring at " + sObjectName);	 
							
		    		wElement.sendKeys(sValue);
		 	
				}
				else
				{
					Log.error(sObjectName + " element is not present");
				}
		    }
			catch(Exception ex)
		    {
		    	Log.error("Exception in type():" + ex.getMessage());
		   	}
	    }  
    /*
	 * click(), wrapper method for selenium click command
	 */
    
    public static void click(String sObjectName, String sLocator) 
    {
    	try
    	{	
    		Selenium.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    	wElement = getWebElement(sLocator,sObjectName);
			if (wElement!=null)
			{
		    	Log.debug("Click event is occuring at " + sObjectName);	
		    /*	// Create the JSExecutor object
				JavascriptExecutor jexecutor = (JavascriptExecutor)driver;
					 
				// Perform the click with javascript
				//jexecutor.executeScript("arguments[0].click();"); */

	    		wElement.click();
		    	Log.info("Click event has been succeeded at " + sObjectName);
			}
			else
			{
				Log.error(sObjectName + " element is not present");
			}
    	}
    	catch(Exception ex)
    	{
	    	try{
				JavascriptExecutor executor = (JavascriptExecutor)Selenium.driver;
				executor.executeScript("arguments[0].click()", wElement);
				Log.info("Click event has been succeeded at " + sObjectName);
	    	}
	    	catch(Exception exp){
				Log.error("Exception in type():" + exp.getMessage());
				exp.printStackTrace();
	    	}
    	}
    }
    
    
    public static void clickButtons(String sObjectName,String sLocator){
    	WebElement welement = null;
		try {
				List <WebElement> NextButton=Selenium.getWebElements(sLocator,sObjectName);
				int BtnCount=NextButton.size();
				//int errCnt=0;
				for (int iBtn=0; iBtn<BtnCount; iBtn++){
					if (NextButton.get(iBtn).isDisplayed()){
						//System.out.println(NextButton.get(iBtn).isDisplayed());
						welement= NextButton.get(iBtn);
						welement.click();
						Log.info("Click event has been succeeded at " + sObjectName);
						break;
						
					}
				}	
			}
			catch(Exception e)
	 		{	
				try{
					JavascriptExecutor executor = (JavascriptExecutor)Selenium.driver;
					executor.executeScript("arguments[0].click()", welement);
					Log.info("Click event has been succeeded at " + sObjectName);
		    	}
		    	catch(Exception exp){
					Log.error("Exception in type():" + exp.getMessage());
					exp.printStackTrace();
		    	}
	 	    		    		    	
	 		}
		
	}
    
    /*
     * doubleClick, wrapper method for selenium double click command
     */
   
	
	public static void doubleClick(String sObjectName, String sLocator) 
    {
    	try
    	{	
    		//Selenium.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    	wElement = getWebElement(sLocator,sObjectName);
			if (wElement!=null)
			{
				Actions action = new Actions(driver);
				action.doubleClick(wElement).build().perform();
		    	Log.debug("Click event is occuring at " + sObjectName);	
		   
		    	Log.info("Click event has been succeeded at " + sObjectName);
			}
			else
			{
				Log.error(sObjectName + " element is not present");
			}
    	}
    	
	    	
	    catch(Exception exp){
			Log.error("Exception in doubleClick():" + exp.getMessage());
			
	    }
    	
    }
    /*
	 * check(), wrapper method for selenium check command
	 */
    
    @SuppressWarnings("unused")
	public static void check(String sObjectName, String sLocator)
    {	
    	try
    	{
	      	boolean sElementChecked = false;
	    	wElement = getWebElement(sLocator,sObjectName);
			if (wElement!=null)
			{	if(!wElement.isSelected()){
		    	Log.debug("Check event is occuring at " + sObjectName);	    
	    		wElement.click();
	    		sElementChecked = true;
		    	Log.info("Check event has been succeeded at " + sObjectName);
			}
			else
			{
				Log.info("Check event is not occured  since it is already Checked-" + sObjectName);	 
				
			}
			}
			else
			{
				Log.error(sObjectName + " element is not present");
			}
    	}
    	catch(Exception ex)
    	{
    		Log.error("Exception in check():" + ex.getMessage());
    	}
    }
    
    
    
    
    /*
   	 * isElementPresent(), wrapper method for selenium isElementPresent command
   	 */
    
    public static boolean isElementPresent(String sLocator) 
    {
    	String sObjectName = sLocator.substring(sLocator.lastIndexOf('.') + 1);
    	return isElementPresent(sObjectName, sLocator) ;
    }
       
    public static boolean isElementPresent(String sObjectName,String sLocator) 	   
   	{
    	boolean sElementPresent=false;
    	try
    	{
	    	wElement = getWebElement(sLocator,sObjectName);   
	    	if(isElePresent)
	   		{
	    		sElementPresent=true;
	   	    	Log.info(sObjectName + " element is present");	    	
	   		}  
	   		else
	   		{
	   			Log.error(sObjectName + " element is not present");
	   		}
    	}
    	catch(Exception ex)
    	{
    		Log.error("Exception in isElementPrfesent():" + ex.getMessage());
    	}
		return sElementPresent;
    }
    
    public static boolean isElementPresent(String sObjectName,String sLocator, boolean CheckVisible) 	   
   	{
    	boolean sElementPresent=false;
    	try
    	{
    		if (CheckVisible)
    			wElement = getWebElement(sLocator,sObjectName);  
    		else
    			wElement = getWebElement(sLocator,sObjectName,CheckVisible);
	    	if(isElePresent)
	   		{
	    		sElementPresent=true;
	   	    	Log.info(sObjectName + " element is present");	    	
	   		}  
	   		else
	   		{
	   			Log.error(sObjectName + " element is not present");
	   		}
    	}
    	catch(Exception ex)
    	{
    		Log.error("Exception in isElementPrfesent():" + ex.getMessage());
    	}
		return sElementPresent;
    }
    
   /*
  	 * isElementEnabled().... method to check, whether element is enabled or disabled
  	 */
       
    public static boolean isElementEnabled(String sObjectName,String sLocator)    
  	{
      	boolean sElementPresent=false;
      	try
      	{
	       	wElement = getWebElement(sLocator,sObjectName);   
	       	if(wElement.isEnabled())
	      	{
	       		sElementPresent=true;
	          	Log.info(sObjectName + " element is enabled");	    	
	   		}  
	   		else
      		{
	   			Log.error(sObjectName + " element is disabled");
	      	}
      	}
      	catch(Exception ex)
    	{
    		Log.error("Exception in isElementEnabled():" + ex.getMessage());
    	}
       	return sElementPresent;
    }
        
     /*
   	 * getWebElement(), method for getting the webelement
   	 */
     
 	public static WebElement getWebElement(String sLocator,String sObjectName)
 	{
 		WebElement wElement=getWebElement(sLocator, sObjectName, true);

 		return wElement;		
 	}
 	
 	
 	public static WebElement getWebElement(String sLocator,String sObjectName, boolean bExistance)
 	{
 		
 		
 		waiting = new WebDriverWait(driver, 10, 20);
 		String sLocatorVal[] = new String[2];
 		try
 		{										
 			if (!bExistance){				
	 			
	 			if (sLocator.contains("=")) 	
	 			{
	 				if (!sLocator.toUpperCase().contains("XPATH"))
	 	 				sLocatorVal = sLocator.split("=");
	 				
	 				if (sLocator.toUpperCase().contains("XPATH"))
	 				{
	 					System.out.println("********xpath=" + sObjectName);
	 					
	 					wElement = waiting.until(ExpectedConditions.elementToBeClickable(By.xpath(sLocator.substring(sLocator.indexOf("=")+1))));
	 					
	 					//wElement = waitForElement(driver,sLocatorVal[1].trim()); 				
	 					isElePresent = true;
	 				}
	 				else if (sLocatorVal[0].toUpperCase().equals("ID"))
	 				{
	 					System.out.println("********Id= " + sLocatorVal[1].trim());
	 					wElement = waiting.until(ExpectedConditions.elementToBeClickable(By.id(sLocatorVal[1])));
	 					isElePresent = true;
	 				}
	 				else if (sLocatorVal[0].toUpperCase().equals("NAME"))
	 				{
	 					System.out.println("********Name= " + sLocatorVal[1].trim());
	 					wElement = waiting.until(ExpectedConditions.elementToBeClickable(By.name(sLocatorVal[1])));
	 					isElePresent = true;
	 				}
	 				else if (sLocatorVal[0].toUpperCase().equals("TAG"))
	 				{
	 					System.out.println("********Tag= " + sLocatorVal[1].trim());
	 					wElement = waiting.until(ExpectedConditions.elementToBeClickable(By.tagName(sLocatorVal[1])));
	 					isElePresent = true;
	 				}
	 				else if (sLocatorVal[0].toUpperCase().equals("CLASS"))
	 				{
	 					System.out.println("********Tag= " + sLocatorVal[1].trim());
	 					wElement = waiting.until(ExpectedConditions.elementToBeClickable(By.tagName(sLocatorVal[1])));
	 					isElePresent = true;
	 				}
	 				
	  				// Scroll the browser to the element's Y position
	 				//((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+wElement.getLocation().y+")");
	 			}
 			}
	 		else{	
	 		
	 			if (sLocator.contains("="))	
	 			{
	 				if (!sLocator.toUpperCase().contains("XPATH"))
	 	 				sLocatorVal = sLocator.split("=");
	 				
	 				if (sLocator.toUpperCase().contains("XPATH"))
	 				{
	 					System.out.println("********xpath=" + sObjectName);
	 					
	 					wElement = waiting.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sLocator.substring(sLocator.indexOf("=")+1))));
	 					//wElement = waitForElement(driver,sLocatorVal[1].trim()); 				
	 					isElePresent = true;
	 				}
	 				else if (sLocatorVal[0].toUpperCase().equals("ID"))
	 				{
	 					System.out.println("********Id= " + sLocatorVal[1].trim());
	 					wElement = waiting.until(ExpectedConditions.visibilityOfElementLocated(By.id(sLocatorVal[1])));
	 					isElePresent = true;
	 				}
	 				else if (sLocatorVal[0].toUpperCase().equals("NAME"))
	 				{
	 					System.out.println("********Name= " + sLocatorVal[1].trim());
	 					wElement = waiting.until(ExpectedConditions.visibilityOfElementLocated(By.name(sLocatorVal[1])));
	 					isElePresent = true;
	 				}
	 				else if (sLocatorVal[0].toUpperCase().equals("TAG"))
	 				{
	 					System.out.println("********Tag= " + sLocatorVal[1].trim());
	 					wElement = waiting.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(sLocatorVal[1])));
	 					isElePresent = true;
	 				}
	 				else if (sLocatorVal[0].toUpperCase().equals("CLASS"))
	 				{
	 					System.out.println("********Tag= " + sLocatorVal[1].trim());
	 					wElement = waiting.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(sLocatorVal[1])));
	 					isElePresent = true;
	 				}
	 			}
	 		
	 		}
 		}
 			
 		catch (NoSuchElementException e)
 		{
 	    	Log.error(sObjectName+" Nosuch element exits");		    		    	
 		}
 		return wElement;
 		
 		
 	}
 	 /*
   	 * getWebElement(), method for getting the list of webelements
   	 */
     
 	public static List <WebElement> getWebElements(String sLocator,String sObjectName)
 	{
 		//waiting = new WebDriverWait(driver, 10, 20);
 		List <WebElement> wElements=null;
 		
 		try
 		{
 			String sLocatorVal[] = new String[2];
 			if (sLocator.contains("="))
 			{
 				if (!sLocator.toUpperCase().contains("XPATH"))
 				sLocatorVal = sLocator.split("=");
 					
 				if (sLocator.toUpperCase().contains("XPATH"))
 				{
 					System.out.println("********xpath=" + sObjectName);
 					
 					wElements = driver.findElements(By.xpath(sLocator.substring(sLocator.indexOf("=")+1)));
// 									
// 					isElePresent = true;
 				}
 				else if (sLocatorVal[0].toUpperCase().equals("ID"))
 				{
 					System.out.println("********Id= " + sLocatorVal[1].trim());
 					wElements = driver.findElements(By.id(sLocatorVal[1]));
 					//isElePresent = true;
 				}
 				else if (sLocatorVal[0].toUpperCase().equals("NAME"))
 				{
 					System.out.println("********Name= " + sLocatorVal[1].trim());
 					wElements = driver.findElements(By.name(sLocatorVal[1]));
 					//isElePresent = true;
 				}
 				else if (sLocatorVal[0].toUpperCase().equals("TAG"))
 				{
 					System.out.println("********Tag= " + sLocatorVal[1].trim());
 					wElements = driver.findElements(By.tagName(sLocatorVal[1]));
 					//isElePresent = true;
 				}
 				else if (sLocatorVal[0].toUpperCase().equals("CLASS"))
 				{
 					System.out.println("********Tag= " + sLocatorVal[1].trim());

 					wElements = driver.findElements(By.tagName(sLocatorVal[1]));
 					//isElePresent = true;
 				}
 				
  				// Scroll the browser to the element's Y position
 				//((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+wElement.getLocation().y+")");
 			}
 		}
 		catch (NoSuchElementException e)
 		{
 	    	Log.error(sObjectName+" Nosuch element exits");		    		    	
 		}
 		return wElements;		
 	}
 	/*************
 	 * Method to switch to the corresponding iframe
 	 * **************/
 	
 	public static void switchToIframe(String sTab,String sXpath,String sFrameName)
 	{
 		try
 		{
 			driver.switchTo().frame(driver.findElement(By.name("slideMenu")));
 			getFrameAttributes("iframe");
			if (sTab.equals("Create"))
			{		
				System.out.println("iframe : "+sFrameName);
				click("Create Tab",sXpath);
				driver.switchTo().frame(sFrameName);	
			}
			else if (sTab.equals("Update"))
			{	
				System.out.println("iframe : "+sFrameName);
				click("Update Tab",sXpath);
				driver.switchTo().frame(sFrameName);
			}
				
			else if (sTab.equals("Delete"))
			{
				System.out.println("iframe : "+sFrameName);
				click("Delete Tab",sXpath);
				driver.switchTo().frame(sFrameName);
			}
			else if (sTab.equals("Publish"))
			{
				System.out.println("iframe : "+sFrameName);
				click("Publish Tab",sXpath);
				driver.switchTo().frame(sFrameName);
			}
			else if (sTab.equals("Import"))
			{
				System.out.println("iframe : "+sFrameName);
				click("Import Tab",sXpath);
				driver.switchTo().frame(sFrameName);
			}	
 		}
 		catch(Exception ex)
		{
 			Log.error("Exception in switchToIFrame() :" + ex.getMessage());
		}
 	}
 	
 	/*************
 	 * Method to switch to the corresponding frame in view mode
 	 * **************/
 	public static void switchToFrames(String sModule,String sXpath)
 	{
 		try
 		{
 			getFrameAttributes("frame");
 			driver.switchTo().frame(driver.findElement(By.name("verticalMenu")));
 			click(sModule,sXpath);
 			driver.switchTo().defaultContent();
 			driver.switchTo().frame(driver.findElement(By.name("slideMenu")));
 		}
 		catch(Exception ex)
		{
 			Log.error("Exception in switchToFrames() :" + ex.getMessage());
		}	
 	}
 	
 	 /*
   	 * getFrameAttributes(), method for getting the Frame attributes
   	 */
 	public static void getFrameAttributes(String sFrame)
 	{
 		List<WebElement> element = driver.findElements(By.tagName(sFrame));
	    System.out.println("Number of frames in a page :" + element.size());
	    for(WebElement el : element)
	    {
	        System.out.println("Frame Id is :" + el.getAttribute("id"));
	        System.out.println("Frame name is :" + el.getAttribute("name"));
	    }
 	}
 	
 	
 	/* 
     * This method is to get the Alert popup 
    */
 	public static boolean getAlert() throws InterruptedException 
 	{

 		  boolean presentFlag = false;

 		  try 
 		  {
 		   // Check the presence of alert
 		   Alert alert = driver.switchTo().alert();
 		   
		   // Alert present; set the flag
 		   presentFlag = true;	       
	       System.out.println(alert.getText());
	       Log.info(alert.getText());
 		   
	       // if present consume the alert
 		   alert.accept();

 		  }
 		  catch (NoAlertPresentException ex) {
 		   // Alert not present
 		   ex.printStackTrace();
 		  }

 		  return presentFlag;
 		 
 	}

 	/*
	 * select(), wrapper method for selenium select command
	 */
 	
// 	public static void select(String sLocator,String optionLocator) {
// 		
// 	}
//    
    public static void select(String sObjectName,String sLocator,String optionLocator) 
    {
    	try 
    	{
        	wElement = getWebElement(sLocator,sObjectName,true);
        	
    		if (wElement!=null)
    		{
    			Log.debug("Select event is occuring at "+sLocator);
    	    	System.out.println("Option : "+optionLocator);
    	    	Select selectObject = new Select(wElement);
    	    	//selectObject.deselectAll();
    	    	selectObject.selectByVisibleText(optionLocator);
    	    	
    	    	Log.info(optionLocator+" selected successfully.");
    		} 
    		else
    		{
    			Log.error(sObjectName + " element is not present");
    		}
    	}
    	catch(Exception ex)
    	{
    		Log.error("Exception in select:" + ex.getMessage());
    	}
		
    }
    
    public static void selectCheckBox(String sObjectName,String sLocator,int IndexValue) 
    {
    	try 
    	{
    		List <WebElement> wElements=Selenium.getWebElements(sLocator,sObjectName);
        	
        	
    		if (wElements.size()!=0)
    		{
    			Log.debug("Select event is occuring at "+sLocator);
    	    	System.out.println("Option : "+IndexValue);
    	    	boolean bValue=false;
    	    	bValue=wElements.get(IndexValue).isSelected();
    	    	if (!bValue){
    	    		wElements.get(IndexValue).click();
    	    	}
    	    	
    	    	Log.info(sObjectName+" selected successfully.");
    		} 
    		else
    		{
    			Log.error(sObjectName + " element is not present");
    		}
    	}
    	catch(Exception ex)
    	{
    		Log.error("Exception in select:" + ex.getMessage());
    	}
		
    }
    
 /*   public static void bzSelectById(String sObjectName,String sLocator,String optionLocator) throws IOException
    {
    	try 
    	{
    		wElement = getWebElement(sLocator,sObjectName);
    		
    		Log.debug("Select event is occuring at "+ sLocator);
    		
    		Select selectBox = new Select(wElement);
    	    selectBox.selectByValue(optionLocator);    	    
    	    
    	    Log.info(optionLocator+" selected successfully.");    
    	}
    	catch(Exception ex)
    	{
    		Log.error("Exception in select:" + ex.getMessage());
    	}
		
    }*/
    
    
    public static void selectComboValue(String sObjectName,String sLocator,String sOptionLocator)
    {
    	
    	try
    	{
        	Log.debug("Selectcombo event is occuring at " + sLocator);	  
	    	/*wElement = getWebElement(sLocator,sObjectName);
        	WebElement select = wElement; // driver.findElement(By.tagName("select"));*/
	    	
	    /*	Select selectObject = new Select(wElement);
	    	selectObject.selectByValue(sOptionLocator);    	  */  
	    	//selectObject.selectByVisibleText(sOptionLocator);

	    	WebElement select = driver.findElement(By.tagName("select")); // driver.findElement(By.tagName("select"));
	    	List<WebElement> allOptions = select.findElements(By.tagName("option"));
	    	for (WebElement option : allOptions) {
	    	    System.out.println(String.format("Value is: %s", option.getAttribute("value")));
	    	    System.out.println("option text "+option.getText());
		    	if(option.getAttribute("value").equals(sOptionLocator))
		    	{
		    	    System.out.println("option text "+option.getText());

		    		option.click();
		    	}
	    	}
	    	
	    	Log.info(sOptionLocator+" selected successfully.");
	    }
    	catch(Exception ex)
    	{
    		Log.error("Exception in selectComboValue :" + ex.getMessage());
    	}	
    }


 	
 	/**
     * Wait for the element to be present in the DOM, and displayed on the page. 
     * And returns the first WebElement using the given method.
     * @return WebElement        the first WebElement using the given method, or null (if the timeout is reached)
     */
   public static WebElement waitForElement(WebDriver driver, String sLocator)
   {
           try{        
                   //To use WebDriverWait(), we would have to nullify implicitlyWait(). 
                   //Because implicitlyWait time also set "driver.findElement()" wait time.  
        	   
                   driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()                      
                   WebDriverWait wait = new WebDriverWait(driver, 120,100); 
                   wElement = wait.until(ExpectedConditions.visibilityOf(Selenium.getWebElement(sLocator, "")));
                   
                   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //reset implicitlyWait
                   return wElement; //return the element        
           } catch (Exception e) {
                   e.printStackTrace();
           } 
           return null; 
   }

  public void check_Alert()
 	{
 		try
 		  {
 			Thread.sleep(2000);
 	 	    System.out.println("the colorbox pop up is displayed ? " +   driver.findElement(By.id("confirmDialog")).isDisplayed() );
 	 	    WebDriverWait wait = new WebDriverWait(driver, 100);
 	 	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("confirmDialog")));
 	 	    System.out.println(driver.findElement(By.id("confirmDialog")).getText());
 	 	    // driver.findElement(By.className("ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only")).click();
			//Alert alert = wait.until(ExpectedConditions.alertIsPresent());

 	 	    driver.switchTo().activeElement().click();
 		  } 
 		catch (InterruptedException e)
 		  {		
 			e.printStackTrace();
 		  }
 		  
 	    System.out.println("the colorbox pop up is displayed ? " +   driver.findElement(By.id("alertDialog")).isDisplayed() );
 		  
 	  }
 	
 	
 	public void testJQueryAlertHandling() 
 	{
	 try {
			Thread.sleep(2000);
			WebElement alert = driver.findElement(By.id("alertDialog"));
			alert.click();
		  }
		  catch (InterruptedException e) {
			  e.printStackTrace();
		  }
 	}
 	
 	/*************
 	 * Method to Right click and choose an option
 	 * **************/
 	public static void rightClickAndChooseAnOption(String sObjectName, String sLocator) 
 	{
 		try
    	{
 		//	wElement=driver.findlements(By.xpath("//a[contains(text(),'xyz')]");
 			
 			//unordered_list(:folders, :id => 'foldersTree')


	    	wElement = getWebElement(sLocator,sObjectName);
			if (wElement!=null)
			{
		    	Log.debug("Right click event is occuring at " + sObjectName);	   
		    	
		    	/*Actions Action=new Actions(driver);
		        Action.moveToElement(wElement).click();
		        Action.contextClick(wElement).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();*/
		    	
		    	Actions actions=new Actions(driver);
		    	actions.moveToElement(wElement).click();
		    	actions.contextClick(wElement).build().perform();
		    	
		    	Log.info("Right click event has been succeeded at " + sObjectName);
			}
			else
			{
				Log.error(sObjectName + " element is not present");
			}
    	}
    	catch(Exception ex)
    	{
    		Log.error("Exception in RightClickAndChooseAnOption():" + ex.getMessage());
    	}   
 	}
    /**
     * Takes an xPath which has been which has been defined with a marker,
     * and replaces the marker with the insertion value.
     * 
     * <p>For example: xPath = //div[@id='X']
     * <br>X = the marker
     * <br>{@code xPathNormalize} will replace X with the value to insert
     * <br>"//div[@id='name']" = xPathNormalize("//div[@id='X']", "name")
     * 
     * @param xPath         the xPath with a marker 'X' 
     * @param insertValue   the value to replace the marker
     * @return              the xPath normalized with the marker replaced
     */
    public static String xPathNormalize(String xPath, String insertValue) {
        Log.debug("Normalizing xPath :: "+ xPath);
        String[] xPathSplit = xPath.split("X", 2);
        if (xPathSplit.length != 2) {
            Log.error("Invalid xPath :: "+ xPath);
            Log.error("Need to include xPath with a position marker 'X': e.g. //div[X]/input");
            return "";
        }
        if ( StringUtil.match("^\\{[0-9]+\\}", xPathSplit[1]) ) {
            String[] s = xPathSplit[1].split("}", 2);
            xPathSplit[1] = s[1];
        }
        return xPathSplit[0] +insertValue+ xPathSplit[1];
    }
    
    /**
     * Takes an xPath which has been which has been defined with a marker,
     * and replaces the marker with the insertion value.
     * 
     * <p>Same as {@link #xPathNormalize(String, String)} instead accepts
     * a Integer as the insertion value.
     * 
     * @see #xPathNormalize(String, String)
     * @param xPath         the xPath with a marker 'X' 
     * @param insertValue   the value to replace the marker
     * @return              the xPath normalized with the marker replaced
     */
    public static String xPathNormalize(String xPath, int insertValue) {
        return xPathNormalize(xPath, Integer.toString(insertValue));
    }
    
  
    /**
     * returns a xpath by taking tagname and text value is a input parameter.
     * 
     * @param TagName      	tag name 
     * @param insertValue   text of the webelement
     * @return              create the xPath 
     */
    public static String normalizeXPath(String TagName, String textValue){
    	String Slocator="";
    	ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");  
   
    	Object objLocator;
		try {
			objLocator = engine.eval("XPath=//" + TagName + "[Contains(text(),'" + textValue + "')]");
			Slocator=String.valueOf(objLocator);
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			Log.error("Exception in isElementPrfesent():" + e.getMessage());
		}
    	
		return Slocator;
    }
    
    /**
     * Sleeps for specified amount of time in milliseconds.
     * @param millis    milliseconds to sleep for
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            //Log.debug("Sleep interrupted :: "+ ex.getMessage());
        }
    }
    
    /**
     * Validates the Object as well as its text
     * @param ObjectName 			name of the object
     * @param sLocator 				valid locator
     * @param SubString				a text in the element to validate
     * @param validateTextFlag		true-if the text needs to e validated otherwise put false
     * @return a boolean value for the validation
    **/
	public static boolean  ValidateObjectAndText (String ObjectName, String sLocator, String SubString, boolean validateTextFlag)
	{
		//String SubString,MainString;
		boolean BoolRes=false;
		String MainString;
		
		//Validate Object
		try
		{
		if (Selenium.isElementPresent(ObjectName, sLocator,true))
		{
			Reporter.log(ObjectName + " is Present on the page.");
			Selenium.passTest(ObjectName + " is Present on the page.");
			BoolRes=true;
			
			//Validate text
			if (validateTextFlag)
			{	
				MainString=Selenium.getWebElement(sLocator, ObjectName,true).getText();//getAttribute("name");
				
				String LSubString=SubString.toLowerCase();
				String LMainString=MainString.toLowerCase();
				if (LMainString.contains(LSubString)){
					
					Reporter.log(ObjectName + " having value : '"+ MainString + "' is validated on the page.");
					Log.info(ObjectName + " having value :'"+ MainString + "' is validated on the page.");
					BoolRes=true;
				}
				else{
					Reporter.log( ObjectName + " having value :'"+ MainString + "' is Not validated with text :" + SubString + ".");
					Log.error(ObjectName + " having text :'"+ MainString + "' is Not validated with text :" + SubString+ ".");
					BoolRes=false;
				}	
			}
			
		}
		else
		{
			Reporter.log(ObjectName + " is NOT Present on the page.");
			Log.error(ObjectName + " is NOT Present on the page.");
			BoolRes=false;
		}
				
	}
	catch(Exception e)
		{
		Log.error("Failed to validate thye object" + e.getMessage() );
		Reporter.log("Failed to validate thye object" + ObjectName );
		BoolRes=false;
		}
	
	return BoolRes;
	}
	
	public static boolean  ValidateObjectAndText (String ObjectName, String sLocator, String SubString, boolean validateTextFlag, boolean Report)
	{
		//String SubString,MainString;
		boolean BoolRes=false;
		String MainString;
		
		//Validate Object
		try
		{
		if (Selenium.isElementPresent(ObjectName, sLocator,true))
		{
			Reporter.log(ObjectName + " is Present on the page.");
			if (Report) {
				Selenium.passTest(ObjectName + " is Present on the page.");
			}
			
			BoolRes=true;
			
			//Validate text
			if (validateTextFlag)
			{	
				MainString=Selenium.getWebElement(sLocator, ObjectName,true).getText();//getAttribute("name");
				
				String LSubString=SubString.toLowerCase();
				String LMainString=MainString.toLowerCase();
				if (LMainString.contains(LSubString)){
					
					Reporter.log(ObjectName + " having value : '"+ MainString + "' is validated on the page.");
					Log.info(ObjectName + " having value :'"+ MainString + "' is validated on the page.");
					if (Report) {
						Selenium.passTest(ObjectName + " having value :'"+ MainString + "' is validated on the page.");
					}
					BoolRes=true;
				}
				else{
					Reporter.log( ObjectName + " having value :'"+ MainString + "' is Not validated with text :" + SubString + ".");
					Log.error(ObjectName + " having text :'"+ MainString + "' is Not validated with text :" + SubString+ ".");
					BoolRes=false;
					if (Report) {
						Selenium.failTest(ObjectName + " having text :'"+ MainString + "' is Not validated with text :" + SubString+ ".");
					}
				}	
			}
			
		}
		else
		{
			Reporter.log(ObjectName + " is NOT Present on the page.");
			Log.error(ObjectName + " is NOT Present on the page.");
			BoolRes=false;
			if (Report) {
				Selenium.failTest(ObjectName + " is NOT Present on the page.");
			}
		}
				
	}
	catch(Exception e){
		Log.error("Failed to validate thye object" + e.getMessage() );
		Reporter.log("Failed to validate thye object" + ObjectName );
		BoolRes=false;
		if (Report) {
			Selenium.failTest("Failed to validate thye object" + ObjectName);
		}
	}
	
	return BoolRes;
	}
	
	public static void handleBrowserPopUp(String sOptions){
		//Accept the browser Message as Yes
		if (sOptions.equalsIgnoreCase("Ok"))
			driver.switchTo().alert().accept();

		else if (sOptions.equalsIgnoreCase("Cancel"))
			driver.switchTo().alert().dismiss();
		
	}
	
	public static void handleBrowser(){
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
			driver.getTitle();
		}
	}
	
	public static boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}
	
	
	public static void TabHandles(String currentWindowHandle) {
	   
	    //String currentWindowHandle = driver.getWindowHandle();

	    //Get the list of all window handles
	    ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());

	    for (String window:windowHandles){

	        //if it contains the current window we want to eliminate that from switchTo();
	        if (window != currentWindowHandle){
	            //Now switchTo new Tab.
	            driver.switchTo().window(window);
	            //Do whatever you want to do here.

	            //Close the newly opened tab
	            driver.close();
	        }
	    }
	}
	
	public static int getTableRowCount(String sTableLocator, String sTableName) {
		   
		//Here  we are getting the webTable Element
		WebElement htmltable=Selenium.getWebElement(sTableLocator, sTableName);
		
		/*
		 * tag <tr> represents the rows of a Table. Here we are 
		 * getting the total rows of a table and selecting any row randomly
		 */
		
		List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
		
		return rows.size();
	}
	
	public static String getTableCellData(String sTableLocator, String sTableName, int iRowNum, int iColNum) {
		String CellText = "";	
		try{   
			
			
			int TotalRows=getTableRowCount(sTableLocator, sTableName);
			
			if (iRowNum >TotalRows){
				Reporter.log("Invalid Row number for table" + sTableName);
				Log.error("Invalid Row number for table" + sTableName);
				CellText="";
			}
			else{
				String sRowNum=Integer.toString(iRowNum);
				String sColNum=Integer.toString(iColNum);
				String slocator=sTableLocator + "/tr["+sRowNum+"]/td["+sColNum+"]";
				WebElement table=Selenium.getWebElement(slocator, sTableName);
				CellText= table.getText();
			}
			
		}
		
		catch(Exception e)
		{
		Log.error("Failed to get cell data from html table" + e.getMessage() );
		Reporter.log("Failed to get cell data from html table" + sTableName );
		}
		return CellText;
		
	}
	
	public static WebElement getTableChildItem(String sTableLocator, String sTableName, int iRowNum, int iColNum) {
		WebElement table=null;	
		try{   
			
			
			int TotalRows=getTableRowCount(sTableLocator, sTableName);
			
			if (iRowNum >TotalRows){
				Reporter.log("Invalid Row number for table" + sTableName);
				Log.error("Invalid Row number for table" + sTableName);
				
			}
			else{
				String sRowNum=Integer.toString(iRowNum);
				String sColNum=Integer.toString(iColNum);
				String slocator=sTableLocator + "/tr["+sRowNum+"]/td["+sColNum+"]";
				table=Selenium.getWebElement(slocator, sTableName);
				
			}
			
		}
		
		catch(Exception e)
		{
		Log.error("Failed to get Item from html table" + e.getMessage() );
		Reporter.log("Failed to get Item from html table" + sTableName );
		}
		return table;
		
	}
	
	public static void validateFieldExistance(String FieldValue, String FieldName){
		if (FieldValue.length() == 0){
			Reporter.log(FieldName + " is not Visible on Fullfillment detail page.");
			Selenium.failTest( FieldName + " is not Visible on Fullfillment detail page. :" + FieldValue);
		}
		else{
			Reporter.log(FieldName + " is Visible on Fullfillment detail page.");
			Selenium.passTest(FieldName + " is Visible on Fullfillment detail page. :" + FieldValue);
		}
	}
	
	public static boolean validateAllItemsWebList(String ObjectName, String Locator, String sOptions){
		boolean validate=true;
		try 
		{	
			int MisMatchCount=0;
			WebElement selectElement = Selenium.getWebElement(Locator, ObjectName);
	    	
			if (selectElement!=null)
			{
				
		    	Select selectObject = new Select(selectElement);
		    	List <WebElement> allOptions = selectObject.getOptions();
//		    	int ListSize=allOptions.size();
		    	String[] arrSplit = sOptions.split(";");
//		    	int OptionSize=arrSplit.length;
		    	
		    	for (int iArr=0; iArr<arrSplit.length;iArr++){
		    		MisMatchCount=0;
		    		
	    	    	for (int iList=0; iList<allOptions.size()-1;iList++){
	    	    		if (arrSplit[iArr].trim().equalsIgnoreCase(allOptions.get(iList).getText())){
	    	    			Log.info(arrSplit[iArr].trim() +" Present in WebList " + ObjectName );
	    	    			Reporter.log(arrSplit[iArr].trim() +" Present in WebList " + ObjectName );
	    	    			break;
	    	    		}
	    	    		else
	    	    			MisMatchCount=MisMatchCount+1;
	    	    	}
	    	    	if (MisMatchCount==allOptions.size()){
	    	    		Log.error(arrSplit[iArr].trim() +" Not Present in WebList " + ObjectName );
		    			Reporter.log(arrSplit[iArr].trim() +" Not Present in WebList " + ObjectName );
		    			validate=false;
		    			Selenium.failTest(arrSplit[iArr].trim() +" Not Present in WebList " + ObjectName);
	    	    	}
		    	}
			} 
			else
			{
				Log.error(ObjectName + " element is not present");
			}
		}
		catch(Exception ex)
		{
			Log.error("Exception in select:" + ex.getMessage());
		}
		return validate;
	}
}

