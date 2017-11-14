package testCases.teleSalesPortal.MedadvFulfillment;


import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;











import appModules.SFDC;
import appModules.SFDCFullfillment;
import appModules.SFDCLeads;
import appModules.SFDCOpportunities;
import appModules.SFDCPersonSearch;
import pageObjects.PageOpportunities;
import pageObjects.PageSFDC;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class MedadvFulfillment_07 extends BaseExtentReport{
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
	  public void main() throws Exception {
		  try{	
			  Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","MedAdv_Fulfillment_07");
			  	
			    //---------------------------------
				// Login SFDC
				//---------------------------------
			    SFDC.login();
			  	
			    //----------------------------------
			    // Create Lead
			    //----------------------------------
			    SFDC.navigateToLeads();
				SFDCLeads.newLead(TestDatadictionary);
				SFDC.navigateToOpportunities();
				
			    //----------------------------------
			    // Search Lead
			    //----------------------------------
				SFDC.navigateToPersonalSearch();
				SFDCPersonSearch.openLeadInfo(SFDCPersonSearch.searchLead());
				

				//--------------------------------
				//Update Street Address of Lead
				//--------------------------------
				SFDCFullfillment.updateLeadStreet_FulfillmentKit();
				
				//--------------------------------
				//Fill New Fulfillment Request
				//--------------------------------
				SFDCOpportunities.click_NewFulfillmentRequest();
				SFDCFullfillment.fill_FulfillmentKit(TestDatadictionary);
				
				
				//------------------------------------
				//Submit Fulfillment Kit information
				//------------------------------------
				 Selenium.click("Submit", PageSFDC.FulfillmentKit.BUTTON_SUBMIT);
				 String FulfillmentNum=SFDCFullfillment.fullfillment_Number();
				 
				 if (utility.StringUtil.matchExact("Request Created", SFDCFullfillment.fullfillment_Status())){
					 Selenium.passTest("Fulfillment Status is as Expected: 'Request Created'");
				 }
				 else
					 Selenium.failTest("Fulfillment Status is not showing as: 'Request Created'");
				 
				 //Open Fulfillment KIT Request and validate it
				 SFDCFullfillment.open_FulfillmentKitRequest();
				 SFDCFullfillment.validateFulfillmentKitDetails(TestDatadictionary, FulfillmentNum);
				 
				 //Edit Fulfillment KIT request
				 SFDCFullfillment.update_FulfillmentKit(TestDatadictionary);
				 Selenium.click("Submit", PageSFDC.FulfillmentKit.BUTTON_SUBMIT);
				
				//Validate Fulfillment Kit request
				 SFDCFullfillment.validateFulfillmentKitDetails(TestDatadictionary, FulfillmentNum);
				
				 //Click_CancelFulfillmentKitRequest
				 SFDCFullfillment.cancel_FulfillmentKitRequest();
				 
				 
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
		   // driver.close();
	  }
}
