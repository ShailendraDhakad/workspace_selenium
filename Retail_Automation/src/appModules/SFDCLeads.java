
package appModules;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import pageObjects.*;
import pageObjects.PageLeads.*;
import testData.LeadData;
import testData.MedicareAdvantageQuote;
import testData.MyInformationData;
import utility.*;



public class SFDCLeads {
	public static LeadData objLeadData=new LeadData();
	public static MedicareAdvantageQuote objmedicareadvantagequote=new MedicareAdvantageQuote();
	
	/* Verify at new Leads page */
	public static boolean isLeadsPage(){
		return Selenium.isElementPresent(PageLeads.VERIFY_AT_ELEMENT);
		
	}
	
	public static boolean isMyInformationPage(){
		return Selenium.isElementPresent(PageOpportunities.MyInformation.BUTTON_CANCEL);
		
	}
	
	public static String getZipCode(String sState){
		String strZip="";
		try {
			if (sState.equalsIgnoreCase("MA"))
				strZip= "02186";
							
			else if (sState.equalsIgnoreCase("ME"))
				strZip= "04101";
			
			else if (sState.equalsIgnoreCase("NH"))
				strZip= "03101";
			}
		catch(Exception e){
	    	   Log.error("Exception:"+e.getMessage());
	       }
		return strZip;
		
	}
	
	
	public static  LeadData SetNewLeadData (Map<String, String> TestDataDictionary)
	{
		try{	
		//Demographic info for Lead
		objLeadData.setFirstName(TestDataDictionary.get("FirstName"));
		objLeadData.setLastName(TestDataDictionary.get("LastName"));
		String StrDOB=TestDataDictionary.get("DateOfBirth");
		List <String> ArrDOB=  Arrays.asList(StrDOB.split(";"));
		objLeadData.setDateOfBirth(DateUtil.myCustomDate(ArrDOB.get(0), ArrDOB.get(1)));
		objLeadData.setZip(TestDataDictionary.get("Zip"));
		objLeadData.setLeadSource(TestDataDictionary.get("LeadSource"));
		objLeadData.setLeadStatus(TestDataDictionary.get("LeadStatus"));
		objLeadData.setLeadType(TestDataDictionary.get("LeadType"));
		objLeadData.setStreet(TestDataDictionary.get("Street"));
				}
		catch(Exception e)
		{
			 Log.error("Error in getting data from Excel. Exception:"+e.getMessage());
			 Selenium.failTest("Error in getting data from Excel.");
			 
		}
		return objLeadData;
	}
		
	
	//Demographic info for Lead
	public static String setNewLeadAllInfo(LeadData objData)
	{
		
		//Write Leads Personal Info
		
		if (objData.getFirstName()!=null) 
			Selenium.type(NewLead.TEXT_FIRSTNAME, objData.getFirstName());
		
		if (objData.getMiddleInitial()!=null) 
				Selenium.type(NewLead.TEXT_MIDDLEINITIAL, objData.getMiddleInitial());
				
		if (objData.getLastName()!=null)
			Selenium.type(NewLead.SELECT_LASTNAME, objData.getLastName());
		
		if (objData.getSuffix()!=null)
			Selenium.type(NewLead.SELECT_SUFFIX, objData.getSuffix());
		
		if (objData.getSSN()!=null)
			Selenium.type(NewLead.TEXT_SSN, objData.getSSN());
		
		if (objData.getSalesRep()!=null)
			Selenium.type(NewLead.TEXT_SALESREP , objData.getSalesRep());
		
		if (objData.getCampaign()!=null)
			Selenium.type(NewLead.TEXT_CAMPAIGN , objData.getCampaign());
		
		//Enter Lead source, Lead status and Lead type
		if (objData.getLeadSource()!=null)
				Selenium.select("Lead Source", NewLead.SELECT_LEADSOURCE, objData.getLeadSource());
				
		if (objData.getLeadStatus()!=null)
				Selenium.select("Lead Status", NewLead.SELECT_LEADSTATUS, objData.getLeadStatus());
				
		if (objData.getLeadType()!=null)
				Selenium.select("Lead Type", NewLead.SELECT_LEADTYPE, objData.getLeadType());
				
		if (objData.getDateOfBirth()!=null){
				Selenium.type(NewLead.TEXT_DATEOFBIRTH, objData.getDateOfBirth());
				Selenium.typeKey("Date of Birth", NewLead.TEXT_DATEOFBIRTH, Keys.TAB);
		}
		
		//Write Contact info
		if (objData.getHomePhone()!=null)
			Selenium.type(NewLead.TEXT_HONEPHONE, objData.getHomePhone());
		
		if (objData.getMobile()!=null)
			Selenium.type(NewLead.TEXT_MOBILEPHONE, objData.getMobile());
		
		if (objData.getWorkPhone()!=null)
			Selenium.type(NewLead.TEXT_WORKPHONE, objData.getWorkPhone());
		
		if (objData.getEmail()!=null)
			Selenium.type(NewLead.TEXT_EMAIL, objData.getEmail());
		
		//Click the Mobile phone Preference Checkbox
		if (objData.getMobilePreferred()=="Yes")
		Selenium.click("Mobile Phone Preference CheckBox", NewLead.CHECK_MOBILEPREF);
		
		if (objData.getHomePhonePreferred()=="Yes")
			Selenium.click("Home Phone Preference CheckBox", NewLead.CHECK_HOMEPHONEPREF);
		
		if (objData.getWorkPhonePreferred()=="Yes")
			Selenium.click("Work Phone Preference CheckBox", NewLead.CHECK_WORKPHONEPREF);
		
		if (objData.getEmailPreferred()=="Yes")
			Selenium.click("Work Phone Preference CheckBox", NewLead.CHECK_EMAILPREF);
				
		if (objData.getStreet()!=null)
			Selenium.type(NewLead.TEXTAREASTREET, objData.getStreet());
		
		if (objData.getCity()!=null)
			Selenium.type(NewLead.TEXT_CITY, objData.getCity());
		
		if (objData.getZip()!=null)
			Selenium.type(NewLead.TEXT_ZIP, objData.getZip());
		
		if (objData.getState()!=null)
				Selenium.select("Lead State", NewLead.SELECT_STATE  ,objData.getState());
		
		//Fill Communication Permission
		if (objData.getPermissionToCall()=="Yes")
				Selenium.click("Permission to call", NewLead.CHECK_PERMISSIONTOCALL  );
		
		if (objData.getPermissionToEmail()=="Yes")
				Selenium.click("Permission to Email", NewLead.CHECK_PERMISSIONTOEMAIL    );
		
		if (objData.getPermissionToMail()=="Yes")
				Selenium.click("Permission to Mail", NewLead.CHECK_PERMISSIONTOMAIL    );
		
		//Fill Eligibilioty Info
		if (objData.getMedicareNo()!=null)
			Selenium.type(NewLead.TEXT_MEDICARENO, objData.getMedicareNo());
		
		if (objData.getPartBEffectiveDate()!=null)
			Selenium.type(NewLead.TEXT_PARTBEFFECTIVEDATE, objData.getPartBEffectiveDate());
		
		return objData.getLastName();
	}
	
