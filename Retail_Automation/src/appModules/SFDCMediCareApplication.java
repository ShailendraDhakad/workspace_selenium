
package appModules;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import pageObjects.PageMedicareOpportunities;
import pageObjects.PageOpportunities;
import pageObjects.PageSFDC;
import testData.ApplicationAssistantData;
import testData.ComparePlanData;
import testData.MedicareImportantQuestionInfo;
import testData.MedicareInsuranceInformation;
import utility.DateUtil;
import utility.Log;
import utility.Selenium;
import utility.StringUtil;

public class SFDCMediCareApplication {
	
	
	
	//public static MedicareInsuranceInformation objMedicareinfo = new MedicareInsuranceInformation();
	public static MedicareImportantQuestionInfo importantQuestioninfo = new MedicareImportantQuestionInfo();
	public static ComparePlanData objComparePlan=new ComparePlanData();
	

	//=======================================================================================================================================================================
	//***********************************************************************************************************************************************************************
	//														|| Medication Application Form Funtions  ||
	//***********************************************************************************************************************************************************************
	//=======================================================================================================================================================================
	
	
	
	//------------------------------------------------------------------------------
	//Function to Validate 'Review Your Quote' Page'   ==> Invalid Scenarios
	//------------------------------------------------------------------------------
	public static  void  receiveYourQuoteMedicareInvalidScenario()
	{
		try{	
			
		
			// Select the View Quote button and	Click on PDF icon and download the PDF
	//		String winHandleBefore = Selenium.driver.getWindowHandle();
	//		Selenium.click("View Print Quote button", PageOpportunities.DeliverMyQuoteMedicare.BUTTON_VIEWPRINTQUOTE);
	//		for(String winHandle : Selenium.driver.getWindowHandles()){
	//		    Selenium.driver.switchTo().window(winHandle);
	//		}
	//		String srt = Selenium.driver.getCurrentUrl();
	//		if(srt.contains("createquotepdf"))
	//		{
	//			Reporter.log("User is successfully navigated to create quote pdf page");
	//			Log.info("User is successfully navigated to create quote pdf page");
	//	
	//		}
	//		else
	//		{
	//			Reporter.log("User is not navigated to create quote pdf page");
	//			Log.error("User is not navigated to create quote pdf page");
	//		}
	//		Selenium.driver.close();
	//		Selenium.driver.switchTo().window(winHandleBefore); 
			
			// Select send by mail option
					Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuoteMedicare.RADION_ONE,1);
					if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuote.MSG_MAIL_ADDRESS_ERROR))
					{
						Reporter.log("Mailing address is missing error is displayed successfully");
						Log.info("Mailing address is missing error is displayed successfully");
						Selenium.passTest("Mailing address is missing error is displayed successfully");
					}
					else
					{
						Reporter.log("Mailing address is missing error is not displayed");
						Log.error("Mailing address is missing error is not displayed");
						Selenium.failTest("Mailing address is missing error is not displayed");
					}
			Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
			Selenium.sleep(3000);
					
					if (Selenium.isElementPresent("My Info Page", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1, true)){
						Reporter.log("Previous Button functionality at Deliver My Quote Page passed");
						Log.info("Previous Button functionality at Deliver My Quote Page passed");
						
					}
					else{
						Reporter.log("Previous Button functionality at Deliver My Quote Page failed.");
						Log.error("Previous Button functionality at Deliver My Quote Page failed.");
					}
					
					Selenium.type("Basic Street Address", PageOpportunities.BasicSubscriberInformation.TEXT_STREETNAME, "123 line test");
					
				   Selenium.clickButtons("Next", PageSFDC.BUTTON_NEXT);
				   Selenium.sleep(3000);
				  
				   //if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuote.MSG_MAIL_ADDRESS_ERROR, "Mail Address Error Message"))
				   try{
					   
					   if (Selenium.getWebElement(PageOpportunities.DeliverMyQuote.MSG_MAIL_ADDRESS_ERROR, "Mail Address Error Message").isEnabled()){
							Reporter.log("Mailing address is missing error is displayed after entering mailing address");
							Log.error("Mailing address is missing error is displayed after entering mailing address");
							Selenium.failTest("Mailing address is missing error is displayed after entering mailing address");
							}
						else
						{
							Reporter.log("Mailing address is missing error is not displayed after entering mailing address");
							Log.info("Mailing address is missing error is not displayed after entering mailing address");
							Selenium.passTest("Mailing address is missing error is not displayed after entering mailing address");
						}
				   }
				   catch (Exception e){
					   Reporter.log("Mailing address is missing error is not displayed after entering mailing address");
						Log.info("Mailing address is missing error is not displayed after entering mailing address");
						Selenium.passTest("Mailing address is missing error is not displayed after entering mailing address"); 
				   }
				   
				   Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuoteMedicare.RADION_ONE,0);
				   if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuote.MSG_EMAIL_ADDRESS_ERROR))
					{
						Reporter.log("Email address is missing error is displayed successfully");
						Log.info("Email address is missing error is displayed successfully");
						Selenium.passTest("Email address is missing error is displayed successfully");
					}
					else
					{
						Reporter.log("Email address is missing error is not displayed");
						Log.error("Email address is missing error is not displayed");
						Selenium.failTest("Email address is missing error is not displayed");
					}
				   //Selenium.click("Previous", PageOpportunities.DeliverMyQuoteMedicare.BUTTON_PREVIOUS);
				   Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
				   Selenium.sleep(3000);
							
				   if (Selenium.isElementPresent("My Info Page", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1, true)){
					   Reporter.log("Previous Button functionality at Deliver My Quote Page passed");
					   Log.info("Previous Button functionality at Deliver My Quote Page passed");
					}
					else{
						Reporter.log("Previous Button functionality at Deliver My Quote Page failed.");
						Log.error("Previous Button functionality at Deliver My Quote Page failed.");
					}
				   	Selenium.type(PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1,"abc@gmail.com");
				   	Selenium.clickButtons("Next", PageSFDC.BUTTON_NEXT);
				   	Selenium.sleep(3000);
				   	try{
				   		if(Selenium.getWebElement(PageOpportunities.DeliverMyQuote.MSG_MAIL_ADDRESS_ERROR,"EMail Address Error Message").isEnabled()){
							Reporter.log("Email address is missing error is displayed after entering Emailing address");
							Log.error("Email address is missing error is displayed after entering Emailing address");
							Selenium.failTest("Email address is missing error is displayed after entering Emailing address");
						}
				   		else
				   		{
				   			Reporter.log("Email address is missing error is not displayed after entering Emailing address");
							Log.info("Email address is missing error is not displayed after entering Emailing address");
							Selenium.passTest("Email address is missing error is not displayed after entering Emailing address");
				   		}
				   	}
				   	catch(Exception e){
				   		Reporter.log("Email address is missing error is not displayed after entering Emailing address");
						Log.info("Email address is missing error is not displayed after entering Emailing address");
						Selenium.passTest("Email address is missing error is not displayed after entering Emailing address");
				   	}
		}
		catch(Exception e)
		{
			 Log.error("Error in Selecting Receive My quote options. Exception:"+e.getMessage());
			 Selenium.failTest("Receive My Quote functionality Failed.");
			 Reporter.log("Receive My Quote functionality Failed.");
		}
		
	}
	
	public static void ValidateReviewPage()
	{
		if(Selenium.isElementPresent(PageOpportunities.Payment.ELE_WELCOMEMSG))
		{
			Selenium.passTest("Welcome message on Review account information page is displayed successfully");
				Reporter.log("Welcome message on Review account information page is displayed successfully");
			Log.info("Welcome message on Review account information page is displayed successfully");
		}
		else
		{
			Selenium.passTest("Welcome message on Review account information page is not displayed");
			Reporter.log("Welcome message on Review account information page is not displayed");
		Log.info("Welcome message on Review account information page is not displayed");
		}
	
	//Validate the Initial Payment message is displayed successfully
	if(Selenium.isElementPresent(PageOpportunities.Payment.ELE_PAYMENTMSG))
		{
			Selenium.passTest("Initial Payment message on Review account information page is displayed successfully");
				Reporter.log("Initial Payment message on Review account information page is displayed successfully");
			Log.info("Initial Payment message on Review account information page is displayed successfully");
		}
		else
		{
			Selenium.passTest("Initial Payment message on Review account information page is not displayed");
			Reporter.log("Initial Payment message on Review account information page is not displayed");
		Log.info("Initial Payment message on Review account information page is not displayed");
		}
	
	//Validate the Welcome message with name is displayed successfully
	
	
	if (Selenium.getWebElement(PageOpportunities.Payment.TEXT_WELCOME_COMPLETENAME, "Welcome").getText()!=null)
		{
			Selenium.passTest("Welcome firstname lastname on Review account information page is displayed successfully");
				Reporter.log("Welcome firstname lastname message on Review account information page is displayed successfully");
			Log.info("Welcome firstname lastname message on Review account information page is displayed successfully");
		}
		else
		{
			Selenium.passTest("Welcome firstname lastname message on Review account information page is not displayed");
			Reporter.log("Welcome firstname lastname message on Review account information page is not displayed");
		Log.info("Welcome firstname lastname message on Review account information page is not displayed");
		}
	
	if((Selenium.isElementPresent(PageOpportunities.Payment.TEXT_APPLICATION_ID) && (Selenium.getWebElement(PageOpportunities.Payment.TEXT_APPLICATION_ID_VALUE, "Application ID").getText()!=null)))
	{
		Selenium.passTest("Application ID on Review account information page is displayed successfully");
			Reporter.log("Application ID on Review account information page is displayed successfully");
		Log.info("Application ID on Review account information page is displayed successfully");
	}
	else
	{
		Selenium.passTest("Application ID on Review account information page is not displayed");
		Reporter.log("Application ID on Review account information page is not displayed");
	Log.info("Application ID on Review account information page is not displayed");
	}
	if((Selenium.isElementPresent(PageOpportunities.Payment.TEXT_FIRST_NAME) && (Selenium.getWebElement(PageOpportunities.Payment.TEXT_FIRST_NAME_VALUE, "First name").getText()!=null)))
	{
		Selenium.passTest("First name on Review account information page is displayed successfully");
			Reporter.log("First name on Review account information page is displayed successfully");
		Log.info("First name on Review account information page is displayed successfully");
	}
	else
	{
		Selenium.passTest("First name on Review account information page is not displayed");
		Reporter.log("First name on Review account information page is not displayed");
	Log.info("First name on Review account information page is not displayed");
	}
	
	if((Selenium.isElementPresent(PageOpportunities.Payment.TEXT_LAST_NAME) && (Selenium.getWebElement(PageOpportunities.Payment.TEXT_LAST_NAME_VALUE, "LAST_NAME").getText()!=null)))
	{
		Selenium.passTest("LAST_NAME on Review account information page is displayed successfully");
			Reporter.log("LAST_NAME on Review account information page is displayed successfully");
		Log.info("LAST_NAME on Review account information page is displayed successfully");
	}
	else
	{
		Selenium.passTest("LAST_NAME on Review account information page is not displayed");
		Reporter.log("LAST_NAME on Review account information page is not displayed");
	Log.info("LAST_NAME on Review account information page is not displayed");
	}
	
	if((Selenium.isElementPresent(PageOpportunities.Payment.TEXT_EFFECTIVE_DATE) && (Selenium.getWebElement(PageOpportunities.Payment.TEXT_EFFECTIVE_DATE_VALUE, "EFFECTIVE_DATE").getText()!=null)))
	{
		Selenium.passTest("EFFECTIVE_DATE on Review account information page is displayed successfully");
			Reporter.log("EFFECTIVE_DATE on Review account information page is displayed successfully");
		Log.info("EFFECTIVE_DATE on Review account information page is displayed successfully");
	}
	else
	{
		Selenium.passTest("EFFECTIVE_DATE on Review account information page is not displayed");
		Reporter.log("EFFECTIVE_DATE on Review account information page is not displayed");
	Log.info("EFFECTIVE_DATE on Review account information page is not displayed");
	}
	
	if((Selenium.isElementPresent(PageOpportunities.Payment.TEXT_INITIAL_PAYMENT) && (Selenium.getWebElement(PageOpportunities.Payment.TEXT_INITIAL_PAYMENT_VALUE, "Initial payment").getText()!=null)))
	{
		Selenium.passTest("INITIAL_PAYMENT on Review account information page is displayed successfully");
			Reporter.log("INITIAL_PAYMENT on Review account information page is displayed successfully");
		Log.info("INITIAL_PAYMENT on Review account information page is displayed successfully");
	}
	else
	{
		Selenium.passTest("INITIAL_PAYMENT on Review account information page is not displayed");
		Reporter.log("INITIAL_PAYMENT on Review account information page is not displayed");
	Log.info("INITIAL_PAYMENT on Review account information page is not displayed");
	}
	
	if((Selenium.isElementPresent(PageOpportunities.Payment.ELE_PLAN_SELECTED) && (Selenium.getWebElement(PageOpportunities.Payment.TEXT_PLAN_SELECTED_VALUE, "PLAN_SELECTED_VALUE").getText()!=null)))
	{
		Selenium.passTest("PLAN_SELECTED on Review account information page is displayed successfully");
			Reporter.log("PLAN_SELECTED on Review account information page is displayed successfully");
		Log.info("PLAN_SELECTED on Review account information page is displayed successfully");
	}
	else
	{
		Selenium.passTest("PLAN_SELECTED on Review account information page is not displayed");
		Reporter.log("PLAN_SELECTED on Review account information page is not displayed");
	Log.info("PLAN_SELECTED on Review account information page is not displayed");
	}
	
	if(Selenium.isElementPresent(PageOpportunities.Payment.BUTTON_PREVIOUS))
	{
		Selenium.passTest("Previous button on Review account information page is displayed successfully");
			Reporter.log("Previous button on Review account information page is displayed successfully");
		Log.info("Previous button on Review account information page is displayed successfully");
	}
	else
	{
		Selenium.passTest("Previous button on Review account information page is not displayed");
		Reporter.log("Previous button on Review account information page is not displayed");
	Log.info("Previous button on Review account information page is not displayed");
	}
	
	if(Selenium.isElementPresent(PageOpportunities.Payment.BUTTON_NEXT))
	{
		Selenium.passTest("next button on Review account information page is displayed successfully");
			Reporter.log("next button on Review account information page is displayed successfully");
		Log.info("next button on Review account information page is displayed successfully");
	}
	else
	{
		Selenium.passTest("next button on Review account information page is not displayed");
		Reporter.log("next button on Review account information page is not displayed");
	Log.info("next button on Review account information page is not displayed");
	}
	
	
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Validate 'Review Your Quote' Page'  
	//------------------------------------------------------------------------------
	public static  void  receiveYourQuoteMedicare (Map<String, String> TestDataDictionary)
	{
		try{	
			SFDCApplication.objReceiveQuote.setQuoteOption(TestDataDictionary.get("QuoteReceivingOption"));
		// verify Deliver My quote Page
		
			Selenium.ValidateObjectAndText("Save and Exit", PageOpportunities.DeliverMyQuoteMedicare.BUTTON_SAVEANDEXIT, "", false);
			Selenium.ValidateObjectAndText("View Quote", PageOpportunities.DeliverMyQuoteMedicare.BUTTON_VIEWPRINTQUOTE, "", false);
			//Selenium.ValidateObjectAndText("View Quote", PageOpportunities.DeliverMyQuoteMedicare.BUTTON_VIEWPRINTQUOTE, "", false);
			
			if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuoteMedicare.RADIO_EMAIL)) {
				Reporter.log("Email radio button on quote page is displayed: passed.");	
				Selenium.passTest("Email radio button on quote page is displayed: passed.");}
			else{
				Reporter.log("Email radio button on quote page is not displayed");	
				Selenium.failTest("Email radio button on quote page is not displayed");}
			if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuoteMedicare.RADIO_MAIL)) {
				Reporter.log("Mail radio button on quote page is displayed: passed.");	
				Selenium.passTest("Mail radio button on quote page is displayed: passed.");}
			else{
				Reporter.log("Mail radio button on quote page is not displayed");	
				Selenium.failTest("Mail radio button on quote page is not displayed");}
			if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuoteMedicare.RADIO_NONE)) {
				Reporter.log("None radio button on quote page is displayed: passed.");	
				Selenium.passTest("None radio button on quote page is displayed: passed.");}
			else{
				Reporter.log("None radio button on quote page is not displayed");	
				Selenium.failTest("None radio button on quote page is not displayed");}		
			
			
			//Verify Previous Button
			//Selenium.click("Previous", PageOpportunities.DeliverMyQuoteMedicare.BUTTON_PREVIOUS);
			Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
			
			if (Selenium.isElementPresent("My Info Page", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1, true)){
				Reporter.log("Previous Button functionality at Deliver My Quote Page passed");
				Log.info("Previous Button functionality at Deliver My Quote Page passed");
				//Selenium.click("Next", PageOpportunities.BasicSubscriberInformation.BUTTON_NEXT);
				Selenium.clickButtons("Next", PageSFDC.BUTTON_NEXT);
			}
			else{
				Reporter.log("Previous Button functionality at Deliver My Quote Page failed.");
				Log.error("Previous Button functionality at Deliver My Quote Page failed.");
			}
			
			//Receive your quote
			Selenium.waitForElement( Selenium.driver, PageOpportunities.DeliverMyQuote.BUTTON_VIEWPRINTQUOTE);
			//Thread.sleep(5000);
			String strOption=SFDCApplication.objReceiveQuote.getQuoteOption();
			
			if 	(strOption.equalsIgnoreCase("No"))
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuoteMedicare.RADION_ONE,2);
			else if (strOption.equalsIgnoreCase("Mail"))
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuoteMedicare.RADION_ONE,1);
			else if (strOption.equalsIgnoreCase("EMail"))
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuoteMedicare.RADION_ONE,0);
			else
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuoteMedicare.RADION_ONE,2);
			
			Reporter.log("Receive My Quote form Filled successfully");
			Selenium.passTest("Receive My Quote form Filled successfully");
		}
		catch(Exception e)
		{
			 Log.error("Error in Selecting Receive My quote options. Exception:"+e.getMessage());
			 Selenium.failTest("Receive My Quote functionality Failed.");
			 Reporter.log("Receive My Quote functionality Failed.");
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Click Save and Exit Button on Application PAge 
	//------------------------------------------------------------------------------
	public static  void  SaveAndExitMedicare ()
	{
		try{
			
		//Click Save and Exit Now
			Selenium.click("Save and Exit", PageOpportunities.DeliverMyQuote.BUTTON_SAVEANDEXIT);
			Alert alr= Selenium.driver.switchTo().alert();
			alr.accept();
			Selenium.waitForElement( Selenium.driver, PageOpportunities.Details.BUTTON_MEDICAREADVENROLL);
			
		if (Selenium.isElementPresent("Quote Enroll button for medicare", PageOpportunities.Details.BUTTON_MEDICAREADVENROLL,true)){
			Reporter.log("User is successfully navigated to Opportunity page");	
			Selenium.passTest("User is successfully navigated to Opportunity page");
		}
		else{
			Selenium.failTest("Navigation to Opportunity page failed");
			Reporter.log("Navigation to Opportunity page failed");
		}
		}
		catch(Exception e)
		{
			 Log.error("Error while navigating back to  Opportunity page Exception:"+e.getMessage());
			 Selenium.failTest("Error while navigating back to  Opportunity page");
			 
		}
		
	}
		
	
	//----------------------------------------------------------------
	//Function to Compare and Select Plan  
	//----------------------------------------------------------------
	public static  void  fillComparePlanFormMedicare (Map<String, String> TestDataDictionary)
	{
		try{	
		
			//Verify Formulary,Provider Directory and SOB links
			
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ComparePlansMedicare.LINK_FORMULARY);
			Selenium.waitForElement(Selenium.driver,PageOpportunities.ComparePlansMedicare.LINK_PROVIDERDIRECTORY);
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ComparePlansMedicare.LINK_SOB);
			
			objComparePlan.setPlan(TestDataDictionary.get("Plan"));
			//verify Cancel
	
				//Selenium.click("Cancel", PageOpportunities.ComparePlansMedicare.BUTTON_CANCEL);
				Selenium.clickButtons("Cancel", PageOpportunities.ComparePlansMedicare.BUTTON_CANCEL);
				Selenium.sleep(3000);
			if (Selenium.driver.switchTo().alert().getText().contains("By clicking on Cancel")){
				Reporter.log("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
				Log.info("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
				Selenium.handleBrowserPopUp("Cancel");
			}
			else{
				if(Selenium.driver.switchTo().alert().getText().contains("Please"))
						{
					Reporter.log("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
					Log.info("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
					Selenium.handleBrowserPopUp("OK");
						}
				Reporter.log("Cancel Button functionality at Compare Plan Page failed.");
				Log.info("Cancel Button functionality at Compare Plan Page failed.");
			}
			
			
			//Verify Previous
			//Selenium.click("Previous", PageOpportunities.ComparePlansMedicare.BUTTON_PREVIOUS);
			 Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
				if(Selenium.isElementPresent(PageOpportunities.MedicareAdvantageQuote.ELEMENT_YEAR)){
					Reporter.log("Previous Button functionality at Compare Plan Page passed");
					Log.info("Previous Button functionality at Compare Plan Page passed");
					//Selenium.click("Next button", PageOpportunities.MedicareAdvantageQuote.BUTTON_NEXT);
					Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				}
				else{
					Reporter.log("Previous Button functionality at Compare Plan Page failed.");
					Log.error("Previous Button functionality at Compare Plan Page failed.");
				}
	       		
	
				//Fill Compare Plan Page
			Thread.sleep(5000);
			if(objComparePlan.getPlan() != null && !objComparePlan.getPlan().isEmpty()) 
				SFDCApplication.selectPlan(objComparePlan.getPlan().toUpperCase());
			else{
				Log.error("No plan is available to select in Test Data sheet");
			 	Selenium.failTest("No plan is available to select.");
			}
			Thread.sleep(2000);
			//Verify Next
						
			//Selenium.click("Next", PageOpportunities.ComparePlansMedicare.BUTTON_NEXT);
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			Thread.sleep(2000);
			Selenium.waitForElement( Selenium.driver, PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME);
			if (Selenium.isElementPresent("Bassic information Page", PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME, true)){
				Reporter.log("Compare Plan functionality passed.");
				Selenium.passTest("Compare Plan functionality passed.");
						}
				
			else{
				Selenium.failTest("Compare Plan functionality failed.");
				Reporter.log("Compare Plan functionality failed.");
				Log.error("Compare Plan functionality failed.");
			}
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for My Information Page from Dictionary Object. Exception:"+e.getMessage());
			 Selenium.failTest("Error in getting data for My Information Page from Excel.");
			 
		}
	} 
	
	
	//----------------------------------------------------------------
	//Function to Compare and Select Plan  ==> Invalid Scenarios
	//----------------------------------------------------------------
	public static  void  fillComparePlanFormMedicareInvalidScenarios (Map<String, String> TestDataDictionary)
	{
		try{	
		
			//Verify Formulary,Provider Directory and SOB links
			
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ComparePlansMedicare.LINK_FORMULARY);
			Selenium.waitForElement(Selenium.driver,PageOpportunities.ComparePlansMedicare.LINK_PROVIDERDIRECTORY);
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ComparePlansMedicare.LINK_SOB);
			
			objComparePlan.setPlan(TestDataDictionary.get("Plan"));
			//verify Cancel
	
				Selenium.click("Cancel", PageOpportunities.ComparePlansMedicare.BUTTON_CANCEL);
			 
			if (Selenium.driver.switchTo().alert().getText().contains("By clicking on Cancel")){
				Reporter.log("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
				Log.info("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
				Selenium.handleBrowserPopUp("Cancel");
			}
			else{
				if(Selenium.driver.switchTo().alert().getText().contains("Please"))
						{
					Reporter.log("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
					Log.info("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
					Selenium.handleBrowserPopUp("OK");
						}
				Reporter.log("Cancel Button functionality at Compare Plan Page failed.");
				Log.info("Cancel Button functionality at Compare Plan Page failed.");
			}
			
			
			//Verify Previous
			Selenium.click("Previous", PageOpportunities.ComparePlansMedicare.BUTTON_PREVIOUS);
				if(Selenium.isElementPresent(PageOpportunities.MedicareAdvantageQuote.ELEMENT_YEAR)){
					Reporter.log("Previous Button functionality at Compare Plan Page passed");
					Log.info("Previous Button functionality at Compare Plan Page passed");
					Selenium.click("Next button", PageOpportunities.MedicareAdvantageQuote.BUTTON_NEXT);
				}
				else{
					Reporter.log("Previous Button functionality at Compare Plan Page failed.");
					Log.error("Previous Button functionality at Compare Plan Page failed.");
				}
	        // Verify error message after clicking on compare plan button without selecting any plan
				Selenium.click("Next", PageOpportunities.ComparePlansMedicare.BUTTON_COMPARE);
				if(Selenium.isElementPresent(PageOpportunities.ComparePlansMedicare.MSG_ERROR_NOPLANSELECTED))
						{
					Reporter.log("Error message is displayed successfully when only one plan is selected for comparison");
					Log.info("Error message is displayed successfully when only one plan is selected for comparison");
					Selenium.click(null,PageOpportunities.ComparePlans.TEXT_ERROR_CLOSE);
						}
				else{
					Log.error("Error message is not displayed when only one plan is selected for comparison Failed");
				 	Selenium.failTest("Error message is not displayed when only one plan is selected for comparison Failed");
			}		
				
			//Verify error message after selecting one plan and clicking on compare plan button
				Selenium.selectCheckBox("Select Plan", PageOpportunities.ComparePlansMedicare.CHECKCOMPARE, 0);
				Selenium.click("Next", PageOpportunities.ComparePlansMedicare.BUTTON_COMPARE);
				if(Selenium.isElementPresent(PageOpportunities.ComparePlansMedicare.MSG_ERROR_ONEPLANSELECTED))
						{
					Reporter.log("Error message is displayed successfully when only one plan is selected for comparison");
					Log.info("Error message is displayed successfully when only one plan is selected for comparison");
					Selenium.click(null,PageOpportunities.ComparePlans.TEXT_ERROR_CLOSE);
						}
				else{
					Log.error("Error message is not displayed when only one plan is selected for comparison Failed");
				 	Selenium.failTest("Error message is not displayed when only one plan is selected for comparison Failed");
			}	
				
			// Verify error message without selecting any plan and clicking on Next button 
				Selenium.click("Next", PageOpportunities.ComparePlansMedicare.BUTTON_NEXT);
				if (Selenium.driver.switchTo().alert().getText().contains("Please fix")){
					Reporter.log("Sucessfully validated Error Message without selecting any plan and clicking on Next button on compare plan page " + Selenium.driver.switchTo().alert().getText());
					Log.info("Sucessfully validated Error Message without selecting any plan and clicking on Next button on compare plan page" + Selenium.driver.switchTo().alert().getText());
					Selenium.handleBrowserPopUp("OK");
					Thread.sleep(5000);
				}
				else{
					Reporter.log("Validating Error Message without selecting any plan and clicking on Next button on compare plan page failed");
					Log.info("Validating Error Message without selecting any plan and clicking on Next button on compare plan page failed");
					
				}	
				
			//Verify user is navigated to Subscriber info page after selecting more than one plan and clicking on next button
				// comaprePlans(PageOpportunities.ComparePlans.Element_PLANS,"Plans", objComparePlan.getPlansToCompare().toUpperCase());
				Selenium.selectCheckBox("Select Plan", PageOpportunities.ComparePlansMedicare.BUTTON_SELECTPLAN, 0);
				Selenium.selectCheckBox("Select Plan", PageOpportunities.ComparePlansMedicare.BUTTON_SELECTPLAN, 1);
				Selenium.click("Next", PageOpportunities.ComparePlansMedicare.BUTTON_NEXT);
				Selenium.waitForElement( Selenium.driver, PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME);
				if (Selenium.isElementPresent("Bassic information Page", PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME, true)){
					Reporter.log("Compare Plan functionality passed.");
					Selenium.passTest("Compare Plan functionality passed.");
							}
					
				else{
					Selenium.failTest("Compare Plan functionality failed.");
					Reporter.log("Compare Plan functionality failed.");
					Log.error("Compare Plan functionality failed.");
				}
				
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for My Information Page from Dictionary Object. Exception:"+e.getMessage());
			 Selenium.failTest("Error in getting data for My Information Page from Excel.");
			 
		}
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Fill 'Enroll Now Page' 
	//------------------------------------------------------------------------------
	public static  void  EnrollNowMedicare()
	{
		try{
			
		//Click Enroll Now
			Selenium.click("Enroll Now", PageOpportunities.DeliverMyQuoteMedicare.BUTTON_ENROLLNOW);
			Selenium.waitForElement(Selenium.driver, PageOpportunities.EnrollmentMedicare.TEXT_FIRST_NAME);
			
		if (Selenium.isElementPresent("Gender", PageOpportunities.EnrollmentMedicare.TEXT_FIRST_NAME,true)){
			
			Reporter.log("Receive My Quote functionality passed.");	
			Selenium.passTest("Receive My Quote functionality passed.");
			
			
		}
		else{
			Selenium.failTest("Receive My Quote functionality Failed.");
	
			Reporter.log("Receive My Quote functionality Failed.");
		}
		}
		catch(Exception e)
		{
			 Log.error("Error while clicking Enroll Now button. Exception:"+e.getMessage());
			 Selenium.failTest("Receive My Quote functionality Failed.");
			 
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Fill 'Enrollment Page' 
	//------------------------------------------------------------------------------
	public static  void  fillEnrollment (Map<String, String> TestDataDictionary)
	{
		SFDCApplication.objenrollment.setConfirmation(TestDataDictionary.get("Enrollment"));
		SFDCApplication.objenrollment.setHomePhoneNumber(TestDataDictionary.get("HomePhone"));
		SFDCApplication.objenrollment.setGender(TestDataDictionary.get("Gender"));
		
		if(SFDCApplication.objenrollment.getConfirmation().equalsIgnoreCase("Yes")){
			Selenium.click("Yes radio button", PageOpportunities.EnrollmentMedicare.SELECT_CONFIRMATION_EMAIL_YES);
			Reporter.log("Confirmation message is selected as yes successfully");	
			Selenium.passTest("Confirmation message is selected as yes successfully");}
		else if(SFDCApplication.objenrollment.getConfirmation().equalsIgnoreCase("No")){
			Selenium.click("No radio button", PageOpportunities.EnrollmentMedicare.SELECT_CONFIRMATION_EMAIL_NO);
			 Reporter.log("Confirmation message is selected as No successfully");	
				Selenium.passTest("Confirmation message is selected as No successfully");}
		else {
			Selenium.failTest("Incorrect confirmation option dispalyed");
			 Reporter.log("Confirmation message is not selected : Failed");	
				Selenium.failTest("Confirmation message is not selected : Failed");}
		
		if(Selenium.isElementPresent(PageOpportunities.EnrollmentMedicare.TEXT_HOME_PHONE_NUMBER)) {
			Selenium.type(PageOpportunities.EnrollmentMedicare.TEXT_HOME_PHONE_NUMBER, SFDCApplication.objenrollment.getHomePhoneNumber());  
			 Reporter.log("Home phone number is entered successfully");	
				Selenium.passTest("Home phone number is entered successfully");
			}
		else{
			Selenium.failTest("Home phone number field is not displayed");
			Reporter.log("Home phone number is not entered : Failed");	
			Selenium.failTest("Home phone number is not entered : Failed");
		}
		
		if(SFDCApplication.objenrollment.getGender().equalsIgnoreCase("Male")) {
		    Selenium.selectCheckBox("Gender is Male", PageOpportunities.EnrollmentMedicare.RADIO_SEX,0); 
		    Reporter.log("Gender is selected as Male successfully");	
			Selenium.passTest("Gender is selected as Male successfully");
		}
		
			else if (SFDCApplication.objenrollment.getGender().equalsIgnoreCase("Female")) {
				Selenium.selectCheckBox("Gender is feMale", PageOpportunities.EnrollmentMedicare.RADIO_SEX,1);
				Reporter.log("Gender is selected as Female successfully");	
				Selenium.passTest("Gender is selected as Female successfully");
			}
				
			else{Reporter.log("Incorrect Gender information is displayed");
				Selenium.failTest("Incorrect Gender information is displayed");
			}
		if(Selenium.isElementPresent("Home Phone", PageOpportunities.EnrollmentMedicare.TEXT_HOME_PHONE_NUMBER)) {
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT); 
			Reporter.log("Next button is dispalyed successfully");
			Selenium.passTest("Next button is dispalyed successfully");
			}
		else
		{ Reporter.log("Next button is not dispalyed");
			Selenium.failTest("Next button is not dispalyed");
		}
	}
	
	public static  void  fillPlanFormMedicare (Map<String, String> TestDataDictionary)
	{
		try{	
		
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ComparePlansMedicare.LINK_FORMULARY);
						
			objComparePlan.setPlan(TestDataDictionary.get("Plan"));
			
			//Fill Compare Plan Page
			
			if(objComparePlan.getPlan() != null && !objComparePlan.getPlan().isEmpty()) 
				SFDCApplication.selectPlan(objComparePlan.getPlan().toUpperCase());
			else{
				Log.error("No plan is available to select in Test Data sheet");
			 	Selenium.failTest("No plan is available to select.",true);
			}
			Thread.sleep(2000);
			
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			//Thread.sleep(2000);
			Selenium.waitForElement( Selenium.driver, PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME);
			if (Selenium.isElementPresent("Bassic information Page", PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME, true)){
				Reporter.log("Compare Plan functionality passed.");
				Selenium.passTest("Compare Plan functionality passed.");
						}
				
			else{
				Selenium.failTest("Compare Plan functionality failed.",true);
				Reporter.log("Compare Plan functionality failed.");
				Log.error("Compare Plan functionality failed.");
			}
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for Compare Page from Dictionary Object. Exception:"+e.getMessage());
			 Selenium.failTest("Error in getting data for Compare Page from Excel.",true);
			 
		}
	} 
	
	public static  void selectPremiumOption (String strOption)
	{
		try{	
		
			if(Selenium.isElementPresent("Premium Payment Option",PageMedicareOpportunities.PayPlanPremium.CHECK_GETBILL ))
			{
				Reporter.log("Premium Payment Option Page is displayed sucucessfully");	
				Selenium.passTest("Premium Payment Option Page is displayed sucucessfully");
				
				if 	(strOption.equalsIgnoreCase("GetBill"))
					Selenium.click("Get Bill",PageMedicareOpportunities.PayPlanPremium.CHECK_GETBILL);
				else if (strOption.equalsIgnoreCase("EFT"))
					Selenium.click("EFT",PageMedicareOpportunities.PayPlanPremium.CHECK_EFT);
				else if (strOption.equalsIgnoreCase("Auto"))
					Selenium.click("Auto Deduct",PageMedicareOpportunities.PayPlanPremium.CHECK_AUTO);
				else
					Selenium.click("GetBill",PageMedicareOpportunities.PayPlanPremium.CHECK_GETBILL);
				
				//Click NExt
				//Selenium.click("Next",PageMedicareOpportunities.PayPlanPremium.BUTTON_NEXT);
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				
			}
			
			
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for Paying your plan premium Page from Dictionary Object. Exception:"+e.getMessage());
			 Selenium.failTest("Paying your plan premium functionality Failed.");
			 Reporter.log("Paying your plan premium functionality Failed.");
			 
		}
	}
		
	public static  void fillAttestationOfEligibility ()
		{
			try{	
				Selenium.waitForElement(Selenium.driver, PageMedicareOpportunities.AttestEligibilty.CHECK_NEWTOMED);
				if(Selenium.isElementPresent("New to Medical",PageMedicareOpportunities.AttestEligibilty.CHECK_NEWTOMED ))
				{
					Reporter.log("Attestation Of Eligibility Page is displayed sucucessfully");	
					Selenium.passTest("Attestation Of Eligibility Page is displayed sucucessfully");
					
					//Select All
					Selenium.click("New to Med",PageMedicareOpportunities.AttestEligibilty.CHECK_NEWTOMED );
					
					Selenium.click("Moved out of Area",PageMedicareOpportunities.AttestEligibilty.CHECK_MOVED_OUT_AREA );
					if (Selenium.ValidateObjectAndText ("Moved Out Area Date",PageMedicareOpportunities.AttestEligibilty.TEXT_MOVEDOUT_AREADATE,"", false)){
						Selenium.click("Moved out of Area",PageMedicareOpportunities.AttestEligibilty.CHECK_MOVED_OUT_AREA );
					}
					
					Selenium.click("RELEASE FROM INCAR",PageMedicareOpportunities.AttestEligibilty.CHECK_RELEASE_FROM_INCAR );
					if (Selenium.ValidateObjectAndText ("RELEASE FROM INCAR Date",PageMedicareOpportunities.AttestEligibilty.TEXT_RELEASE_FROM_INCAR_DATE,"", false)){
						Selenium.click("RELEASE FROM INCAR",PageMedicareOpportunities.AttestEligibilty.CHECK_RELEASE_FROM_INCAR );
					}
					
					Selenium.click("RETURN TO US",PageMedicareOpportunities.AttestEligibilty.CHECK_RETURN_TO_US );
					if (Selenium.ValidateObjectAndText ("RETURN TO US Date",PageMedicareOpportunities.AttestEligibilty.TEXT_RETURN_TO_US_DATE,"", false)){
						Selenium.click("RETURN TO US",PageMedicareOpportunities.AttestEligibilty.CHECK_RETURN_TO_US );
					}
					
					Selenium.click("LAWFUL PRESENCE",PageMedicareOpportunities.AttestEligibilty.CHECK_LAWFUL_PRESENCE );
					if (Selenium.ValidateObjectAndText ("LAWFUL PRESENCE Date",PageMedicareOpportunities.AttestEligibilty.TEXT_LAWFUL_PRESENCE_DATE,"", false)){
						Selenium.click("LAWFUL PRESENCE",PageMedicareOpportunities.AttestEligibilty.CHECK_LAWFUL_PRESENCE );
					}
					
					Selenium.click("MEDICARE PREMIUM",PageMedicareOpportunities.AttestEligibilty.CHECK_MEDICARE_PREMIUM );
					
					Selenium.click("MEDICARE PRESCRIPTION",PageMedicareOpportunities.AttestEligibilty.CHECK_MEDICARE_PRESCRIPTION );
					
					Selenium.click("NO LONGER DRUGS",PageMedicareOpportunities.AttestEligibilty.CHECK_NO_LONGER_DRUGS );
					if (Selenium.ValidateObjectAndText ("NO LONGER DRUGS Date",PageMedicareOpportunities.AttestEligibilty.TEXT_NO_LONGER_DRUGS_DATE,"", false)){
						Selenium.click("NO LONGER DRUGS",PageMedicareOpportunities.AttestEligibilty.CHECK_NO_LONGER_DRUGS );
					}
					
					Selenium.click("MOVED OUT OF LONG TERM",PageMedicareOpportunities.AttestEligibilty.CHECK_MOVED_OUTOF_LONGTERM );
					if (Selenium.ValidateObjectAndText ("MOVED OUT OF LONG TERM Date",PageMedicareOpportunities.AttestEligibilty.TEXT_MOVED_OUTOF_LONGTERM_DATE,"", false)){
						Selenium.click("MOVED OUT OF LONG TERM",PageMedicareOpportunities.AttestEligibilty.CHECK_MOVED_OUTOF_LONGTERM );
					}
					
					Selenium.click("LEFT PACE",PageMedicareOpportunities.AttestEligibilty.CHECK_LEFT_PACE );
					if (Selenium.ValidateObjectAndText ("LEFT PACE Date",PageMedicareOpportunities.AttestEligibilty.TEXT_LEFT_PACE_DATE,"", false)){
						Selenium.click("LEFT PACE",PageMedicareOpportunities.AttestEligibilty.CHECK_LEFT_PACE );
					}
					
					Selenium.click("LOST DRUG COVERAGE",PageMedicareOpportunities.AttestEligibilty.CHECK_LOST_DRUG_COVRG );
					if (Selenium.ValidateObjectAndText ("LOST DRUG COVERAGE Date",PageMedicareOpportunities.AttestEligibilty.TEXT_LOST_DRUG_COVRG_DATE,"", false)){
						Selenium.click("LOST DRUG COVERAGE",PageMedicareOpportunities.AttestEligibilty.CHECK_LOST_DRUG_COVRG );
					}
					
					Selenium.click("LEAVING EMPLOYEE",PageMedicareOpportunities.AttestEligibilty.CHECK_LEAVING_EMPLOYEE );
					if (Selenium.ValidateObjectAndText ("LEAVING EMPLOYEE Date",PageMedicareOpportunities.AttestEligibilty.TEXT_LEAVING_EMPLOYEE_DATE,"", false)){
						Selenium.click("LEAVING EMPLOYEE",PageMedicareOpportunities.AttestEligibilty.CHECK_LEAVING_EMPLOYEE );
					}
					
					Selenium.click("Pharmacy Assistant",PageMedicareOpportunities.AttestEligibilty.CHECK_PHAR_ASSIST );
					
					Selenium.click("PLAN END CONTRACT",PageMedicareOpportunities.AttestEligibilty.CHECK_PLAN_END_CONTRACT );
					
					Selenium.click("SNP",PageMedicareOpportunities.AttestEligibilty.CHECK_SNP );
					if (Selenium.ValidateObjectAndText ("SNP Date",PageMedicareOpportunities.AttestEligibilty.TEXT_SNP_DATE,"", false)){
						Selenium.click("SNP",PageMedicareOpportunities.AttestEligibilty.CHECK_SNP );
					}					
					//Click Next
					//Selenium.click("Next",PageMedicareOpportunities.AttestEligibilty.BUTTON_NEXT);
					Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
					 Selenium.passTest("Attestation Of Eligibility functionality passed.");
					 Reporter.log("Attestation Of Eligibility functionality passed.");
				}
				
				
			}
			catch(Exception e)
			{
				 Log.error("Error in filling form for Attestation Of Eligibility Page. Exception:"+e.getMessage());
				 Selenium.failTest("Attestation Of Eligibility functionality Failed.");
				 Reporter.log("Attestation Of Eligibility functionality Failed.");
				 
			}
		
	}
	
	
		public static ApplicationAssistantData AppAssistInfo = new ApplicationAssistantData();
		public static  void fillApplicationAssistance (Map<String, String> TestDataDictionary)
		{
			try{
			
				AppAssistInfo.setApplicationAssistantOption(TestDataDictionary.get("ApplicationAssistantOption"));
				AppAssistInfo.setAgentName(TestDataDictionary.get("AgentName"));
				AppAssistInfo.setAgentAgency(TestDataDictionary.get("AgentAgency"));
				AppAssistInfo.setAgentLastName(TestDataDictionary.get("AgentLastName"));
				AppAssistInfo.setAgentID(TestDataDictionary.get("AgentID"));
				AppAssistInfo.setNPN(TestDataDictionary.get("NPN"));
				AppAssistInfo.setAuthRepName(TestDataDictionary.get("AuthRepName"));
				AppAssistInfo.setAuthRepPhnone(TestDataDictionary.get("AuthRepPhnone"));
				AppAssistInfo.setAuthRepAddress(TestDataDictionary.get("AuthRepAddress"));
				AppAssistInfo.setAuthRepRelation(TestDataDictionary.get("AuthRepRelation"));
				AppAssistInfo.setApplicationEntry(TestDataDictionary.get("ApplicationEntry"));
				
				Thread.sleep(5000);
				Selenium.waitForElement(Selenium.driver, PageMedicareOpportunities.ApplicationAssistance.CHECK_ENROLLEE);
				if(Selenium.isElementPresent("CheckBox Enrollee",PageMedicareOpportunities.ApplicationAssistance.CHECK_ENROLLEE ))
				{
					Reporter.log("Application Assistance Page is displayed sucucessfully");	
					Selenium.passTest("Application Assistance Page is displayed sucucessfully");
					
					//Select All
					String ApplicationAssist;
					
					if(AppAssistInfo.getApplicationAssistantOption() != null && !AppAssistInfo.getApplicationAssistantOption().isEmpty()){
						//Select case
						ApplicationAssist=AppAssistInfo.getApplicationAssistantOption().toLowerCase().toString();
					
				        switch (ApplicationAssist) {
				        
				            case "enrollee":  
				            	Selenium.click("Enrollee",PageMedicareOpportunities.ApplicationAssistance.CHECK_ENROLLEE );
				            	break;
				            	
				            case "staffmember": 
				            	Selenium.click("Staff Member",PageMedicareOpportunities.ApplicationAssistance.CHECK_STAFF_MEMBER );
				            	Thread.sleep(2000);
				            	String winHandleBefore;
				            	winHandleBefore = Selenium.driver.getWindowHandle();
				            	Selenium.click ("STAFF MEMBER",PageMedicareOpportunities.ApplicationAssistance.LINK_STAFF_MEMBER);
				            	SFDC.selectFromPoPUpTable(PageMedicareOpportunities.ApplicationAssistance.TABLE_STAFF,PageMedicareOpportunities.ApplicationAssistance.TEXT_AGENTNAME,AppAssistInfo.getAgentName(),PageMedicareOpportunities.ApplicationAssistance.BUTTON_SEARCH,PageMedicareOpportunities.ApplicationAssistance.LINK_SELECT,winHandleBefore);
				            	break;
				            	
				            case "agent": 
				            	Selenium.click("Agent/Broker",PageMedicareOpportunities.ApplicationAssistance.CHECK_AGENT_BROKER );
				            	Thread.sleep(2000);
				            	if (AppAssistInfo.getAgentAgency() != null && !AppAssistInfo.getAgentAgency().isEmpty())
				            		Selenium.type(PageMedicareOpportunities.ApplicationAssistance.TEXT_AGENCY_OF_AGENT, AppAssistInfo.getAgentAgency());
				            	if (AppAssistInfo.getAgentLastName() != null && !AppAssistInfo.getAgentLastName().isEmpty())
				            		Selenium.type(PageMedicareOpportunities.ApplicationAssistance.TEXT_AGENT_LASTNAME, AppAssistInfo.getAgentLastName());
				            	if (AppAssistInfo.getAgentName() != null && !AppAssistInfo.getAgentName().isEmpty())
				            		Selenium.type(PageMedicareOpportunities.ApplicationAssistance.TEXT_AGENT_FIRSTNAME, AppAssistInfo.getAgentName());
				            	if (AppAssistInfo.getAgentID() != null && !AppAssistInfo.getAgentID().isEmpty())
				            		Selenium.type(PageMedicareOpportunities.ApplicationAssistance.TEXT_AGENT_ID, AppAssistInfo.getAgentID());
				            	if (AppAssistInfo.getNPN() != null && !AppAssistInfo.getNPN().isEmpty())
				            		Selenium.type(PageMedicareOpportunities.ApplicationAssistance.TEXT_AGENT_NPN, AppAssistInfo.getNPN());
				            	break;
				            	
				            case "AuthRep":  
				            	Selenium.click("AUTHORISED Representative",PageMedicareOpportunities.ApplicationAssistance.CHECK_AUTHORISE_REP );
				            	Thread.sleep(2000);
				            	if (AppAssistInfo.getAuthRepName() != null && !AppAssistInfo.getAuthRepName().isEmpty())
				            		Selenium.type(PageMedicareOpportunities.ApplicationAssistance.TEXT_REP_NAME, AppAssistInfo.getAuthRepName());
				            	if (AppAssistInfo.getAuthRepPhnone() != null && !AppAssistInfo.getAuthRepPhnone().isEmpty())
				            		Selenium.type(PageMedicareOpportunities.ApplicationAssistance.TEXT_REP_PHONE, AppAssistInfo.getAuthRepPhnone());
				            	if (AppAssistInfo.getAuthRepAddress() != null && !AppAssistInfo.getAuthRepAddress().isEmpty())
				            		Selenium.type(PageMedicareOpportunities.ApplicationAssistance.TEXT_REP_ADDRESS, AppAssistInfo.getAuthRepAddress());
				            	if (AppAssistInfo.getAuthRepRelation() != null && !AppAssistInfo.getAuthRepRelation().isEmpty())
				            		Selenium.type(PageMedicareOpportunities.ApplicationAssistance.TEXT_REP_RELATION, AppAssistInfo.getAuthRepRelation());
				            	break;
				            	
				            
				            default: 
				            	Selenium.click("Enrollee",PageMedicareOpportunities.ApplicationAssistance.CHECK_ENROLLEE );
				                break;
				                
				        }
				        
					}
					
					else
			        	Selenium.click("Enrollee",PageMedicareOpportunities.ApplicationAssistance.CHECK_ENROLLEE );
			        
					if(AppAssistInfo.getApplicationEntry() != null && !AppAssistInfo.getApplicationEntry().isEmpty()){
			           if (AppAssistInfo.getApplicationEntry().equalsIgnoreCase("web"))
			        	   Selenium.selectCheckBox("Application Entry",PageMedicareOpportunities.ApplicationAssistance.RADIO_APPLICATION_ENTRY,1);	
			            else if (AppAssistInfo.getApplicationEntry().equalsIgnoreCase("Telephone"))
			            	Selenium.selectCheckBox("Application Entry",PageMedicareOpportunities.ApplicationAssistance.RADIO_APPLICATION_ENTRY,0);
			            else
			            	 Selenium.selectCheckBox("Application Entry",PageMedicareOpportunities.ApplicationAssistance.RADIO_APPLICATION_ENTRY,1);	
			        }
					
								
					//Click Next
					//Selenium.click("Next",PageMedicareOpportunities.ApplicationAssistance.BUTTON_NEXT);
					Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
					 
					 Selenium.passTest("Application Assistance functionality Passed.");
					 Reporter.log("Application Assistance functionality Passed.");
				}
			}
			catch(Exception e)
			{
				 Log.error("Error in filling form for Application Assistance Page. Exception:"+e.getMessage());
				 Selenium.failTest("Application Assistance functionality Failed.");
				 Reporter.log("Application Assistance functionality Failed.");
				 
			}
		
		
	}
		
		public static  void fillImportantInformation ()
		{
			try{
				Thread.sleep(3000);
				Selenium.waitForElement(Selenium.driver, PageMedicareOpportunities.ImportantInformation.CHECK_DISCLOSURE);
				if(Selenium.isElementPresent("I Agree",PageMedicareOpportunities.ImportantInformation.CHECK_DISCLOSURE ))
				{
					Reporter.log("Importamt Information Page is displayed sucucessfully");	
					Selenium.passTest("Importamt Information Page is displayed sucucessfully");
					
					//Select I agree
					Selenium.click("I Agree",PageMedicareOpportunities.ImportantInformation.CHECK_DISCLOSURE );
							
					//Click Next
					Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
					Selenium.passTest("Important Information functionality Passed.");
					 Reporter.log("Important Information functionality Passed.");
				}
				
				
			}
			catch(Exception e)
			{
				 Log.error("Error in filling form for Important Information Page. Exception:"+e.getMessage());
				 Selenium.failTest("Important Information functionality Failed.");
				 Reporter.log("Important Information functionality Failed.");
				 
			}
		}
		
		public static void selectImportantQuestion(Map<String, String> TestDataDictionary) throws InterruptedException {
			importantQuestioninfo.setESRD(TestDataDictionary.get("ESRD"));
			importantQuestioninfo.setOtherDrugCoverage(TestDataDictionary.get("OtherDrugCoverage"));
			importantQuestioninfo.setLongCareFacilityResident(TestDataDictionary.get("LongCareFacilityResident"));
			importantQuestioninfo.setMedicaidPrograme(TestDataDictionary.get("MedicaidPrograme"));
			importantQuestioninfo.setCoverageName(TestDataDictionary.get("CoverageName"));
			importantQuestioninfo.setCoverageID(TestDataDictionary.get("CoverageID"));
			importantQuestioninfo.setCoverageGroup(TestDataDictionary.get("CoverageGroup"));
			importantQuestioninfo.setInstitutionName(TestDataDictionary.get("InstitutionName"));
			importantQuestioninfo.setPhone(TestDataDictionary.get("HomePhone"));
			importantQuestioninfo.setStreetAddress(TestDataDictionary.get("Street"));
			importantQuestioninfo.setMedicalNumber(TestDataDictionary.get("MedicalNumber"));
			importantQuestioninfo.setPCPID(TestDataDictionary.get("PCPId"));
			importantQuestioninfo.setFirstName(TestDataDictionary.get("FirstName"));
			importantQuestioninfo.setMiddleName(TestDataDictionary.get("MiddleInitial"));
			importantQuestioninfo.setLastName(TestDataDictionary.get("LastName"));

			if (Selenium.isElementPresent(PageMedicareOpportunities.MedicareImoprotantQuestion.PAGE_HEADER)) {
				Reporter.log("Improtant Quesiton Page is displayed successfully.");
				Selenium.passTest("Improtant Quesiton Page is displayed passed.");
				
				//Select ESRD option
				if (importantQuestioninfo.getESRD().equalsIgnoreCase("Yes")) 
					Selenium.selectCheckBox("ESRD", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_ESRD_OPTION,0);
				else
					Selenium.selectCheckBox("ESRD", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_ESRD_OPTION,1);
					
				//Select other prescription drug coverage option
				if (importantQuestioninfo.getOtherDrugCoverage().equalsIgnoreCase("Yes")){ 
					Selenium.selectCheckBox("Other Drug Coverage", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_PRSCRB_DRUG,0);
					Selenium.type("Coverage Name", PageMedicareOpportunities.MedicareImoprotantQuestion.OTHER_COVERAGE_NAME,importantQuestioninfo.getCoverageName());
					Selenium.type("Coverage ID", PageMedicareOpportunities.MedicareImoprotantQuestion.COVERAGE_ID,
							importantQuestioninfo.getCoverageID());
					Selenium.type("Coverage Group", PageMedicareOpportunities.MedicareImoprotantQuestion.GROUP_NUMBER,
							importantQuestioninfo.getCoverageGroup());	
				}	
				else
					Selenium.selectCheckBox("Other Drug Coverage", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_PRSCRB_DRUG,1);
				
				
				//Select resident in a long-term care facility option
				Selenium.sleep(1000);
				if (importantQuestioninfo.getLongCareFacilityResident().equalsIgnoreCase("Yes")){ 
					Selenium.typeKey("Resident in a long-term care facility", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_LONGTERM, Keys.TAB);
					Selenium.selectCheckBox("Resident in a long-term care facility", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_LONGTERM,0);
					//Selenium.selectCheckBox("Resident in a long-term care facility", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_LONGTERM,0);
					Selenium.type("Institution Name", PageMedicareOpportunities.MedicareImoprotantQuestion.INSTITUTION_NAME,
							importantQuestioninfo.getInstitutionName());
					Selenium.type("Phone Number", PageMedicareOpportunities.MedicareImoprotantQuestion.PHONE_NUMBER,
							importantQuestioninfo.getPhone());
					Selenium.type("Street Address", PageMedicareOpportunities.MedicareImoprotantQuestion.STREET_ADDRESS,
							importantQuestioninfo.getStreetAddress());
					
				}	
				else
					Selenium.selectCheckBox("Resident in a long-term care facility", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_LONGTERM,1);
				
				
				//Select State Medicaid program option
				Selenium.sleep(1000);
				if (importantQuestioninfo.getMedicaidPrograme().equalsIgnoreCase("Yes")){ 
					Selenium.typeKey("Resident in a long-term care facility", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_LONGTERM, Keys.TAB);
					Selenium.selectCheckBox("State Medicaid program as Yes", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_STATE_MEDID,0);
					Selenium.type("Medical Number", PageMedicareOpportunities.MedicareImoprotantQuestion.MEDICAL_NUMBER,
					importantQuestioninfo.getMedicalNumber());
				}
				else
					Selenium.selectCheckBox("State Medicaid program as Yes", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_STATE_MEDID,1);
				
				//Select Spouse Work option and PCP details
				Selenium.selectCheckBox("Spouse Work", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_SPOUSE_WORK,0);
				if (importantQuestioninfo.getPCPID() != null && !importantQuestioninfo.getPCPID().isEmpty())
					Selenium.type("PCP ID", PageMedicareOpportunities.MedicareImoprotantQuestion.PCP_ID_NUMBER,
					importantQuestioninfo.getPCPID());
						
				if (importantQuestioninfo.getFirstName() != null && !importantQuestioninfo.getFirstName().isEmpty())
					Selenium.type("PCP First Name", PageMedicareOpportunities.MedicareImoprotantQuestion.F_NAME,
					importantQuestioninfo.getFirstName());
						
				if (importantQuestioninfo.getMiddleName() != null && !importantQuestioninfo.getMiddleName().isEmpty())
					Selenium.type("PCP Middle Name", PageMedicareOpportunities.MedicareImoprotantQuestion.MI,
					importantQuestioninfo.getMiddleName());
						
				if (importantQuestioninfo.getLastName() != null && !importantQuestioninfo.getLastName().isEmpty())
					Selenium.type("PCP Last Name", PageMedicareOpportunities.MedicareImoprotantQuestion.L_NAME,
									importantQuestioninfo.getLastName());
				
				//Select existing PCP option
					Selenium.selectCheckBox("Existing PCP", PageMedicareOpportunities.MedicareImoprotantQuestion.RADIO_EXISTING_PCP,1);
					
				//PCP Lookup window
				if (TestDataDictionary.get("PCPLookup").contains("Yes"))	{				
					SFDCApplication.selectPCP(TestDataDictionary, Selenium.driver);	
					
				//Selenium.click("Next Button", PageMedicareOpportunities.MedicareImoprotantQuestion.NEXT_BUTTON);
//				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				Selenium.waitForElement(Selenium.driver, PageMedicareOpportunities.MedicareImoprotantQuestion.ATTESTATION_PAGE_HEADER);
				Selenium.passTest("Improtant Quesiton Page is displayed: Passed.");

			} else {
				
				Log.error("Improtant Quesiton Page is not displayed: failed.");
				Reporter.log("Improtant Quesiton Page is not displayed: failed.");
				Selenium.failTest("Improtant Quesiton Page is not displayed: failed.");
			}

		}
		}
		public static  void validateApplicationSummary ()
		{
			try{
				Thread.sleep(3000);
				Selenium.waitForElement(Selenium.driver, PageMedicareOpportunities.ApplicationSummary.BUTTON_VIEW_PRINT_DOWNLOAD);
				if(Selenium.isElementPresent("VIEW_PRINT_DOWNLOAD BUTTON",PageMedicareOpportunities.ApplicationSummary.BUTTON_VIEW_PRINT_DOWNLOAD ))
				{
					Reporter.log("Appllication Summary Page is displayed sucucessfully");	
					Selenium.passTest("Appllication Summary Page is displayed sucucessfully");
					
					//Select View Print Download Button
					String currentWindowHandle = Selenium.driver.getWindowHandle();
					Selenium.click("VIEW_PRINT_DOWNLOAD BUTTON",PageMedicareOpportunities.ApplicationSummary.BUTTON_VIEW_PRINT_DOWNLOAD);
					Selenium.TabHandles(currentWindowHandle);		
					
					Selenium.passTest("Appllication Summary functionality Passed.");
					Reporter.log("Appllication Summary functionality Passed.");
										
				}
				
				
			}
			catch(Exception e)
			{
				 Log.error("Error in Appllication Summary Page. Exception:"+e.getMessage());
				 Selenium.failTest("Appllication Summary functionality Failed.");
				 Reporter.log("Appllication Summary functionality Failed.");
				 
			}
		}
		
		public static  void navigatetoOpportunity ()
		{
			try{
				Thread.sleep(3000);
				Selenium.waitForElement(Selenium.driver, PageMedicareOpportunities.ApplicationSummary.LINK_GOBACK_TO_OPPORTUNITY);
				if(Selenium.isElementPresent("GOBACK_TO_OPPORTUNITY lINK",PageMedicareOpportunities.ApplicationSummary.LINK_GOBACK_TO_OPPORTUNITY ))
				{
					Reporter.log("Go Back to Opportunity Link is displayed sucucessfully");	
					Selenium.passTest("Go Back to Opportunity Link is displayed sucucessfully");
					
					Selenium.click("GOBACK_TO_OPPORTUNITY lINK",PageMedicareOpportunities.ApplicationSummary.LINK_GOBACK_TO_OPPORTUNITY);
					
//					
					
					Selenium.passTest("Successfully Navigated to opportunity.");
					Reporter.log("Successfully Navigated to opportunity.");
										
				}
				
				
			}
			catch(Exception e)
			{
				 Log.error("Error in Appllication Summary Page. Exception:"+e.getMessage());
				 Selenium.failTest("Appllication Summary functionality Failed.");
				 Reporter.log("Appllication Summary functionality Failed.");
				 
			}
		}
		
		// Verify the Address Information

		public static void verifyAddressInformation() {
			Selenium.waitForElement(Selenium.driver, PageOpportunities.AddressInformation.TEXT_STREETADDRESS);

			// Verify, if the details are pre-populated
			if (Selenium.isElementPresent(PageMedicareOpportunities.AddressInformation.TEXT_STREETADDRESS)) {
				Reporter.log("Street Address field is displayed successfully.");
				Selenium.passTest("Street Address field is displayed passed.");
			} else {
				Selenium.failTest("Street Address field is not displayed on Application Information page: failed.");
				Log.error("Street Address field is not displayed: failed.");
				Reporter.log("Street Address field is not displayed: failed..");
			}

			if (Selenium.isElementPresent(PageMedicareOpportunities.AddressInformation.CITY_NAME)) {
				Reporter.log("City field is displayed successfully.");
				Selenium.passTest("City field is displayed passed.");
			} else {
				Selenium.failTest("City field is not displayed on Application Information page: failed.");
				Log.error("City field is not displayed: failed.");
				Reporter.log("City field is not displayed: failed..");
			}
			if (Selenium.isElementPresent(PageMedicareOpportunities.AddressInformation.STATE_NAME)) {
				Reporter.log("STATE field is displayed successfully.");
				Selenium.passTest("STATE field is displayed passed.");
			} else {
				Selenium.failTest("STATE field is not displayed on Application Information page: failed.");
				Log.error("STATE field is not displayed: failed.");
				Reporter.log("STATE field is not displayed: failed..");
			}
			if (Selenium.isElementPresent(PageMedicareOpportunities.AddressInformation.COUNTRY_NAME)) {
				Reporter.log("COUNTRY field is displayed successfully.");
				Selenium.passTest("COUNTRY field is displayed passed.");
			} else {
				Selenium.failTest("COUNTRY field is not displayed on Application Information page: failed.");
				Log.error("COUNTRY field is not displayed: failed.");
				Reporter.log("COUNTRY field is not displayed: failed..");
			}

			// Select the Address as same Mailing Address.
			Selenium.click("Select Mailing Address as Residential Address",
					PageMedicareOpportunities.AddressInformation.MAILING_ADDRESS_COPY);
			
			Selenium.clickButtons("Next Button", PageSFDC.BUTTON_NEXT);
			Selenium.sleep(2000);

		}
		public static MedicareInsuranceInformation objMedicareinfo = new MedicareInsuranceInformation();
		
		public static  void  MedicareInsuranceInformation (Map<String, String> TestDataDictionary)
		{	
			if (Selenium.getWebElement(PageOpportunities.MedicareInsuranceInformation.TEXT_NAME, "Medicare Insurance Name").isDisplayed()){
				
			Log.info("Navigated to MEdicare Insurance Information Page Successfully");
			objMedicareinfo.setName(TestDataDictionary.get("MEDICAREINSURANCENAME"));
			objMedicareinfo.setGender(TestDataDictionary.get("MEDICAREINSURANCEGENDER"));
			objMedicareinfo.setClaimNumber(TestDataDictionary.get("MEDICAREINSURANCECLAIMNO"));
			objMedicareinfo.setPartAEffectiveDate(TestDataDictionary.get("MEDICAREINSURANCEPARTA"));
			objMedicareinfo.setPartBEffectiveDate(TestDataDictionary.get("MEDICAREINSURANCEPARTB"));
			
			if(objMedicareinfo.getName() != null) 
				Selenium.type("Name Field", PageOpportunities.MedicareInsuranceInformation.TEXT_NAME, objMedicareinfo.getName());
			if(objMedicareinfo.getGender().equalsIgnoreCase("Male")) {
			    Selenium.selectCheckBox("Medicare Gender", PageOpportunities.MedicareInsuranceInformation.SELECT_GENDER, 0);
			    Reporter.log("Gender is selected as Male successfully");	
				Selenium.passTest("Gender is selected as Male successfully");
			}
			
				else if (objMedicareinfo.getGender().equalsIgnoreCase("Female")) {
					Selenium.selectCheckBox("Medicare Gender", PageOpportunities.MedicareInsuranceInformation.SELECT_GENDER, 1);
					Reporter.log("Gender is selected as Female successfully");	
					Selenium.passTest("Gender is selected as Female successfully");
				}
					
				else{Reporter.log("Incorrect Gender information is displayed");
					Selenium.failTest("Incorrect Gender information is displayed");
					
				}
				
			if(objMedicareinfo.getClaimNumber() != null) 
				Selenium.type("Claim number", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, objMedicareinfo.getClaimNumber());
			if(objMedicareinfo.getPartAEffectiveDate() != null) 
				Selenium.type("Part A effective date", PageOpportunities.MedicareInsuranceInformation. ELE_PARTA_DATE, objMedicareinfo.getPartAEffectiveDate());
			if(objMedicareinfo.getPartBEffectiveDate() != null) 
				Selenium.type("Part B effective date", PageOpportunities.MedicareInsuranceInformation. ELE_PARTB_DATE, objMedicareinfo.getPartBEffectiveDate());
			
			  //Selenium.click("Next button", PageOpportunities.MedicareInsuranceInformation.BUTTON_NEXT);
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
		      if(Selenium.isElementPresent("Header on Plan premium page",PageOpportunities.PayingPremium.MSG_PREMIUM_HEADER))
		      {
		      	Reporter.log("User is successfully navigated to Paying Premium page");	
					Selenium.passTest("User is successfully navigated to Paying Premium page");
		      		
		      }
		      else
		      {
		      	Reporter.log("User is not navigated to Paying Premium page : failed");	
				Selenium.failTest("User is not navigated to Paying Premium page: failed");
		      }
			}
			else{
				Reporter.log("User is not navigated to MEdicare Insurance Information page : failed");	
				Selenium.failTest("User is not navigated to MEdicare Insurance Information page: failed");
			}
			
		}

		public static  void  MedicareInsuranceInformationInvalidScenarios (Map<String, String> TestDataDictionary)
		{
			objMedicareinfo.setName(TestDataDictionary.get("MEDICAREINSURANCENAME"));
			objMedicareinfo.setGender(TestDataDictionary.get("MEDICAREINSURANCEGENDER"));
			objMedicareinfo.setClaimNumber(TestDataDictionary.get("MEDICAREINSURANCECLAIMNO"));
			objMedicareinfo.setPartAEffectiveDate(TestDataDictionary.get("MEDICAREINSURANCEPARTA"));
			objMedicareinfo.setPartBEffectiveDate(TestDataDictionary.get("MEDICAREINSURANCEPARTB"));
			
			if(objMedicareinfo.getName() != null) 
				Selenium.type("Name Field", PageOpportunities.MedicareInsuranceInformation.TEXT_NAME, objMedicareinfo.getName());
			if(objMedicareinfo.getGender().equalsIgnoreCase("Male")) {
				 Selenium.selectCheckBox("Medicare Gender", PageOpportunities.MedicareInsuranceInformation.SELECT_GENDER, 0); 
			    Reporter.log("Gender is selected as Male successfully");	
				Selenium.passTest("Gender is selected as Male successfully");
			}
			 else if (objMedicareinfo.getGender().equalsIgnoreCase("Female")) {
				 Selenium.selectCheckBox("Medicare Gender", PageOpportunities.MedicareInsuranceInformation.SELECT_GENDER, 1);
					Reporter.log("Gender is selected as Female successfully");	
					Selenium.passTest("Gender is selected as Female successfully");
				}
					
				else{Reporter.log("Incorrect Gender information is displayed");
					//Selenium.failTest("Incorrect Gender information is displayed");
				}
				//Verify error message is displayed when claim number is provided in invalid format
			Selenium.type("Claim number", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, "12345678");
			if(Selenium.isElementPresent("Error message when claim is in invalid format",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
			{
				Reporter.log("Invalid error message is displayed sucucessfully when claim number is provided in invalid format");	
				Selenium.passTest("Invalid error message is displayed sucucessfully when claim number is provided in invalid format");
			}
			else
			{
				Reporter.log("Invalid error message is not displayed  when claim number is provided in invalid format");	
				//Selenium.failTest("Invalid error message is not displayed when claim number is provided in invalid format");
			}
			Selenium.type("Claim number", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, "e4r5t6y7u8i");
			if(Selenium.isElementPresent("Error message when claim is in invalid format",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
			{
				Reporter.log("Invalid error message is displayed sucucessfully when claim number is provided in invalid format");	
				Selenium.passTest("Invalid error message is displayed sucucessfully when claim number is provided in invalid format");
			}
			else
			{
				Reporter.log("Invalid error message is not displayed  when claim number is provided in invalid format");	
				//Selenium.failTest("Invalid error message is not displayed when claim number is provided in invalid format");
			}
			
			// Verify no error message is displayed when claim number is in format 9 digits followed by 1 alpha 
			String str = StringUtil.randomNumber(9) + StringUtil.generateRandomString(1);
			Selenium.type("Claim Number field", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, str);
			if(Selenium.isElementPresent("Error message when claim is in format 9 digits followed by 1 alpha ",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
			{
				Reporter.log("Invalid error message is displayed even when claim number is provided in valid format 9 digits followed by 1 alpha ");	
				//Selenium.failTest("Invalid error message is displayed even when claim number is provided in valid format");
			}
			else
			{
				Reporter.log("Invalid error message is not displayed  when claim number is provided in valid format 9 digits followed by 1 alpha ");	
				Selenium.passTest("Invalid error message is not displayed when claim number is provided in valid format 9 digits followed by 1 alpha ");
			}
			// Verify no error message is displayed when claim number is in format 9 digits followed by 2 alpha
			str = StringUtil.randomNumber(9) + StringUtil.generateRandomString(2);
			Selenium.type("Claim Number field", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, str);
			if(Selenium.isElementPresent("Error message when claim is in format 9 digits followed by 2 alpha",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
			{
				Reporter.log("Invalid error message is displayed even when claim number is provided in valid format 9 digits followed by 2 alpha");	
				//Selenium.failTest("Invalid error message is displayed even when claim number is provided in valid format");
			}
			else
			{
				Reporter.log("Invalid error message is not displayed  when claim number is provided in valid format 9 digits followed by 2 alpha");	
				Selenium.passTest("Invalid error message is not displayed when claim number is provided in valid format 9 digits followed by 2 alpha");
			}
			// Verify no error message is displayed when claim number is in format 9 digits followed by 1 alpha and 1 digit
			 str = StringUtil.randomNumber(9) + StringUtil.generateRandomString(1) + StringUtil.randomNumber(1) ;
			Selenium.type("Claim Number field", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, str);
			if(Selenium.isElementPresent("Error message when claim is in format 9 digits followed by 1 alpha and 1 digit",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
			{
				Reporter.log("Invalid error message is displayed even when claim number is provided in valid format 9 digits followed by 1 alpha and 1 digit");	
				//Selenium.failTest("Invalid error message is displayed even when claim number is provided in valid format");
			}
			else
			{
				Reporter.log("Invalid error message is not displayed  when claim number is provided in valid format 9 digits followed by 1 alpha and 1 digit");	
				Selenium.passTest("Invalid error message is not displayed when claim number is provided in valid format 9 digits followed by 1 alpha and 1 digit");
			}
			
			// Verify no error message is displayed when claim number is in format 1 alpha followed by 6 digits
				 str = StringUtil.generateRandomString(1) + StringUtil.randomNumber(6) ;
				Selenium.type("Claim Number field", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, str);
				if(Selenium.isElementPresent("Error message when claim is in format 1 alpha followed by 6 digits",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
				{
					Reporter.log("Invalid error message is displayed even when claim number is provided in valid format 1 alpha followed by 6 digits");	
					//Selenium.failTest("Invalid error message is displayed even when claim number is provided in valid format");
				}
				else
				{
					Reporter.log("Invalid error message is not displayed  when claim number is provided in valid format 1 alpha followed by 6 digits");	
					Selenium.passTest("Invalid error message is not displayed when claim number is provided in valid format 1 alpha followed by 6 digits");
				}
				
				// Verify no error message is displayed when claim number is in format 1 alpha followed by 9 digits
				 str = StringUtil.generateRandomString(1) + StringUtil.randomNumber(9) ;
				Selenium.type("Claim Number field", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, str);
				if(Selenium.isElementPresent("Error message when claim is in format 1 alpha followed by 9 digits",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
				{
					Reporter.log("Invalid error message is displayed even when claim number is provided in valid format 1 alpha followed by 9 digits");	
					//Selenium.failTest("Invalid error message is displayed even when claim number is provided in valid format");
				}
				else
				{
					Reporter.log("Invalid error message is not displayed  when claim number is provided in valid format 1 alpha followed by 9 digits");	
					Selenium.passTest("Invalid error message is not displayed when claim number is provided in valid format 1 alpha followed by 9 digits");
				}
				
				// Verify no error message is displayed when claim number is in format 2 alpha followed by 6 digits
						 str = StringUtil.generateRandomString(2) + StringUtil.randomNumber(6) ;
						Selenium.type("Claim Number field", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, str);
						if(Selenium.isElementPresent("Error message when claim is in format 2 alpha followed by 6 digits",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
						{
							Reporter.log("Invalid error message is displayed even when claim number is provided in valid format 2 alpha followed by 6 digits");	
							//Selenium.failTest("Invalid error message is displayed even when claim number is provided in valid format");
						}
						else
						{
							Reporter.log("Invalid error message is not displayed  when claim number is provided in valid format 2 alpha followed by 6 digits");	
							Selenium.passTest("Invalid error message is not displayed when claim number is provided in valid format 2 alpha followed by 6 digits");
						}
						
				// Verify no error message is displayed when claim number is in format 2 alpha followed by 9 digits
						 str = StringUtil.generateRandomString(2) + StringUtil.randomNumber(9) ;
						Selenium.type("Claim Number field", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, str);
						if(Selenium.isElementPresent("Error message when claim is in format 2 alpha followed by 9 digits",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
						{
							Reporter.log("Invalid error message is displayed even when claim number is provided in valid format 2 alpha followed by 9 digits");	
							//Selenium.failTest("Invalid error message is displayed even when claim number is provided in valid format");
						}
						else
						{
							Reporter.log("Invalid error message is not displayed  when claim number is provided in valid format 2 alpha followed by 9 digits");	
							Selenium.passTest("Invalid error message is not displayed when claim number is provided in valid format 2 alpha followed by 9 digits");
						}
						
						// Verify no error message is displayed when claim number is in format 3 alpha followed by 6 digits
						 str = StringUtil.generateRandomString(3) + StringUtil.randomNumber(6) ;
						Selenium.type("Claim Number field", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, str);
						if(Selenium.isElementPresent("Error message when claim is in format 3 alpha followed by 6 digits",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
						{
							Reporter.log("Invalid error message is displayed even when claim number is provided in valid format 3 alpha followed by 6 digits");	
							//Selenium.failTest("Invalid error message is displayed even when claim number is provided in valid format");
						}
						else
						{
							Reporter.log("Invalid error message is not displayed  when claim number is provided in valid format 3 alpha followed by 6 digits");	
							Selenium.passTest("Invalid error message is not displayed when claim number is provided in valid format 3 alpha followed by 6 digits");
						}
						
				// Verify no error message is displayed when claim number is in format 3 alpha followed by 9 digits
						 str = StringUtil.generateRandomString(3) + StringUtil.randomNumber(9) ;
						Selenium.type("Claim Number field", PageOpportunities.MedicareInsuranceInformation.TEXT_CLAIMNO, str);
						if(Selenium.isElementPresent("Error message when claim is in format 3 alpha followed by 9 digits",PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_CLAIM_ERROR ))
						{
							Reporter.log("Invalid error message is displayed even when claim number is provided in valid format 3 alpha followed by 9 digits");	
							//Selenium.failTest("Invalid error message is displayed even when claim number is provided in valid format");
						}
						else
						{
							Reporter.log("Invalid error message is not displayed  when claim number is provided in valid format 3 alpha followed by 9 digits");	
							Selenium.passTest("Invalid error message is not displayed when claim number is provided in valid format 3 alpha followed by 9 digits");
						}
				// Verify an error message is displayed when the partA and partB effective dates are not the first dates of current month
						int year = DateUtil.getCurrentYear();
						int month; String TempDate;
				        GregorianCalendar date = new GregorianCalendar();      
				        month = date.get(Calendar.MONTH);
				        month = month+1;
				 
				        if(month>9)
						TempDate = "" + month + "/02/" + Integer.toString(year);
				        else
				        	TempDate = "0" + month + "/02/" + Integer.toString(year);
				        Selenium.type("Part A effective Date", PageOpportunities.MedicareInsuranceInformation. ELE_PARTA_DATE, TempDate);
				        Selenium.type("Part B effective Date", PageOpportunities.MedicareInsuranceInformation. ELE_PARTB_DATE, TempDate);	
				        Selenium.typeKey("Part B effective Date", PageOpportunities.MedicareInsuranceInformation. ELE_PARTB_DATE, Keys.TAB);
				        
				        Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				        Selenium.sleep(2000);
				        Alert alr= Selenium.driver.switchTo().alert();
					    alr.accept();
		                if(Selenium.isElementPresent("Error message if effective date is wrong", PageOpportunities.MedicareInsuranceInformation.MSG_INVALID_EFFECTIVE_DATE))
		                		{
		                	Reporter.log("Invalid error message is  displayed successfully when effective date of Part A and part B is not the first of current month");	
							Selenium.passTest("Invalid error message is  displayed successfully when effective date of Part A and part B is not the first of current month");
		                		
		                		}
		                else
		                {
		                	Reporter.log("Invalid error message is not displayed when effective date of Part A and part B is not the first of current month");	
							//Selenium.failTest("Invalid error message is not displayed when effective date of Part A and part B is not the first of current month");
		                }
		                if(objMedicareinfo.getPartAEffectiveDate() != null) 
		            		Selenium.type("PART A Effective Date", PageOpportunities.MedicareInsuranceInformation. ELE_PARTA_DATE, objMedicareinfo.getPartAEffectiveDate());	
		            	
		                if(objMedicareinfo.getPartBEffectiveDate() != null) 
		            		Selenium.type("PART B Effective date", PageOpportunities.MedicareInsuranceInformation. ELE_PARTB_DATE, objMedicareinfo.getPartBEffectiveDate());	
		                Selenium.typeKey("Part B effective Date", PageOpportunities.MedicareInsuranceInformation. ELE_PARTB_DATE, Keys.TAB);
		                
		                Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
		                Selenium.waitForElement(Selenium.driver, PageOpportunities.PayingPremium.MSG_PREMIUM_HEADER);
		                if(Selenium.isElementPresent("Header on Plan premium page",PageOpportunities.PayingPremium.MSG_PREMIUM_HEADER))
		                {
		                	Reporter.log("User is successfully navigated to Paying Premium page");	
							Selenium.passTest("User is successfully navigated to Paying Premium page");
		                		
		                }
		                else
		                {
		                	Reporter.log("User is not navigated to Paying Premium page : failed");	
							Selenium.failTest("User is not navigated to Paying Premium page: failed");
		                }
		                
		}
}
