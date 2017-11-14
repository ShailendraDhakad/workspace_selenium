package testCases.teleSalesPortal.U65_Fullfillment;

import java.util.List;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModules.SFDC;
import appModules.SFDCApplication;
import appModules.SFDCFullfillment;
import appModules.SFDCOpportunities;
import pageObjects.PageFullfilment;
import pageObjects.PageSFDC;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class U65_Fulfillment_05 extends BaseExtentReport{
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
	 	
		Map<String, String> AppURLdictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.Applications_URL_File,"SIT", "Retail");
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
			// Serach Fullfillment Request
			//------------------------------------------
			
			
			Selenium.click("Fullfillment Request Tab", PageFullfilment.ELE_FULLFILLMENT_TAB);
			SFDCFullfillment.selectFulfillmentView(TestDatadictionary);
			
			//get Fulfillment ID of First entry
			Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.CHECKBOX_ALL);
			String RequestID= Selenium.getWebElement(PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_FIRST_REQUEST_ID, "Resquest ID").getText();
			Selenium.click("Select All", PageFullfilment.FullfillmentRequests.CHECKBOX_ALL);
			
			Actions action = new Actions(driver);
			WebElement btnElement=Selenium.getWebElement(PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_FIRST_REQUEST_STATUS, "Request Status in Table");
			action.doubleClick(btnElement).build().perform();
			
			//Change Fullfillment edit status to CMAPS Completed
			Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.SELECT_FULLFILLMENT_EDIT_STATUS);
			Selenium.select("Fulfillment Status",PageFullfilment.FullfillmentRequests.SELECT_FULLFILLMENT_EDIT_STATUS, "CMAPS Completed");
			Selenium.selectCheckBox("Single or Mass Apply Radio", PageFullfilment.FullfillmentRequests.RADIO_MASS_APPLY, 1);
			Selenium.click("Save", PageFullfilment.FullfillmentRequests.BUTTON_SAVE);
			
			//CLICK rEFRESH
			Selenium.click("Refresh", PageFullfilment.FullfillmentRequests.BUTTON_REFRESH);
			Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_FIRST_REQUEST_ID);
			if (!Selenium.getWebElement(PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_FIRST_REQUEST_ID, "Resquest ID").getText().equalsIgnoreCase(RequestID)){
				Reporter.log("Fulfillment Sttaus is successfully changed to CMAPS Complted.");
				Selenium.passTest("Fulfillment Sttaus is successfully changed to CMAPS Complted.");
			}
			else{
				Reporter.log("Fulfillment Sttaus is Not changed to CMAPS Complted.");
				Selenium.failTest("Fulfillment Sttaus is Not changed to CMAPS Complted.");
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
