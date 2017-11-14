
package appModules;



import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pageObjects.PageAdminTool;
import pageObjects.PageAdminTool.PageAdminHome;
import pageObjects.PageAdminTool.PagePDFRepository;
import utility.Constant;
import utility.Log;
import utility.Selenium;
import utility.XMLUtil;

public class SBC_AdminTool {

	/**
	 * Log into the User SBC AdminTool home page
	 * 
	 * @param user  the AdminTool user email address
	 * @param pass  the AdminTool user password
	 */
	
	public static WebDriver driver;
	public static void login() {
		
		if ( SBC_AdminTool.isLoginPage() ) {
			Reporter.log("Logging into AdminTool Application.");
			Log.info("Logging into AdminTool Application");
			
			
			String user = XMLUtil.getXMLValue(Constant.Credential_File, "//Credentials/SIT/Project/AdminTool/UserName");
			String pass = XMLUtil.getXMLValue(Constant.Credential_File, "//Credentials/SIT/Project/AdminTool/Password");
			Selenium.type(PageAdminTool.TEXT_USERNAME,user);
			Selenium.type(PageAdminTool.TEXT_PASSWORD, pass);
			//Selenium.check("Remember Me", PageSFDC.CHECK_REMEMBERME);
			Selenium.click("Login",PageAdminTool.BUTTON_LOGIN);
			if ( Selenium.isElementPresent("Admin",PageAdminHome.Admin_Link)) {
				Log.info("SBC_AdminTool Login is sucessfull");
				Reporter.log("Logged into SBC_AdminTool Application");
				Selenium.passTest("Logging into SBC_AdminTool Application is passed",true);
				
			}
			else{
				Log.debug("SBC_AdminTool Login is Failed");
				Reporter.log("Logging into SBC_AdminTool Application is failed");
				Selenium.failTest("Logging into SBC_AdminTool Application is failed",true);
			}
		}
		else
		{
			Log.debug("Not at SBC_AdminTool Login page");
			Selenium.failTest("Failed authentication into the Admin Console!",true);
			Reporter.log("SBC_AdminTool Login is Failed");
		}

	}
	
	/*public static void login(String LoginType, String sVerifyPageLocator) {
		String user,pass;
		Log.info("Logging into SFDC Application");
		if ( SBC_AdminTool.isLoginPage() ) {
			Reporter.log("Logging into SFDC Application.");
			Log.info("Entering login credentials");
//			if (Selenium.isElementPresent("User Name", PageSFDC.LINK_ClEAR_USERNAME)){
//				Selenium.click("Clear User Name", PageSFDC.LINK_ClEAR_USERNAME);
				//Selenium.getWebElement(PageSFDC.TEXT_USERNAME, "User Name").clear();
				
		//	}
			if (LoginType.toLowerCase().equalsIgnoreCase("fullfillment")){
				 user = XMLUtil.getXMLValue(Constant.Credential_File, "//Credentials/SIT/Project/SFDC/Fulfillment/UserName");
				 pass = XMLUtil.getXMLValue(Constant.Credential_File, "//Credentials/SIT/Project/SFDC/Fulfillment/Password");
			}
			else{
				 user = XMLUtil.getXMLValue(Constant.Credential_File, "//Credentials/SIT/Project/SFDC/UserName");
				 pass = XMLUtil.getXMLValue(Constant.Credential_File, "//Credentials/SIT/Project/SFDC/Password");
			}
				
			Selenium.type(PageSFDC.TEXT_USERNAME,user);
			Selenium.type(PageSFDC.TEXT_PASSWORD, pass);
			Selenium.check("Remember Me", PageSFDC.CHECK_REMEMBERME);
			Selenium.click("Login",PageSFDC.BUTTON_LOGIN);
			boolean sResult = false;
			sResult = Selenium.isElementPresent("SFDC Home", sVerifyPageLocator);
			if(sResult){
				Log.info("SFDC Login is sucessfull");
				Reporter.log("Logged into SFDC Application");
				Selenium.passTest("Logging into SFDC Application is passed");
			}
			else{
				Log.debug("SFDC Login is Failed");
				Reporter.log("Logging into SFDC Application is failed");
				Selenium.failTest("Logging into SFDC Application is failed");
			}
		}
		else
		{
			Log.debug("Not at SFDC Login page");
			Selenium.failTest("Failed authentication into the Admin Console!");
			Reporter.log("SFDC Login is Failed");
		}

	}
	
*/	/* Verify at login page */

