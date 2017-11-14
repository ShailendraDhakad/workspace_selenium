package appModules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
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
import testData.ReceiveQuoteData;
import utility.DateUtil;
import utility.Log;
import utility.Selenium;
import utility.StringUtil;

public class SFDCApplication {
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
	public static ReceiveQuoteData objReceiveQuote  = new ReceiveQuoteData();
	
	//---------------------------------------
	//Function to fill 'My Information Page'
	//---------------------------------------	
	public static  MyInformationData setMyInofrmation (Map<String, String> TestDataDictionary)
	{
		try{	
		//My Information Page
			objMyInfoData.setDependentCoverage(TestDataDictionary.get("DependentCoverage"));
			String StrDOB=TestDataDictionary.get("MyDateOfBirth");
			if (StrDOB.contains(";"))
			{
				List <String> ArrDOB=  Arrays.asList(StrDOB.split(";"));
				objMyInfoData.setMyDateOfBirth(DateUtil.myCustomDate(ArrDOB.get(0), ArrDOB.get(1)));
			}
			else
				objMyInfoData.setMyDateOfBirth(StrDOB);	
			
			
			objMyInfoData.setMyZip(TestDataDictionary.get("MyZip"));
						
			String StrEffectiveDate=TestDataDictionary.get("RequestedCoverEffDate");
			if (StrEffectiveDate.contains("Dynamic"))
			{
				Calendar c = Calendar.getInstance();   // this takes current date
			    c.set(Calendar.DAY_OF_MONTH, 1);
			    Date Firstday=c.getTime();  
			    String strFirstDay=StringUtil.dateToString(Firstday, "MM/dd/yyyy");
			    objMyInfoData.setRequestedCoverageEffDate(strFirstDay);
			}
			else
			objMyInfoData.setRequestedCoverageEffDate(StrEffectiveDate);
			
			if (!TestDataDictionary.get("Zip").contains("MA"))
				objMyInfoData.setTobaccoUse("No");
			
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for My Information Page from Dictionary Object. Exception:"+e.getMessage());
			 //Selenium.failTest("Error in getting data for My Information Page from Excel.");
			 
		}
		return objMyInfoData;
	}
	
	public static void setAllMyInformation(MyInformationData objMyInfoData) throws InterruptedException
	{
		Selenium.waitForElement( Selenium.driver, PageOpportunities.MyInformation.TEXT_DATEOFBIRTH);
		
		//Fill My Information Page
				
		if(objMyInfoData.getMyDateOfBirth() != null && !objMyInfoData.getMyDateOfBirth().isEmpty())
			{ Selenium.type(PageOpportunities.MyInformation.TEXT_DATEOFBIRTH, objLead.objLeadData.getDateOfBirth()); }
	
					
		if(objMyInfoData.getMyZip() != null && !objMyInfoData.getMyZip().isEmpty())
				Selenium.type(PageOpportunities.MyInformation.TEXT_ZIPCODE, objMyInfoData.getMyZip());
		
		if(objMyInfoData.getDependentCoverage() != null && !objMyInfoData.getDependentCoverage().isEmpty())
			Selenium.select("Dependent Coverage", PageOpportunities.MyInformation.SELECT_COVERAGE, objMyInfoData.getDependentCoverage());
		
		if(objMyInfoData.getRequestedCoverageEffDate() != null && !objMyInfoData.getRequestedCoverageEffDate().isEmpty())
				Selenium.select("Requested Coverage EffDate", PageOpportunities.MyInformation.SELECT_COVERAGEEFFECTIVEDATE, objMyInfoData.getRequestedCoverageEffDate());
		
		if(objMyInfoData.getTobaccoUse() != null && !objMyInfoData.getTobaccoUse().isEmpty())
			Selenium.select("Tobacco Use", PageOpportunities.MyInformation.SELECT_TOBACCO_USE, objMyInfoData.getTobaccoUse());
	
		Reporter.log("All fields in My Information Page is filled.");
		Selenium.passTest("All fields in My Information Page is filled successsfully.");
		
	}
	
	
	//---------------------------------------
	//Function to Verify 'My Information Page'
	//---------------------------------------
	public static void verifyMyInformationPage(){
		//verify if Zip code is pre-filled
		if (Selenium.getWebElement(PageOpportunities.MyInformation.TEXT_ZIPCODE, "Zip Code").getText()!=null){
			Reporter.log("Zip code is Pre-filled at My Information page.");
			Selenium.passTest("Zip code is Pre-filled at My Information page.");
		}
		else{
			Reporter.log("Zip code is empty at My Information page.");
			Selenium.passTest("Zip code is empty at My Information page.");
		}
		
		//verify if DOB is pre-filled
		if (Selenium.getWebElement(PageOpportunities.MyInformation.TEXT_DATEOFBIRTH, "Date of Birth").getText()!=null){
			Reporter.log("Date of Birth is Pre-filled at My Information page.");
			Selenium.passTest("Date of Birth is Pre-filled at My Information page.");
		}
		else{
			Reporter.log("Date of Birth is empty at My Information page.");
			Selenium.passTest("Date of Birth is empty at My Information page.");
		}
		
		//Verify tobacco
		
		
		//verify Cancel
		Selenium.click("Cancel", PageOpportunities.MyInformation.BUTTON_CANCEL);
		//ErrorMessage=Selenium.driver.switchTo().alert().getText();
		if (Selenium.driver.switchTo().alert().getText().contains("exit")){
			Reporter.log("Sucessfully validated Error Message after clicking Cancel at My Information Page: " + Selenium.driver.switchTo().alert().getText());
			Log.info("Sucessfully validated Error Message after clicking Cancel at My Information Page: " + Selenium.driver.switchTo().alert().getText());
			Selenium.handleBrowserPopUp("Cancel");
		}
		else{
			Reporter.log("Cancel Button functionality at My Information Page failed");
			Log.info("Cancel Button functionality at My Information Page failed");
		}
		
		//Verify Next
		//Selenium.click("Next", PageOpportunities.MyInformation.BUTTON_NEXT);
		Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);	
		
		if (objMyInfoData.getDependentCoverage().contains("No")){
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
			if (Selenium.isElementPresent("Product Dropdown", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, true)){
				Reporter.log("Next Button functionality at My Information Page passed");
				Reporter.log("My Information Page is filled successsfully.");
				Log.info("Next Button functionality at My Information Page passed");
				Reporter.log("Next Button functionality at My Information Page passed");
				Selenium.passTest("My Information Page is filled successsfully.");
			}
			else{
				Selenium.failTest("My Information Page is not filled successsfully.");
				Reporter.log("My Information Page is NOT filled.");
				Reporter.log("Next Button functionality at My Information Page failed");
				Log.info("Next Button functionality at My Information Page failed");
				Log.error("My Information Page is NOT filled.");
			}
		}
		
