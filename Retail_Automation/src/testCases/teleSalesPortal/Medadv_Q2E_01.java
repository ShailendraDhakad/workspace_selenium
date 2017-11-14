package testCases.teleSalesPortal;

import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModules.SFDC;
import appModules.SFDCApplication;
import appModules.SFDCLeads;
import appModules.SFDCMediCareApplication;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class Medadv_Q2E_01 extends BaseExtentReport{
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
	  public void MedAdv_Q2E_Lead_FillApplication() throws Exception {
		  try{	
//			
			Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","Medadv_Q2E_01");
//			
			SFDC.login();	
			
			//--------------------------
			//Create Lead
			//--------------------------
			SFDC.navigateToLeads();
			SFDCLeads.newLead(TestDatadictionary);
			SFDC.navigateToOpportunities();
			SFDCLeads.validateVIP();
			SFDCLeads.convertLead();
			SFDCLeads.navigateToMedicare();
			SFDCLeads.ValidateAndfillToMedicare(TestDatadictionary);
			
			//-------------------------------
			// Fill Medicare Application
			//-------------------------------
			SFDCMediCareApplication.fillComparePlanFormMedicare(TestDatadictionary);
			SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
			SFDCMediCareApplication.receiveYourQuoteMedicare(TestDatadictionary);
			SFDCMediCareApplication.EnrollNowMedicare();
			SFDCMediCareApplication.fillEnrollment(TestDatadictionary);
			SFDCMediCareApplication.verifyAddressInformation();
			SFDCMediCareApplication. MedicareInsuranceInformation(TestDatadictionary);
			SFDCMediCareApplication.selectPremiumOption("GetBill");
			SFDCMediCareApplication.selectImportantQuestion(TestDatadictionary);
			SFDCMediCareApplication.fillAttestationOfEligibility();
			SFDCMediCareApplication.fillApplicationAssistance(TestDatadictionary);
			SFDCMediCareApplication.fillImportantInformation();
			SFDCMediCareApplication.validateApplicationSummary();
			
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
