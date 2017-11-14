package testCases.teleSalesPortal.MedadvFulfillment;


import java.util.List;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModules.SFDC;
import appModules.SFDCAccounts;
import appModules.SFDCApplication;
import appModules.SFDCFullfillment;
import appModules.SFDCLeads;
import appModules.SFDCMediCareApplication;
import appModules.SFDCOpportunities;
import appModules.SFDCPersonSearch;
import appModules.SFDCQuotes;
import pageObjects.PageFullfilment;
import pageObjects.PageLeads;
import pageObjects.PageOpportunities;
import pageObjects.PageSFDC;
import pageObjects.PageLeads.NewLead;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class MedadvFulfillment_04 extends BaseExtentReport{
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
			  Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","MedAdv_Fulfillment_01");
			  	
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
				SFDCLeads.click_ConvertLead();
				
				
			    //----------------------------------
			    // Open Account 			  
			    //----------------------------------
				SFDC.navigateToAccounts();
				SFDCAccounts.selectAccount();
				
							
				//--------------------------------
				//Click new fulfillment request when address is empty
				//--------------------------------
				SFDCOpportunities.click_NewFulfillmentRequest();
				SFDCOpportunities.validate_NoAddressError_NewFulfillmentRequest();
				
				
				//--------------------------------
				//Add new Mailing Address of Account
				//--------------------------------
				SFDCFullfillment.updateAccountMailAddress_FulfillmentKit(TestDatadictionary);
								
				
				//----------------------------------------------------------------
				//Remove mailing address and Update the residential adddress
				//----------------------------------------------------------------
				SFDCOpportunities.click_NewFulfillmentRequest();
				SFDCFullfillment.click_CancelFulfillmentKit();
				SFDCFullfillment.removeAccountMailAddress_FulfillmentKit();
				SFDCFullfillment.updateAccountResidentialAddress_FulfillmentKit(TestDatadictionary);
				SFDCOpportunities.click_NewFulfillmentRequest();
				SFDCFullfillment.validateFulfillmentRequestPage();
				
				
//				//Validate Fulfillment Kit
//				SFDCFullfillment.validateKitType_Lead();
				
				
			 	
			 	//--------------------------------
				//fill Kit information
				//--------------------------------
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
