package testCases.teleSalesPortal.U65_Fullfillment;

import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
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

public class U65_Fulfillment_04 extends BaseExtentReport{
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
			
			Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","U65_Fulfillment_04");
					
			//--------------------------------------
			// Login with Fulfillment user access
			//--------------------------------------
			
			SFDC.login("Fullfillment",PageFullfilment.ELE_FULLFILLMENT_TAB);
			
			
			//----------------------------------------
			// Serach and Open any Fullfillment
			//------------------------------------------
			Selenium.click("Fullfillment Request Tab", PageFullfilment.ELE_FULLFILLMENT_TAB);
			SFDCFullfillment.selectFulfillmentView(TestDatadictionary);
			
			//Open any Request ID
			String RequestID= Selenium.getWebElement(PageFullfilment.FullfillmentRequests.LINK_FULLFILLMENT_FIRST_REQUEST_ID, "Resquest ID").getText();
			Selenium.click("Request ID", PageFullfilment.FullfillmentRequests.LINK_FULLFILLMENT_FIRST_REQUEST_ID);
			
			
			//----------------------------------------
			// Serach and Open any Fullfillment
			//------------------------------------------
			SFDCFullfillment.editFulFillmentRequest_Option(TestDatadictionary);
			
			
			//----------------------------------------
			//Validate Fulfillment status
			//----------------------------------------
			String FulfillmentStatus=SFDCFullfillment.search_Fulfillment(RequestID);
			SFDCFullfillment.validateFulfillmentStatus(TestDatadictionary,FulfillmentStatus);
			
				

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
