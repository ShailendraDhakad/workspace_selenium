
package appModules;


import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import pageObjects.PageFullfilment;
import pageObjects.PageOpportunities;
import pageObjects.PageSFDC;
import testData.AccountDetails;
import testData.AdditionalEmailData;
import testData.AddressInformation;
import testData.ApplicationSubInfoData;
import testData.BasicSubscriberInfoData;
import testData.CommunicationPrefData;
import testData.ComparePlanData;
import testData.Enrollment;

import testData.MyFamilyData;
import testData.MyInformationData;

import utility.Log;
import utility.Selenium;


public class SFDCOpportunities {
	
	
	
	public static MyInformationData objMyInfoData=new MyInformationData();
	public static MyFamilyData objMyFamilyData=new MyFamilyData();
	public static ComparePlanData objComparePlan=new ComparePlanData();
	public static BasicSubscriberInfoData objBasicSubscriberInfo=new BasicSubscriberInfoData();
	public static ApplicationSubInfoData objApplicationInfo=new ApplicationSubInfoData();
	public static CommunicationPrefData objCommunicationPref=new CommunicationPrefData();
	public static AdditionalEmailData objAdditionalEMail=new AdditionalEmailData();
	public static SFDCLeads objLead=new SFDCLeads();
	public static AccountDetails accdetail = new AccountDetails();
	public static Enrollment objenrollment= new Enrollment();
	public static AddressInformation objAddressInfo =new AddressInformation(); 
	
	public static String createOpportunity(Map<String, String> TestDataDictionary){
		String Opportunity_Name="";
		
		try{
			//-------------------------
			// Create Lead and Opportunity
			//-------------------------
			SFDC.navigateToLeads();
			SFDCLeads.newLead(TestDataDictionary);
			SFDC.navigateToOpportunities();
			SFDCLeads.validateVIP();
			Opportunity_Name=SFDCLeads.convertLead();
			Reporter.log("Opportunity Successfully Created.");
			Selenium.passTest("Opportunity Successfully Created.");
			
		}
		catch(Exception e){
			Log.error("Error in Creating an Opportunity. Exception:"+e.getMessage());
			Selenium.passTest("Error in Creating an Opportunity.");
		}
		
	
		return Opportunity_Name;
		
	}

	public static String NavigationToOpportunitiesDetailPage(){
		String OpportunityName="";
		if (Selenium.isElementPresent("Opportunitie page", PageOpportunities.ITEM_OPPORTUNITYNAME, true)){
			Reporter.log("Opportunities page is available");
			Selenium.passTest("Opportunities page is available");
			OpportunityName=Selenium.getWebElement(PageOpportunities.ITEM_OPPORTUNITYNAME, "Opportunitie Name").getText();
			Selenium.click("Opportunitie Name", PageOpportunities.ITEM_OPPORTUNITYNAME);
			if (Selenium.isElementPresent("Opportunity Name", PageOpportunities.Details.Element_OpportunityName,true)){
				Reporter.log("User is successfully navigated to opportunities detail page");
				Selenium.passTest("User is successfully navigated to opportunities detail page");
			}
			else
			{
				Selenium.failTest("Navigation to opportunities detail page failed");
				Reporter.log("Navigation to opportunities detail page failed");
			}
		}
		return OpportunityName;
		}
	
	public static void NavigationToQuoteEnrollPage(){
		
		if (Selenium.isElementPresent( "Quote Enroll button", PageOpportunities.Details.BUTTON_QUOTEENROLL, true)){
			Reporter.log("Quote Enroll button is available");
			Selenium.passTest("Quote Enroll button is available");
			Selenium.click("Quote Enroll button", PageOpportunities.Details.BUTTON_QUOTEENROLL);
			//verifyMyInformationPage();
		}
		else
		{
			Reporter.log("Quote Enroll button not available");
			Selenium.failTest("Quote Enroll button not available");
		}
	}

	public static void ValidateFulfillmentRequest(){
		
		//Check if fullfillment section is updated
		Selenium.waitForElement(Selenium.driver, PageOpportunities.Details.Element_FulfillmentStatus);
		
		if (Selenium.isElementPresent("Fulfillment Status", PageOpportunities.Details.Element_FulfillmentStatus))
			Reporter.log("Fulfillment status is Validated.");
		else{
			Log.error("Fulfillment status is not updated as desired.");
			Selenium.failTest("Fulfillment status is not updated as desired.");
			Reporter.log("Fulfillment status is not updated as desired.");
		}
	}
	
