package testCases.teleSalesPortal.MedadvFulfillment;

import java.util.Map;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import appModules.SFDC;
import appModules.SFDCApplication;
import appModules.SFDCFullfillment;
import appModules.SFDCLeads;
import appModules.SFDCMediCareApplication;
import appModules.SFDCOpportunities;
import pageObjects.PageFullfilment;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class MedAdvFulfillment_12_13 extends BaseExtentReport{

	public  WebDriver driver;
	Map<String, String> TestDatadictionary;
	
	@BeforeMethod
	  public void beforeMethod() throws Exception {
			try{
				WindowsUtils.tryToKillByName("chrome.exe");
				WindowsUtils.tryToKillByName("chromedriver.exe");
			}
			catch(Exception e){
				
			}
			
		  	DOMConfigurator.configure("log4j.xml");
		  	Selenium.sTestCaseName = Utils.getTestCaseName(this.toString());
		 	Log.startTestCase(Selenium.sTestCaseName);
		 		 	
		 	//Set Extent Reporter Object and Test Object
		 	test=extent.createTest(Utils.getTestCaseName(this.toString()));
		 	AdvanceReporting.setTest(test);
		 	
		 	Map<String, String> AppURLdictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.Applications_URL_File,"SIT","Retail");
			driver = Selenium.openApplication(AppURLdictionary.get("URL"), "chrome");
		 	  
	}
	  
	  // This is the starting of the Main Test Case
	  @Test
	  public void change_MedAdv_FulfillmentRequest() throws Exception {
		  try{	
		
			
			//--------------------------------------
			// Login with Fulfillment user access
			//--------------------------------------	
			SFDC.login("Fullfillment",PageFullfilment.ELE_FULLFILLMENT_TAB);
			
			
			//Dictionary Object Test Data for MedAdv_Fulfillment
			TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","MedAdv_Fulfillment_12_13");
			
	/*=====================================================================================================================================================
	* 															MedAdv_Fulfillment_13
	*=====================================================================================================================================================
	*/
			
			//----------------------------------------
			// Select Fullfillment Request
			//------------------------------------------
			SFDCFullfillment.selectFulfillmentView(TestDatadictionary);
			
			//get table row number of Request ID 
			String Fulfillment_ID=SFDCFullfillment.selectAllFulfillments();
			
			//----------------------------------------
			// Change Fullfillment Request Status
			//------------------------------------------
			SFDCFullfillment.changeFulfillmentStatusforAll(TestDatadictionary,Fulfillment_ID);
			
	/*=====================================================================================================================================================
	* 															MedAdv_Fulfillment_12
	*=====================================================================================================================================================
	*/
			SFDCFullfillment.selectFulfillmentView(TestDatadictionary);
			
			//----------------------------------------
			// Open the First record of Fulfillment
			//------------------------------------------
			SFDCFullfillment.search_Fulfillment(Fulfillment_ID);
				
			
			//------------------------------------------------------------
			// Change Fullfillment Status
			//-------------------------------------------------------------

			SFDCFullfillment.editFulFillmentRequest_Option("CMAPS Completed");	
			
			
			
//			
				
		}
		  catch (Exception e){
			  Utils.takeScreenshot(driver, Selenium.sTestCaseName);
			  Log.error(e.getMessage());
			  throw (e);
		  }
			
	  }
			
	  // Its time to close the finish the test case		
	  @AfterMethod
	  public void afterMethod() {
		    // Printing beautiful logs to end the test case
		    Log.endTestCase(Selenium.sTestCaseName);
		    // Closing the opened driver
//		    driver.close();
	  }
}

