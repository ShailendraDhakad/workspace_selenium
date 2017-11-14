package testCases.teleSalesPortal.U65_Fullfillment;

import java.util.Map;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import appModules.SFDC;
import appModules.SFDCApplication;
import appModules.SFDCFullfillment;
import appModules.SFDCOpportunities;
import pageObjects.PageSFDC;
import utility.AdvanceReporting;
import utility.BaseExtentReport;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class U65_Fulfillment_09 extends BaseExtentReport{

	public  WebDriver driver;
	Map<String, String> TestDatadictionary;
	
			
	@BeforeMethod
	  public void beforeMethod() throws Exception {
			
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
			
			String Opportuunity="";
			
			SFDC.login();
			//-------------------------
			// Create Lead and Opportunity
			//-------------------------
			//Dictionary Object Test Data for Creating an Opportunity
			Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","Create_Lead_Individual");
			Opportuunity=SFDCOpportunities.createOpportunity(TestDatadictionary);
			
			//-------------------------
			// OPEN AND Fill Application
			//-------------------------
			//Dictionary Object Test Data for U65_Fulfillment
			TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","U65_Fulfillment_01");
				
			//Fill Application
			SFDCApplication.setAllMyInformation(SFDCApplication.setMyInofrmation(TestDatadictionary));
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			SFDCApplication.fillPlanForm(TestDatadictionary);
			SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
			SFDCApplication.receiveYourQuote("Mail");
			SFDCApplication.SaveAndExit();
			
			//Verify the Fulfillment REcord   
			SFDC.NavigateToOpportunitiesFromHomePage();
			SFDCOpportunities.NavigationToOpportunitiesDetailPage();
			SFDCFullfillment.NewFullfillment_validation();
			
			//Click Inprogress Link
			SFDCOpportunities.NavigateToInProgressQuote();
			
			
			//Complete the Application form
			SFDCApplication.receiveYourQuote("Mail");
			SFDCApplication.EnrollNow();
			SFDCApplication.fillApplicationSubInfo(TestDatadictionary);
			SFDCApplication.selectPCP(TestDatadictionary, Selenium.driver);
			SFDCApplication.fillApplicationDepInfo(TestDatadictionary);
			SFDCApplication.fillCommunicationPref(TestDatadictionary);
			SFDCApplication.clickCommunicationPrefNext();
			SFDCApplication.fillAdditionalEMailForm(TestDatadictionary);
			SFDCApplication.validateReviewYourSelection(TestDatadictionary);
			SFDCApplication.fillTermsAndConditions();
					
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
		     Selenium.driver.close();
	  }
	  
	    
	 
	  
}

