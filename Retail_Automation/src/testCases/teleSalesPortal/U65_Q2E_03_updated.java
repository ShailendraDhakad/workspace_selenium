package testCases.teleSalesPortal;

import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModules.SFDC;
import appModules.SFDCApplication;
import appModules.SFDCLeads;
import appModules.SFDCMediCareApplication;
import appModules.SFDCOpportunities;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class U65_Q2E_03_updated extends BaseExtentReport {
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
		 	
		 	//Initiate Extennt Reporter
		 	test=extent.createTest(Utils.getTestCaseName(this.toString()));
		 	AdvanceReporting.setTest(test);
		 	
		 	 Map<String, String> AppURLdictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.Applications_URL_File,"SIT","Retail");
		 	driver = Selenium.openApplication(AppURLdictionary.get("URL"), "chrome");
//		 	DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();
//			handlSSLErr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//			 WebDriver driver = new ChromeDriver (handlSSLErr);
			
		 	  
	}
	  
	  // This is the starting of the Main Test Case
	  @Test
	  public void U65_Q2E_FillApplication_Payment() throws Exception {
		  try{	
			
			Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","U65_Q2E_03_Updated");
			
			SFDC.login();

			//-------------------------
			// Create Lead
			//-------------------------
			SFDC.navigateToLeads();
			SFDCLeads.newLead(TestDatadictionary);
			SFDC.navigateToOpportunities();
			SFDCLeads.validateVIP();
			SFDCLeads.convertLead();
			
			//-------------------------------
			// Fill Application
			//-------------------------------
			SFDCApplication.setAllMyInformation(SFDCApplication.setMyInofrmation(TestDatadictionary));
			SFDCApplication.verifyMyInformationPage();
			SFDCApplication.fillMyFamilyForm(TestDatadictionary);
			SFDCApplication.fillComparePlanForm(TestDatadictionary);
			SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
			SFDCApplication.receiveYourQuote("EMail");
			SFDCApplication.EnrollNow();
			SFDCApplication.fillApplicationSubInfo(TestDatadictionary);
			SFDCApplication.selectPCP(TestDatadictionary, Selenium.driver);
			SFDCApplication.fillApplicationDepInfo(TestDatadictionary);
			SFDCApplication.fillCommunicationPref(TestDatadictionary);
			SFDCApplication.clickCommunicationPrefNext();
			SFDCApplication.fillAdditionalEMailForm(TestDatadictionary);
			SFDCApplication.validateReviewYourSelection(TestDatadictionary);
			SFDCApplication.fillTermsAndConditions();
			SFDCApplication.validateApplicationCompleted();

			//-------------------------------
			// Fill Payment Page
			//-------------------------------
			SFDCOpportunities.AcceptCertificate();
			SFDCMediCareApplication.ValidateReviewPage();
			SFDCOpportunities.ValidatePaymentOptionPage();
			SFDCOpportunities.fillAccountDetails(TestDatadictionary);
			SFDCOpportunities.verifyAutopayOption();
			SFDCOpportunities.verifyPaymentPage();
			SFDCOpportunities.paymentConfirmationPage(); 
			//System.out.println(Selenium.getWebElement("xpath=/html/body", "Review", true).getText());
		  }
		  catch (Exception e){
			  Utils.takeScreenshot(driver, Selenium.sTestCaseName);
			  Log.error(e.getMessage());
			  throw (e);
		  }
			
	  }
			
	  // Its time to close the finish the test case		
	  @AfterMethod
	  public void afterMethod(ITestResult result) {
		    // Printing beautiful logs to end the test case
		    Log.endTestCase(Selenium.sTestCaseName);
		    		   	    
		    // Closing the opened driver
		    // Selenium.driver.close();
	  }
	  
	    
	  

}