	/* Function Name		: 	newLead 
	 * Description			:	It will create a new lead	
	 * @param	dobCriteria : 	Either Adult or Child (randomly generated)
	 * @param	lstate 		:	State of Lead at which he lives(MA, ME or NH)
	 * @param	leadSource 	:	Source ("Other/Null/Blank")
	 * @param	leadStatus 	:	Status of the application(eg: Open)
	 * @param	leadType 	:	Type of Lead(medicare or individual)
	 * */
	
	
	public static String newLead(Map<String, String> TestDataDictionary){
		String LeadLName="";
		Selenium.click("New", PageLeads.BUTTON_NEW);
		boolean sResult = Selenium.isElementPresent(NewLead.VERIFY_AT_ELEMENT);
		if(sResult){
			Reporter.log("Sucessfully Navigated to NEW Lead Page.");
			Selenium.passTest("Sucessfully Navigated to NEW Lead Page.");
			//Set the leads demographic info
			LeadLName= setNewLeadAllInfo(SetNewLeadData(TestDataDictionary));
			
			
		}	
		else{
			Reporter.log("Navigation to NEW Lead Page is failed.");
			Selenium.failTest("Navigation to Leads Page is failed.");
		}
		return LeadLName;

	}
	
	public static void mergeCreateNewLead(String sOption){
		try{
			if (sOption.equalsIgnoreCase("create")){
				if (Selenium.getWebElement(PageLeads.LeadDeatils.BUTTON_CREATE_NEW_ACCOUNT,"Create New").isDisplayed()){
					Selenium.click("create New Account", PageLeads.LeadDeatils.BUTTON_CREATE_NEW_ACCOUNT);
					Selenium.click("Yes", PageLeads.LeadDeatils.BUTTON_YES);
					Selenium.sleep(5000);
				}
			}
		}
		catch (Exception exception){
			Log.info(exception.toString());
		}

	}
	public static String convertLead() throws InterruptedException{	
		
		String OpportunityName="";
		//Selenium.waitForElement( Selenium.driver, PageLeads.LeadDeatils.BUTTON_TOPCONVERTLEAD);
		Selenium.click("Convert Lead", PageLeads.LeadDeatils.BUTTON_TOPCONVERTLEAD);
		Selenium.sleep(5000);
		
		mergeCreateNewLead("Create");
		
		
		boolean sResult = Selenium.isElementPresent(PageLeads.LeadDeatils.BUTTON_TOPEDIT);
				
		if(sResult){
			Reporter.log("Sucessfully Navigated to Opportunity Page.");
			
			//Validate Opportunity Page
			String leadsFName=objLeadData.getFirstName();//"Lead_FirstName"
			String leadType=objLeadData.getLeadType();//"Lead_type"
			String leadSrc=objLeadData.getLeadSource();//"Source"
			String strExpiarydate;
						
			boolean blnOpprName,blnLsource,blnltype,blnProb,blnExpirydate;
			blnOpprName=Selenium.ValidateObjectAndText("Opportunity Name", PageOpportunities.Details.Element_OpportunityName, leadsFName, true);
			OpportunityName=Selenium.getWebElement(PageOpportunities.Details.Element_OpportunityName, "Opportunity Name").getText();
			blnLsource=Selenium.ValidateObjectAndText("Lead Source", PageOpportunities.Details.Element_LeadSource, leadSrc, true);
			blnltype=Selenium.ValidateObjectAndText("Record type", PageOpportunities.Details.Element_OpporRecordType, leadType, true);
			blnProb=Selenium.ValidateObjectAndText("Probability", PageOpportunities.Details.Element_Probability, "25", true);
			
			//get expiry daye
			Date ExpiaryDate=StringUtil.toDate(DateUtil.myCustomDate("Dynamic", "6MF"), "M/d/yyyy");
			//ExpiaryDate=DateUtil.addDays(ExpiaryDate,-1);
			strExpiarydate=StringUtil.dateToString(ExpiaryDate, "M/d/yyyy");
			
			blnExpirydate=Selenium.ValidateObjectAndText("Expiry Date", PageOpportunities.Details.Element_OpporExpiryDate, strExpiarydate, false);
			
			if (blnOpprName && blnLsource && blnltype  && blnProb){
				Log.info("Opportunity page is Successfully validated.");
				Reporter.log("Opportunity page is Successfully validated.");
				Selenium.passTest("Opportunity page is Successfully validated.");
			}
			else{
				Log.error("Opportunity page validation is failed.");
				Reporter.log("Opportunity page validation is failed.");
				Selenium.failTest("Opportunity page validation is failed.");
			}
			
			//Click Quote Enroll
			if (Selenium.isElementPresent( "Quote Enroll",PageOpportunities.Details.BUTTON_QUOTEENROLL)){   
			Selenium.click("QUOTE ENROLL", PageOpportunities.Details.BUTTON_QUOTEENROLL);
			Selenium.sleep(10000);
			}
											
		}
		else{
			Reporter.log("Navigation to Opportunity is failed");
			Selenium.failTest("Navigation to Opportunity is failed");
		}
		return OpportunityName;
	}
	
public static String click_ConvertLead() throws InterruptedException{	
		
		String OpportunityName="";
		//Selenium.waitForElement( Selenium.driver, PageLeads.LeadDeatils.BUTTON_TOPCONVERTLEAD);
		Selenium.click("Convert Lead", PageLeads.LeadDeatils.BUTTON_TOPCONVERTLEAD);
		Selenium.sleep(5000);
		
		mergeCreateNewLead("Create");
		
		
		boolean sResult = Selenium.isElementPresent(PageLeads.LeadDeatils.BUTTON_TOPEDIT);
				
		if(sResult){
			Reporter.log("Sucessfully Navigated to Opportunity Page.");
			
			//Validate Opportunity Page
			String leadsFName=objLeadData.getFirstName();//"Lead_FirstName"
			String leadType=objLeadData.getLeadType();//"Lead_type"
			String leadSrc=objLeadData.getLeadSource();//"Source"
			String strExpiarydate;
						
			boolean blnOpprName,blnLsource,blnltype,blnProb,blnExpirydate;
			blnOpprName=Selenium.ValidateObjectAndText("Opportunity Name", PageOpportunities.Details.Element_OpportunityName, leadsFName, true);
			OpportunityName=Selenium.getWebElement(PageOpportunities.Details.Element_OpportunityName, "Opportunity Name").getText();
			blnLsource=Selenium.ValidateObjectAndText("Lead Source", PageOpportunities.Details.Element_LeadSource, leadSrc, true);
			blnltype=Selenium.ValidateObjectAndText("Record type", PageOpportunities.Details.Element_OpporRecordType, leadType, true);
			blnProb=Selenium.ValidateObjectAndText("Probability", PageOpportunities.Details.Element_Probability, "25", true);
			
			//get expiry daye
			Date ExpiaryDate=StringUtil.toDate(DateUtil.myCustomDate("Dynamic", "6MF"), "M/d/yyyy");
			//ExpiaryDate=DateUtil.addDays(ExpiaryDate,-1);
			strExpiarydate=StringUtil.dateToString(ExpiaryDate, "M/d/yyyy");
			
			blnExpirydate=Selenium.ValidateObjectAndText("Expiry Date", PageOpportunities.Details.Element_OpporExpiryDate, strExpiarydate, false);
			
			if (blnOpprName && blnLsource && blnltype  && blnProb){
				Log.info("Opportunity page is Successfully validated.");
				Reporter.log("Opportunity page is Successfully validated.");
				Selenium.passTest("Opportunity page is Successfully validated.");
			}
			else{
				Log.error("Opportunity page validation is failed.");
				Reporter.log("Opportunity page validation is failed.");
				Selenium.failTest("Opportunity page validation is failed.");
			}
			
														
		}
		else{
			Reporter.log("Navigation to Opportunity is failed");
			Selenium.failTest("Navigation to Opportunity is failed");
		}
		return OpportunityName;
	}