	public static void NavigateToInProgressQuote(){
		
		
		//Selenium.driver.switchTo().frame(PageOpportunities.Details.Frame_quote);
		if (Selenium.isElementPresent( "In Progress button under quote section", PageOpportunities.Details.Element_Probability)){
			Reporter.log("In Progress button under quote section is available");
			Selenium.passTest("In Progress button under quote section is available");
			Selenium.driver.findElement(By.xpath("//a[contains(text(),'In Progress')]")).click();
			
			Selenium.getWebElement(PageOpportunities.Details.Element_InProgressQuote, "In Progress", false).click();
			//Selenium.click("In Progress", PageOpportunities.Details.Element_InProgressQuote);
		}
		else
		{
			Reporter.log("In Progress button under quote section is not available");
			Selenium.failTest("In Progress button under quote section is not available");
		}
	}
	
	public static void ValidateFulfillmentRequestAfterCompletion(){
		
		//Check if fullfillment section is updated
		Selenium.waitForElement(Selenium.driver, PageOpportunities.Details.Element_FulfillmentStatus);
		
		if (Selenium.isElementPresent("Fulfillment Status", PageOpportunities.Details.Element_FulfillmentStatusFirst)) {
			if(Selenium.isElementPresent("Fulfillment Status", PageOpportunities.Details.Element_FulfillmentStatusSecond))
			Reporter.log("Fulfillment status is Validated after updation."); 
		else{
			Log.error("Fulfillment status is not updated as desired after completion.");
			Selenium.failTest("Fulfillment status is not updated as desired after completion.");
			Reporter.log("Fulfillment status is not updated as desired after completion.");
		}
		}
	
	}
	
	public static void validateOpportunityStage(String stage){
			Selenium.waitForElement(Selenium.driver, PageOpportunities.Details.Element_Status);
			if(Selenium.isElementPresent(PageOpportunities.Details.Element_Status))
	{ 
				String text1 = Selenium.getWebElement(PageOpportunities.Details.Element_Status.toString(),"Stage").getText();
				
				if(text1.contains(stage))
				{
				Reporter.log("Value of Stage is displayed correctly");
				Log.info("Value of Stage is displayed correctly");
				Selenium.passTest("Value of Stage is displayed correctly");
	}}
	else{
		
	Reporter.log("Value of Stage is not displayed correctly");
	
	
				Log.error("Value of Stage is not displayed correctly");
				Selenium.failTest("Value of Stage is not displayed correctly");
	}}
	
	public static void validateOpportunityProbability(String percent){
		
		if(Selenium.isElementPresent(PageOpportunities.Details.Element_Probability))
		{ 
			String text1 = Selenium.getWebElement(PageOpportunities.Details.Element_Probability,"Probability").getText();
					
			if(text1.contains(percent)){
				Reporter.log("Value of Probability is displayed correctly");
				Log.info("Value of Probability is displayed correctly");
				Selenium.passTest("Value of Probability is displayed correctly");
			}
		}
		else{	
			Reporter.log("Value of Probability is not displayed correctly");
			Log.error("Value of Probability is not displayed correctly");
			Selenium.failTest("Value of Probability is not displayed correctly");
		}
	}
	
