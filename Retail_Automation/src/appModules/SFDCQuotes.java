package appModules;

import java.util.Map;

import org.testng.Reporter;

import pageObjects.PageFullfilment;
import pageObjects.PageSFDC;
import utility.Log;
import utility.Selenium;
import utility.Utils;

public class SFDCQuotes {
	
	public static void createNewMedicareQuote(Map<String, String> TestDatadictionary) throws Exception {
		  try{	
			 		
			//----------------------------------
			//Fill Opportunities
			//----------------------------------
			SFDCLeads.ValidateAndfillToMedicare(TestDatadictionary);
			SFDCMediCareApplication.fillPlanFormMedicare(TestDatadictionary);
			SFDCApplication.fillBasicSubscriberInfo(TestDatadictionary);
			SFDCMediCareApplication.receiveYourQuoteMedicare(TestDatadictionary);
			SFDCMediCareApplication.EnrollNowMedicare();
			SFDCMediCareApplication.fillEnrollment(TestDatadictionary);
			SFDCApplication.fillAddressInformation(TestDatadictionary);
			SFDCMediCareApplication. MedicareInsuranceInformation(TestDatadictionary);
			SFDCMediCareApplication.selectPremiumOption("GetBill");
			SFDCMediCareApplication.selectImportantQuestion(TestDatadictionary);
			SFDCMediCareApplication.fillAttestationOfEligibility();
			SFDCMediCareApplication.fillApplicationAssistance(TestDatadictionary);
			SFDCMediCareApplication.fillImportantInformation();
			SFDCMediCareApplication.validateApplicationSummary();

		  }
		  catch (Exception e){
			Utils.takeScreenshot(Selenium.driver, Selenium.sTestCaseName);
			Log.error(e.getMessage());
			throw (e);
		  }
				
	}
	
	public static String search_Quote(String Quote_Number) {
		String QuoteName="";
		
		if (Selenium.isElementPresent(PageFullfilment.TEXT_FULLFILLMENT_SERACH_INPUT)) {
			// Enter Fulfillment number
			Selenium.click("Search input fieled for Quote number",PageFullfilment.TEXT_FULLFILLMENT_SERACH_INPUT);
			Selenium.type("Enter the Fullfillment Created by the user", PageFullfilment.TEXT_FULLFILLMENT_SERACH_INPUT,Quote_Number, true);
			Selenium.click("Search Button", PageFullfilment.BUTTON_FULLFILLMENT_SEARCH);
			Selenium.sleep(2000);
			//Selenium.ValidateObjectAndText("Quote Search result check",PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_SERACH_CHECK, Quote_Number, true);
			

			if (Selenium.isElementPresent("Select the record", PageSFDC.Quote.LINK_SEARCH_QUOTE_NAME)) {
				
				QuoteName=Selenium.getWebElement(PageSFDC.Quote.LINK_SEARCH_QUOTE_NAME, "Quote Name").getText();
				Reporter.log("Quote Name found in search Results Sucessfully: " + QuoteName);
				Selenium.passTest("Quote Name found in search Results  Sucessfully: " + QuoteName);	
			}

			else {
				Reporter.log("Quote Name  NOT found in search Results.");
				Selenium.failTest("Quote Name  NOT found in search Results.");
			}
		}

		else {
			Reporter.log("Search page is not loaded");
			Selenium.failTest("Search page is not loaded");
		}
		return QuoteName; 
	}
	
	public static void openSearchedQuote(){
		
		Selenium.waitForElement(Selenium.driver, PageSFDC.Quote.LINK_SEARCH_QUOTE_NAME);
		Selenium.click("Select the record", PageSFDC.Quote.LINK_SEARCH_QUOTE_NAME);
		Selenium.waitForElement(Selenium.driver, PageSFDC.Quote.BUTTON_MAILEMAILQUOTE);
		if (Selenium.isElementPresent("Mail/Email Quote", PageSFDC.Quote.BUTTON_MAILEMAILQUOTE)){
			Reporter.log("Quote Detail Page is displayed successfully.");
			Selenium.passTest("Quote Detail Page is displayed successfully.");	
		}
		else {
			Reporter.log("Quote Detail Page is NOT displayed.");
			Log.error("Mail/Email Quote Button is not present.");
			Selenium.failTest("Quote Detail Page is NOT displayed.");
		}
	}
	
	public static void saveSendQuoteOption(Map<String, String> TestDatadictionary){

		String SaveQuoteOption=TestDatadictionary.get("SaveQuoteOption");
		
		Selenium.click("Mail/Email Quote", PageSFDC.Quote.BUTTON_MAILEMAILQUOTE);
		Selenium.waitForElement(Selenium.driver, PageSFDC.Quote.BUTTON_SAVE_MAIL_QUOTE);
		if(Selenium.isElementPresent("Save and Mail Quote", PageSFDC.Quote.BUTTON_SAVE_MAIL_QUOTE)){
			Reporter.log("PDF Preview Page is displayed successfully.");
			Selenium.passTest("PDF Preview Page is displayed successfully.");				
		}
		else {
			Reporter.log("PDF Preview Page is NOT displayed.");
			Log.error("Save and Mail Quote Button is not present.");
			Selenium.failTest("PDF Preview Page is NOT displayed.");
		}
		
		
		if (SaveQuoteOption.toLowerCase().contains("mail"))
			Selenium.click("Save and mail Quote", PageSFDC.Quote.BUTTON_SAVE_MAIL_QUOTE);
		
		else if(SaveQuoteOption.toLowerCase().contains("email"))
			Selenium.click("Save and Email Quote", PageSFDC.Quote.BUTTON_SAVE_EMAIL_QUOTE);
		
		else
			Selenium.click("Save and Email Quote", PageSFDC.Quote.BUTTON_SAVE_EMAIL_QUOTE);
		
		Selenium.sleep(2000);
		if (Selenium.isAlertPresent()){
			Reporter.log("Quote is saved and send successfully. " + Selenium.driver.switchTo().alert().getText());
			Selenium.passTest("Quote is saved and send successfully " + Selenium.driver.switchTo().alert().getText());
			Selenium.handleBrowserPopUp("Ok");
		}
		else{
			Reporter.log("Quote is Not saved and send on PDF Preview Page");
			Selenium.failTest("Quote is Not saved and send on PDF Preview Page");
		}
		
	}
	
}
	
	  
	

