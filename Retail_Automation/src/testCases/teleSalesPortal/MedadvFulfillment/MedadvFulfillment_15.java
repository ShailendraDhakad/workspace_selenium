package testCases.teleSalesPortal.MedadvFulfillment;

import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
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
import pageObjects.PageFullfilment;
import pageObjects.PageOpportunities;
import pageObjects.PageSFDC;
import pageObjects.PageSFDCHome;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.StringUtil;
import utility.Utils;

public class MedadvFulfillment_15 extends BaseExtentReport{
	public WebDriver driver;
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
			Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","MedAdv_Fulfillment_15");
			String opportunityName=SFDCOpportunities.createOpportunity(TestDatadictionary);
			SFDCLeads.navigateToMedicare();
			SFDCLeads.ValidateAndfillToMedicare(TestDatadictionary);
			//-------------------------
			// OPEN AND Fill Application
			//-------------------------
	
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
			
			
			//------------------------------------------------------------
			// Edit FullFillment
			//-------------------------------------------------------------
			if (Selenium.isElementPresent("Edit",PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT)) {
				
				Reporter.log("Edit Button is present on Fullmment Detail Page.");
				Log.info("Edit Button is present on Fullmment Detail Page.");
				
								
				//Change Fullfillment Status
				SFDCFullfillment.editFulFillmentRequest_Option("CMAPS Completed");
				
			}

			
			else {
				Reporter.log("Edit Button is not present on Fullmment Detail Page.");
				Log.error("Edit Button is not present on Fullmment Detail Page.");
				Selenium.failTest("Edit Button is not present on Fullmment Detail Page.");
			}
			
			//---------------------------------------------------------------
			// Login as Telesales User
			//----------------------------------------------------------------
			AppURLdictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.Applications_URL_File,	"SIT", "Retail");
			driver = Selenium.openApplication(AppURLdictionary.get("URL"), "chrome");
			SFDC.login();
			
			String sTaskFilter="All Open";
			Selenium.waitForElement(Selenium.driver, PageSFDCHome.MyTask.SELECT_MYTASKLIST);
			Selenium.select("Task List", PageSFDCHome.MyTask.SELECT_MYTASKLIST, sTaskFilter);
			Selenium.sleep(5000);
			
			//Open the Task based on fulfillment ID
			int Rows=Selenium.getTableRowCount(PageSFDCHome.MyTask.TABLE_MYTASK, "My Task Table");
			int irow=0;
			String FRNum="";
			for (irow=1 ;irow<=Rows; irow++){
				FRNum=Selenium.getTableCellData(PageSFDCHome.MyTask.TABLE_MYTASK, "My Task Table", irow, 5);
				if (Fullfillment_Num.equalsIgnoreCase(FRNum)){
					break;
				}
			}
			
			String sLocator=PageSFDCHome.MyTask.LINK_MYTASK_FULFILLMENT.replace("RowNum", StringUtil.intToString(irow));
			Selenium.click("Fulfillment", sLocator);
			
			
			//Validate Fulfillment Task
			Selenium.waitForElement(Selenium.driver, PageSFDCHome.TaskFulfillment.ELE_FULFILLMENT_SUBJECT);
			Selenium.ValidateObjectAndText("Fufillment Subject", PageSFDCHome.TaskFulfillment.ELE_FULFILLMENT_SUBJECT, "Fulfillment", true);
			Selenium.ValidateObjectAndText("Fufillment Comments", PageSFDCHome.TaskFulfillment.ELE_FULFILLMENT_COMMENTS, "Scheduled call back Fulfillment requested.", true);
			
			
			
			
			

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