	public static void validateVIP()
	{
		boolean vipflag=Selenium.isElementPresent(PageLeads.LeadDeatils.ELEMENT_VIP);
		if (vipflag)
		{
			WebElement LeadVIPElement=Selenium.getWebElement(PageLeads.LeadDeatils.ELEMENT_VIP, "VIP");
			String strVIPNumbr=LeadVIPElement.getText();
			objLeadData.setLeadVIP(strVIPNumbr);
			Reporter.log("Lead VIP# is generated Sucessfully for the new lead.");
			Selenium.passTest("Lead VIP# is generated Sucessfully for the new lead.");
		}
		else{
			Reporter.log("Lead VIP# is not generated correctly.");
			Selenium.failTest("Lead VIP# is not generated.");
		}
	}
	
	public static void ValidateAndfillToMedicare(Map<String, String> TestDataDictionary) throws InterruptedException, AWTException
	{

//		Selenium.waitForElement(Selenium.driver, PageOpportunities.MedicareAdvantageQuote.MSG_AUDITPUROSES); 
//		Selenium.waitForElement(Selenium.driver, PageOpportunities.MedicareAdvantageQuote.MSG_CONFIRMMESSAGE);
//		Selenium.waitForElement(Selenium.driver, PageOpportunities.MedicareAdvantageQuote.MSG_MANDATORYMESSAGE);
		Selenium.waitForElement(Selenium.driver, PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE);
		objmedicareadvantagequote.setYear(TestDataDictionary.get("MEDICAREYEAR"));
		objmedicareadvantagequote.setZip(TestDataDictionary.get("MEDICAREZIP"));
		if(objmedicareadvantagequote.getYear() != null) 
			
		
		Selenium.select("Year", PageOpportunities.MedicareAdvantageQuote.ELEMENT_YEAR, new SimpleDateFormat("yyyy").format(new Date()));
		
		 if(objmedicareadvantagequote.getZip() != null) 
			Selenium.type("Zip code", PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE, objmedicareadvantagequote.getZip());
		
		Selenium.sleep(3000); 
		Selenium.typeKey("Zip code", PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE, Keys.TAB);
        Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
        Selenium.sleep(2000);
        try{
	        if (Selenium.driver.switchTo().alert() != null){
				Selenium.handleBrowserPopUp("OK");
				Selenium.sleep(2000);
				 Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				Selenium.sleep(2000);
			}
	        else
	        	Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
        }
        catch(Exception e){
        	
        }
       
		Selenium.waitForElement(Selenium.driver, PageOpportunities.ComparePlansMedicare.BUTTON_CANCEL);
		if(Selenium.isElementPresent("Cancel button", PageOpportunities.ComparePlansMedicare.BUTTON_CANCEL))
		{
			Reporter.log("Sucessfully validated user is navigated to compare plan page");
			Log.info("Sucessfully validated user is navigated to compare plan page");
			Selenium.passTest("Sucessfully validated user is navigated to compare plan page");
		}
		else
		{
			Reporter.log(" validated user is navigated to compare plan page: Failed");
			Log.error("validated user is navigated to compare plan page: Failed");
			Selenium.failTest("validated user is navigated to compare plan page: Failed");
		}
	}
	