		else{
			Selenium.waitForElement(Selenium.driver, PageOpportunities.MyFamily.SELECT_RELATION);
			if (Selenium.isElementPresent("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION, true)){
				Reporter.log("Next Button functionality at My Information Page passed");
				Reporter.log("My Information Page is filled successsfully.");
				Log.info("Next Button functionality at My Information Page passed");
				Reporter.log("Next Button functionality at My Information Page passed");
				Selenium.passTest("My Information Page is filled successsfully.");
			}
			else{
				Selenium.failTest("My Information Page is not filled successsfully.");
				Reporter.log("My Information Page is NOT filled.");
				Reporter.log("Next Button functionality at My Information Page failed");
				Log.error("Next Button functionality at My Information Page failed");
				Log.error("My Information Page is NOT filled.");
			}
		}
	}
	
	
	//----------------------------------------------------------------
	//Function to fill 'My Information Page'  ==> Invalid Scenarios
	//----------------------------------------------------------------
	public static void setMyInformation_InvalidConditions(MyInformationData objMyInfoData)
	{
		Selenium.waitForElement( Selenium.driver, PageOpportunities.MyInformation.TEXT_DATEOFBIRTH);
		//Fill My Information Page
		if(objMyInfoData.getDependentCoverage() != null && !objMyInfoData.getDependentCoverage().isEmpty())
			Selenium.select("Dependent Coverage", PageOpportunities.MyInformation.SELECT_COVERAGE, objMyInfoData.getDependentCoverage());
		
		if(objMyInfoData.getRequestedCoverageEffDate() != null && !objMyInfoData.getRequestedCoverageEffDate().isEmpty())
				Selenium.select("Requested Coverage EffDate", PageOpportunities.MyInformation.SELECT_COVERAGEEFFECTIVEDATE, objMyInfoData.getRequestedCoverageEffDate());
		String ErrorMessage="";			
		
		//Invalid Zip
			//Set the zip code other than MA, ME and NH
			Selenium.type(PageOpportunities.MyInformation.TEXT_ZIPCODE, "13901");
			Selenium.ValidateObjectAndText("Non MA/ME/NH Zip Code Error", PageOpportunities.MyInformation.ELE_ERRORMESSAGE, "Zip", true);
			Selenium.getWebElement(PageOpportunities.MyInformation.TEXT_ZIPCODE, "Zip Code").clear();
			
			//<5 digit Zip code
			Selenium.type("Zip Code",PageOpportunities.MyInformation.TEXT_ZIPCODE, "111",false);
			//Selenium.click("Next", PageOpportunities.MyInformation.BUTTON_NEXT);
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			ErrorMessage=Selenium.driver.switchTo().alert().getText();
			if (ErrorMessage.contains("fix")){
				Reporter.log("Sucessfully validated Error Message after submitting Zip code of less than 5 digit.");
				Log.info("Sucessfully validated Error Message after submitting Zip code of less than 5 digit.");
			}
			else{
				Reporter.log("Zip code of less than 5 digit error validation Failed with error message as:" + ErrorMessage+" .");
				Log.info("Zip code of less than 5 digit error validation Failed with error message as:" + ErrorMessage+" .");
			}
				
			Selenium.handleBrowserPopUp("OK");
			Selenium.getWebElement(PageOpportunities.MyInformation.TEXT_ZIPCODE, "Zip Code").clear();
			
			//>5 digit Zip code
			Selenium.type("Zip Code",PageOpportunities.MyInformation.TEXT_ZIPCODE, "111111",false);
			//Selenium.click("Next", PageOpportunities.MyInformation.BUTTON_NEXT);
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			ErrorMessage=Selenium.driver.switchTo().alert().getText();
			if (ErrorMessage.contains("fix")){
				Reporter.log("Sucessfully validated Error Message: "+ErrorMessage +" after submitting Zip code of greater than 5 digit.");
				Log.info("Sucessfully validated Error Message : "+ErrorMessage +" after submitting Zip code of greater than 5 digit.");
			}
			else{
				Reporter.log("Zip code of greater than 5 digit error validation Failed with error message as:" + ErrorMessage+" .");
				Log.info("Zip code of greater than 5 digit error validation Failed with error message as:" + ErrorMessage+" .");
			}
						
			Selenium.handleBrowserPopUp("OK");
			Selenium.getWebElement(PageOpportunities.MyInformation.TEXT_ZIPCODE, "Zip Code").clear();
			
			//"01006"
			Selenium.type(PageOpportunities.MyInformation.TEXT_ZIPCODE, "01006");
			//Selenium.click("Next", PageOpportunities.MyInformation.BUTTON_NEXT);
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			ErrorMessage=Selenium.driver.switchTo().alert().getText();
			if (ErrorMessage.contains("fix")){
				Reporter.log("Sucessfully validated Error Message: "+ErrorMessage +" after submitting Zip code '01006'.");
				Log.info("Sucessfully validated Error Message : "+ErrorMessage +" after submitting Zip code '01006'.");
			}
			else{
				Reporter.log("Zip code '01006' error validation Failed with error message as:" + ErrorMessage+" .");
				Log.info("Zip code '01006' error validation Failed with error message as:" + ErrorMessage+" .");
			}
			Selenium.handleBrowserPopUp("OK");
			Selenium.getWebElement(PageOpportunities.MyInformation.TEXT_ZIPCODE, "Zip Code").clear();
			
			//Reset to MA		
			Selenium.type(PageOpportunities.MyInformation.TEXT_ZIPCODE, objLead.objLeadData.getZip());
		
		// InValid DOB
			//Set DOB having age more than 130 years
			Selenium.type(PageOpportunities.MyInformation.TEXT_DATEOFBIRTH,DateUtil.myCustomDate("Dynamic", "150YP") ); 
			//Selenium.click("Next", PageOpportunities.MyInformation.BUTTON_NEXT);
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			Selenium.sleep(4000);
			
			
			ErrorMessage=Selenium.driver.switchTo().alert().getText();
			
			if (ErrorMessage.contains("fix")){
				Reporter.log("Sucessfully validated Error Message: "+ErrorMessage +" after submitting DOB of greater than 130 Years.");
				Log.info("Sucessfully validated Error Message : "+ErrorMessage +" after submitting DOB of greater than 130 Years.");
				Selenium.handleBrowserPopUp("OK");
			}
			else{
				Reporter.log("DOB of greater than 130 Years error validation Failed with error message as:" + ErrorMessage+" .");
				Log.info("DOB of greater than 130 Years digit error validation Failed with error message as:" + ErrorMessage+" .");
			}
			
			//Selenium.ValidateObjectAndText("DOB More than 130 Years", PageOpportunities.MyInformation.ELE_ERRORMESSAGE, "Date of Birth is greater than 130 years in the past", true);
			Selenium.getWebElement(PageOpportunities.MyInformation.TEXT_DATEOFBIRTH, "Zip Code").clear();	
			
			//Set DOB as Future
			Selenium.type(PageOpportunities.MyInformation.TEXT_DATEOFBIRTH,DateUtil.myCustomDate("Dynamic", "15DF") ); 
			Selenium.ValidateObjectAndText("DOB as Future Date Error ", PageOpportunities.MyInformation.ELE_FUTUREDOBERROR, "Date of Birth Cannot Be In the Future", true);
			Selenium.getWebElement(PageOpportunities.MyInformation.TEXT_DATEOFBIRTH, "Zip Code").clear();	
			
			//Set Valid DOB
			Selenium.type(PageOpportunities.MyInformation.TEXT_DATEOFBIRTH, objLead.objLeadData.getDateOfBirth()); 
						
		//Click Next
		
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
		
	}
	
	
	//----------------------------------------------------------------
	//Function to Compare and Select Plan with Navigation functionality Check'  
	//----------------------------------------------------------------
	public static  void  fillComparePlanForm (Map<String, String> TestDataDictionary)
	{
		try{	
		//Compare Plan Page
					
			objComparePlan.setProductType(TestDataDictionary.get("ProductType"));
			objComparePlan.setDental(TestDataDictionary.get("PediatricDental"));
			objComparePlan.setFavorites(TestDataDictionary.get("Favorites"));
			objComparePlan.setMetalLevel(TestDataDictionary.get("MetalLevel"));
			objComparePlan.setNetwork(TestDataDictionary.get("Network"));
			objComparePlan.setPlan(TestDataDictionary.get("Plan"));
			objComparePlan.setPlansToCompare(TestDataDictionary.get("ComparePlans"));
			
			Selenium.waitForElement( Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
			//Thread.sleep(5000);
			
			//Verify Dropdowns on Compare Plan Page
			Selenium.ValidateObjectAndText("Product Type", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, "", false);
			Selenium.ValidateObjectAndText("Metal Level", PageOpportunities.ComparePlans.SELECT_METALLEVEL, "", false);
			Selenium.ValidateObjectAndText("Network", PageOpportunities.ComparePlans.SELECT_NETWORK, "", false);
			Selenium.ValidateObjectAndText("Pediatric Dental", PageOpportunities.ComparePlans.SELECT_DENTAL, "", false);
			Selenium.ValidateObjectAndText("Favorites", PageOpportunities.ComparePlans.SELECT_FAVORITES, "", false);
			
			//verify Cancel
//			
//			Selenium.clickButtons("Cancel", PageSFDC.BUTTON_CANCEL);
//			Selenium.sleep(3000);
//			
//			if (Selenium.isAlertPresent()){
//				Reporter.log("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
//				Log.info("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
//				Selenium.handleBrowserPopUp("Cancel");
//			}
//			else{
//				Reporter.log("Cancel Button functionality at Compare Plan Page failed.");
//				Log.info("Cancel Button functionality at Compare Plan Page failed.");
//			}
			
			
			//Verify Previous
			Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
			Selenium.sleep(2000);
			if (objMyInfoData.getDependentCoverage().contains("No")){
				if (Selenium.isElementPresent("My Info Page", PageOpportunities.MyInformation.SELECT_COVERAGE, true)){
					Reporter.log("Previous Button functionality at Compare Plan Page passed");
					Log.info("Previous Button functionality at Compare Plan Page passed");
					Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
					Selenium.waitForElement( Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
				}
				else{
					Reporter.log("Previous Button functionality at Compare Plan Page failed.");
					Log.error("Previous Button functionality at Compare Plan Page failed.");
				}
			}
			else{
				if (Selenium.isElementPresent("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION, true)){
					Reporter.log("Previous Button functionality at Compare Plan Page passed");
					Log.info("Previous Button functionality at Compare Plan Page passed");
					Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
					Selenium.waitForElement( Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
				}
				else{
					Reporter.log("Previous Button functionality at Compare Plan Page failed.");
					Log.error("Previous Button functionality at Compare Plan Page failed.");
					
				}
			}
			
			//Fill Compare Plan Page
			
			if(objComparePlan.getProductType() != null && !objComparePlan.getProductType().isEmpty()) 
				Selenium.select("Product Type", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, objComparePlan.getProductType());
			
			if(objComparePlan.getMetalLevel() != null && !objComparePlan.getMetalLevel().isEmpty()) 
				Selenium.select("Metal Level", PageOpportunities.ComparePlans.SELECT_METALLEVEL, objComparePlan.getMetalLevel());
			
			if(objComparePlan.getNetwork() != null && !objComparePlan.getNetwork().isEmpty()) 
				Selenium.select("Network", PageOpportunities.ComparePlans.SELECT_NETWORK, objComparePlan.getNetwork());
			
			if(objComparePlan.getDental() != null && !objComparePlan.getDental().isEmpty()) 
				Selenium.select("Pediatric Dental", PageOpportunities.ComparePlans.SELECT_DENTAL, objComparePlan.getDental());
			
			if(objComparePlan.getFavorites() != null && !objComparePlan.getFavorites().isEmpty()) 
				Selenium.select("Favorites", PageOpportunities.ComparePlans.SELECT_FAVORITES, objComparePlan.getFavorites());
			
			if(objComparePlan.getPlansToCompare() != null && !objComparePlan.getPlansToCompare().isEmpty()) 
				comaprePlans(PageOpportunities.ComparePlans.Element_PLANS,"Plans", objComparePlan.getPlansToCompare().toUpperCase());
			
			if(objComparePlan.getPlan() != null && !objComparePlan.getPlan().isEmpty()) 
				selectPlan(objComparePlan.getPlan().toUpperCase());
			else{
				Log.error("No plan is available to select in Test Data sheet");
			 	Selenium.failTest("No plan is available to select.");
			}
			
			
				
			//Verify Next
						
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
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
	//Function to Compare and Select Plan with Navigation functionality Check' ==> Invalid Scenarios  
	//----------------------------------------------------------------
	public static void fillComparePlanForm_InvalidConditions(Map<String, String> TestDataDictionary)
	{
	// validate all drop down fields
		//Verify Dropdowns on Compare Plan Page
		Selenium.ValidateObjectAndText("Product Type", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, "", false);
		Selenium.ValidateObjectAndText("Metal Level", PageOpportunities.ComparePlans.SELECT_METALLEVEL, "", false);
		Selenium.ValidateObjectAndText("Network", PageOpportunities.ComparePlans.SELECT_NETWORK, "", false);
		Selenium.ValidateObjectAndText("Pediatric Dental", PageOpportunities.ComparePlans.SELECT_DENTAL, "", false);
		Selenium.ValidateObjectAndText("Favorites", PageOpportunities.ComparePlans.SELECT_FAVORITES, "", false);
		
		//Validate all buttons
		Selenium.ValidateObjectAndText("Compare plan", PageOpportunities.ComparePlans.BUTTON_COMPAREPLAN, "", false);
		Selenium.ValidateObjectAndText("Select Plan", PageOpportunities.ComparePlans.BUTTON_SELECTPLAN, "", false);
		Selenium.ValidateObjectAndText("Compare plan checkbox", PageOpportunities.ComparePlans.CHECKCOMPARE, "", false);
		
		//Validate Links
		Selenium.ValidateObjectAndText("View Schedule of Benefits", PageOpportunities.ComparePlans.Link_SOB, "", false);
		Selenium.ValidateObjectAndText("View Summary of Benefits and Coverage", PageOpportunities.ComparePlans.Link_SBC, "", false);
		Selenium.ValidateObjectAndText("Search Provider Directory", PageOpportunities.ComparePlans.Link_SEARCHPROVIDERDIRECTORY, "", false);
		Selenium.ValidateObjectAndText("Formulary", PageOpportunities.ComparePlans.Link_Formulary, "", false);
	
		//verify Cancel
		Selenium.clickButtons("Cancel", PageSFDC.BUTTON_CANCEL);
		Selenium.sleep(2000);
		if (Selenium.isAlertPresent()){
			Reporter.log("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
			Log.info("Sucessfully validated Error Message after clicking Cancel at Compare Plan Page: " + Selenium.driver.switchTo().alert().getText());
			Selenium.handleBrowserPopUp("Cancel");
		}
		else{
			Reporter.log("Cancel Button functionality at Compare Plan Page failed.");
			Log.info("Cancel Button functionality at Compare Plan Page failed.");
		}
		
		
		//Verify Previous
		 Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
		 Selenium.sleep(2000);
		if (objMyInfoData.getDependentCoverage().contains("No")){
			if (Selenium.isElementPresent("My Info Page", PageOpportunities.MyInformation.SELECT_COVERAGE, true)){
				Reporter.log("Previous Button functionality at Compare Plan Page passed");
				Log.info("Previous Button functionality at Compare Plan Page passed");
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				Selenium.waitForElement( Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
			}
			else{
				Reporter.log("Previous Button functionality at Compare Plan Page failed.");
				Log.error("Previous Button functionality at Compare Plan Page failed.");
			}
		}
		else{
			if (Selenium.isElementPresent("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION, true)){
				Reporter.log("Previous Button functionality at Compare Plan Page passed");
				Log.info("Previous Button functionality at Compare Plan Page passed");
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				Selenium.waitForElement( Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
			}
			else{
				Reporter.log("Previous Button functionality at Compare Plan Page failed.");
				Log.error("Previous Button functionality at Compare Plan Page failed.");
				
			}
		}
		
		objComparePlan.setProductType(TestDataDictionary.get("ProductType"));
		objComparePlan.setDental(TestDataDictionary.get("PediatricDental"));
		objComparePlan.setFavorites(TestDataDictionary.get("Favorites"));
		objComparePlan.setMetalLevel(TestDataDictionary.get("MetalLevel"));
		objComparePlan.setNetwork(TestDataDictionary.get("Network"));
		objComparePlan.setPlan(TestDataDictionary.get("Plan"));
		objComparePlan.setPlansToCompare(TestDataDictionary.get("ComparePlans"));
		
		Selenium.waitForElement( Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
		//Thread.sleep(5000);
		//Fill Compare Plan Page
		
		if(objComparePlan.getProductType() != null && !objComparePlan.getProductType().isEmpty()) 
			Selenium.select("Product Type", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, objComparePlan.getProductType());
		
		if(objComparePlan.getMetalLevel() != null && !objComparePlan.getMetalLevel().isEmpty()) 
			Selenium.select("Metal Level", PageOpportunities.ComparePlans.SELECT_METALLEVEL, objComparePlan.getMetalLevel());
		
		if(objComparePlan.getNetwork() != null && !objComparePlan.getNetwork().isEmpty()) 
			Selenium.select("Network", PageOpportunities.ComparePlans.SELECT_NETWORK, objComparePlan.getNetwork());
		
		if(objComparePlan.getDental() != null && !objComparePlan.getDental().isEmpty()) 
			Selenium.select("Pediatric Dental", PageOpportunities.ComparePlans.SELECT_DENTAL, objComparePlan.getDental());
		
		if(objComparePlan.getFavorites() != null && !objComparePlan.getFavorites().isEmpty()) 
			Selenium.select("Favorites", PageOpportunities.ComparePlans.SELECT_FAVORITES, objComparePlan.getFavorites());
		
		if(objComparePlan.getPlansToCompare() != null && !objComparePlan.getPlansToCompare().isEmpty()) 
			comaprePlans(PageOpportunities.ComparePlans.Element_PLANS,"Plans", objComparePlan.getPlansToCompare().toUpperCase());
		
		if(objComparePlan.getPlan() != null && !objComparePlan.getPlan().isEmpty()) 
			selectPlan(objComparePlan.getPlan().toUpperCase());
		else{
			Log.error("No plan is available to select in Test Data sheet");
		 	Selenium.failTest("No plan is available to select.");
		}
		
		
			
		//Verify Next
					
		Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
		Selenium.sleep(2000);
		Selenium.waitForElement( Selenium.driver, PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME);
		if (Selenium.isElementPresent("Bassic information Page", PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME, true)){
			Reporter.log("Compare Plan functionality passed.");
			Selenium.passTest("Compare Plan functionality passed.");
			if(Selenium.isElementPresent(PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME))
				 Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
			else
			{
				Selenium.failTest("Previous button on basic subscriber information page is not available.");
				Reporter.log("Previous button on basic subscriber information page is not available.");
				Log.error("Previous button on basic subscriber information page is not available");
			}
		}	
		else{
			Selenium.failTest("Compare Plan functionality failed.");
			Reporter.log("Compare Plan functionality failed.");
			Log.error("Compare Plan functionality failed.");
		}
		
		//Navigate to previous page and return back
		 Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
		 Selenium.sleep(2000);
		if (objMyInfoData.getDependentCoverage().contains("No")){
			if (Selenium.isElementPresent("My Info Page", PageOpportunities.MyInformation.SELECT_COVERAGE, true)){
				Reporter.log("Previous Button functionality at Compare Plan Page passed");
				Log.info("Previous Button functionality at Compare Plan Page passed");
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				Selenium.sleep(4000);
			}
			else{
				Reporter.log("Previous Button functionality at Compare Plan Page failed.");
				Log.error("Previous Button functionality at Compare Plan Page failed.");
			}
		
		// Click on Compare Plans button without selecting any plan
		Selenium.clickButtons("Compare Plan", PageOpportunities.ComparePlans.BUTTON_COMPAREPLAN);
		Selenium.sleep(4000);
		if(Selenium.isElementPresent(PageOpportunities.ComparePlans.TEXT_ERROR_ONEPLANSELECTED))
		{
			Reporter.log("Error message is displayed successfully when only one plan is selected for comparison");
			Log.info("Error message is displayed successfully when only one plan is selected for comparison");
			Selenium.click("Close",PageOpportunities.ComparePlans.TEXT_ERROR_CLOSE);
		}
		else{
			Log.error("Error message is not displayed when only one plan is selected for comparison Failed");
		 	Selenium.failTest("Error message is not displayed when only one plan is selected for comparison Failed");
		}		
		// Select one Compare checkbox and click on Compare Plans button
		if(objComparePlan.getPlan() != null && !objComparePlan.getPlan().isEmpty()) 
			selectPlan(objComparePlan.getPlan().toUpperCase());
		else{
			Log.error("No plan is available to select in Test Data sheet");
		 	Selenium.failTest("No plan is available to select.");
	}
		
		Selenium.clickButtons("Compare Plan", PageOpportunities.ComparePlans.BUTTON_COMPAREPLAN);
		Selenium.sleep(2000);
		if(Selenium.isElementPresent(PageOpportunities.ComparePlans.TEXT_ERROR_ONEPLANSELECTED))
			{
			Reporter.log("Error message is displayed successfully when only one plan is selected for comparison");
			Log.info("Error message is displayed successfully when only one plan is selected for comparison");
			Selenium.click("Close",PageOpportunities.ComparePlans.TEXT_ERROR_CLOSE);
			}
		else{
			Log.error("Error message is not displayed when only one plan is selected for comparison Failed");
		 	Selenium.failTest("Error message is not displayed when only one plan is selected for comparison Failed");
	}
		//Select 4 or more plans and compare
		comaprePlans(PageOpportunities.ComparePlans.Element_PLANS,"Plans", objComparePlan.getPlansToCompare().toUpperCase());
		
		//un-select all plans and click next
		 Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
		if (objMyInfoData.getDependentCoverage().contains("No")){
			if (Selenium.isElementPresent("My Info Page", PageOpportunities.MyInformation.SELECT_COVERAGE, true)){
				Reporter.log("Previous Button functionality at Compare Plan Page passed");
				Log.info("Previous Button functionality at Compare Plan Page passed");
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				
			}
			else{
				Reporter.log("Previous Button functionality at Compare Plan Page failed.");
				Log.error("Previous Button functionality at Compare Plan Page failed.");
			}
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			Selenium.sleep(5000);
	   	
		if (Selenium.isAlertPresent()){
			Reporter.log("Sucessfully validated Error Message after clicking on next button without selecting any plans.");
			Log.info("Sucessfully validated Error Message after clicking on next button without selecting any plans.");
			Selenium.handleBrowserPopUp("OK");
			if(Selenium.isElementPresent(PageOpportunities.ComparePlans.TEXT_ERROR_NOPLANSELECTED))
			{
				Reporter.log("Error message is displayed successfully when no plan is selected and next button is clicked");
				Log.info("Error message is displayed successfully when no plan is selected and next button is clicked");
			}
			else
			{
				Reporter.log("Error message is not displayed when no plan is selected and next button is clicked failed.");
	        	Log.error("Error message is not displayed when no plan is selected and next button is clicked failed");
			}
			
			// Select more than 1 plan and click on next button
			selectPlan(objComparePlan.getPlan().toUpperCase());
			selectPlan("Best Buy HMO 3000");
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			
			Selenium.waitForElement( Selenium.driver, PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME);
			if (Selenium.isElementPresent("Basic information Page", PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME, true)){
				Reporter.log("User is successfully navigated to Subscriber information page after selecting more than one plan.");
				Selenium.passTest("User is successfully navigated to Subscriber information page after selecting more than one plan");
			}
				else
				{
					Selenium.failTest("User is not navigated to Subscriber information page after selecting more than one plan");
					Reporter.log("User is not navigated to Subscriber information page after selecting more than one plan");
					Log.error("User is not navigated to Subscriber information page after selecting more than one plan");
				}
		
		}} }}
	
	
	//----------------------------------------------------------------
	//Generic Function to Compare Plan  in Application
	//----------------------------------------------------------------
	public static void comaprePlans(String sLocator, String ObjectName, String strPlan){
		
		String[] ArrPlan = null;
		try{
			//Store all the Plans into Array of String type
			if (strPlan.contains(";"))
				ArrPlan=strPlan.split(";");
			else
				ArrPlan[0]=strPlan;
			
			//Initialise an array to store the index of all the plans that need to compare
			int[] ListPlanIndex=new int[ArrPlan.length];
			
			for (int iPlan=0; iPlan < ArrPlan.length; iPlan++){
				ListPlanIndex[iPlan]=getPlanIndex(sLocator,  ObjectName, ArrPlan[iPlan] );
			}
			
			if(ArrPlan.length < 5)
			{
			//Selecting the the required plans
			for(int jPlan=0; jPlan<ListPlanIndex.length; jPlan++){
				Selenium.selectCheckBox("Compare", PageOpportunities.ComparePlans.CHECKCOMPARE, ListPlanIndex[jPlan]);
			}
			
			//Click Compare
			Selenium.click("Compare Plans", PageOpportunities.ComparePlans.BUTTON_COMPAREPLAN);
			
			boolean bComparePlans=Selenium.isElementPresent("Close", PageOpportunities.ComparePlans.BUTTON_CLOSE);
			if (bComparePlans){
				Reporter.log("CheckBox for Compare Plans :"+strPlan +" functionality validated");
				Selenium.click("Close", PageOpportunities.ComparePlans.BUTTON_CLOSE);
				
			}
			else{
				Reporter.log("Clicking CheckBox for Compare Plans functionality Failed");
				Selenium.failTest("Compare Plans functionality Failed");
			}}
			
			if(ListPlanIndex.length>4)
			{
				for(int jPlan=0; jPlan<4; jPlan++){
					Selenium.selectCheckBox("Compare", PageOpportunities.ComparePlans.CHECKCOMPARE, ListPlanIndex[jPlan]);
				}
				try 
				{
					Selenium.selectCheckBox("Compare", PageOpportunities.ComparePlans.CHECKCOMPARE, ListPlanIndex[4]);
				}
				catch(Exception e)
				{
					Reporter.log("User is not able to select more than 4 plans to compare: passed.");	
					Selenium.passTest("User is not able to select more than 4 plans to compare: passed.");
				}
				Selenium.click("Compare Plans", PageOpportunities.ComparePlans.BUTTON_COMPAREPLAN);
				
				boolean bComparePlans=Selenium.isElementPresent("Close", PageOpportunities.ComparePlans.BUTTON_CLOSE);
				if (bComparePlans){
					Reporter.log("CheckBox for Compare Plans :"+strPlan +" functionality validated");
					Selenium.click("Close", PageOpportunities.ComparePlans.BUTTON_CLOSE);
					
				}
			}
		}
		catch(Exception e)
		{
			Log.error("Error while Comapring Plans :"+e.getMessage());
			Selenium.failTest("Compare Plans functionality Failed");
		}
	}

	public static int getPlanIndex(String sLocator, String ObjectName, String sPlanName ){
		int PlanIndex=0;
		List <WebElement> PlanElements=Selenium.getWebElements(sLocator,ObjectName);
		
		for (int iPlanElement=0; iPlanElement<PlanElements.size(); iPlanElement ++){
				System.out.println(PlanElements.get(iPlanElement).getText());
				
				if (PlanElements.get(iPlanElement).getText().toUpperCase().contains(sPlanName.toString().toUpperCase())){
					PlanIndex=iPlanElement;
					break;
				}		
			}
			return PlanIndex;
	}
	
	
	//----------------------------------------------------------------
	//Generic Function to Select Plan  in Application
	//----------------------------------------------------------------
	public static void selectPlan(String strSelectPlan){
		try{
			Selenium.sleep(2000);
			List <WebElement> PlanElements=Selenium.getWebElements(PageOpportunities.ComparePlans.Element_PLANS,"Plans");
			
			int PlanIndex=0;
			for (int jPlanElement=0; jPlanElement<PlanElements.size(); jPlanElement++){
				
				if (PlanElements.get(jPlanElement).getText().toUpperCase().contains(strSelectPlan.toUpperCase()))
				{
					PlanIndex=jPlanElement;
					break;
				}			
			}
			Selenium.selectCheckBox("Select Plan", PageOpportunities.ComparePlans.BUTTON_SELECTPLAN, PlanIndex);
			
			if (Selenium.isElementPresent("Selected", PageOpportunities.ComparePlans.BUTTON_SELECTED,true)){
				Reporter.log("Plan selection functioanlity completed");
				Selenium.passTest("Plan selection functioanlity completed");
			}
			else
				Log.error("Selected button after selecting a plan is not visible");				
		}
		
		catch(Exception e)
		{	
			Log.error("Error while Selecting Plans :"+e.getMessage());
			Selenium.failTest("Plan selection functioanlity failed.");
		}
	}
	
	
	//----------------------------------------------------------------
	//Function to Compare and Select Plan  
	//----------------------------------------------------------------
	public static  void  fillPlanForm (Map<String, String> TestDataDictionary)
	{
		try{	
		//Compare Plan Page
					
			objComparePlan.setProductType(TestDataDictionary.get("ProductType"));
			objComparePlan.setDental(TestDataDictionary.get("PediatricDental"));
			objComparePlan.setFavorites(TestDataDictionary.get("Favorites"));
			objComparePlan.setMetalLevel(TestDataDictionary.get("MetalLevel"));
			objComparePlan.setNetwork(TestDataDictionary.get("Network"));
			objComparePlan.setPlan(TestDataDictionary.get("Plan"));
			objComparePlan.setPlansToCompare(TestDataDictionary.get("ComparePlans"));
			
			Selenium.waitForElement( Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
			//Thread.sleep(5000);
			
			
			//Fill Compare Plan Page
			if(objComparePlan.getProductType() != null && !objComparePlan.getProductType().isEmpty()) 
				Selenium.select("Product Type", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, objComparePlan.getProductType());
			
			if(objComparePlan.getMetalLevel() != null && !objComparePlan.getMetalLevel().isEmpty()) 
				Selenium.select("Metal Level", PageOpportunities.ComparePlans.SELECT_METALLEVEL, objComparePlan.getMetalLevel());
			
			if(objComparePlan.getNetwork() != null && !objComparePlan.getNetwork().isEmpty()) 
				Selenium.select("Network", PageOpportunities.ComparePlans.SELECT_NETWORK, objComparePlan.getNetwork());
			
			if(objComparePlan.getDental() != null && !objComparePlan.getDental().isEmpty()) 
				Selenium.select("Pediatric Dental", PageOpportunities.ComparePlans.SELECT_DENTAL, objComparePlan.getDental());
			
			if(objComparePlan.getFavorites() != null && !objComparePlan.getFavorites().isEmpty()) 
				Selenium.select("Favorites", PageOpportunities.ComparePlans.SELECT_FAVORITES, objComparePlan.getFavorites());
			
			if(objComparePlan.getPlansToCompare() != null && !objComparePlan.getPlansToCompare().isEmpty()) 
				comaprePlans(PageOpportunities.ComparePlans.Element_PLANS,"Plans", objComparePlan.getPlansToCompare().toUpperCase());
			
			if(objComparePlan.getPlan() != null && !objComparePlan.getPlan().isEmpty()) 
				selectPlan(objComparePlan.getPlan().toUpperCase());
			else{
				Log.error("No plan is available to select in Test Data sheet");
			 	Selenium.failTest("No plan is available to select.");
			}
			
				
			//Verify Next			
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
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
	
	public static  void  Selecttwoplans (Map<String, String> TestDataDictionary)
	{
			
			objComparePlan.setPlan(TestDataDictionary.get("Plan"));
				if(objComparePlan.getPlan() != null && !objComparePlan.getPlan().isEmpty()) 
				selectPlan(objComparePlan.getPlan().toUpperCase());
			else{
				Log.error("No plan is available to select in Test Data sheet");
			 	Selenium.failTest("No plan is available to select.");
			}
			 selectPlan("Best Buy HSA PPO 3100");
			
				
			//Verify Next
						
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
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
		
	public static  void  EnrollWithtwoplans(Map<String, String> TestDataDictionary)
	{
		// verify Deliver My quote Page
		objReceiveQuote.setQuoteOption(TestDataDictionary.get("QuoteReceivingOption"));
	          //Receive your quote
			Selenium.waitForElement( Selenium.driver, PageOpportunities.DeliverMyQuote.BUTTON_VIEWPRINTQUOTE);
			//Thread.sleep(5000);
			String strOption=objReceiveQuote.getQuoteOption();
			
			
			if 	(strOption.equalsIgnoreCase("No"))
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,2);
			else if (strOption.equalsIgnoreCase("Mail"))
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,1);
			else if (strOption.equalsIgnoreCase("EMail"))
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,0);
			else
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,2);
		
		//Click on Enroll now
	               Selenium.click("Enroll Now", PageOpportunities.DeliverMyQuote.BUTTON_ENROLLNOW);
	               Selenium.sleep(3000);
	             //Verify alert message displayed and user is navigated to compare plan page
	             
	        if (Selenium.isAlertPresent()  ){     
	       
			Reporter.log("Sucessfully validated Error Message after selecting 2plans and trying to enroll a policy " + Selenium.driver.switchTo().alert().getText());
			Log.info("Sucessfully validated Error Message after selecting 2plans and trying to enroll a policy " + Selenium.driver.switchTo().alert().getText());
			Selenium.handleBrowserPopUp("OK");
			}
			else{
				Reporter.log("No error message is dispalyed after selecting 2 plans and trying to enroll a policy");
				Log.info("No error message is dispalyed after selecting 2 plans and trying to enroll a policy");
			}
	
	        Selenium.ValidateObjectAndText("Product Type", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, "", false);
	
	        if(Selenium.isElementPresent("xpath=//small[text()='Only 1 Product should be selected for the option Enroll Now']"))
			{ 
				Reporter.log("Error message on compare plan page is dispalyed successfully when 2 plans are selected ");
				Log.info("Error message on compare plan page is dispalyed successfully when 2 plans are selectedy " );
				Selenium.passTest("Error message on compare plan page is dispalyed successfully when 2 plans are selected");
			}
			else
			{
				Reporter.log("Error message on compare plan page is not dispalyed  when 2 plans are selected ");
				Log.info("Error message on compare plan page is not dispalyed  when 2 plans are selectedy " );
				Selenium.failTest("Error message on compare plan page is not dispalyed when 2 plans are selected");
			}
	           objComparePlan.setPlan(TestDataDictionary.get("Plan"));
	           unselectPlan(objComparePlan.getPlan().toUpperCase());
	         //Verify Next
				
	           Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
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
		
	public static void unselectPlan(String strSelectPlan){
		
		
		List <WebElement> PlanElements=Selenium.getWebElements(PageOpportunities.ComparePlans.Element_PLANS,"Plans");
		System.out.println(strSelectPlan);
		// objComparePlan.setPlan(TestDataDictionary.get("Plan"));
		int PlanIndex=0;
		for (int jPlanElement=0; jPlanElement<PlanElements.size(); jPlanElement++){
			System.out.println(PlanElements.get(jPlanElement).getText());
				
			if (PlanElements.get(jPlanElement).getText().toUpperCase().contains(strSelectPlan.toUpperCase()))
			{
				PlanIndex=jPlanElement;
				break;
			}			
		}
		String xpath1 = "xpath=//*[@id='output2']/ng-include/div/div[3]/div/div["+(PlanIndex+1)+"]/div/section/div[1]/button";
		System.out.println(xpath1);
		Selenium.selectCheckBox("Unselect Plan", xpath1, PlanIndex);
		
	}
	

	//------------------------------------------------------------------------------
	//Function to Fill 'My Family Form Page'  with Navigation functionality Check 
	//------------------------------------------------------------------------------
	public static  void  fillMyFamilyForm (Map<String, String> TestDataDictionary)
	{
		try{
			if (TestDataDictionary.get("DependentCoverage").contains("Yes")){
		//My Family Page
				
			String StrDOB=TestDataDictionary.get("DepDOB");
			List <String> ArrDOB=  Arrays.asList(StrDOB.split(";"));
			objMyFamilyData.setDepDateOfBirth(DateUtil.myCustomDate(ArrDOB.get(0), ArrDOB.get(1)));
			objMyFamilyData.setDependentRelation(TestDataDictionary.get("DepRelation"));
			objMyFamilyData.setTobaccoUse("No");
			
			
			//Selenium.waitForElement( Selenium.driver, PageOpportunities.MyFamily.SELECT_RELATION);
			Selenium.sleep(5000);
			
			//verify DOB 
			Selenium.ValidateObjectAndText("Dependent Date of Birth", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, "", false);
			
			//Verify Dependent Relation
			WebElement wElement = Selenium.getWebElement(PageOpportunities.MyFamily.SELECT_RELATION,"Dependent Relation",true);
			Select selectObject = new Select(wElement);
			List<WebElement> allOptions=selectObject.getOptions();
			List<String> ExpectedOptions = Arrays.asList("Clear", "Spouse", "Child Under 26", "Step Child Under 26","Dependent Child", "Ex Spouse", "Domestic Partner"); 
			int Error=0;
			String ErrorOption="";
			for (int ioption=0 ; ioption<ExpectedOptions.size();ioption++){
				System.out.println(allOptions.get(ioption).getText());
				if (!allOptions.get(ioption).getText().contains(ExpectedOptions.get(ioption))){
					Error=Error+1;	
					ErrorOption= ErrorOption + ExpectedOptions.get(ioption) + " ,";
				}
			}
			
			if (Error>0){
				Reporter.log(ErrorOption +": is not present in Dependent Relation Dropdown.");
				Log.error(ErrorOption +": is not present in Dependent Relation Dropdown.");
			}
			else{
				Reporter.log(" Dependent Relation Dropdown functionality passed.");
				Log.info("Dependent Relation Dropdown functionality passed.");
			}
				
			
			//Verify Age
			Selenium.ValidateObjectAndText("Dependent age", PageOpportunities.MyFamily.ELE_AGE, "", false);
			
			//Verify Add new Dependent 
			Selenium.ValidateObjectAndText("Add New Dependent", PageOpportunities.MyFamily.LINK_ADD_NEWDEP, "", false);
			
			
			//Fill My Family Page
			if(objMyFamilyData.getDepDateOfBirth() != null && !objMyFamilyData.getDepDateOfBirth().isEmpty()) 
				Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, objMyFamilyData.getDepDateOfBirth());
			
			if(objMyFamilyData.getDependentRelation() != null && !objMyFamilyData.getDependentRelation().isEmpty()) 
				Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION, objMyFamilyData.getDependentRelation());
			
			if (!TestDataDictionary.get("Zip").contains("MA"))
				Selenium.select("Tobacco use", PageOpportunities.MyFamily.SELECT_TOBACCO, objMyFamilyData.getTobaccoUse());
				
			
			
			//verify Cancel
			Selenium.clickButtons("Cancel", PageSFDC.BUTTON_CANCEL);
			Selenium.sleep(5000);
			
			if (Selenium.isAlertPresent()){
				Reporter.log("Sucessfully validated Error Message after clicking Cancel at My Family Page: " + Selenium.driver.switchTo().alert().getText());
				Log.info("Sucessfully validated Error Message after clicking Cancel at My Family Page: " + Selenium.driver.switchTo().alert().getText());
				Selenium.handleBrowserPopUp("Cancel");
			}
			else{
				Reporter.log("Cancel Button functionality at My Family Page failed");
				Log.info("Cancel Button functionality at My Family Page failed");
			}
			
			
			
			//Verify Previous
			Selenium.ValidateObjectAndText("Previous Button - My Family aPage", PageOpportunities.MyFamily.BUTTON_PREVIOUS, "", false);
			
			
			//Verify Next
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
			if (Selenium.isElementPresent("Product Dropdown", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, true)){
				Reporter.log("Next Button functionality at My Family Page passed");
				Reporter.log("My Family Page is filled successsfully.");
				Log.info("Next Button functionality at My Family Page passed");	
				Selenium.passTest("My Family Page functionality Passed.",true);
			}
			else{
				Selenium.failTest("My Information Page is not filled successsfully.");
				Reporter.log("My Family Page is NOT filled.");
				Reporter.log("Next Button functionality at My Family Page failed");
				Log.error("Next Button functionality at My Family Page failed");
				Selenium.failTest("My Family Page functionality Failed.",true);
			}
			}
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for My Information Page from Dictionary Object. Exception:"+e.getMessage());
			 Selenium.failTest("Error in getting data for My Information Page from Excel.");
			 
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Fill 'My Family Form Page' 
	//------------------------------------------------------------------------------
	public static  void  myFamilyForm (Map<String, String> TestDataDictionary)
	{
		try{
			if (TestDataDictionary.get("DependentCoverage").contains("Yes")){
		//My Family Page
				
			String StrDOB=TestDataDictionary.get("DepDOB");
			List <String> ArrDOB=  Arrays.asList(StrDOB.split(";"));
			objMyFamilyData.setDepDateOfBirth(DateUtil.myCustomDate(ArrDOB.get(0), ArrDOB.get(1)));
			objMyFamilyData.setDependentRelation(TestDataDictionary.get("DepRelation"));
			objMyFamilyData.setTobaccoUse("No");
			
			
			//Selenium.waitForElement( Selenium.driver, PageOpportunities.MyFamily.SELECT_RELATION);
			Selenium.sleep(5000);
									
			//Fill My Family Page
			if(objMyFamilyData.getDepDateOfBirth() != null && !objMyFamilyData.getDepDateOfBirth().isEmpty()) 
				Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, objMyFamilyData.getDepDateOfBirth());
			
			if(objMyFamilyData.getDependentRelation() != null && !objMyFamilyData.getDependentRelation().isEmpty()) 
				Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION, objMyFamilyData.getDependentRelation());
			
			if (!TestDataDictionary.get("Zip").contains("MA"))
				Selenium.select("Tobacco use", PageOpportunities.MyFamily.SELECT_TOBACCO, objMyFamilyData.getTobaccoUse());
				
			Reporter.log("My Family Form is successfully filled");
			Selenium.passTest("My Family Form is successfully filled");
			}
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for My Family form Info Page from Dictionary Object. Exception:"+e.getMessage());
			 Selenium.failTest("Error in getting data for My Family form Info Page from Excel.",true);
			 
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Fill 'My Family Form Page'  ==> Invalid Scenarios
	//------------------------------------------------------------------------------
	public static  void  fillMyFamilyFormInvalidScenarios (Map<String, String> TestDataDictionary)
	{
		try{
			if (TestDataDictionary.get("DependentCoverage").contains("Yes")){
		//My Family Page
				
			String StrDOB=TestDataDictionary.get("DepDOB");
			List <String> ArrDOB=  Arrays.asList(StrDOB.split(";"));
			objMyFamilyData.setDepDateOfBirth(DateUtil.myCustomDate(ArrDOB.get(0), ArrDOB.get(1)));
			objMyFamilyData.setDependentRelation(TestDataDictionary.get("DepRelation"));
			objMyFamilyData.setTobaccoUse("No");
			
			
			//Selenium.waitForElement( Selenium.driver, PageOpportunities.MyFamily.SELECT_RELATION);
			Thread.sleep(5000);
			
			//verify DOB 
			Selenium.ValidateObjectAndText("Dependent Date of Birth", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, "", false);
			Thread.sleep(5000);
			//Verify Dependent Relation
			WebElement wElement = Selenium.getWebElement(PageOpportunities.MyFamily.SELECT_RELATION,"Dependent Relation",true);
			Select selectObject = new Select(wElement);
			List<WebElement> allOptions=selectObject.getOptions();
			List<String> ExpectedOptions = Arrays.asList("Clear", "Spouse", "Child Under 26", "Step Child Under 26","Dependent Child", "Ex Spouse", "Domestic Partner"); 
			int Error=0;
			String ErrorOption="";
			for (int ioption=0 ; ioption<ExpectedOptions.size();ioption++){
				System.out.println(allOptions.get(ioption).getText());
				if (!allOptions.get(ioption).getText().contains(ExpectedOptions.get(ioption))){
					Error=Error+1;	
					ErrorOption= ErrorOption + ExpectedOptions.get(ioption) + " ,";
				}
			}
			
			if (Error>0){
				Reporter.log(ErrorOption +": is not present in Dependent Relation Dropdown.");
				Log.error(ErrorOption +": is not present in Dependent Relation Dropdown.");
			}
			else{
				Reporter.log(" Dependent Relation Dropdown functionality passed.");
				Log.info("Dependent Relation Dropdown functionality passed.");
			}
				
			
			//Verify Age
			Selenium.ValidateObjectAndText("Dependent age", PageOpportunities.MyFamily.ELE_AGE, "", false);
			
			
			//verify Cancel
			Selenium.click("Cancel", PageOpportunities.MyFamily.BUTTON_CANCEL);
			Selenium.sleep(5000);
			if (Selenium.driver.switchTo().alert().getText().contains("exit")){
				Reporter.log("Successfully validated Error Message after clicking Cancel at My Family Page: " + Selenium.driver.switchTo().alert().getText());
				Log.info("Successfully validated Error Message after clicking Cancel at My Family Page: " + Selenium.driver.switchTo().alert().getText());
				Selenium.handleBrowserPopUp("Cancel");
				Selenium.sleep(5000);
			}
			else{
				Reporter.log("Cancel Button functionality at My Family Page failed");
				Log.info("Cancel Button functionality at My Family Page failed");
			}
			
	
			}
	
	     // Verify error message when child dependent age is greater than 26
			int	year = DateUtil.getCurrentYear()- 132;
			String MyDateOfBirth = ("12/31/" + Integer.toString(year));
			Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, MyDateOfBirth);
			                   
	        Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION,"Child Under 26");
	        Selenium.sleep(5000);
	        Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
	        Selenium.sleep(2000);
	        if (Selenium.isAlertPresent()){
	        	Reporter.log("Successfully validated Error Message after trying to add a dependent of age greater than 26 " + Selenium.driver.switchTo().alert().getText());
	            Log.info("Successfully validated Error Message after trying to add a dependent of age greater than 26" + Selenium.driver.switchTo().alert().getText());
	            Selenium.handleBrowserPopUp("OK");
	            Selenium.sleep(5000);
	        }
	        else{
	            Reporter.log("Error message not dispalyed after trying to add a dependent of age greater than 26");
	            Log.info("Error message not dispalyed after trying to add a dependent of age greater than 26");
	        }
	                        
	        if(Selenium.isElementPresent(PageOpportunities.MyFamily.TEXT_DEPENDENT_AGE_ERROR )){
	        	Reporter.log("Successfully validated Error Message after trying to add a child dependent of age grater than 26 " );   
	            Log.info("Successfully validated Error Message after trying to add a child dependent of age grater than 26 " );
	        }
	                   		
	        else{
	        	Reporter.log("Successfully validated Error Message after trying to add a child dependent of age grater than 26: Failed");
	        	Log.info("Successfully validated Error Message after trying to add a child dependent of age grater than 26: Failed");
	        }
	                   			
	        // Verify error message when child dependent age is 26 
	        year = DateUtil.getCurrentYear()- 27;
	        MyDateOfBirth = ("12/12/" + Integer.toString(year));
	        Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, MyDateOfBirth);
	        Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION,"Child Under 26");
	        Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
	        Selenium.sleep(5000);
	        
	        if (Selenium.isAlertPresent()){
	        	Reporter.log("Successfully validated Error Message after trying to add a dependent of age is 26  " + Selenium.driver.switchTo().alert().getText());
	        	Log.info("Successfully validated Error Message after trying to add a dependent of age is 26  " + Selenium.driver.switchTo().alert().getText());
	        	Selenium.handleBrowserPopUp("Ok");
	        	Selenium.sleep(5000);
	        }
	        else{
	        	Reporter.log("Error message not dispalyed after trying to add a dependnet of age is 26");
	        	Log.info("Error message not dispalyed after trying to add a dependnet of age is 26");
	        }
	                            
	        if(Selenium.isElementPresent(PageOpportunities.MyFamily.TEXT_DEPENDENT_AGE_ERROR ))
	        {
	        	Reporter.log("Successfully validated Error Message after trying to add a child dependent of age is 26" );                   				
	        	Log.info("Successfully validated Error Message after trying to add a child dependent of age is 26 " );
	        }
	                      		
	        else{
	        	Reporter.log("Successfully validated Error Message after trying to add a child dependent of age is 26: Failed");
	        	Log.info("Successfully validated Error Message after trying to add a child dependent of age is 26 Failed");
	        }
	        
	                   			
	      // Verify child dependent is added successfully when age is less than 26	
	        year = DateUtil.getCurrentYear()- 25;
	        MyDateOfBirth = "" + ("12/12/" + Integer.toString(year));
	        Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, MyDateOfBirth);
	        Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION,"Child Under 26");
	        Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
	        Selenium.sleep(5000);
	        if (Selenium.isElementPresent("Product Dropdown", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, true)){
	        	Reporter.log("Next Button functionality at My Family Page passed when child dependent age is less than 26.");
	        	Log.info("Next Button functionality at My Family Page passed when child dependent age is less than 26");
	        	Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
	        	Selenium.sleep(5000);
	        }
	        else{
	        	Selenium.failTest("Next Button functionality at My Family Page failed when child dependent age is less than 26.");
	        	Reporter.log("Next Button functionality at My Family Page failed when child dependent age is less than 26.");
	        	Log.error("Next Button functionality at My Family Page failed when child dependent age is less than 26.");
	        }
	                              
	                                                
	            // Verify a spouse of age less than 14yrs cant be added 
	        year = DateUtil.getCurrentYear()- 13;
	        MyDateOfBirth = ("12/31/" + Integer.toString(year));
	        Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, MyDateOfBirth);
	        Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION,"Spouse");
	        Selenium.sleep(5000);
	        Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
	        Selenium.sleep(3000);
	        if (Selenium.isAlertPresent()){
	        	Reporter.log("Successfully validated Error Message after trying to add a Spouse of age less than 14yrs " + Selenium.driver.switchTo().alert().getText());
	        	Log.info("Successfully validated Error Message after trying to add a Spouse of age with invalid date " + Selenium.driver.switchTo().alert().getText());
	        	Selenium.handleBrowserPopUp("OK");
	        	Selenium.sleep(2000);
	        }
	        else{
	        	Reporter.log("Error message not dispalyed after trying to add a Spouse age less than 14yrs");
	        	Log.info("Error message not dispalyed after trying to add a Spouse age less than 14yrs");
	        }
	        
	        if(Selenium.isElementPresent(PageOpportunities.MyFamily.TEXT_DEPENDENT_AGE_ERROR ))
	        {
	        	Reporter.log("Successfully validated Error Message after trying to add a spouse of age less than 14 " );                   				
	        	Log.info("Successfully validated Error Message after trying to add a spouse dependent of age less than 14 " );
	        }
	        
	        else{
	        	Reporter.log("Failed validated Error Message after trying to add a spouse dependent of age less than 14: Failed");
	        	Log.info("Failed validated Error Message after trying to add a spouse dependent of age less than 14: Failed");
	        }
	        
	        
	        // Verify user is able to add a spouse of age greater than 14years
	        year = DateUtil.getCurrentYear()- 15;
	        MyDateOfBirth = "" + ("12/31/" + Integer.toString(year));
	        Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, MyDateOfBirth);
	        Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION,"Spouse");
	        Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
	        Selenium.sleep(5000);
	        if (Selenium.isElementPresent("Product Dropdown", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, true)){
	        	Reporter.log("Next Button functionality at My Family Page passed when spouse dependent age is more than 14yrs");
	        	Log.info("Next Button functionality at My Family Page passed when spouse dependent age is more than 14yrs");
	        	Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
	        	Selenium.sleep(5000);
	                             		}
	        else{
	        	Selenium.failTest("Next Button functionality at My Family Page failed when spouse dependent age is more than 14yrs");
	        	Reporter.log("Next Button functionality at My Family Page failed when spouse dependent age  is more than 14yrs");
	        	Log.error("Next Button functionality at My Family Page failed when spouse dependent age is more than 14yrs");
	        }	
				
			//  	Verify user is not able to add a spouse of age more than 130 yrs
	        year = DateUtil.getCurrentYear()- 132;
	        MyDateOfBirth = ("12/31/" + Integer.toString(year));
	        Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, MyDateOfBirth);
	        Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION,"Spouse");
	        Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
	        Selenium.sleep(5000);
	        if (Selenium.isAlertPresent()){
	        	Reporter.log("Successfully validated Error Message after trying to add a spouse of age more than 130 yrs " + Selenium.driver.switchTo().alert().getText());
	        	Log.info("Successfully validated Error Message after trying to add a spouse of age more than 130 yrs " + Selenium.driver.switchTo().alert().getText());
	        	Selenium.handleBrowserPopUp("OK");
	        	Thread.sleep(3000);
	        }
	        else{
	        	Reporter.log("Error message not dispalyed after trying to add a spouse of age more than 130 yrs");
	        	Log.info("Error message not dispalyed after trying to add a spouse of age more than 130 yrs");
	        }
	        
	        if(Selenium.isElementPresent(PageOpportunities.MyFamily.TEXT_DEPENDENT_AGE_ERROR ))
	        {
	        	Reporter.log("Successfully validated Error Message after trying to add a spouse dependent of age more than 130 " );                   				
	        	Log.info("Successfully validated Error Message after trying to add a spouse dependent of age  more than 130  " );
	        }
									
	        else{
	        	Reporter.log("Failed validated Error Message after trying to add a spouse dependent of age  more than 130 : Failed");
	        	Log.info("Failed validated Error Message after trying to add a spouse dependent of age  more than 130 : Failed");
	        }
				
		   // Verify user is able to add a spouse dependent of age less than 130
	        year = DateUtil.getCurrentYear()- 129;
			MyDateOfBirth = "" + ("12/31/" + Integer.toString(year));
			Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, MyDateOfBirth);
			Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION,"Spouse");
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			Thread.sleep(20000);
			if (Selenium.isElementPresent(PageOpportunities.ComparePlans.SELECT_PRODUCTYPE)){
				Reporter.log("Next Button functionality at My Family Page passed when spouse dependent age is less than 130yrs");
				Log.info("Next Button functionality at My Family Page passed when spouse dependent age is less than 130yrs");
				Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
				Thread.sleep(3000);
			}
			else{
				Selenium.failTest("Next Button functionality at My Family Page failed when spouse dependent age is less than 130yrs");
				Reporter.log("Next Button functionality at My Family Page failed when spouse dependent age is less than 130yrs");
				Log.error("Next Button functionality at My Family Page failed when spouse dependent age is less than 130yrs");
			}	
				