	public static boolean isLoginPage(){
		boolean sResult = false;
		sResult = Selenium.isElementPresent("FMSSIT1", PageAdminTool.TEXT_MESSAGE);
		if(sResult){
			Reporter.log("Application is at Login Page");
			Selenium.passTest("Application is at Login Page");
		}
		else{
			Reporter.log("Application is not at Login Page");
			Selenium.passTest("Application is Not at Login Page");
		}
		return sResult;
	}


/* Navigate to SBCpdfRepository */
public static void navigateTopdfRepository() {
	// return Selenium.isElementPresent(PageSFDC.VERIFY_AT_ELEMENT);
	boolean sResult = false;
	Reporter.log("Navigating to SBCpdfRepository Page");
	Selenium.click("SBC PDF Repository", PageAdminHome.SBC_PDF_Repository);
	sResult = Selenium.isElementPresent(PagePDFRepository.AlwaysUseThisSBC_dropDown);
	if(sResult){
		Reporter.log("Sucessfully Navigated to SBCpdfRepository Page");
		Log.info("Sucessfully Navigated to SBCpdfRepository Page");
		Selenium.passTest("Sucessfully Navigated to SBCpdfRepository Page");
	}
	else{
		Reporter.log("Navigation to SBCpdfRepository Page is failed");
		Log.error("Navigation to SBCpdfRepository Page is failed");
		Selenium.failTest("Navigation to SBCpdfRepository Page is failed");
	}
}

}

	/* Navigate to PersonalSearch */
	/*public static void navigateToPersonalSearch(){
		// return Selenium.isElementPresent(PageSFDC.VERIFY_AT_ELEMENT);
		boolean sResult = false;
		Reporter.log("Navigating to Personal search Page");
		Selenium.click("Personal search", PageSFDC.Menu.MENU_PERSONSEARCH);
		sResult = Selenium.isElementPresent(PagePersonSearch.BUTTON_BOTTOM_SEARCH);
		if(sResult){
			Reporter.log("Sucessfully Navigated to Personal search Page");
			Log.info("Sucessfully Navigated to Personal search Page");
			Selenium.passTest("Sucessfully Navigated to Personal search Page");
		}
		else{
			Reporter.log("Navigation to Personal search Page is failed");
			Log.error("Navigation to Personal search Page is failed");
			Selenium.failTest("Navigation to Personal search Page is failed");
		}
	}

	 Navigate to Leads 
	public static void navigateToLeads(){
		boolean sResult = false;
		Reporter.log("Navigating to Leads Page");
		Selenium.click("Leads", PageSFDC.Menu.MENU_LEADS);
		sResult = Selenium.isElementPresent(PageLeads.VERIFY_AT_ELEMENT);
		if(sResult){
			Reporter.log("Sucessfully Navigated to Leads Page");
			Log.info("Sucessfully Navigated to Leads Page");
			Selenium.passTest("Sucessfully Navigated to Leads Page");
		}
		else{
			Reporter.log("Navigation to Leads Page is failed");
			Log.error("Navigation to Leads Page is failed");
			Selenium.failTest("Navigation to Leads Page is failed");
		}
	}

	 Navigate to Accounts 
	public static void navigateToAccounts(){
		boolean sResult = false;
		Reporter.log("Navigating to Accounts Page");
		Selenium.click("Accounts Tab", PageSFDC.Menu.MENU_ACCOUNTS);
		
		sResult = Selenium.isElementPresent("New", PageAccounts.ELE_VERIFY_AT_ELEMENT);
		if(sResult){
			Reporter.log("Sucessfully Navigated to Accounts Page");
			Log.info("Sucessfully Navigated to Accounts Page");
			Selenium.passTest("Sucessfully Navigated to Accounts Page");
		}
		else{
			Reporter.log("Navigation to Accounts Page is failed");
			Log.error("Navigation to Accounts Page is failed");
			Selenium.failTest("Navigation to Accounts Page is failed");
		}
	}

	 Navigate to Opportunities 
	public static boolean navigateToOpportunities(){
		boolean flag=true;
		
		Selenium.clickButtons("Save", NewLead.BUTTON_BOTTOM_SAVE);	
		SFDCLeads.mergeCreateNewLead("Create New");
		if (Selenium.isElementPresent(PageOpportunities.BUTTON_NEWFULFILLMENTREQST)){
			Reporter.log("Lead info is Sucessfully saved.");
			Log.info("Lead info is Sucessfully saved.");
			Selenium.passTest("Lead info is Sucessfully saved. Navigation to Opportunity Page is successful.");
		}
		else{
			flag=false;
			Reporter.log("Lead info is not filled correctly.");
			Log.error("Navigation to Opportunity Page is failed.");
			Selenium.failTest("Lead info is not filled correctly.Navigation to Opportunity Page is failed.",true);
			
		}
		return flag;
	}

	
	
	 Navigate to Applications 
	public static boolean navigateToApplications(){
		return Selenium.isElementPresent(PageSFDC.VERIFY_AT_ELEMENT);
	}

	 Navigate to Campaigns 
	public static boolean navigateToCampaigns(){
		return Selenium.isElementPresent(PageSFDC.VERIFY_AT_ELEMENT);
	}

	 Navigate to Reports 
	public static boolean isNavigateToComparePlansPage(){
		boolean resultflag=true;
		if (Selenium.isElementPresent("Product Dropdown", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, true)){
			Reporter.log("Successsfully navigate to Compare Plans Page.");
			Selenium.passTest("Successsfully navigate to Compare Plans Page.");
		}
		else{
			Selenium.failTest("Navigate to Compare Plans Page failed.");
			Reporter.log("Navigate to Compare Plans Page failed.");
			resultflag=false;
		}
		return resultflag;
		
	}
	
	public static void clickNext(){
		Selenium.click("Next", PageOpportunities.MyInformation.BUTTON_NEXT);
	}

	public static void fillInvalidData(String TestData, String sLocator, String sErrorLocator){
		if (TestData.contains(";")){
			String ArrTestData[]=TestData.split(";");
			for (int iData=0; iData<ArrTestData.length;iData++){
					Selenium.type(sLocator, ArrTestData[iData]);
						
					if (Selenium.isElementPresent("Error Message",sErrorLocator,  true)){
						Reporter.log(ArrTestData[iData] +" : Invalid Data is Validated successfully");
						Log.info(ArrTestData[iData] +" : Invalid Data is Validated successfully");
					}
					else{
						Reporter.log(ArrTestData[iData] +" : Invalid Data is Not Validated");
						Log.error(ArrTestData[iData] +" : Invalid Data is Not Validated");
					}
			}
		}
		else
			Log.error("Invalid date format for '" + TestData +"' in Test Data sheet");
				
	}
	
	public static void NavigateToOpportunitiesFromHomePage(){
		boolean sResult = false;
		Reporter.log("Navigating to Opportunities Page");
		Selenium.click("Opportunities", PageSFDC.Menu.MENU_OPPORTUNITIES);
		sResult = Selenium.isElementPresent(PageOpportunities.ITEM_OPPORTUNITYNAME);
		if(sResult){
			Reporter.log("Sucessfully Navigated to Opportunities Page");
			Log.info("Sucessfully Navigated to Opportunities Page");
			Selenium.passTest("Sucessfully Navigated to Opportunities Page");
		}
		else{
			Reporter.log("Navigation to Opportunities Page is failed");
			Log.error("Navigation to Opportunities Page is failed");
			Selenium.failTest("Navigation to Opportunities Page is failed");
		}
	}
	
	
	public static void selectFromPoPUpTable(String sTableLocator,String TextLocator, String TextValue , String SearchLocator ,String sSelectLocator,String winHandleBefore){
		try {
			
			
			if (TextValue.toString() !=null &&  !TextValue.toString().isEmpty()){
				if (!TextValue.equalsIgnoreCase("Dynamic")){
				Selenium.waitForElement(Selenium.driver, TextLocator);
				//Fill the text box
				Selenium.type(TextLocator, TextValue);
				Selenium.click(SearchLocator, "Search");
				Thread.sleep(2000);							
				String SelectLinkLocator = sSelectLocator.replace("INDEX","0");
				Selenium.click("Select", SelectLinkLocator);	
				}
			
				else{
					Selenium.click(SearchLocator, "Search");
					
					// Here we are getting the webTable Element
					WebElement htmltable = Selenium.getWebElement(sTableLocator,"Table");
					Thread.sleep(2000);		
					
						* tag <tr> represents the rows of a Table. Here we are getting
						* the total rows of a table and selecting any row randomly
					
					List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
					int TotalRows = rows.size();
					int randomNumber;
					randomNumber = (int) (Math.random() * (TotalRows - 1)) + 1;
					String strRandomNumber = Integer.toString(randomNumber);
					String SelectLinkLocator = sSelectLocator.replace("INDEX",strRandomNumber);
					Selenium.click("Select", SelectLinkLocator);
				}
					Thread.sleep(5000);
		
				// Accept the browser Message as Yes
				if (Selenium.isAlertPresent())
					Selenium.driver.switchTo().alert().accept();							

				// Switch back to original browser (first window)
				Selenium.driver.switchTo().window(winHandleBefore);
		
			}
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void fillIndividualApplication(Map<String, String> TestDatadictionary) throws Exception {
		  try{	
			 
			//Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","U65_Q2E_01_updated");

//			SFDC.login();	 
			//---------------------------------
			// Create New Lead
			//---------------------------------
//			 
//			SFDC.navigateToLeads();
//			SFDCLeads.newLead(TestDatadictionary);
//			SFDC.navigateToOpportunities();
//			SFDCLeads.validateVIP();
//			SFDCLeads.convertLead();	//convert Lead and Validate Lead Info Page
			
			//----------------------------------
			//Fill Individual Application
			//----------------------------------
			SFDCApplication.setAllMyInformation(SFDCApplication.setMyInofrmation(TestDatadictionary));
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			SFDCApplication.myFamilyForm(TestDatadictionary);
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);		
			SFDCApplication.fillPlanForm(TestDatadictionary);
			SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
			SFDCMediCareApplication.receiveYourQuoteMedicare(TestDatadictionary);
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
		  }
			catch (Exception e){
				  Utils.takeScreenshot(Selenium.driver, Selenium.sTestCaseName);
				  Log.error(e.getMessage());
				  throw (e);
			  }
				
		  }
	
	public static void createOpportunity(Map<String, String> TestDatadictionary) throws Exception {
		  try{	
			 
			//Map<String, String> TestDatadictionary = ExcelUtils.ReadExcelRowByIndexValue(Constant.TestData_File,"SIT","U65_Q2E_01_updated");

//			SFDC.login();	 
			//---------------------------------
			// Create New Opportunity
			//---------------------------------
//			 
			SBC_AdminTool.NavigateToOpportunitiesFromHomePage();
			
			Selenium.click("New", PageOpportunities.BUTTON_NEW);
			LeadData objLeaddata = SFDCLeads.SetNewLeadData(TestDatadictionary);
			Selenium.select("Lead Type",PageOpportunities.SELECT_OPPORTUNITIES_RECORDTYPE, objLeaddata.getLeadType());
			Selenium.click("Continue", PageOpportunities.BUTTON_CONTINUE);
			
			Selenium.type("Account NAme", PageOpportunities.TEXT_ACCOUNTNAME, objLeaddata.getFirstName() + " " + objLeaddata.getLastName());
			Selenium.clickButtons("Save", PageLeads.NewLead.BUTTON_BOTTOM_SAVE);
			SBC_AdminTool.navigateToOpportunities();
//			SFDCLeads.validateVIP();
//			SFDCLeads.convertLead();	//convert Lead and Validate Lead Info Page
			
			//----------------------------------
			//Fill Individual Application
			//----------------------------------
			SFDCApplication.setAllMyInformation(SFDCApplication.setMyInofrmation(TestDatadictionary));
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			SFDCApplication.myFamilyForm(TestDatadictionary);
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);		
			SFDCApplication.fillPlanForm(TestDatadictionary);
			SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
			SFDCMediCareApplication.receiveYourQuoteMedicare(TestDatadictionary);
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
		  }
			catch (Exception e){
				  Utils.takeScreenshot(Selenium.driver, Selenium.sTestCaseName);
				  Log.error(e.getMessage());
				  throw (e);
			  }
				
		  }

}



*/