	public static void ValidateAndfillToMedicareInvalidScenarios(Map<String, String> TestDataDictionary) throws InterruptedException
	{

//		Selenium.waitForElement(Selenium.driver, PageOpportunities.MedicareAdvantageQuote.MSG_AUDITPUROSES); 
//		Selenium.waitForElement(Selenium.driver, PageOpportunities.MedicareAdvantageQuote.MSG_CONFIRMMESSAGE);
//		Selenium.waitForElement(Selenium.driver, PageOpportunities.MedicareAdvantageQuote.MSG_MANDATORYMESSAGE);
		Selenium.waitForElement(Selenium.driver, PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE);
		objmedicareadvantagequote.setYear(TestDataDictionary.get("MEDICAREYEAR"));
		objmedicareadvantagequote.setZip(TestDataDictionary.get("MEDICAREZIP"));
		
		if(objmedicareadvantagequote.getYear() != null) 
			Selenium.select("Year", PageOpportunities.MedicareAdvantageQuote.ELEMENT_YEAR, "2017");
		
		//Verify error message when zip is entered for any other state then NH/MA/ME
		Selenium.type("Zip Code", PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE, "06001");
		 Selenium.typeKey("Zip code", PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE, Keys.TAB);
		 Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
		 Selenium.sleep(4000);

		 if (Selenium.isElementPresent(PageOpportunities.ComparePlansMedicare.ELEMENT_NOPRODUCT_MSG)){
			Reporter.log("Sucessfully validated Error Message after entering ZIP code of any other state than NH/MA/ME at Medicare Enrollment page.");
			Log.info("Sucessfully validated Error Message after entering ZIP code of any other state than NH/MA/ME at Medicare Enrollment page.");
			Selenium.passTest("Sucessfully validated Error Message after entering ZIP code of any other state than NH/MA/ME at Medicare Enrollment page.");
			 Selenium.clickButtons("Prevoius button", PageSFDC.BUTTON_PREVIOUS);
			Selenium.sleep(3000);
		}
		else{
			Reporter.log("Validating Error Message after entering ZIP code of any other state than NH/MA/ME at Medicare Enrollment page failed");
			Log.info("Validating Error Message after entering ZIP code of any other state than NH/MA/ME at Medicare Enrollment page failed");
			Selenium.failTest("Validating Error Message after entering ZIP code of any other state than NH/MA/ME at Medicare Enrollment page failed");
			
		}
		//Verify error message when zip length is less than 5 digits
		Selenium.type("ZIP code", PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE, "0600");
		Selenium.typeKey("Zip code", PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE, Keys.ENTER);
		Selenium.sleep(2000);
		 
		if (Selenium.isAlertPresent()){
			Reporter.log("Sucessfully validated Error Message after entering ZIP<5 code of any state E at Medicare Enrollment page " + Selenium.driver.switchTo().alert().getText());
			Log.info("Sucessfully validated Error Message after entering ZIP < 5 code of any state at Medicare Enrollment page " + Selenium.driver.switchTo().alert().getText());
			Selenium.passTest("Sucessfully validated Error Message after entering ZIP < 5 code of any state at Medicare Enrollment page.",false);
			Selenium.handleBrowserPopUp("OK");
			Selenium.sleep(2000);
		}
		else{
			Reporter.log("Validating Error Message after entering ZIP <5 code of any  state  at Medicare Enrollment page failed");
			Log.info("Validating Error Message after entering ZIP < 5 code of any state  at Medicare Enrollment page failed");
			Selenium.failTest("Validating Error Message after entering ZIP < 5 code of any state  at Medicare Enrollment page failed");
			}
		//Verify error message when incorrect 5 digit zip code is entered 
		Selenium.type("ZIP code", PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE, "01006");
		Selenium.typeKey("Zip code", PageOpportunities.MedicareAdvantageQuote.ELEMENT_ZIPCODE, Keys.TAB);
		Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
		 Selenium.sleep(2000);

		if (Selenium.isAlertPresent()){
			Reporter.log("Sucessfully validated Error Message after entering invalid zip code of state NH/MA/ME at Medicare Enrollment page " + Selenium.driver.switchTo().alert().getText());
			Log.info("Sucessfully validated Error Message after entering invalid zip code of state NH/MA/ME at Medicare Enrollment page " + Selenium.driver.switchTo().alert().getText());
			Selenium.passTest("Sucessfully validated Error Message after entering invalid zip code of state NH/MA/ME at Medicare Enrollment page .",false);
			Selenium.handleBrowserPopUp("OK");
			Selenium.sleep(2000);
		}
		else{
			Reporter.log("Validating Error Message after entering invalid ZIP code of state NH/MA/ME at Medicare Enrollment page failed");
			Log.info("Validating Error Message after entering invalid ZIP code of state NH/MA/ME at Medicare Enrollment page failed");
			Selenium.failTest("Validating Error Message after entering invalid ZIP code of state NH/MA/ME at Medicare Enrollment page failed");
			
			}
		
	}
	