	        // Verify an error message is displayed when dob is future date
			year = DateUtil.getCurrentYear()+1;
			MyDateOfBirth = ("12/31/" + Integer.toString(year));
			Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, MyDateOfBirth);
			
			Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION,"Spouse");
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			Thread.sleep(3000);
			if (Selenium.isAlertPresent()){
				Reporter.log("Successfully validated Error Message after trying to add a dependent dob is future date " + Selenium.driver.switchTo().alert().getText());
				Log.info("Successfully validated Error Message after trying to add a dependent dob is future date " + Selenium.driver.switchTo().alert().getText());
				Selenium.handleBrowserPopUp("OK");
				Thread.sleep(3000);
			}
			else{
				Reporter.log("Error message not dispalyed after trying to add a dependnet dob is future date");
				Log.info("Error message not dispalyed after trying to add a dependnet dob is future date");
			}
							      
			//  Verify second and third section of add dependent opens up after clicking on Add dependent button
										//Verify Add new Dependent link
									
			if(Selenium.isElementPresent(PageOpportunities.MyFamily.LINK_ADD_NEWDEP))
			{
				Reporter.log("Link to add a new dependent is available " );                   				
				Log.info("Link to add a new dependent is available " );  
				Selenium.click("Add dependent link",PageOpportunities.MyFamily.LINK_ADD_NEWDEP);
				Selenium.click("Add dependent link",PageOpportunities.MyFamily.LINK_ADD_NEWDEP);
			}
			else
			{
				Reporter.log("Link to add a new dependent is not available " );                   				
				Log.info("Link to add a new dependent is not available " ); 
				Selenium.failTest("Link to add a new dependent is not available");
			}
			List<WebElement> MultipleDOB = Selenium.driver.findElements(By.id(PageOpportunities.MyFamily.TEXT_DATEOFBIRTHID));
			List<WebElement> MultipleRelation = Selenium.driver.findElements(By.id(PageOpportunities.MyFamily.SELECT_RELATIONID));
			
			// Inserting data for spouse and 2 child dependents 
			year = DateUtil.getCurrentYear()-30;
			MyDateOfBirth = ("12/31/" + Integer.toString(year));
			Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, MyDateOfBirth);
			year = DateUtil.getCurrentYear()-10;
			MyDateOfBirth = ("12/31/" + Integer.toString(year));
			MultipleDOB.get(1).sendKeys(MyDateOfBirth);
			year = DateUtil.getCurrentYear()-5;
			MyDateOfBirth = ("12/31/" + Integer.toString(year));
			MultipleDOB.get(2).sendKeys(MyDateOfBirth);
			Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION,"Spouse");
			MultipleRelation.get(1).sendKeys("Child Under 26");
			MultipleRelation.get(2).sendKeys("Child Under 26");
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			Selenium.sleep(5000);
			if (Selenium.isElementPresent("Product Dropdown", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, true)){
				Reporter.log("Next Button functionality at My Family Page passed when spouse dependent age is less than 130yrs");
				Log.info("Next Button functionality at My Family Page passed when spouse dependent age is less than 130yrs");
				Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
			}
			else{
				Selenium.failTest("Next Button functionality at My Family Page failed when spouse dependent age is less than 130yrs");
				Reporter.log("Next Button functionality at My Family Page failed when spouse dependent age  is less than 130yrs");
				Log.error("Next Button functionality at My Family Page failed when spouse dependent age is less than 130yrs");
			}	
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for My Information Page from Dictionary Object. Exception:"+e.getMessage());
			 Selenium.failTest("Error in getting data for My Information Page from Excel.");
			 
		}
		
	}
	
	//------------------------------------------------------------------------------
	//Function to Fill 'Application Subscriber Infor Page' 
	//------------------------------------------------------------------------------
	public static  void  fillApplicationSubInfo (Map<String, String> TestDataDictionary) //
	{
		try{	
		//Compare Plan Page
				 		 			
		
			objApplicationInfo.setGender(TestDataDictionary.get("Gender"));
			objApplicationInfo.setMailingAddress(TestDataDictionary.get("MailingAddress"));
			objApplicationInfo.setPCPCity(TestDataDictionary.get("PCPCity"));
			objApplicationInfo.setPCPZIP(TestDataDictionary.get("PCPZip"));
			objApplicationInfo.setStreetAddress(TestDataDictionary.get("Street"));
			objApplicationInfo.setHomePhone(TestDataDictionary.get("HomePhone"));
			//Add Home Phone Number
			
			if (Selenium.isElementPresent("Gender", PageOpportunities.ApplicationSubInfo.SELECT_GENDER, true)){
				
			
				//Fill Application Information Page
				if(objApplicationInfo.getGenders() != null && !objApplicationInfo.getGenders().isEmpty()) 
					Selenium.select("Gender", PageOpportunities.ApplicationSubInfo.SELECT_GENDER, objApplicationInfo.getGenders());
				
				if(objApplicationInfo.getStreetAddress() != null && !objApplicationInfo.getStreetAddress().isEmpty())
					Selenium.type("Street Address", PageOpportunities.ApplicationSubInfo.TEXT_STREETADDRESS, objApplicationInfo.getStreetAddress());
				
				if(objApplicationInfo.getHomePhone() != null && !objApplicationInfo.getHomePhone().isEmpty())
					Selenium.type("Home Phone", PageOpportunities.ApplicationSubInfo.TEXT_HOMEPHONE, objApplicationInfo.getHomePhone(),false);
				
				if(objApplicationInfo.getMailingAddress() != null && !objApplicationInfo.getMailingAddress().isEmpty()){
					Selenium.selectCheckBox("Different Mailing Address",PageOpportunities.ApplicationSubInfo.RADION_MAILINGPERMAMNENTADDRESS,1 );
					Selenium.type("Mailing Address", PageOpportunities.ApplicationSubInfo.TEXT_MAILINGADDRESS, objApplicationInfo.getMailingAddress());
				}
				else
					Selenium.selectCheckBox("Different Mailing Address",PageOpportunities.ApplicationSubInfo.RADION_MAILINGPERMAMNENTADDRESS,0 );
					
				Reporter.log("Application Information Page is filled successfully.");
				Selenium.passTest("Application Information Page functionality passed.");
			}
			else{
				 Selenium.failTest("Application Information Page functionality failed.");
				 Log.error("Gender dropdown is not present on Application Info page");
				 Reporter.log("Application Information Page functionality failed.");

			}
			
			//Verify Applicant information page 
			if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_PREFIX))
			{
				Reporter.log("Prefix Salutation field is displayed successfully.");
				Selenium.passTest("Prefix Salutation field is displayed passed.");
			}
			else
			{
				Selenium.failTest("Prefix Salutation field is not displayed on Application Information page: failed.");
				 Log.error("Prefix Salutation field is not displayed: failed.");
				 Reporter.log("Prefix Salutation field is not displayed: failed..");
			}
			
			if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_FIRSTNAME))
			{
				Reporter.log("First name field is displayed successfully.");
				Selenium.passTest("First name field is displayed passed.");
			}
			else
			{
				Selenium.failTest("First name  field is not displayed on Application Information page: failed.");
				 Log.error("First name  field is not displayed: failed.");
				 Reporter.log("First name field is not displayed: failed..");
			}
			
			if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_MIDDLENAME))
			{
				Reporter.log("Middle name field is displayed successfully.");
				Selenium.passTest("Middle name field is displayed passed.");
			}
			else
			{
				Selenium.failTest("Middle name  field is not displayed on Application Information page: failed.");
				 Log.error("Middle name  field is not displayed: failed.");
				 Reporter.log("Middle name field is not displayed: failed..");
			}
			
			if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_LASTNAME))
			{
				Reporter.log("Last name field is displayed successfully.");
				Selenium.passTest("Last name field is displayed passed.");
			}
			else
			{
				Selenium.failTest("Last name  field is not displayed on Application Information page: failed.");
				 Log.error("Last name  field is not displayed: failed.");
				 Reporter.log("Last name field is not displayed: failed..");
			}
			
			if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_SUFFIX))
			{
				Reporter.log("Suffix field is displayed successfully.");
				Selenium.passTest("Suffix field is displayed passed.");
			}
			else
			{
				Selenium.failTest("Suffix  field is not displayed on Application Information page: failed.");
				 Log.error("Suffix field is not displayed: failed.");
				 Reporter.log("Suffix field is not displayed: failed..");
			}
			
			if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_DOB))
			{
				Reporter.log("DOB field is displayed successfully.");
				Selenium.passTest("DOB field is displayed passed.");
			}
			else
			{
				Selenium.failTest("DOB field is not displayed on Application Information page: failed.");
				 Log.error("DOB field is not displayed: failed.");
				 Reporter.log("DOB field is not displayed: failed..");
			}
			
			
			if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_STREETADDRESS))
			{
				Reporter.log("Street Address field is displayed successfully.");
				Selenium.passTest("Street Address field is displayed passed.");
			}
			else
			{
				Selenium.failTest("Street Address field is not displayed on Application Information page: failed.");
				 Log.error("Street Address field is not displayed: failed.");
				 Reporter.log("Street Address field is not displayed: failed..");
			}

