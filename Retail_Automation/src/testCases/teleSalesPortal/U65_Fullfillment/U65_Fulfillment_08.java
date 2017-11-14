package testCases.teleSalesPortal.U65_Fullfillment;

import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModules.SFDC;
import appModules.SFDCAccounts;
import appModules.SFDCApplication;
import appModules.SFDCFullfillment;
import appModules.SFDCLeads;
import appModules.SFDCOpportunities;
import appModules.SFDCQuotes;
import pageObjects.PageFullfilment;
import pageObjects.PageOpportunities;
import pageObjects.PageSFDC;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class U65_Fulfillment_08 extends BaseExtentReport{
	public WebDriver driver;
	Map<String, String> TestDatadictionary;

	@BeforeMethod
	public void beforeMethod() throws Exception {
		
		DOMConfigurator.configure("log4j.xml");
		Selenium.sTestCaseName = Utils.getTestCaseName(this.toString());
		Log.startTestCase(Selenium.sTestCaseName);
		
		//Set Extent Reporter Object and Test Object
	 	test=extent.createTest(Utils.getTestCaseName(this.toString()));
	 	AdvanceReporting.setTest(test);
	 	
		Map<String, String> AppURLdictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.Applications_URL_File, "SIT", "Retail");
		driver = Selenium.openApplication(AppURLdictionary.get("URL"), "chrome");
	}

	// This is the starting of the Main Test Case
	@Test
	public void main() throws Exception {
		try {
			
			
			SFDC.login();
			//-------------------------
			// Create Lead and Opportunity
			//-------------------------
			//Dictionary Object Test Data for Creating an Opportunity
			Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","Create_Lead_Individual");
			SFDCOpportunities.createOpportunity(TestDatadictionary);
			
			//-------------------------
			// OPEN AND Fill Application
			//-------------------------
			//Dictionary Object Test Data for U65_Fulfillment
			TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","U65_Fulfillment_04");
				
			SFDCApplication.setAllMyInformation(SFDCApplication.setMyInofrmation(TestDatadictionary));
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			SFDCApplication.fillPlanForm(TestDatadictionary);
			SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
			SFDCApplication.receiveYourQuote("Mail");
			SFDCApplication.SaveAndExit();
			
			
			//----------------------------------------
			// Verify get the Quote REcord
			//----------------------------------------
			SFDC.NavigateToOpportunitiesFromHomePage();
			String OpportunityName=SFDCOpportunities.NavigationToOpportunitiesDetailPage();
			SFDCFullfillment.NewFullfillment_validation();
			String fullfillment_number = SFDCFullfillment.fullfillment_Number();
			String Quote_Num=SFDCFullfillment.quote_Number();
			
			
			//----------------------------------------
			// Search and Re-Send the Quote
			//----------------------------------------
			SFDCQuotes.search_Quote(Quote_Num);
			SFDCQuotes.openSearchedQuote();
			SFDCQuotes.saveSendQuoteOption(TestDatadictionary);
			
			
			//Verify Fullfillment Status Request
			SFDCOpportunities.search_Opportunity(OpportunityName);
			String Fulfillment_Status = SFDCFullfillment.fullfillment_Status();
			
			//Report Validation result
			if (Fulfillment_Status.toLowerCase().contains("request sent to cmaps")){
				Reporter.log("New individual fulfillment request is created and sent to CMPAS");
				Log.info("Fulfillment Request Status is: 'Request sent to CMAPS' ");
				Selenium.passTest("New individual fulfillment request is created and sent to CMPAS");
			}
			else{
				Reporter.log("New individual fulfillment request is Not created.");
				Log.info("Fulfillment Request Status is not as: 'Request sent to CMAPS' ");
				Selenium.failTest("New individual fulfillment request is NOT created.");
			}
				
			
			//Close browser
			driver.close();
			
			//--------------------------------------
			// Login with Fulfillment user access
			//--------------------------------------
			Map<String, String> AppURLdictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.Applications_URL_File,	"SIT", "Retail");
			driver = Selenium.openApplication(AppURLdictionary.get("URL"), "chrome");
			SFDC.login("Fullfillment",PageFullfilment.ELE_FULLFILLMENT_TAB);
			
			
			//----------------------------------------
			// Serach Fullfillment
			//------------------------------------------
			SFDCFullfillment.search_Fulfillment(fullfillment_number);
				
			
			
			//Test Case End	
	

		} catch (Exception e) {
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