	public static void navigateToMedicare() throws InterruptedException
	{
		//Selenium.waitForElement( Selenium.driver, PageOpportunities.Details.BUTTON_MEDICAREADVENROLL);
		Selenium.sleep(5000);
		Selenium.clickButtons("Medicare Advantage Enrollment", PageOpportunities.Details.BUTTON_MEDICAREADVENROLL);
		Selenium.sleep(10000);
		if(Selenium.isElementPresent(PageOpportunities.MedicareAdvantageQuote.ELEMENT_YEAR))
				{Log.info("Medicare Advantage quote page is Successfully validated.");
					Reporter.log("Medicare Advantage quote  page is Successfully validated.");
					Selenium.passTest("Medicare Advantage quote  page is Successfully validated.");
				
				}
		else 
		{ Log.info("Medicare Advantage quote  page is not validated failed.");
		Reporter.log("Medicare Advantage quote  page is not validated failed.");
		Selenium.failTest("Medicare Advantage quote  page is not validated failed.");
		}
	}
}
//	public static void ValidateAccount(){
//		String LinkName=objLeadData.getFirstName().toUpperCase() + " " + objLeadData.getLastName().toUpperCase();
//		String sLocator=PageOpportunities.Details.LINK_ACCOUNTNAME.replace("strToreplace", LinkName);
//		
//		//Click Account Name
//		Selenium.click("Account Name", sLocator);
//		
//	}
//	
	