if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_CITY))
			{
				Reporter.log("City field is displayed successfully.");
				Selenium.passTest("City field is displayed passed.");
			}
			else
			{
				Selenium.failTest("City field is not displayed on Application Information page: failed.");
				 Log.error("City field is not displayed: failed.");
				 Reporter.log("City field is not displayed: failed..");
			}
if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_STATE))
			{
				Reporter.log("STATE field is displayed successfully.");
				Selenium.passTest("STATE field is displayed passed.");
			}
			else
			{
				Selenium.failTest("STATE field is not displayed on Application Information page: failed.");
				 Log.error("STATE field is not displayed: failed.");
				 Reporter.log("STATE field is not displayed: failed..");
			}
if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_COUNTRY))
			{
				Reporter.log("COUNTRY field is displayed successfully.");
				Selenium.passTest("COUNTRY field is displayed passed.");
			}
			else
			{
				Selenium.failTest("COUNTRY field is not displayed on Application Information page: failed.");
				 Log.error("COUNTRY field is not displayed: failed.");
				 Reporter.log("COUNTRY field is not displayed: failed..");
			}
if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_HOMEPHONE))
{
	Reporter.log("Home phone number field is displayed successfully.");
	Selenium.passTest("Home phone number field is displayed passed.");
}
else
{
	Selenium.failTest("Home phone number field is not displayed on Application Information page: failed.");
	 Log.error("Home phone number field is not displayed: failed.");
	 Reporter.log("Home phone number field is not displayed: failed..");
}

