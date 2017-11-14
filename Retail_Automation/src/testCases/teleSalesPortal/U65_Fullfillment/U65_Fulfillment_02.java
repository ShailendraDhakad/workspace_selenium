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

public class U65_Fulfillment_02 extends BaseExtentReport{
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
	 	
		Map<String, String> AppURLdictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.Applications_URL_File,
				"SIT", "Retail");
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
			TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","U65_Fulfillment_01");
				
			SFDCApplication.setAllMyInformation(SFDCApplication.setMyInofrmation(TestDatadictionary));
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			SFDCApplication.fillPlanForm(TestDatadictionary);
			SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
			SFDCApplication.receiveYourQuote("Mail");
			SFDCApplication.SaveAndExit();
			
			
			//----------------------------------------
			// Verify the Fulfillment REcord
			//----------------------------------------
			SFDC.NavigateToOpportunitiesFromHomePage();
			SFDCOpportunities.NavigationToOpportunitiesDetailPage();
			SFDCFullfillment.NewFullfillment_validation();
			String fullfillment_number = SFDCFullfillment.fullfillment_Number();
			
			
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
			
			//----------------------------------------
			//Verify Fullfillment Details
			//----------------------------------------
			
			String Fullfillment_Num=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Fullfillment_Number", 1, 1);
			String Fullfillment_Type=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Fullfillment_Type", 1, 2);
			String Member_Name=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Member_Name", 2, 1);
			String Fullfillment_Status=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Fullfillment_Status", 2, 2);
			String Quote_Number=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Quote_Number", 3, 2);
			String Member_Address=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Member_Address", 4, 1);
			String Requested_DateTime=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Requested_DateTime", 5, 1);
			
			Selenium.validateFieldExistance(Fullfillment_Num,"FullFillment Number");
			Selenium.validateFieldExistance(Fullfillment_Type,"FullFillment Type");
			Selenium.validateFieldExistance(Member_Name,"Member Name");
			Selenium.validateFieldExistance(Fullfillment_Status,"FullFillment Status");
			Selenium.validateFieldExistance(Quote_Number,"Quote Number");
			Selenium.validateFieldExistance(Member_Address,"Member Address");
			Selenium.validateFieldExistance(Requested_DateTime,"Requested Date and Time");
			
			
			//=====================================================================U65_Fullfillment_03=====================================================================
			
			//------------------------------------------------------------
			// Edit FullFillment
			//-------------------------------------------------------------
			if (Selenium.isElementPresent("Edit",PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT)) {
				
				Reporter.log("Edit Button is present on Fullmment Detail Page.");
				Log.info("Edit Button is present on Fullmment Detail Page.");
				
				//Change Fullfillment Status
				SFDCFullfillment.editFulFillmentRequest_Option("CMAPS On Hold");
				
				//Change Fullfillment Status
				SFDCFullfillment.editFulFillmentRequest_Option("CMAPS Completed");
				
			}

			
			else {
				Reporter.log("Edit Button is not present on Fullmment Detail Page.");
				Log.error("Edit Button is not present on Fullmment Detail Page.");
				Selenium.failTest("Edit Button is not present on Fullmment Detail Page.");
			}
			
				

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
