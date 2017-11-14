
package testCases.teleSalesPortal.MedadvFulfillment;


import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
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

public class MedAdv_Fulfillment_08_09 extends BaseExtentReport{
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
			    Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","Medadv_Q2E_02");
			  	
			    //---------------------------------
				// Login SFDC
				//---------------------------------
			    SFDC.login();
			  	
			  //-------------------------
			  // Create Lead and Opportunity
			  //-------------------------
			  //Dictionary Object Test Data for Creating an Opportunity
			
			    String Opportuunity=SFDCOpportunities.createOpportunity(TestDatadictionary);
				
				SFDCLeads.navigateToMedicare();
				SFDCLeads.ValidateAndfillToMedicare(TestDatadictionary);
				
				//-------------------------------
				// Fill Medicare Application
				//-------------------------------
				SFDCMediCareApplication.fillComparePlanFormMedicare(TestDatadictionary);
				SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
				SFDCMediCareApplication.receiveYourQuoteMedicare(TestDatadictionary);
				SFDCApplication.SaveAndExit();
				
				//----------------------------------------
				// Verify the Fulfillment REcord
				//----------------------------------------
				SFDC.NavigateToOpportunitiesFromHomePage();
				SFDCOpportunities.NavigationToOpportunitiesDetailPage();
				SFDCFullfillment.NewFullfillment_validation();
				String fullfillment_number = SFDCFullfillment.fullfillment_Number();
				
				 if (utility.StringUtil.matchExact("Request Created", SFDCFullfillment.fullfillment_Status())){
					 Selenium.passTest("Fulfillment Status is as Expected: 'Request Created'");
				 }
				 else
					 Selenium.failTest("Fulfillment Status is not showing as: 'Request Created'");
				 
				 //Open Fulfillment KIT Request and validate it
				 SFDCFullfillment.open_FulfillmentKitRequest();
				 SFDCFullfillment.validateFulfillmentKitDetails(TestDatadictionary, fullfillment_number);
				 
				 
				 
				 test=extent.createTest(Utils.getTestCaseName("MedAdv_Fulfillment_09"));
				 	AdvanceReporting.setTest(test);
				 	
				 //Click_CancelFulfillmentKitRequest
				 SFDCFullfillment.cancel_FulfillmentKitRequest();
				 
//				//--------------------------------------
//				// Login with Fulfillment user access
//				//--------------------------------------
//				Map<String, String> AppURLdictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.Applications_URL_File,	"SIT", "Retail");
//				driver = Selenium.openApplication(AppURLdictionary.get("URL"), "chrome");
//				SFDC.login("Fullfillment",PageFullfilment.ELE_FULLFILLMENT_TAB);
//				
//				
//				
//				//----------------------------------------
//				// Serach Fullfillment
//				//------------------------------------------
//				SFDCFullfillment.search_Fulfillment(fullfillment_number);
//				
//				//----------------------------------------
//				//Verify Fullfillment Details
//				//----------------------------------------
//				
//				String Fullfillment_Num=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Fullfillment_Number", 1, 1);
//				String Fullfillment_Type=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Fullfillment_Type", 1, 2);
//				String Member_Name=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Member_Name", 2, 1);
//				String Fullfillment_Status=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Fullfillment_Status", 2, 2);
//				String Quote_Number=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Quote_Number", 3, 2);
//				String Member_Address=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Member_Address", 4, 1);
//				String Requested_DateTime=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Requested_DateTime", 5, 1);
//				
//				Selenium.validateFieldExistance(Fullfillment_Num,"FullFillment Number");
//				Selenium.validateFieldExistance(Fullfillment_Type,"FullFillment Type");
//				Selenium.validateFieldExistance(Member_Name,"Member Name");
//				Selenium.validateFieldExistance(Fullfillment_Status,"FullFillment Status");
//				Selenium.validateFieldExistance(Quote_Number,"Quote Number");
//				Selenium.validateFieldExistance(Member_Address,"Member Address");
//				Selenium.validateFieldExistance(Requested_DateTime,"Requested Date and Time");
				
				
			
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