	public static void AcceptCertificate() 
	{
		
		try {
		Selenium.sleep(3000);	
		Robot r;
		
		r = new Robot(); 
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER); 
			
	//		if (Selenium.isAlertPresent()) 
	//			Selenium.handleBrowserPopUp("Ok");
			
		
			if(Selenium.isElementPresent(PageOpportunities.Payment.BUTTON_NEXT))
		{
			Selenium.passTest("User is successfully navigated to Review account information page");
				Reporter.log("User is successfully navigated to Review account information page");
			Log.info("User is successfully navigated to Review account information page ");
		}
		else
		{
			Selenium.passTest("User navigation to Review account information page failed");
			Reporter.log("User navigation to Review account information page failed");
			Log.info("User navigation to Review account information page failed");
		
		}}
		catch(Exception e)
		{
			
		}
	}
	
	public static void fillAccountDetails(Map<String, String> TestDataDictionary) {
	    accdetail.setBankName(TestDataDictionary.get("BankName"));
	    accdetail.setAccountType(TestDataDictionary.get("AccountType"));
	    accdetail.setROUTING(TestDataDictionary.get("Routing"));
	    accdetail.setBANK_ACCOUNT(TestDataDictionary.get("AccountNumber"));
	
	    if (Selenium.isElementPresent(PageOpportunities.AccountDetails.SELECT_PAYMENT_DATE)) {
	           Selenium.passTest("Payment date field on Initial payment page is displayed successfully");
	           Reporter.log("Payment date field on Initial payment page is displayed successfully");
	           Log.info("Payment date field on Initial payment page is displayed successfully");
	    } else {
	           Selenium.failTest("Payment date field on Initial payment page is not displayed");
	           Reporter.log("Payment date field on Initial payment page is not displayed");
	           Log.info("Payment date field on Initial payment page is not displayed");
	    }
	
	    if ((accdetail.getBankName() != null)
	                 && (Selenium.isElementPresent(PageOpportunities.AccountDetails.TEXT_BANK_NAME))) {
	           Selenium.type(PageOpportunities.AccountDetails.TEXT_BANK_NAME, accdetail.getBankName());
	           Selenium.passTest("Bank Name field on Initial payment page is displayed successfully");
	           Reporter.log("Bank Name field on Initial payment page is displayed successfully");
	           Log.info("Bank Name field on Initial payment page is displayed successfully");
	    } else {
	           Selenium.failTest("Bank Name field on Initial payment page is not displayed");
	           Reporter.log("Bank Name field on Initial payment page is not displayed");
	           Log.info("Bank Name field on Initial payment page is not displayed");
	    }
	
	
	      if ((accdetail.getAccountType() != null)
				&& (Selenium.isElementPresent(PageOpportunities.AccountDetails.SELECT_ACCOUNT_TYPE))) {
			Selenium.click(" Account type", PageOpportunities.AccountDetails.SELECT_ACCOUNT_TYPE);
			Selenium.select("Account type", PageOpportunities.AccountDetails.SELECT_ACCOUNT_TYPE, accdetail.getAccountType());
			Selenium.passTest("Accout type drop down field on Initial payment page is displayed successfully");
			Reporter.log("Accout type drop down field on Initial payment page is displayed successfully");
			Log.info("Accout type drop down field on Initial payment page is displayed successfully");
		} else {
			Selenium.failTest("Accout type drop down field on Initial payment page is not displayed");
			Reporter.log("Accout type drop down field on Initial payment page is not displayed");
			Log.info("Accout type drop down field on Initial payment page is not displayed");
		} 
	    if ((accdetail.getROUTING() != null) && (Selenium.isElementPresent(PageOpportunities.AccountDetails.TEXT_ROUTING))) {
	           Selenium.type(PageOpportunities.AccountDetails.TEXT_ROUTING, accdetail.getROUTING());
	           Selenium.passTest("Routing field on Initial payment page is displayed successfully");
	           Reporter.log("Routing field on Initial payment page is displayed successfully");
	           Log.info("Routing field on Initial payment page is displayed successfully");
	    } else {
	           Selenium.failTest("Routing field on Initial payment page is not displayed");
	           Reporter.log("Routing field on Initial payment page is not displayed");
	           Log.info("Routing field on Initial payment page is not displayed");
	    }
	    if ((accdetail.getBANK_ACCOUNT() != null)
	                 && (Selenium.isElementPresent(PageOpportunities.AccountDetails.TEXT_BANK_ACCOUNT))
	                 && (Selenium.isElementPresent(PageOpportunities.AccountDetails.TEXT_REENTER_BANK_ACCOUNT))) {
	           Selenium.type(PageOpportunities.AccountDetails.TEXT_BANK_ACCOUNT, accdetail.getBANK_ACCOUNT());
	           Selenium.type(PageOpportunities.AccountDetails.TEXT_REENTER_BANK_ACCOUNT, accdetail.getBANK_ACCOUNT());
	           Selenium.passTest(
	                        "Bank account field and reenter account fields on Initial payment page are displayed successfully");
	           Reporter.log(
	                        "Bank account field and reenter account fields on Initial payment page are displayed successfully");
	           Log.info(
	                        "Bank account field and reenter account fields on Initial payment page are displayed successfully");
	    } else {
	           Selenium.failTest(
	                        "Bank account field and reenter account fields on Initial payment page are not displayed");
	           Reporter.log("Bank account field and reenter account fields on Initial payment page are not displayed");
	           Log.info("Bank account field and reenter account fields on Initial payment page are not displayed");
	    }
	
	    if (Selenium.isElementPresent(PageOpportunities.AccountDetails.NEXT_BUTTON)) {
	           Selenium.passTest("next button on Initial payment page is displayed successfully");
	           Reporter.log("next button on Initial payment page is displayed successfully");
	           Log.info("next button on Initial payment page is displayed successfully");
	           Selenium.click("Next button", PageOpportunities.AccountDetails.NEXT_BUTTON);
	    } else {
	           Selenium.passTest("next button on Initial payment page is not displayed");
	           Reporter.log("next button on Initial payment page is not displayed");
	           Log.info("next button on Initial payment page is not displayed");
	    }
	
	}
	
	public static void verifyAutopayOption() {
	
	    if (Selenium.isElementPresent(PageOpportunities.AutoPay.SELECT_AGREE_AUTOPAY_AGREEMENT)) {
	           Selenium.passTest("Autopay page is displayed successfully");
	           Reporter.log("Autopay page is displayed successfully");
	           Log.info("Autopay page is displayed successfully");
	           Selenium.click("AutoPay_Agree_Button", PageOpportunities.AutoPay.SELECT_AGREE_AUTOPAY_AGREEMENT);
	    } else {
	           Selenium.failTest("Autopay page is not displayed");
	           Reporter.log("Autopay page is not displayed");
	           Log.info("Autopay page is not displayed");
	    }
	
	    if (Selenium.isElementPresent(PageOpportunities.AutoPay.BUTTON_NEXT)) {
	           Selenium.passTest("next button on Autopay page is displayed successfully");
	           Reporter.log("next button on Autopay page page is displayed successfully");
	           Log.info("next button on Autopay page is displayed successfully");
	           Selenium.click("Next button", PageOpportunities.AutoPay.BUTTON_NEXT);
	    } else {
	           Selenium.passTest("next button on Autopay page is not displayed");
	           Reporter.log("next button on Autopay page is not displayed");
	           Log.info("next button on Autopay page is not displayed");
	    }
	
	}
	
	public static void verifyPaymentPage() throws InterruptedException {
	
	    if (Selenium.isElementPresent(PageOpportunities.PaymentReviewPage.TEXT_PAYMENT_HEADER)) {
	
	           Selenium.passTest("Payment Review Page is displayed successfully");
	           Reporter.log("Payment Review Page page is displayed successfully");
	           Log.info("Payment Review Page is displayed successfully");
	    } else {
	           Selenium.passTest("Payment Review Page is not displayed");
	           Reporter.log("Payment Review Page is not displayed");
	           Log.info("Payment Review Page is not displayed");
	    }
	
	    if (Selenium.isElementPresent(PageOpportunities.PaymentReviewPage.TEXT_SHOW_COVERGARE_DETAILS)) {
	
	           Selenium.passTest("Show Coverage arrow is clicked on Payment Review Page successfully");
	           Reporter.log("Show Coverage arrow is clicked on Payment Review Page successfully");
	           Log.info("Show Coverage arrow is clicked on Payment Review Page successfully");
	           Selenium.click("Show Coverage Button", PageOpportunities.PaymentReviewPage.TEXT_SHOW_COVERGARE_DETAILS);
	    } else {
	           Selenium.passTest("Show Coverage arrow on Payment Review Page is not clicked successfully");
	           Reporter.log("Show Coverage arrow on Payment Review Page is not clicked successfully");
	           Log.info("Show Coverage arrow on Payment Review Page is not clicked successfully");
	    }
	
	    Thread.sleep(2000);
	
	    if (Selenium.isElementPresent(PageOpportunities.PaymentReviewPage.TEXT_COVERGARE_DETAILS)) {
	
	           Selenium.passTest("Coverage arrow is clicked on Payment Review Page successfully");
	           Reporter.log("Coverage arrow is clicked on Payment Review Page successfully");
	           Log.info("Coverage arrow is clicked on Payment Review Page successfully");
	           Selenium.click("Show Coverage Button", PageOpportunities.PaymentReviewPage.TEXT_COVERGARE_DETAILS);
	    } else {
	           Selenium.passTest("Coverage arrow on Payment Review Page is not clicked successfully");
	           Reporter.log("Coverage arrow on Payment Review Page is not clicked successfully");
	           Log.info("Coverage arrow on Payment Review Page is not clicked successfully");
	    }
	    Thread.sleep(2000);
	
	    if (Selenium.isElementPresent(PageOpportunities.PaymentReviewPage.BUTTON_SUBMIT)) {
	           Selenium.passTest("Agree button on Payment Review page is displayed successfully");
	           Reporter.log("Agree button on Payment Review page page is displayed successfully");
	           Log.info("Agree button on Payment Review page is displayed successfully");
	           Selenium.click("Agree button", PageOpportunities.PaymentReviewPage.BUTTON_SUBMIT);
	    } else {
	           Selenium.passTest("Agree button on Payment Review page is not displayed");
	           Reporter.log("Agree button on Payment Review page is not displayed");
	           Log.info("Agree button on Payment Review page is not displayed");
	    }
	}

	public static void ValidatePaymentOptionPage()
	{
		if(Selenium.isElementPresent(PageOpportunities.Payment.BUTTON_NEXT))
		{
			Selenium.passTest("next button on Review account information page is displayed successfully");
				Reporter.log("next button on Review account information page is displayed successfully");
			Log.info("next button on Review account information page is displayed successfully");
			Selenium.click("Next button", PageOpportunities.Payment.BUTTON_NEXT);
		}
		else
		{
			Selenium.passTest("next button on Review account information page is not displayed");
			Reporter.log("next button on Review account information page is not displayed");
		Log.info("next button on Review account information page is not displayed");
		}
		
		if(Selenium.isElementPresent(PageOpportunities.PaymentOptions.STATUS_TABLE))
		{
			Selenium.passTest("Status table on payment options page is displayed successfully");
				Reporter.log("Status table on payment options page is displayed successfully");
			Log.info("Status table on payment options page is displayed successfully");
		}
		else
		{
			Selenium.passTest("Status table on payment options page is not displayed");
			Reporter.log("Status table on payment options page is not displayed");
		Log.info("Status table on payment options page is not displayed");
		}
	
		if(Selenium.isElementPresent(PageOpportunities.PaymentOptions.PAYMENT_OPTIONS))
		{
			Selenium.passTest("PAYMENT_OPTIONS on payment options page is displayed successfully");
				Reporter.log("PAYMENT_OPTIONS on payment options page is displayed successfully");
			Log.info("PAYMENT_OPTIONS on payment options page is displayed successfully");
		}
		else
		{
			Selenium.passTest("PAYMENT_OPTIONS on payment options page is not displayed");
			Reporter.log("PAYMENT_OPTIONS on payment options page is not displayed");
			Log.info("PAYMENT_OPTIONS on payment options page is not displayed");
		}
	
		if(Selenium.isElementPresent(PageOpportunities.PaymentOptions.PAYMENT_AMOUNT))
		{
			if(!(Selenium.getWebElement(PageOpportunities.PaymentOptions.PAYMENT_AMOUNT, "Payment Amount").isEnabled()))
			{
				Selenium.passTest("PAYMENT_AMOUNT on payment options page is displayed successfully");
				Reporter.log("PAYMENT_AMOUNT on payment options page is displayed successfully");
				Log.info("PAYMENT_AMOUNT on payment options page is displayed successfully");
			}
		}
		else
		{
			Selenium.passTest("PAYMENT_AMOUNT on payment options page is not displayed");
			Reporter.log("PAYMENT_AMOUNT on payment options page is not displayed");
			Log.info("PAYMENT_AMOUNT on payment options page is not displayed");
		}
		if((Selenium.isElementPresent(PageOpportunities.PaymentOptions.RADIO_ELECTRONIC_CHECK)) && (Selenium.isElementPresent(PageOpportunities.PaymentOptions.RADIO_MAIL_CHECK)))
		{
			Selenium.passTest("Both radio buttons: electronic check and radio mail check on payment options page are displayed successfully");
				Reporter.log("Both radio buttons: electronic check and radio mail check on payment options page are displayed successfully");
			Log.info("Both radio buttons: electronic check and radio mail check on payment options page are displayed successfully");
			
		}
		else
		{
			Selenium.passTest("Both radio buttons: electronic check and radio mail check on payment options page are not displayed");
			Reporter.log("Both radio buttons: electronic check and radio mail check on payment options page are not displayed");
		Log.info("Both radio buttons: electronic check and radio mail check on payment options page are not displayed");
		}
	
		if(Selenium.isElementPresent(PageOpportunities.PaymentOptions.ELE_PAYMENT_DATE))
		{
			Selenium.passTest("PAYMENT_DATE on payment options page is displayed successfully");
				Reporter.log("PAYMENT_DATE on payment options page is displayed successfully");
			Log.info("PAYMENT_DATE on payment options page is displayed successfully");
		}
		else
		{
			Selenium.passTest("PAYMENT_DATE on payment options page is not displayed");
			Reporter.log("PAYMENT_DATE on payment options page is not displayed");
		Log.info("PAYMENT_DATE on payment options page is not displayed");
		}
	
		if(Selenium.isElementPresent(PageOpportunities.PaymentOptions.ELE_MAIN_PAGE))
		{
			Selenium.passTest("Link to navigate to main page on payment options page is displayed successfully");
				Reporter.log("Link to navigate to main page on payment options page is displayed successfully");
			Log.info("Link to navigate to main page on payment options page is displayed successfully");
		}
		else
		{
			Selenium.passTest("Link to navigate to main page on payment options page is not displayed");
			Reporter.log("Link to navigate to main page on payment options page is not displayed");
		Log.info("Link to navigate to main page on payment options page is not displayed");
		}
	
		if(Selenium.isElementPresent(PageOpportunities.PaymentOptions.NEXT_BUTTON))
		{
			Selenium.passTest("NEXT_BUTTON on payment options page is displayed successfully");
				Reporter.log("NEXT_BUTTON on payment options page is displayed successfully");
			Log.info("NEXT_BUTTON on payment options page is displayed successfully");
			Selenium.clickButtons("Next button", PageOpportunities.PaymentOptions.NEXT_BUTTON);
			Selenium.sleep(2000);
		}
		else
		{
			Selenium.passTest("NEXT_BUTTON on payment options page is not displayed");
			Reporter.log("NEXT_BUTTON on payment options page is not displayed");
		Log.info("NEXT_BUTTON on payment options page is not displayed");
		}
	}
	
	
	public static void paymentConfirmationPage() throws InterruptedException {
	    if (Selenium.isElementPresent(PageOpportunities.PaymentConfrimationPage.TEXT_PAGE_HEADER)) {
	
	           Selenium.passTest("Payment Confirmation Page is displayed successfully");
	           Reporter.log("Payment Confirmation Page page is displayed successfully");
	           Log.info("Payment Confirmation Page is displayed successfully");
	           Selenium.click("Print button is clicked", PageOpportunities.PaymentConfrimationPage.BUTTON_PRINT);
	    } else {
	           Selenium.passTest("Payment Confirmation Page is not displayed");
	           Reporter.log("Payment Confirmation Page is not displayed");
	           Log.info("Payment Confirmation Page is not displayed");
	    }
	    
	    if (Selenium.isElementPresent(PageOpportunities.PaymentConfrimationPage.BUTTON_NEXT)) {
	           Selenium.passTest("Next button on Payment Confirmation page is displayed successfully");
	           Reporter.log("Next button on Payment Confirmation page page is displayed successfully");
	           Log.info("Next button on Payment Confirmation page is displayed successfully");
	           Selenium.click("Next button", PageOpportunities.PaymentConfrimationPage.BUTTON_NEXT);
	    } else {
	           Selenium.passTest("Next button on Payment Confirmation page is not displayed");
	           Reporter.log("Next button on Payment Confirmation page is not displayed");
	           Log.info("Next button on Payment Confirmation page is not displayed");
	    }
	    
	    Thread.sleep(2000);
	    /*
	    Actions print =new Actions(Selenium.driver);
	    print.keyDown(Keys.DOWN).perform();
	    Selenium.driver.switchTo().alert().accept();*/
	    
	
	    
	}
	
	public static  void  ValidateProbability (String str)
	{
		try{
			
		//Click Save and Exit Now
			
			
		if (Selenium.isElementPresent("Quote Enroll button for medicare", PageOpportunities.Details.Element_Probability,true)){
			Reporter.log("Probability option is dispalyed on Opportunity page");	
			Selenium.passTest("Probability option is dispalyed on Opportunity page");
			String text1 = Selenium.driver.findElement(By.xpath(PageOpportunities.Details.Element_ProbabilityText)).getText();
			if(str.equals(text1)) {
				Reporter.log("Probability value is dispalyed on Opportunity page");	
				Selenium.passTest("Probability value is dispalyed on Opportunity page");
			}
			else
			{
				Selenium.failTest("Probability value is not dispalyed on Opportunity page failed");
				Reporter.log("Probability value is not dispalyed on Opportunity page failed");	
			}
		}
		else{
			Selenium.failTest("Probability option is dispalyed on Opportunity page failed");
			Reporter.log("Probability option is dispalyed on Opportunity page failed");
		}
		}
		catch(Exception e)
		{
			 Log.error("Error while validating probability "+e.getMessage());
			 Selenium.failTest("Error while validating probability on Opportunity page");
			 
		}
		
	}	

	
	public static  void  SelectOpportunityType (Map<String, String> TestDataDictionary)
	{
		try{
			
		//Click Save and Exit Now
			
			String strOption=TestDataDictionary.get("OpportunityType");
		if (Selenium.isElementPresent("Select Opportunity Type", PageOpportunities.SELECT_OPPORTUNITIES,true)){
			Reporter.log("Select Opportunity Type is dispalyed on Opportunity page");	
			Selenium.select("Opportunity Type",PageOpportunities.SELECT_OPPORTUNITIES,strOption) ;
			Selenium.click("Go", PageOpportunities.BUTTON_GO);
			Selenium.sleep(5000);
			Selenium.passTest("Opportunity Type is filtered Successfully.");
			
		}
		else{
			Selenium.failTest("Select Opportunity Type is not dispalyed on Opportunity page");
			Reporter.log("Select Opportunity Type is not dispalyed on Opportunity page.");
		}
		}
		catch(Exception e)
		{
			 Log.error("Select Opportunity Type is not dispalyed on Opportunity page" +e.getMessage());
			 Selenium.failTest("Error while Selecting Opportunity Type on Opportunity page");
			 
		}
		
	}	
	
	public static void search_Opportunity(String Opportunity_Name) {
		
		
		if (Selenium.isElementPresent(PageOpportunities.LINK_OPPORTUNITY_TAB)) {
			// Enter Fulfillment number
			Selenium.click("Search input fieled for Fulfillment number",PageFullfilment.TEXT_FULLFILLMENT_SERACH_INPUT);
			Selenium.type("Enter the Fullfillment Created by the user", PageFullfilment.TEXT_FULLFILLMENT_SERACH_INPUT,Opportunity_Name, true);
			Selenium.click("Search Button", PageFullfilment.BUTTON_FULLFILLMENT_SEARCH);
			Selenium.sleep(2000);
			
			Selenium.click("Select the record", PageOpportunities.ELE_OPPORTUNITY_NAME);

			if (Selenium.isElementPresent("Edit",PageOpportunities.Details.BUTTON_EDIT)) {
				
				Reporter.log("Opportunity found in search Results Sucessfully");
				Selenium.passTest("Opportunity found in search Results  Sucessfully");
				
			}

			else {
				Reporter.log("Opportunity  NOT found in search Results.");
				Selenium.failTest("Opportunity  NOT found in search Results.");
			}
		}

		else {
			Reporter.log("Opportunity page is not loaded");
			Selenium.failTest("Opportunity page is not loaded");
		}
		
	}
	
	
	public static void click_NewFulfillmentRequest() {
		
		
		if (Selenium.isElementPresent(PageOpportunities.BUTTON_NEWFULFILLMENTREQST)) {
			// Enter Fulfillment number
			Selenium.click("New Fulfillment Request",PageOpportunities.BUTTON_NEWFULFILLMENTREQST);
			Selenium.sleep(5000);
		}

		else {
			Reporter.log("New Fulfillment Request Button not present on Opportunity page..");
			Selenium.failTest("New Fulfillment Request Button not present on Opportunity page.");
		}
		
	}
	
	public static void validate_NoAddressError_NewFulfillmentRequest() {
		
		
		if (Selenium.isAlertPresent()) {
			Reporter.log("Opportunity found in search Results Sucessfully");
			Selenium.passTest("Error message pop up saying 'Please provide valid address before creating fulfillment request'");
			Selenium.handleBrowserPopUp("Ok");
		}

		else {
			Reporter.log("Error message pop up didn't come when the street address is empty");
			Selenium.failTest("Error message pop up didn't come when the street address is empty.");
		}
		
	}
	
	
	
}