package testCases.teleSalesPortal;

import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModules.SFDC;
import appModules.SFDCApplication;
import appModules.SFDCLeads;
import appModules.SFDCMediCareApplication;
import appModules.SFDCOpportunities;
import pageObjects.PageOpportunities;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class Medadv_Q2E_05 extends BaseExtentReport{
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

			 
				Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","Medadv_Q2E_05");
				
				SFDC.login();
				
				//----------------------
				// Create Medicare  Lead
				//-----------------------
				SFDC.navigateToLeads();
				SFDCLeads.newLead(TestDatadictionary);
				SFDC.navigateToOpportunities();
				SFDCLeads.validateVIP();
				SFDCLeads.convertLead();
				SFDCLeads.navigateToMedicare();
				
				//----------------------
				// Fill Medicare Application
				//-----------------------
				SFDCLeads.ValidateAndfillToMedicare(TestDatadictionary);
				SFDCMediCareApplication.fillComparePlanFormMedicare(TestDatadictionary);
				SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
				SFDCMediCareApplication.receiveYourQuoteMedicareInvalidScenario();
				SFDCMediCareApplication.SaveAndExitMedicare();
				SFDCOpportunities.ValidateProbability("50%");
				SFDCOpportunities.NavigateToInProgressQuote();
				Selenium.sleep(90000);
				SFDCMediCareApplication.EnrollNowMedicare();
		  }
		  catch (Exception e){
			  Selenium.click("In Progress", "xpath=//*[@id='j_id0:j_id1:j_id2:j_id3:0:j_id5']/a");
				Selenium.getTableChildItem(PageOpportunities.Details.WEBTABLE_InProgressQuote, "Quote Table", 1, 2).click();
				Selenium.driver.findElement(By.xpath("//*[@class='opportunityBlock']//div[@class='pbBody']/table/tbody//a[contains(text(),'In Progress')]")).submit();
				Selenium.typeKey("In Progress", "xpath=//a[contains(text(),'In Progress')]", Keys.TAB);
			
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
