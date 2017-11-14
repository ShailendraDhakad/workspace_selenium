package appModules;

import java.util.Map;

import org.testng.Reporter;

import pageObjects.PageAccounts;
import testData.AccountDetails;
import utility.Log;
import utility.Selenium;

public class SFDCAccounts {
	public static  AccountDetails objLeadData = new AccountDetails();
	
	
	//Select Account for new Opportunity
	public static void selectAccount(){
		if (Selenium.isElementPresent(PageAccounts.BUTTON_NEW)){
			Reporter.log("Accounts info is Sucessfully saved");
			Log.info("Accounts info is Sucessfully saved");;
			Selenium.passTest("Navigation to Accounts Page is successful");
			Selenium.click("Account is selected", PageAccounts.SelectAccount.ELE_ACCOUNT_NAME);
		}
		else{
			Reporter.log("Accounts info is not filled correctly");
			Selenium.failTest("Navigation to Accounts Page is failed");
			Log.error("Navigation to Accounts Page is failed");
		}
	}
	
	//Navigate to New Opportunity page for selected Account
	public static void Account_DetailsPage(){
		if (Selenium.isElementPresent(PageAccounts.Account_NewOpportunity.VERIFY_PAGE)){
			Reporter.log("Accounts Details page is Sucessfully loaded");
			Log.info("Accounts Details page is Sucessfully loaded");;
			Selenium.passTest("Accounts Details page is Sucessfully loaded");
			
		}
		else{
			Reporter.log("Accounts Details page is not loaded");
			Selenium.failTest("Navigation to Accounts details page  is failed.");
			Log.error("Navigation to Accounts details page is failed.");
		}
		
		//Select New Opportunity button
		if (Selenium.isElementPresent(PageAccounts.Account_NewOpportunity.BUTTON_NEW_OPPORTUNITY)){
			Reporter.log("Accounts Opportunity details is Sucessfully loaded");
			Log.info("Accounts Opportunity details is Sucessfully loaded");;
			Selenium.passTest("Accounts Opportunity details is Sucessfully loaded");
			Selenium.click("New Opportunity is Clicked", PageAccounts.Account_NewOpportunity.BUTTON_NEW_OPPORTUNITY);
		}
		else{
			Reporter.log("Accounts Opportunity details is not loaded");
			Selenium.failTest("Navigation to Accounts Opportunity details is failed.");
			Log.error("Navigation to Accounts Opportunity details is failed.");
		}	

	}
	
	
	
	//Select Opportunity Record Type
	public static void Select_Opportunity_Record_Type(Map<String, String> TestDataDictionary){
		objLeadData.setLeadType(TestDataDictionary.get("LeadType"));
		
		if (Selenium.isElementPresent(PageAccounts.Account_Opportunity_Recordtype.ELE_VERIFY_PAGE_ELEMENT)){
			Reporter.log("Select Opportunity Record Type page is Sucessfully loaded");
			Log.info("Select Opportunity Record Type page is Sucessfully loaded");;
			Selenium.passTest("Select Opportunity Record Type page is Sucessfully loaded");
					
			Selenium.click("Record types Dropdown", PageAccounts.Account_Opportunity_Recordtype.ELE_SELECT_RECORD_TYPE);
			
			if (objLeadData.getLeadType() != null)
				Selenium.select("Record types Dropdown", PageAccounts.Account_Opportunity_Recordtype.SELECT_RECORD_TYPE, objLeadData.getLeadType());
				
			//Click Continue
			Selenium.click("Continue Button", PageAccounts.Account_Opportunity_Recordtype.BUTTON_CONTINUE);
		}
		else{
			Reporter.log("Select Opportunity Record Type page is not loaded");
			Log.error("Navigation to Select Opportunity Record Types page is failed.");
			Selenium.failTest("Navigation to Select Opportunity Record Type page  is failed.");
			
		}
	}


			public static void Account_Opportunity_Save(){
				Selenium.sleep(2000);
				Selenium.waitForElement(Selenium.driver, PageAccounts.Account_Opportunity_Edit.BUTTON_SAVE);
				
				if (Selenium.isElementPresent(PageAccounts.Account_Opportunity_Edit.BUTTON_SAVE)){
					Reporter.log("Opportunity Edit page is Sucessfully loaded");
					Log.info("Opportunity Edit page is Sucessfully loaded");;
					Selenium.passTest("Opportunity Edit page is Sucessfully loaded");
					Selenium.click("Save Button is clicked", PageAccounts.Account_Opportunity_Edit.BUTTON_SAVE);
				}
				else{
					Reporter.log("Opportunity Edit page is not loaded");
					Selenium.failTest("Navigation to Opportunity Edit page  is failed.");
					Log.error("Navigation to Opportunity Edit page is failed.");
				}
			}
	
	

}