if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_WORKPHONE))
{
	Reporter.log("Work Phone number field is displayed successfully.");
	Selenium.passTest("Work Phone number field is displayed passed.");
}
else
{
	Selenium.failTest("Work Phone number field is not displayed on Application Information page: failed.");
	 Log.error("Work Phone number field is not displayed: failed.");
	 Reporter.log("Work Phone number field is not displayed: failed..");
}

if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_MOBILEPHONE))
{
	Reporter.log("Mobile number field is displayed successfully.");
	Selenium.passTest("Mobile number field is displayed passed.");
}
else
{
	Selenium.failTest("Mobile number field is not displayed on Application Information page: failed.");
	 Log.error("Mobile number field is not displayed: failed.");
	 Reporter.log("Mobile number field is not displayed: failed..");
}

if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.TEXT_EMAIL))
{
	Reporter.log("Email field is displayed successfully.");
	Selenium.passTest("Email field is displayed passed.");
}
else
{
	Selenium.failTest("Email field is not displayed on Application Information page: failed.");
	 Log.error("Email field is not displayed: failed.");
	 Reporter.log("Email field is not displayed: failed..");
}

if(Selenium.isElementPresent(PageOpportunities.ApplicationSubInfo.BUTTON_BROKERLOOKUP))
{
	Reporter.log("Broker lookup button is displayed successfully.");
	Selenium.passTest("Broker lookup button is displayed passed.");
}
else
{
	Selenium.failTest("Broker lookup button is not displayed on Application Information page: failed.");
	 Log.error("Broker lookup button is not displayed: failed.");
	 Reporter.log("Broker lookup button is not displayed: failed..");
}
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for Application Information Page from excel. Exception:"+e.getMessage());
			 Selenium.failTest("Application Information Page functionality failed.");
			 
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Fill 'Receive your Quote Page' 
	//------------------------------------------------------------------------------
	public static  void  receiveYourQuote (String strOption)
	{
		try{	
			
		// verify Deliver My quote Page
		
			Selenium.ValidateObjectAndText("Save and Exit", PageOpportunities.DeliverMyQuote.BUTTON_SAVEANDEXIT, "", false);
			Selenium.ValidateObjectAndText("View Quote", PageOpportunities.DeliverMyQuote.BUTTON_VIEWPRINTQUOTE, "", false);
			
			if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuote.RADIO_EMAIL)) {
				Reporter.log("Email radio button on quote page is displayed: passed.");	
				Selenium.passTest("Email radio button on quote page is displayed: passed.");}
			else{
				Reporter.log("Email radio button on quote page is not displayed");	
				Selenium.failTest("Email radio button on quote page is not displayed");}
			if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuote.RADIO_MAIL)) {
				Reporter.log("Mail radio button on quote page is displayed: passed.");	
				Selenium.passTest("Mail radio button on quote page is displayed: passed.");}
			else{
				Reporter.log("Mail radio button on quote page is not displayed");	
				Selenium.failTest("Mail radio button on quote page is not displayed");}
			if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuote.RADIO_NONE)) {
				Reporter.log("None radio button on quote page is displayed: passed.");	
				Selenium.passTest("None radio button on quote page is displayed: passed.");}
			else{
				Reporter.log("None radio button on quote page is not displayed");	
				Selenium.failTest("None radio button on quote page is not displayed");}		
			//click Radio Button as Mail
			Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,1);
			Selenium.ValidateObjectAndText("SOB", PageOpportunities.DeliverMyQuote.RADION_INCLUDESOB, "", false);
			Selenium.ValidateObjectAndText("SBC", PageOpportunities.DeliverMyQuote.RADION_INCLUDESBC, "", false);
			
			//Verify Previous Button
			 Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
			
			if (Selenium.isElementPresent("My Info Page", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1, true)){
				Reporter.log("Previous Button functionality at Deliver My Quote Page passed");
				Log.info("Previous Button functionality at Deliver My Quote Page passed");
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				Selenium.sleep(5000);
			}
			else{
				Reporter.log("Previous Button functionality at Deliver My Quote Page failed.");
				Log.error("Previous Button functionality at Deliver My Quote Page failed.");
			}
			
			//Receive your quote
			Selenium.waitForElement( Selenium.driver, PageOpportunities.DeliverMyQuote.BUTTON_VIEWPRINTQUOTE);
			//Thread.sleep(5000);
			
			if 	(strOption.equalsIgnoreCase("No"))
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,2);
			else if (strOption.equalsIgnoreCase("Mail")){
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,1);
				Selenium.click("Select Include SBC options as, Yes", PageOpportunities.DeliverMyQuote.RADION_INCLUDESBC_YES);
				Selenium.click("Select Include SOB options as, Yes", PageOpportunities.DeliverMyQuote.RADION_INCLUDESOB_YES);
			}
			else if (strOption.equalsIgnoreCase("EMail"))
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,0);
			else
				Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,2);
		//Options Selected
			Reporter.log("Receive My Quote form Filled successfully");
			Selenium.passTest("Receive My Quote form Filled successfully for option: " + strOption);
		
		}
		catch(Exception e)
		{
			 Log.error("Error in Selecting Receive My quote options. Exception:"+e.getMessage());
			 Selenium.failTest("Receive My Quote functionality Failed.",true);
			 Reporter.log("Receive My Quote functionality Failed.");
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Fill 'Receive your Quote Page'  ==> Invalid Scenarios
	//------------------------------------------------------------------------------
	public static void receiveYourQuoteInvalidScenarios()
	{

		// Select send by mail option
		Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,1);
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
		
		Selenium.ValidateObjectAndText("SOB", PageOpportunities.DeliverMyQuote.RADION_INCLUDESOB, "", false);
		Selenium.ValidateObjectAndText("SBC", PageOpportunities.DeliverMyQuote.RADION_INCLUDESBC, "", false);
		
		//Navigate back to basic subscriber page and enter street address
		
		 Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
		
		if (Selenium.isElementPresent("My Info Page", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1, true)){
			Reporter.log("Previous Button functionality at Deliver My Quote Page passed");
			Log.info("Previous Button functionality at Deliver My Quote Page passed");
			
		}
		else{
			Reporter.log("Previous Button functionality at Deliver My Quote Page failed.");
			Log.error("Previous Button functionality at Deliver My Quote Page failed.");
		}
		
       Selenium.type(PageOpportunities.BasicSubscriberInformation.TEXT_STREETADDRESS,"123 Line test");
       Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
	   if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuote.MSG_MAIL_ADDRESS_ERROR))
		{
			
			Reporter.log("Mailing address is missing error is not displayed after entering mailing address");
			Log.error("Mailing address is missing error is not displayed after entering mailing address");
			Selenium.failTest("Mailing address is missing error is not displayed after entering mailing address");
		}
		else
		{
			Reporter.log("Mailing address is missing error is displayed after entering mailing address");
		Log.info("Mailing address is missing error is displayed after entering mailing address");
			Selenium.passTest("Mailing address is missing error is displayed after entering mailing address");
		}
	   Selenium.selectCheckBox("Quote Receiving option",PageOpportunities.DeliverMyQuote.RADION_ONE,0);
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
	   Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
		
		if (Selenium.isElementPresent("My Info Page", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1, true)){
			Reporter.log("Previous Button functionality at Deliver My Quote Page passed");
			Log.info("Previous Button functionality at Deliver My Quote Page passed");
		
		}
		else{
			Reporter.log("Previous Button functionality at Deliver My Quote Page failed.");
			Log.error("Previous Button functionality at Deliver My Quote Page failed.");
		}
      Selenium.type(PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1,"abc@gmail.com");
      Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
	   if(Selenium.isElementPresent(PageOpportunities.DeliverMyQuote.MSG_MAIL_ADDRESS_ERROR))
		{
			Reporter.log("Email address is missing error is displayed even after entring valid email id");
			Log.error("Email address is missing error is displayed even after entring valid email id");
			Selenium.failTest("Email address is missing error is displayed even after entring valid email id");
		}
		else
		{
			Reporter.log("Email address is missing error is not displayed");
			Log.info("Email address is missing error is not displayed");
			Selenium.passTest("Email address is missing error is not displayed");
		}
	   
	}

	//------------------------------------------------------------------------------
	//Function to Fill 'Enroll Now Page' 
	//------------------------------------------------------------------------------
	public static  void  EnrollNow ()
	{
		try{
			
		//Click Enroll Now
			Selenium.click("Enroll Now", PageOpportunities.DeliverMyQuote.BUTTON_ENROLLNOW);
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ApplicationSubInfo.SELECT_GENDER);
			
		if (Selenium.isElementPresent("Gender", PageOpportunities.ApplicationSubInfo.SELECT_GENDER,true)){
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
	//Function to Fill 'Basic Subscriber Infor Page' 
	//------------------------------------------------------------------------------
	public static  void  fillBasicSubscriberInfo (Map<String, String> TestDataDictionary)
	{
		try{
			String FirstName, LastName,MiddleName; 
		//basic Info  Page
			FirstName=SFDCLeads.objLeadData.getFirstName();	
			LastName=SFDCLeads.objLeadData.getLastName();	
			MiddleName=SFDCLeads.objLeadData.getMiddleInitial();
			
			if(FirstName == null) {
				objBasicSubscriberInfo.setFirstName(TestDataDictionary.get("FirstName"));
				FirstName=objBasicSubscriberInfo.getFirstName();
			}
			
			if(MiddleName == null  ){ 
				objBasicSubscriberInfo.setMiddleName(TestDataDictionary.get("MiddleInitial"));
				MiddleName=objBasicSubscriberInfo.getFirstName();
			}
			
			if(LastName == null ) {
				objBasicSubscriberInfo.setLastName(TestDataDictionary.get("LastName"));
				LastName=objBasicSubscriberInfo.getFirstName();
			}
			
			objBasicSubscriberInfo.setSuffix(TestDataDictionary.get("Suffix"));
			objBasicSubscriberInfo.setStreetAddress(TestDataDictionary.get("BasicStreet")); // Need to Enter Basic street in Excel
			objBasicSubscriberInfo.setEmailAddress(TestDataDictionary.get("Email"));
			objBasicSubscriberInfo.setEmailAddress2(TestDataDictionary.get("Email2"));
			objBasicSubscriberInfo.setSalutation(TestDataDictionary.get("Salutation"));
			
			String StrDOB=TestDataDictionary.get("DOB");
			if (StrDOB.contains(";"))
				
			{
				List <String> ArrDOB=  Arrays.asList(StrDOB.split(";"));
				objBasicSubscriberInfo.setDOB(DateUtil.myCustomDate(ArrDOB.get(0), ArrDOB.get(1)));
				
			}
			else
				objMyInfoData.setMyDateOfBirth(StrDOB);	
			
				
			//Fill Basic Information Page
			
			if(FirstName != null && !FirstName.isEmpty()) 
				Selenium.type("Basic First Name", PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME, FirstName);
			
			if(MiddleName != null && !MiddleName.isEmpty()) 
				Selenium.type("Basic Middle Name", PageOpportunities.BasicSubscriberInformation.TEXT_MIDDLENAME, MiddleName);
			
			if(LastName != null && !LastName.isEmpty()) 
				Selenium.type("Basic Last Name", PageOpportunities.BasicSubscriberInformation.TEXT_LASTNAME, LastName);
			
			if(objBasicSubscriberInfo.getSuffix() != null && !objBasicSubscriberInfo.getSuffix().isEmpty()) 
				Selenium.type("Basic Suffix", PageOpportunities.BasicSubscriberInformation.TEXT_SUFFIX, objBasicSubscriberInfo.getSuffix());
			
			if(objBasicSubscriberInfo.getStreetAddress() != null && !objBasicSubscriberInfo.getStreetAddress().isEmpty()) 
				Selenium.type("Basic Street Address", PageOpportunities.BasicSubscriberInformation.TEXT_STREETNAME, objBasicSubscriberInfo.getStreetAddress());
			
			if(objBasicSubscriberInfo.getEmailAddress() != null && !objBasicSubscriberInfo.getEmailAddress().isEmpty()) 
				Selenium.type("Basic Email Address 1", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1, objBasicSubscriberInfo.getEmailAddress());
			
			if(objBasicSubscriberInfo.getEmailAddress2() != null && !objBasicSubscriberInfo.getEmailAddress2().isEmpty()) 
				Selenium.type("Basic Email Address 2", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS2, objBasicSubscriberInfo.getEmailAddress2());
			
			if(objBasicSubscriberInfo.getDOB() != null && !objBasicSubscriberInfo.getDOB().isEmpty())
			 Selenium.type(PageOpportunities.BasicSubscriberInformation.TEXT_DOB, objBasicSubscriberInfo.getDOB());
			
			if(objBasicSubscriberInfo.getSalutation() != null && !objBasicSubscriberInfo.getSalutation().isEmpty()) 
				Selenium.select("Salutation", PageOpportunities.BasicSubscriberInformation.SELECT_SALUTATION, objBasicSubscriberInfo.getSalutation());
			
			
			//Verify Previous
			 Selenium.clickButtons("Previous", PageSFDC.BUTTON_PREVIOUS);
			Selenium.sleep(3000);
			if (Selenium.isElementPresent("Compare Plan Button", PageOpportunities.ComparePlans.BUTTON_COMPAREPLAN, true)){
				Reporter.log("Previous Button functionality at Basic Subscriber Info Page passed");
				Log.info("Previous Button functionality at Basic Subscriber Info Page passed");
				Selenium.clickButtons("Next", PageSFDC.BUTTON_NEXT);
				Selenium.sleep(3000);
			}
			else{
				Reporter.log("Previous Button functionality at Basic Subscriber Info Page failed");
				Log.error("Previous Button functionality at Basic Subscriber Info Page failed");
			}
			

			//Click Next
			//Selenium.click("Next", PageOpportunities.BasicSubscriberInformation.BUTTON_NEXT);
			Selenium.clickButtons("Next", PageSFDC.BUTTON_NEXT);
			Selenium.waitForElement(Selenium.driver, PageOpportunities.DeliverMyQuote.BUTTON_VIEWPRINTQUOTE);
			if (Selenium.isElementPresent("View Print Quote", PageOpportunities.DeliverMyQuote.BUTTON_VIEWPRINTQUOTE, true)){
				Reporter.log("Basic Information Page functionality passed.");
				Selenium.passTest("Basic Information Page functionality passed");
			}
			else{
				Selenium.failTest("Basic Information Page functionality failed.");
				Reporter.log("Basic Information Page functionality failed.");
			}
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for Basic Information Page from excel. Exception:"+e.getMessage());
			 Selenium.failTest("Basic Information Page functionality failed");
			 
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Fill 'Basic Subscriber Infor Page' ==> Invalid Scenarios
	//------------------------------------------------------------------------------
	public static  void  fillBasicSubscriberInfoInvalidScenarios (Map<String, String> TestDataDictionary)
	{
		try{	
		//basic Info  Page
				 		 			
		
			objBasicSubscriberInfo.setFirstName(TestDataDictionary.get("FirstName"));
			objBasicSubscriberInfo.setMiddleName(TestDataDictionary.get("MiddleInitial"));
			objBasicSubscriberInfo.setLastName(TestDataDictionary.get("LastName"));
			objBasicSubscriberInfo.setSuffix(TestDataDictionary.get("Suffix"));
			objBasicSubscriberInfo.setStreetAddress(TestDataDictionary.get("BasicStreet")); // Need to Enter Basic street in Excel
			objBasicSubscriberInfo.setEmailAddress(TestDataDictionary.get("Email"));
			objBasicSubscriberInfo.setEmailAddress2(TestDataDictionary.get("Email2"));
			objBasicSubscriberInfo.setSalutation(TestDataDictionary.get("Salutation"));
			String StrDOB=TestDataDictionary.get("DOB");
			if (StrDOB.contains(";"))
				
			{
				List <String> ArrDOB=  Arrays.asList(StrDOB.split(";"));
				objBasicSubscriberInfo.setDOB(DateUtil.myCustomDate(ArrDOB.get(0), ArrDOB.get(1)));
				
			}
			else
				objMyInfoData.setMyDateOfBirth(StrDOB);	
			
			
			//Fill Basic Information Page
			
			if(objBasicSubscriberInfo.getFirstName() != null && !objBasicSubscriberInfo.getFirstName().isEmpty()) 
				Selenium.type("Basic First Name", PageOpportunities.BasicSubscriberInformation.TEXT_FIRSTNAME, objBasicSubscriberInfo.getFirstName());
			
			if(objBasicSubscriberInfo.geMiddleName() != null && !objBasicSubscriberInfo.geMiddleName().isEmpty()) 
				Selenium.type("Basic Middle Name", PageOpportunities.BasicSubscriberInformation.TEXT_MIDDLENAME, objBasicSubscriberInfo.geMiddleName());
			
			if(objBasicSubscriberInfo.getLastName() != null && !objBasicSubscriberInfo.getLastName().isEmpty()) 
				Selenium.type("Basic Last Name", PageOpportunities.BasicSubscriberInformation.TEXT_LASTNAME, objBasicSubscriberInfo.getLastName());
			
			if(objBasicSubscriberInfo.getSuffix() != null && !objBasicSubscriberInfo.getSuffix().isEmpty()) 
				Selenium.type("Basic Suffix", PageOpportunities.BasicSubscriberInformation.TEXT_SUFFIX, objBasicSubscriberInfo.getSuffix());
			
			if(objBasicSubscriberInfo.getStreetAddress() != null && !objBasicSubscriberInfo.getStreetAddress().isEmpty()) 
				Selenium.type("Basic Street Address", PageOpportunities.BasicSubscriberInformation.TEXT_STREETADDRESS, objBasicSubscriberInfo.getStreetAddress());
			
			if(objBasicSubscriberInfo.getEmailAddress() != null && !objBasicSubscriberInfo.getEmailAddress().isEmpty()) 
				Selenium.type("Basic Email Address 1", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS1, objBasicSubscriberInfo.getEmailAddress());
			
			if(objBasicSubscriberInfo.getEmailAddress2() != null && !objBasicSubscriberInfo.getEmailAddress2().isEmpty()) 
				Selenium.type("Basic Email Address 2", PageOpportunities.BasicSubscriberInformation.TEXT_EMAILADDRESS2, objBasicSubscriberInfo.getEmailAddress2());
			
			if(objBasicSubscriberInfo.getDOB() != null && !objBasicSubscriberInfo.getDOB().isEmpty())
			 Selenium.type(PageOpportunities.BasicSubscriberInformation.TEXT_DOB, objBasicSubscriberInfo.getDOB());
			
			if(objBasicSubscriberInfo.getSalutation() != null && !objBasicSubscriberInfo.getSalutation().isEmpty()) 
				Selenium.select("Salutation", PageOpportunities.BasicSubscriberInformation.SELECT_SALUTATION, objBasicSubscriberInfo.getSalutation());
			
					
			// Verify error message is displayed when DOB is a future date 
			int year = DateUtil.getCurrentYear()+ 2;
			String DOB = "01/01/" + Integer.toString(year);
			 Selenium.type(PageOpportunities.BasicSubscriberInformation.TEXT_DOB, DOB);
			 Selenium.typeKey("DOB", PageOpportunities.BasicSubscriberInformation.TEXT_DOB, Keys.TAB);
	        if( Selenium.isElementPresent("Error message when DOB is future date", PageOpportunities.BasicSubscriberInformation.TEXT_FUTURE_DOB_ERROR)){
	        	Reporter.log("Error message is displayed successfully when DOB is futre date on Basic Subscriber Info Page passed");
				Log.info("Error message is displayed successfully when DOB is futre date on Basic Subscriber Info Page passed");
	         }
	        else{
	        	Reporter.log("Error message is not displayed when DOB is futre date on Basic Subscriber Info Page Failed");
				Log.error("Error message is not displayed when DOB is futre date on Basic Subscriber Info Page Failed");
	         }
	         year = DateUtil.getCurrentYear()-1 ;
			 DOB = "01/01/" + Integer.toString(year);
			 Selenium.type(PageOpportunities.BasicSubscriberInformation.TEXT_DOB, DOB);
			 Selenium.typeKey("DOB", PageOpportunities.BasicSubscriberInformation.TEXT_DOB, Keys.TAB);
	        if( Selenium.isElementPresent("Error message when age is less than 2 years", PageOpportunities.BasicSubscriberInformation.TEXT_DOB_LOWERLIMITERROR)){
	        	Reporter.log("Error message is displayed successfully when DOB is less than 2 yrs on Basic Subscriber Info Page passed");
				Log.info("Error message is displayed successfully when DOB is less than 2 yrs on Basic Subscriber Info Page passed");
	         }
	        else{
	        	Reporter.log("Error message is not displayed when DOB is less than 2 yrs on Basic Subscriber Info Page Failed");
				Log.error("Error message is not displayed when DOB is less than 2 yrs on Basic Subscriber Info Page Failed");
	         }
	        if(objBasicSubscriberInfo.getDOB() != null && !objBasicSubscriberInfo.getDOB().isEmpty())
	   		 Selenium.type(PageOpportunities.BasicSubscriberInformation.TEXT_DOB, objBasicSubscriberInfo.getDOB());
	        Selenium.typeKey("DOB", PageOpportunities.BasicSubscriberInformation.TEXT_DOB, Keys.TAB);
			 //Click Next
	        Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			Thread.sleep(3000);
			if (Selenium.isElementPresent("View Print Quote", PageOpportunities.DeliverMyQuote.BUTTON_VIEWPRINTQUOTE, true)){
				Reporter.log("Basic Information Page functionality passed.");
				Selenium.passTest("Basic Information Page functionality passed");
			}
			else{
				Selenium.failTest("Basic Information Page functionality failed.");
				Reporter.log("Basic Information Page functionality failed.");
			}
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for Basic Information Page from excel. Exception:"+e.getMessage());
			 Selenium.failTest("Basic Information Page functionality failed");
			 
		}
		
	}
	
	
	//-----------------------------------
	//Function to Select PCP ' 
	//------------------------------------
	public static  WebDriver  selectPCP (Map<String, String> TestDataDictionary, WebDriver driver)
	{
		try{	
			objApplicationInfo.setPCPCity(TestDataDictionary.get("PCPCity"));
			objApplicationInfo.setPCPZIP(TestDataDictionary.get("PCPZip"));
			
			// Store the current window handle
			String winHandleBefore = driver.getWindowHandle();
			if (TestDataDictionary.get("Plan").contains("HMO")){
				
				//Click PCP lookup
				Selenium.click("PCP Lookup", PageOpportunities.ApplicationSubInfo.BUTTON_PCPLOOKUP);
				
				// Switch to new window opened
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
					driver.getTitle();
				}
				
				//Enter PCP City and Zip
				if(objApplicationInfo.getPCPCity() != null && !objApplicationInfo.getPCPCity().isEmpty()) 
					Selenium.type("PCP City", PageOpportunities.ApplicationSubInfo.TEXT_PCPCITY, objApplicationInfo.getPCPCity());
				
				if(objApplicationInfo.getPCPZIP() != null && !objApplicationInfo.getPCPZIP().isEmpty())
					Selenium.type("PCP Zip", PageOpportunities.ApplicationSubInfo.TEXT_PCPZIP, objApplicationInfo.getPCPZIP());
				
				//Select Option as Accepting new patients
				Selenium.select("Accept New Patients", PageOpportunities.ApplicationSubInfo.SELECT_ACCEPTNEWPATIENTS, "Yes");
				
				//Click Search PCP
				Selenium.click("Search PCP", PageOpportunities.ApplicationSubInfo.BUTTON_SEARCHPCP);
				Selenium.waitForElement(driver, PageOpportunities.ApplicationSubInfo.TABLE_PCP);
				
				//Here  we are getting the webTable Element
				WebElement htmltable=Selenium.getWebElement(PageOpportunities.ApplicationSubInfo.TABLE_PCP, "PCP Table");
				
				/*
				 * tag <tr> represents the rows of a Table. Here we are 
				 * getting the total rows of a table and selecting any row randomly
				 */
				
				List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
				int TotalRows=rows.size();
				int randomNumber ;
				randomNumber = (int) (Math.random() * (TotalRows - 1)) + 1;
				String strRandomNumber=Integer.toString(randomNumber);
				String SelectLinkLocator=PageOpportunities.ApplicationSubInfo.LINK_SELECTPCP.replace("INDEX", strRandomNumber);
				Thread.sleep(5000);
				Selenium.click("Select PCP", SelectLinkLocator);
				Thread.sleep(5000);
				//Accept the browser Message as Yes
				driver.switchTo().alert().accept();
				Thread.sleep(5000);
				// Switch back to original browser (first window)
				driver.switchTo().window(winHandleBefore);
				
				//Get PCP Information from WebPage
				objApplicationInfo.setPCPCity(Selenium.getWebElement(PageOpportunities.ApplicationSubInfo.ELE_PCPCITY,"PCP City",true).getAttribute("value"));
				objApplicationInfo.setPCPFName(Selenium.getWebElement(PageOpportunities.ApplicationSubInfo.ELE_PCPFNAME,"PCP First Name",true).getAttribute("value"));
				objApplicationInfo.setPCPLName(Selenium.getWebElement(PageOpportunities.ApplicationSubInfo.ELE_PCPLNAME,"PCP Last Name",true).getAttribute("value"));
				objApplicationInfo.setPCPID(Selenium.getWebElement(PageOpportunities.ApplicationSubInfo.ELE_PCPID,"PCP ID",true).getAttribute("value"));
			
			}
			
				
			//Click next
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);

		}
		catch(Exception e)
		{
			 Log.error("Error on Application info Page. Exception:"+e.getMessage());
			 Selenium.failTest("Select PCP functionality on Application info Page is Failed."); 
		}
		return driver;
	}	
	
	
	//-----------------------------------
	//Function to Select PCP for Dependent 
	//------------------------------------
	public static  WebDriver  selectPCPForDependent (Map<String, String> TestDataDictionary, WebDriver driver)
	{
		try{	
			objApplicationInfo.setPCPCity(TestDataDictionary.get("PCPCity"));
			objApplicationInfo.setPCPZIP(TestDataDictionary.get("PCPZip"));
			
			// Store the current window handle
			String winHandleBefore = driver.getWindowHandle();
			if (TestDataDictionary.get("Plan").contains("HMO")){
			//	Thread.sleep(10000);
				//Click PCP lookup
				 Selenium.clickButtons("PCP Lookup",PageOpportunities.ApplicationDepInfo.BUTTON_ADDPCP); //
			//	driver.findElement(By.id("PCPButton")).click();
			//	Selenium.driver.findElement(By.id("PCPButton")).click();
				// Switch to new window opened
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
					driver.getTitle();
				}
				
				//Enter PCP City and Zip
				if(objApplicationInfo.getPCPCity() != null && !objApplicationInfo.getPCPCity().isEmpty()) 
					Selenium.type("PCP City", PageOpportunities.ApplicationSubInfo.TEXT_PCPCITY, objApplicationInfo.getPCPCity());
				
				if(objApplicationInfo.getPCPZIP() != null && !objApplicationInfo.getPCPZIP().isEmpty())
					Selenium.type("PCP Zip", PageOpportunities.ApplicationSubInfo.TEXT_PCPZIP, objApplicationInfo.getPCPZIP());
				
				//Select Option as Accepting new patients
				Selenium.select("Accept New Patients", PageOpportunities.ApplicationSubInfo.SELECT_ACCEPTNEWPATIENTS, "Yes");
				
				//Click Search PCP
				Selenium.click("Search PCP", PageOpportunities.ApplicationSubInfo.BUTTON_SEARCHPCP);
				Selenium.waitForElement(driver, PageOpportunities.ApplicationSubInfo.TABLE_PCP);
				
				//Here  we are getting the webTable Element
				WebElement htmltable=Selenium.getWebElement(PageOpportunities.ApplicationSubInfo.TABLE_PCP, "PCP Table");
				
				/*
				 * tag <tr> represents the rows of a Table. Here we are 
				 * getting the total rows of a table and selecting any row randomly
				 */
				
				List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
				int TotalRows=rows.size();
				int randomNumber ;
				randomNumber = (int) (Math.random() * (TotalRows - 1)) + 1;
				String strRandomNumber=Integer.toString(randomNumber);
				String SelectLinkLocator=PageOpportunities.ApplicationSubInfo.LINK_SELECTPCP.replace("INDEX", strRandomNumber);
				
				Selenium.click("Select PCP", SelectLinkLocator);
				Thread.sleep(5000);
				//Accept the browser Message as Yes
				driver.switchTo().alert().accept();
				
				// Switch back to original browser (first window)
				driver.switchTo().window(winHandleBefore);
				
				Reporter.log("PCP is selected for Dependent");
				
			}
		
		}
		catch(Exception e)
		{
			 Log.error("Error on Application info Page. Exception:"+e.getMessage());
			 Selenium.failTest("Select PCP functionality on Application info Page is Failed."); 
		}
		return driver;
	}
	
	
	//-----------------------------------
	//Function to Select Broker ' 
	//------------------------------------
	public static  WebDriver  selectBroker (Map<String, String> TestDataDictionary, WebDriver driver)
	{
		try{	
			
			
			// Store the current window handle
			String winHandleBefore = driver.getWindowHandle();
			if (TestDataDictionary.get("Broker").contains("Yes")){
				
				//Click Broker lookup
				Selenium.click("Broker Lookup", PageOpportunities.ApplicationSubInfo.BUTTON_BROKERLOOKUP);
				
				// Switch to new window opened
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
					driver.getTitle();
				}
								
				//Click Search PCP
				Selenium.click("Search Broker", PageOpportunities.ApplicationSubInfo.BUTTON_SEARCH_BROKER);
				Selenium.waitForElement(driver, PageOpportunities.ApplicationSubInfo.TABLE_BROKER);
				
				//Here  we are getting the webTable Element
				WebElement htmltable=Selenium.getWebElement(PageOpportunities.ApplicationSubInfo.TABLE_BROKER, "Broker Table");
				
				/*
				 * tag <tr> represents the rows of a Table. Here we are 
				 * getting the total rows of a table and selecting any row randomly
				 */
				
				List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
				int TotalRows=rows.size();
				int randomNumber ;
				randomNumber = (int) (Math.random() * (TotalRows - 1)) + 1;
				String strRandomNumber=Integer.toString(randomNumber);
				String SelectLinkLocator=PageOpportunities.ApplicationSubInfo.LINK_SELECT_BROKER.replace("INDEX", strRandomNumber);
				Selenium.click("Select Broker", SelectLinkLocator);
								
				// Switch back to original browser (first window)
				driver.switchTo().window(winHandleBefore);
				
			}
						
		}
		catch(Exception e)
		{
			 Log.error("Error on Broker Page. Exception:"+e.getMessage());
			 Selenium.failTest("Select Broker functionality on Application info Page is Failed."); 
		}
		return driver;
	}
	

	//------------------------------------------------------------------------------
	//Function to Fill 'Application Dep Info' Page' 
	//------------------------------------------------------------------------------
	public static  void  fillApplicationDepInfo (Map<String, String> TestDataDictionary) //
	{
		try{	
		//Fill Dependent Application info
			if (TestDataDictionary.get("DependentCoverage").contains("Yes")){
				Selenium.sleep(2000);
				Selenium.waitForElement(Selenium.driver, PageOpportunities.ApplicationDepInfo.SELECT_GENDER);
				
				if (Selenium.isElementPresent("Gender", PageOpportunities.ApplicationDepInfo.SELECT_GENDER, true)){
					Selenium.select("Gender", PageOpportunities.ApplicationDepInfo.SELECT_GENDER, "Male");
					Selenium.type("Dependent First Name", PageOpportunities.ApplicationDepInfo.TEXT_DEPENDENT_FNAME, "NAME");
					Selenium.type("Dependent Last Name", PageOpportunities.ApplicationDepInfo.TEXT_DEPENDENT_LNAME, "ABC");
					
					selectPCPForDependent ( TestDataDictionary, Selenium.driver);
					
					Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
					
					if (Selenium.isElementPresent("Phone Pref", PageOpportunities.CommunicationPref.CHECKBOX_PHONE,true)){
						Reporter.log("Functionality on Application Dependent Info Page is passed.");
						Selenium.passTest("Select PCP functionality on Application Dependent info Page is passed.");
					}
					else{
						Selenium.failTest("Functionality on Application Dependent info Page is Failed.");
						Log.error("Next button is not clicked on Application Dependent info Page");
						Reporter.log("Functionality on Application Dependent Info Page is failed.");
					}
				}
				else{
					Selenium.failTest("Application Information Dependent Page functionality failed.");
					Log.error("Gender dropdown is not present on Application Info Dependent page");
					Reporter.log("Application Dependent Information Page functionality failed.");
				}
			}
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for Application Information Page from excel. Exception:"+e.getMessage());
			 Selenium.failTest("Application Information Page functionality failed.");
			 
		}
		
	}
	
	public static  void AddSecondDependent (Map<String, String> TestDataDictionary)
	{
		try{
			if (TestDataDictionary.get("DependentCoverage").contains("Yes")){
		//My Family Page
				
			String StrDOB=TestDataDictionary.get("DepDOB");
			List <String> ArrDOB=  Arrays.asList(StrDOB.split(";"));
			objMyFamilyData.setDepDateOfBirth(DateUtil.myCustomDate(ArrDOB.get(0), ArrDOB.get(1)));
			objMyFamilyData.setDependentRelation(TestDataDictionary.get("DepRelation"));
			objMyFamilyData.setTobaccoUse("No");
			
			
			//Selenium.waitForElement( Selenium.driver, PageOpportunities.MyFamily.SELECT_RELATION);
			Thread.sleep(5000);
			
			//verify DOB 
			Selenium.ValidateObjectAndText("Dependent Date of Birth", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, "", false);
			
			//Verify Dependent Relation
			WebElement wElement = Selenium.getWebElement(PageOpportunities.MyFamily.SELECT_RELATION,"Dependent Relation",true);
			Select selectObject = new Select(wElement);
			List<WebElement> allOptions=selectObject.getOptions();
			List<String> ExpectedOptions = Arrays.asList("Clear", "Spouse", "Child Under 26", "Step Child Under 26","Dependent Child", "Ex Spouse", "Domestic Partner"); 
			int Error=0;
			String ErrorOption="";
			for (int ioption=0 ; ioption<ExpectedOptions.size();ioption++){
				System.out.println(allOptions.get(ioption).getText());
				if (!allOptions.get(ioption).getText().contains(ExpectedOptions.get(ioption))){
					Error=Error+1;	
					ErrorOption= ErrorOption + ExpectedOptions.get(ioption) + " ,";
				}
			}
			
			if (Error>0){
				Reporter.log(ErrorOption +": is not present in Dependent Relation Dropdown.");
				Log.error(ErrorOption +": is not present in Dependent Relation Dropdown.");
			}
			else{
				Reporter.log(" Dependent Relation Dropdown functionality passed.");
				Log.info("Dependent Relation Dropdown functionality passed.");
			}
				
			
			//Verify Age
			Selenium.ValidateObjectAndText("Dependent age", PageOpportunities.MyFamily.ELE_AGE, "", false);
			
			//Verify Add new Dependent 
			Selenium.ValidateObjectAndText("Add New Dependent", PageOpportunities.MyFamily.LINK_ADD_NEWDEP, "", false);
			
			
			//Fill My Family Page
			if(objMyFamilyData.getDepDateOfBirth() != null && !objMyFamilyData.getDepDateOfBirth().isEmpty()) 
				Selenium.type("Dep DOB", PageOpportunities.MyFamily.TEXT_DATEOFBIRTH, objMyFamilyData.getDepDateOfBirth());
			
			if(objMyFamilyData.getDependentRelation() != null && !objMyFamilyData.getDependentRelation().isEmpty()) 
				Selenium.select("Dependent Relation", PageOpportunities.MyFamily.SELECT_RELATION, objMyFamilyData.getDependentRelation());
			
			if (!TestDataDictionary.get("Zip").contains("MA"))
				Selenium.select("Tobacco use", PageOpportunities.MyFamily.SELECT_TOBACCO, objMyFamilyData.getTobaccoUse());
				
			
			
			//verify Cancel
			Selenium.click("Cancel", PageOpportunities.MyFamily.BUTTON_CANCEL);
			
			if (Selenium.driver.switchTo().alert().getText().contains("exit")){
				Reporter.log("Sucessfully validated Error Message after clicking Cancel at My Family Page: " + Selenium.driver.switchTo().alert().getText());
				Log.info("Sucessfully validated Error Message after clicking Cancel at My Family Page: " + Selenium.driver.switchTo().alert().getText());
				Selenium.handleBrowserPopUp("Cancel");
			}
			else{
				Reporter.log("Cancel Button functionality at My Family Page failed");
				Log.info("Cancel Button functionality at My Family Page failed");
			}
			
			
			
			//Verify Previous
			Selenium.ValidateObjectAndText("Previous Button - My Family aPage", PageOpportunities.MyFamily.BUTTON_PREVIOUS, "", false);
			
			
			//Verify Next
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ComparePlans.SELECT_PRODUCTYPE);
			if (Selenium.isElementPresent("Product Dropdown", PageOpportunities.ComparePlans.SELECT_PRODUCTYPE, true)){
				Reporter.log("Next Button functionality at My Family Page passed");
				Reporter.log("My Family Page is filled successsfully.");
				Log.info("Next Button functionality at My Family Page passed");	
			}
			else{
				Selenium.failTest("My Information Page is not filled successsfully.");
				Reporter.log("My Family Page is NOT filled.");
				Reporter.log("Next Button functionality at My Family Page failed");
				Log.error("Next Button functionality at My Family Page failed");
			}
			}
		}
		catch(Exception e)
		{
			 Log.error("Error in getting data for My Information Page from Dictionary Object. Exception:"+e.getMessage());
			 Selenium.failTest("Error in getting data for My Information Page from Excel.");
			 
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Fill 'Communication Preference Info' Page' 
	//------------------------------------------------------------------------------
	public static  void  fillCommunicationPref (Map<String, String> TestDataDictionary)
	{
		try{	
			objCommunicationPref.setPhonePref(TestDataDictionary.get("PermissionToCall"));
			objCommunicationPref.setMailPref(TestDataDictionary.get("PermissionToMail"));
			objCommunicationPref.setEmailPref(TestDataDictionary.get("PermissionToEmail"));
		
			if(objCommunicationPref.getMailPref() != null && !objCommunicationPref.getMailPref().isEmpty() && !objCommunicationPref.getPhonePref().equalsIgnoreCase("No"))
				Selenium.check("Phone Pref", PageOpportunities.CommunicationPref.CHECKBOX_PHONE);
			
			if(objCommunicationPref.getMailPref() != null && !objCommunicationPref.getMailPref().isEmpty() && !objCommunicationPref.getMailPref().equalsIgnoreCase("No")) 
				Selenium.check("Mail Pref", PageOpportunities.CommunicationPref.CHECKBOX_MAIL);
			
			if(objCommunicationPref.getEmailPref() != null && !objCommunicationPref.getEmailPref().isEmpty() && !objCommunicationPref.getMailPref().equalsIgnoreCase("No")) 
				Selenium.check("Email Pref", PageOpportunities.CommunicationPref.CHECKBOX_EMAIL);
			
		}
		catch(Exception e)
		{
			 Log.error("Error while Selecting communication preference. Exception:"+e.getMessage());
			 Selenium.failTest("Communication preference functionality Failed.");
			 
		}
		
	}

	public static void clickCommunicationPrefNext(){
		//Click Next
		Selenium.sleep(2000);
		Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				
		//Validate the next page page
		if (Selenium.isElementPresent("CCEMail radio button", PageOpportunities.AdditionalEMail.RADIO_CCEMAIL,true)){
			Reporter.log("Communication preference functionality passed.");
			Selenium.passTest("Communication preference functionality passed.");
		}
		else{
			Selenium.failTest("Communication preference functionality failed.");
			Reporter.log("Communication preference functionality failed.");
			
		}
	}
	
	public static void NavigateToCommunicationPrefPage(Map<String, String> TestDataDictionary){
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
				if (TestDataDictionary.get("DependentCoverage").contains("No")){
					if (Selenium.isElementPresent("Phone Pref", PageOpportunities.CommunicationPref.CHECKBOX_PHONE,true)){
						Reporter.log("Functionality on Application Info Page is passed.");
						Selenium.passTest("Select PCP functionality on Application info Page is passed.");
					}
					else{
						Selenium.failTest("Functionality on Application info Page is Failed.");
						Log.error("Next button is not clicked on Application info Page");
						Reporter.log("Functionality on Application Info Page is failed.");
					}
				}
				else{
					if (Selenium.isElementPresent("Dependent First Name", PageOpportunities.ApplicationDepInfo.TEXT_DEPENDENT_FNAME,true)){
						Reporter.log("Functionality on Application Info Page is passed.");
						Selenium.passTest("Select PCP functionality on Application info Page is passed.");
					}
					else{
						Selenium.failTest("Functionality on Application info Page is Failed.");
						Log.error("Next button is not clicked on Application info Page");
						Reporter.log("Functionality on Application Info Page is failed.");
					}
				}
			}
	
	public static void NavigateToCommunicationPref(){
		
		
		if (Selenium.isElementPresent( "Dep First NAme", PageOpportunities.ApplicationDepInfo.TEXT_DEPENDENT_FNAME, true)){
			Reporter.log("Next button is available");
			Selenium.passTest("Next button is available");
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			//verifyMyInformationPage();
		}
		else
		{
			Reporter.log("Next button is not available");
			Selenium.failTest("Next button is not available");
		}
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Fill 'Additional Email form' Page' 
	//------------------------------------------------------------------------------
	public static void fillAdditionalEMailForm(Map<String, String> TestDataDictionary){
		//Select additional EMail
		objAdditionalEMail.setAdditionalEMail(TestDataDictionary.get("AdditionalEMail"));
		
		if (Selenium.isElementPresent("CCEMail", PageOpportunities.AdditionalEMail.RADIO_CCEMAIL)){
			
			if(objAdditionalEMail.getAdditionalEMail() != null && !objAdditionalEMail.getAdditionalEMail().isEmpty() && objAdditionalEMail.getAdditionalEMail().equalsIgnoreCase("Yes")){ 
				Selenium.selectCheckBox("CC Email Address",PageOpportunities.AdditionalEMail.RADIO_CCEMAIL,0 );
				Selenium.type("CC Email", PageOpportunities.AdditionalEMail.TEXT_EMAIL, objAdditionalEMail.getAdditionalEMail());
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			}
			else
			{
				//Select "No"
				Selenium.selectCheckBox("CC Email Address",PageOpportunities.AdditionalEMail.RADIO_CCEMAIL,1 );
				Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			}
				
			if (Selenium.isElementPresent("Review page", PageOpportunities.ReviewYourSelection.ELE_PERMISSIONCALL,true)){
				Reporter.log("Additional Email functionality passed.");
				Selenium.passTest("Additional Email functionality passed.");
			}
			else{
				Log.error("Review page is not present after clicking next button.");
				Selenium.failTest("Additional Email functionality Failed.");
				Reporter.log("Additional Email functionality Failed.");
			}
		}
		else
		{
			 Log.error("Additional EMail Page is not present.");
			 Selenium.failTest("Additional Email functionality Failed.");
			 Reporter.log("Additional Email functionality Failed.");
		}
		
	}
	
	public static void NavigateToAdditionalEmailForm(){
		
		
		if (Selenium.isElementPresent( "Phone Pref", PageOpportunities.CommunicationPref.CHECKBOX_PHONE, true)){
			Reporter.log("Next button is available");
			Selenium.passTest("Next button is available");
			Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			
		}
		else
		{
			Reporter.log("Next button is not available");
			Selenium.failTest("Next button is not available");
		}
	}

	
	//------------------------------------------------------------------------------
	//Function to Fill 'Terms and Condition' Page' 
	//------------------------------------------------------------------------------
	public static void fillTermsAndConditions(){
		try {
			//Click I Agree Checkbox
			Selenium.waitForElement(Selenium.driver, PageOpportunities.TermsAndConditions.CHECK_IAGREE);
			Selenium.check("I Agree", PageOpportunities.TermsAndConditions.CHECK_IAGREE);
			if (Selenium.isElementPresent(PageOpportunities.TermsAndConditions.CHECK_FOCUS_IAGREE))
				Selenium.check("Focus Disclosure", PageOpportunities.TermsAndConditions.CHECK_FOCUS_IAGREE);
			Selenium.click("Submit Application", PageOpportunities.TermsAndConditions.BUTTON_SUBMIT);
			Selenium.sleep(3000);
			Selenium.waitForElement(Selenium.driver, PageOpportunities.ApplicationCompleted.LINK_MAKEPAYMENT);
			if (Selenium.isElementPresent("Make Payment", PageOpportunities.ApplicationCompleted.LINK_MAKEPAYMENT)){
				Reporter.log("Terms and Conditions functionality passed.");
				Selenium.passTest("Terms and Conditions functionality passed.",true);
			}
	//		else if(Selenium.isElementPresent("Continue", PageOpportunities.ApplicationCompleted.BUTTON_CONTINUE)){
	//			Selenium.click("Continue", PageOpportunities.ApplicationCompleted.BUTTON_CONTINUE);
	//			Reporter.log("Terms and Conditions functionality passed.");
	//			Selenium.passTest("Terms and Conditions functionality passed.",true);
	//		}
			else{
				Log.error("MakePayment Link is not present after clicking Submit button.");
				Reporter.log("Terms and Conditions functionality failed.");
				Selenium.failTest("Terms and Conditions functionality Failed.",true);
			}
		}
		catch(Exception e){
			Log.error("Error in Submitting the Application  :" + e);
			Selenium.failTest("Error in Submitting the Application",true);
		}
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Validate 'Review Your Selection' Page' 
	//------------------------------------------------------------------------------
	public static void validateReviewYourSelection(Map<String, String> TestDataDictionary){
		
		//Check if Review Page is appeared
		Selenium.waitForElement(Selenium.driver, PageOpportunities.ReviewYourSelection.ELE_CITY);
		
		//Get all the values from the review page and store in a string
		String PageInfo="";
		List <WebElement> Ele=Selenium.getWebElements(PageOpportunities.ReviewYourSelection.ELE_REVIEPAGE, "Review");
		if (Ele.size()>0){
			for(int a=0; a<Ele.size();a++){   
				PageInfo= PageInfo + "###" + Ele.get(a).getAttribute("value");
				
			}
			System.out.println(PageInfo);
			
			//Store all the input values in a array
			List<String> TestData=new ArrayList<String>();
			
			//Get Application Informant
			
			if (objLead.objLeadData.getFirstName() !=null && !objLead.objLeadData.getFirstName().isEmpty())
				TestData.add(objLead.objLeadData.getFirstName());
			if (objLead.objLeadData.getLastName() !=null && !objLead.objLeadData.getLastName().isEmpty())
				TestData.add(objLead.objLeadData.getLastName());
			if (objLead.objLeadData.getDateOfBirth() !=null && !objLead.objLeadData.getDateOfBirth().isEmpty())
				TestData.add(objLead.objLeadData.getDateOfBirth());
			if (objLead.objLeadData.getZip() !=null && !objLead.objLeadData.getZip().isEmpty())
				TestData.add(objLead.objLeadData.getZip());
			if (objApplicationInfo.getGenders() !=null && !objApplicationInfo.getGenders().isEmpty())
				TestData.add(objApplicationInfo.getGenders());
			
			//Get Application Informant
			if(objBasicSubscriberInfo.getStreetAddress() != null && !objBasicSubscriberInfo.getStreetAddress().isEmpty()) 
				TestData.add(objBasicSubscriberInfo.getStreetAddress());
				//City State Zip
			
			if(objApplicationInfo.getMailingAddress() != null && !objApplicationInfo.getMailingAddress().isEmpty())
				TestData.add(objApplicationInfo.getMailingAddress());
			
			//Get PCP
			if (TestDataDictionary.get("Plan").contains("HMO")){
				if (objApplicationInfo.getPCPCity() !=null && !objApplicationInfo.getPCPCity().isEmpty())
					TestData.add(objApplicationInfo.getPCPCity());
				if (objApplicationInfo.getPCPFName() !=null && !objApplicationInfo.getPCPFName().isEmpty())
					TestData.add(objApplicationInfo.getPCPFName());
				if (objApplicationInfo.getPCPLName() !=null && !objApplicationInfo.getPCPLName().isEmpty())
					TestData.add(objApplicationInfo.getPCPLName());
				if (objApplicationInfo.getPCPID() !=null && !objApplicationInfo.getPCPID().isEmpty())
					TestData.add(objApplicationInfo.getPCPID());
			}
			
			//Phone and EMail
			String HomePhone="";
			if(objApplicationInfo.getHomePhone() != null && !objApplicationInfo.getHomePhone().isEmpty()){
				HomePhone="(" + objApplicationInfo.getHomePhone().substring(0, 3)+ ") " + objApplicationInfo.getHomePhone().substring(3, 6) + "-" + objApplicationInfo.getHomePhone().substring(6, 10);
				TestData.add(HomePhone);
			}
			int Error=0;
			//Validate the Review page with input data
			for(int iTestData=0; iTestData<TestData.size()-1; iTestData++){
				if (!PageInfo.contains(TestData.get(iTestData))){
					Error=Error+1;	
					System.out.println(TestData.get(iTestData));
				}
			}
			
			
			//Communication pref
			if(objCommunicationPref.getPhonePref().equals(null) || objCommunicationPref.getPhonePref().isEmpty() || objCommunicationPref.getPhonePref().equalsIgnoreCase("No"))
				Selenium.ValidateObjectAndText("Permission to Call", PageOpportunities.ReviewYourSelection.ELE_PERMISSIONCALL, "No", false);
			else
				Selenium.ValidateObjectAndText("Permission to Call", PageOpportunities.ReviewYourSelection.ELE_PERMISSIONCALL, "Yes", false);
					
			if(objCommunicationPref.getMailPref().equals(null) || objCommunicationPref.getMailPref().isEmpty() || objCommunicationPref.getMailPref().equalsIgnoreCase("No"))
				Selenium.ValidateObjectAndText("Permission to mail", PageOpportunities.ReviewYourSelection.ELE_PERMISSIONMAIL, "No", false);
			else
				Selenium.ValidateObjectAndText("Permission to mail", PageOpportunities.ReviewYourSelection.ELE_PERMISSIONMAIL, "Yes", false);
				
			if(objCommunicationPref.getEmailPref().equals(null) || objCommunicationPref.getEmailPref().isEmpty() || objCommunicationPref.getEmailPref().equalsIgnoreCase("No"))
				Selenium.ValidateObjectAndText("Permission to email", PageOpportunities.ReviewYourSelection.ELE_PERMISSIONEMAIL, "No", false);
			else
				Selenium.ValidateObjectAndText("Permission to email", PageOpportunities.ReviewYourSelection.ELE_PERMISSIONEMAIL, "Yes", false);
				
				
		}
		Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
			
			if (Selenium.isElementPresent("I Agree", PageOpportunities.TermsAndConditions.CHECK_IAGREE)){
				Reporter.log("Review Your Selection Page is Validated.");
				Selenium.passTest("Review Your Selection Page functionality Passed.",true);
			}
			else{
				Log.error("Next Button on Review page is not clicked.");
				Selenium.failTest("Review Your Selection Page functionality Failed.",true);
				Reporter.log("Review Your Selection Page functionality Failed.");
			}
			
	}
	
	
	//------------------------------------------------------------------------------
	//Function to Validate 'Application Completed' Page' 
	//------------------------------------------------------------------------------
	public static void validateApplicationCompleted(){
		String ApplicationNum="";
		//Check if Review Page is appeared
		Selenium.waitForElement(Selenium.driver, PageOpportunities.ApplicationCompleted.LINK_MAKEPAYMENT);
		Selenium.ValidateObjectAndText("Application Number", PageOpportunities.ApplicationCompleted.ELE_APPLICATION_NUMBR, "", false);
		ApplicationNum=Selenium.getWebElement(PageOpportunities.ApplicationCompleted.ELE_APPLICATION_NUMBR, "Application Number", true).getText();
		if 	(ApplicationNum.length()>0){
			Reporter.log(ApplicationNum);
			Log.info("Application Number Generated.");
			Selenium.click("Make Payment",PageOpportunities.ApplicationCompleted.LINK_MAKEPAYMENT);
//			Selenium.wElement.sendKeys(Keys.ENTER);
//			Selenium.handleBrowserPopUp("Ok");
		}
		else{
			Reporter.log("Application Number is not generated.");
			Selenium.failTest("Application Number is not generated.");
		}
			
		}

	
	//------------------------------------------------------------------------------
	//Function to Navigate to 'Compare Plans' Page' 
	//------------------------------------------------------------------------------
	public static void NavigationToComparePlanPage()
	{
	
	try
	{
		Alert alr= Selenium.driver.switchTo().alert();
	    alr.accept(); }
	catch(Exception e)
	{
		
	}

	Selenium.waitForElement( Selenium.driver, PageOpportunities.MyInformation.TEXT_DATEOFBIRTH);
	
	//Fill My Information Page
			
	if(objMyInfoData.getMyDateOfBirth() != null && !objMyInfoData.getMyDateOfBirth().isEmpty())
		Selenium.type(PageOpportunities.MyInformation.TEXT_DATEOFBIRTH, objLead.objLeadData.getDateOfBirth()); 
				
	if(objMyInfoData.getMyZip() != null && !objMyInfoData.getMyZip().isEmpty())
			Selenium.type(PageOpportunities.MyInformation.TEXT_ZIPCODE, objMyInfoData.getMyZip());
	
	if(objMyInfoData.getDependentCoverage() != null && !objMyInfoData.getDependentCoverage().isEmpty())
		Selenium.select("Dependent Coverage", PageOpportunities.MyInformation.SELECT_COVERAGE, objMyInfoData.getDependentCoverage());
	
	if(objMyInfoData.getRequestedCoverageEffDate() != null && !objMyInfoData.getRequestedCoverageEffDate().isEmpty())
			Selenium.select("Requested Coverage EffDate", PageOpportunities.MyInformation.SELECT_COVERAGEEFFECTIVEDATE, objMyInfoData.getRequestedCoverageEffDate());
	
	if(objMyInfoData.getTobaccoUse() != null && !objMyInfoData.getTobaccoUse().isEmpty())
		Selenium.select("Tobacco Use", PageOpportunities.MyInformation.SELECT_TOBACCO_USE, objMyInfoData.getTobaccoUse());

	Reporter.log("All fields in My Information Page is filled.");
	Selenium.passTest("All fields in My Information Page is filled successsfully.");

	Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
		

}
	
	
	//------------------------------------------------------------------------------
	//Function to Click Save and Exit Button on Application PAge 
	//------------------------------------------------------------------------------
	public static  void  SaveAndExit ()
	{
	try{
		
		//Click Save and Exit Now
		Selenium.clickButtons("Save and Exit", PageOpportunities.DeliverMyQuote.BUTTON_SAVEANDEXIT);
		Selenium.sleep(3000);
		Selenium.handleBrowserPopUp("Ok");
		Selenium.waitForElement(Selenium.driver, PageOpportunities.Details.Element_OpportunityName);
		
		if (Selenium.isElementPresent("Opportunity Name", PageOpportunities.Details.Element_OpportunityName,true)){
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
	//Function to fill 'Address Information Page'  ==> Invalid Scenarios
	//----------------------------------------------------------------
	public static  void  fillAddressInformationInvalidScenarios (Map<String, String> TestDataDictionary) 
	{
		try{	
			objAddressInfo.setStreetAddress(TestDataDictionary.get("Street"));
			if(objAddressInfo.getStreetAddress() != null && !objAddressInfo.getStreetAddress().isEmpty())
				Selenium.type("Street Address", PageOpportunities.AddressInformation.TEXT_STREETADDRESS, objAddressInfo.getStreetAddress());
			if(Selenium.isElementPresent(PageOpportunities.AddressInformation.TEXT_STREETADDRESS))
			{
				Reporter.log("Street Address field is displayed successfully.");
				Selenium.passTest("Street Address field is displayed passed.");
			}
			else
			{
				Selenium.failTest("Street Address field is not displayed on Application Information page: failed.");
				 Log.error("Street Address field is not displayed: failed.");
				 Reporter.log("Street Address field is not displayed: failed..");
			}
	
	if(Selenium.isElementPresent(PageOpportunities.AddressInformation.TEXT_CITY))
			{
				Reporter.log("City field is displayed successfully.");
				Selenium.passTest("City field is displayed passed.");
			}
			else
			{
				Selenium.failTest("City field is not displayed on Application Information page: failed.");
				 Log.error("City field is not displayed: failed.");
				 Reporter.log("City field is not displayed: failed..");
			}
	if(Selenium.isElementPresent(PageOpportunities.AddressInformation.TEXT_STATE))
			{
				Reporter.log("STATE field is displayed successfully.");
				Selenium.passTest("STATE field is displayed passed.");
			}
			else
			{
				Selenium.failTest("STATE field is not displayed on Application Information page: failed.");
				 Log.error("STATE field is not displayed: failed.");
				 Reporter.log("STATE field is not displayed: failed..");
			}
	if(Selenium.isElementPresent(PageOpportunities.AddressInformation.TEXT_COUNTRY))
			{
				Reporter.log("COUNTRY field is displayed successfully.");
				Selenium.passTest("COUNTRY field is displayed passed.");
			}
			else
			{
				Selenium.failTest("COUNTRY field is not displayed on Application Information page: failed.");
				 Log.error("COUNTRY field is not displayed: failed.");
				 Reporter.log("COUNTRY field is not displayed: failed..");
			}
	
	
	
	// Verify if Mailing address is same as Billing address,then billing address field is not displayed 
	Selenium.selectCheckBox("Different Mailing Address",PageOpportunities.AddressInformation.RADION_MAILINGPERMAMNENTADDRESS,0 );
	 if(Selenium.isElementPresent("Permanent address field",PageOpportunities.AddressInformation.TEXT_MAILINGSTREETADDRESS))
	 {
		 Reporter.log("Permanent address text field is still displayed when the mailing address is same as billing address");
		Selenium.failTest("Permanent address text field is still displayed when the mailing address is same as billing address");
	 }
	 else {
		 Reporter.log("Permanent address text field is not displayed when the mailing address is same as billing address");
			Selenium.passTest("Permanent address text field is not displayed when the mailing address is same as billing address");
	} 
	 
	
		Reporter.log("Next button is displayed successfully.");
		Selenium.passTest("Next button is displayed passed.");
		Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
		if(Selenium.isElementPresent("Medicare claim number", PageOpportunities.MedicareInformationNumber.TEXT_MEDICARE_CLAIM_NUMBER))
		{
			Reporter.log("User is navigated to Medicare Information page successfully.");
			Selenium.passTest("User is navigated to Medicare Information page successfully.");
		}
		else
		{
			Reporter.log("User is not navigated to Medicare Information page:Failed");
			Selenium.failTest("User is not navigated to Medicare Information page:Failed");
		}
	
	
	}
		catch(Exception e)
		{
			 Log.error("Error in getting data for Application Information Page from excel. Exception:"+e.getMessage());
			 Selenium.failTest("Application Information Page functionality failed.");
			 
		}
		
	}
	
	
	//----------------------------------------------------------------
	//Function to fill 'Address Information Page'
	//----------------------------------------------------------------
	public static  void  fillAddressInformation (Map<String, String> TestDataDictionary) //
	{
		try{	
		
			objAddressInfo.setMailingAddress(TestDataDictionary.get("MailingAddress"));
			objAddressInfo.setStreetAddress(TestDataDictionary.get("Street"));
			objAddressInfo.setMyZip(TestDataDictionary.get("MyZip"));
			
		 if(objAddressInfo.getStreetAddress() != null && !objAddressInfo.getStreetAddress().isEmpty())
					Selenium.type("Street Address", PageOpportunities.AddressInformation.TEXT_STREETADDRESS, objAddressInfo.getStreetAddress());
				if(objAddressInfo.getMailingAddress() != null && !objAddressInfo.getMailingAddress().isEmpty()){
					Selenium.selectCheckBox("Different Mailing Address",PageOpportunities.AddressInformation.RADION_MAILINGPERMAMNENTADDRESS,1 );
					Selenium.type("Mailing Address", PageOpportunities.AddressInformation.TEXT_MAILINGSTREETADDRESS, objAddressInfo.getMailingAddress());
					Selenium.type("Zip code", PageOpportunities.AddressInformation.TEXT_MAILINGZIP, objAddressInfo.getMyZip());
					
				}
				else
					Selenium.selectCheckBox("Different Mailing Address",PageOpportunities.AddressInformation.RADION_MAILINGPERMAMNENTADDRESS,0 );
					
				Reporter.log("Application Information Page is filled successfully.");
				Selenium.passTest("Application Information Page functionality passed.");
			
			
			if(Selenium.isElementPresent(PageOpportunities.AddressInformation.TEXT_STREETADDRESS))
			{
				Reporter.log("Street Address field is displayed successfully.");
				Selenium.passTest("Street Address field is displayed passed.");
			}
			else
			{
				Selenium.failTest("Street Address field is not displayed on Application Information page: failed.");
				 Log.error("Street Address field is not displayed: failed.");
				 Reporter.log("Street Address field is not displayed: failed..");
			}
	
	if(Selenium.isElementPresent(PageOpportunities.AddressInformation.TEXT_CITY))
			{
				Reporter.log("City field is displayed successfully.");
				Selenium.passTest("City field is displayed passed.");
			}
			else
			{
				Selenium.failTest("City field is not displayed on Application Information page: failed.");
				 Log.error("City field is not displayed: failed.");
				 Reporter.log("City field is not displayed: failed..");
			}
	if(Selenium.isElementPresent(PageOpportunities.AddressInformation.TEXT_STATE))
			{
				Reporter.log("STATE field is displayed successfully.");
				Selenium.passTest("STATE field is displayed passed.");
			}
			else
			{
				Selenium.failTest("STATE field is not displayed on Application Information page: failed.");
				 Log.error("STATE field is not displayed: failed.");
				 Reporter.log("STATE field is not displayed: failed..");
			}
	if(Selenium.isElementPresent(PageOpportunities.AddressInformation.TEXT_COUNTRY))
			{
				Reporter.log("COUNTRY field is displayed successfully.");
				Selenium.passTest("COUNTRY field is displayed passed.");
			}
			else
			{
				Selenium.failTest("COUNTRY field is not displayed on Application Information page: failed.");
				 Log.error("COUNTRY field is not displayed: failed.");
				 Reporter.log("COUNTRY field is not displayed: failed..");
			}
	
	
		Selenium.clickButtons("Next button", PageSFDC.BUTTON_NEXT);
		if(Selenium.isElementPresent("Medicare claim number", PageOpportunities.MedicareInformationNumber.TEXT_MEDICARE_CLAIM_NUMBER))
		{
			Reporter.log("User is navigated to Medicare Information page successfully.");
			Selenium.passTest("User is navigated to Medicare Information page successfully.");
		}
		else
		{
			Reporter.log("User is not navigated to Medicare Information page:Failed");
			Selenium.failTest("User is not navigated to Medicare Information page:Failed");
		}
	
	
	
	}
		catch(Exception e)
		{
			 Log.error("Error in getting data for Application Information Page from excel. Exception:"+e.getMessage());
			 Selenium.failTest("Application Information Page functionality failed.");
			 
		}
		
	}
	
	
}

