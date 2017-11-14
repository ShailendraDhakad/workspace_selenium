
package appModules;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import pageObjects.PageAccounts;
import pageObjects.PageFullfilment;
import pageObjects.PageLeads;
import pageObjects.PageOpportunities;
import pageObjects.PageSFDC;
import pageObjects.PageLeads.NewLead;
import testData.AccountDetails;
import testData.FulFillmentDetails;
import testData.FulfillmentKit;
import utility.Constant;
import utility.Log;
import utility.Selenium;
import utility.XMLUtil;

public class SFDCFullfillment {
	
	public static FulFillmentDetails objFulfillmentdata = new FulFillmentDetails();
	public static FulfillmentKit objFulfillmentKitdata = new FulfillmentKit();
	
	
	// Verify is the fulfillment is generated successfully
	public static void NewFullfillment_validation()  {
		//Refresh the page
		Selenium.sleep(9000);
		Selenium.driver.navigate().refresh();
		Selenium.sleep(3000);
		if (Selenium.isElementPresent(PageOpportunities.DataFullfillment.TEXT_FULLFILLMENT_STATUS)) {
			
			Selenium.ValidateObjectAndText("Fulfillment Record Status",PageOpportunities.DataFullfillment.TEXT_FULLFILLMENT_STATUS, "Request sent to CMAPS", true);
			Selenium.ValidateObjectAndText("Opportunity Status",PageOpportunities.Details.Element_Status, "Quote", true);
			
			Reporter.log("New Fullfillment is created is Sucessfully");
			Log.info("New Fullfillment is created is Sucessfully");
			Selenium.passTest("New Fullfillment is created is Sucessfully");
		}

		else {
			Reporter.log("New Fullfillment is not created");
			Selenium.failTest("New Fullfillment is not created");
			Log.error("New Fullfillment is not created");
		}

	}
	
	
	public static String quote_Number() {
		final String Quote_Number = Selenium.getWebElement(PageOpportunities.DataFullfillment.TEXT_QUOTE_NUMBER, "Quote Number").getText();

		return Quote_Number;
	}
	
	public static String fullfillment_Number() {
		String sLocator=PageOpportunities.DataFullfillment.ELE_FULLFILLMENTTABLE_HEADER;
		int TableCount=Selenium.getWebElements(sLocator, "Fulfillment Table Header Name").size();
		int iloop=0;
		for (iloop=0; iloop<TableCount; TableCount++){
			if (Selenium.getWebElements(sLocator, "Fulfillment Table Header Name").get(iloop).getText().contains("Fulfillment")){
				break;
			}
		}
		
		
//		Selenium.waitForElement(Selenium.driver, PageOpportunities.DataFullfillment.TEXT_FULLFILLMENT_NUMBER);
		final String Fullfillment_Number = Selenium.getWebElements(PageOpportunities.DataFullfillment.ELE_FULLFILLMENTTABLE_NUMBER, "Fulfillment Number").get(iloop).getText();

		return Fullfillment_Number;
	}
	
	public static String fullfillment_Status() {
		String sLocator=PageOpportunities.DataFullfillment.ELE_FULLFILLMENTTABLE_HEADER;
		int TableCount=Selenium.getWebElements(sLocator, "Fulfillment Table Header Name").size();
		int iloop=0;
		for (iloop=0; iloop<TableCount; TableCount++){
			if (Selenium.getWebElements(sLocator, "Fulfillment Table Header Name").get(iloop).getText().contains("Fulfillment")){
				break;
			}
		}
		final String Fullfillment_Status = Selenium.getWebElements(PageOpportunities.DataFullfillment.ELE_FULLFILLMENTTABLE_STATUS, "Fulfillment Status").get(iloop).getText();

		return Fullfillment_Status;
	}

	public static String search_Fulfillment(String fullfillment_Number) {
		String FullfillmentStatus="";
		
		if (Selenium.isElementPresent(PageFullfilment.TEXT_FULLFILLMENT_SERACH_INPUT)) {
			// Enter Fulfillment number
			Selenium.click("Search input fieled for Fulfillment number",PageFullfilment.TEXT_FULLFILLMENT_SERACH_INPUT);
			Selenium.type("Enter the Fullfillment Created by the user", PageFullfilment.TEXT_FULLFILLMENT_SERACH_INPUT,fullfillment_Number, true);
			Selenium.click("Search Button", PageFullfilment.BUTTON_FULLFILLMENT_SEARCH);
			Selenium.sleep(2000);
			Selenium.ValidateObjectAndText("Fullffillment Search result check",PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_SERACH_CHECK, fullfillment_Number, true);
			Selenium.click("Fulfillment ID Link", PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_REQUEST_CREATED);

			if (Selenium.isElementPresent("Edit",PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT)) {
				
				Reporter.log("Fullfillment ID found in search Results Sucessfully");
				Selenium.passTest("Fullfillment ID found in search Results  Sucessfully");
				
				//Get Fullfillment Request status
				FullfillmentStatus=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Fullfillment_Status", 2, 2);
	
			}

			else {
				Reporter.log("Fullfillment ID  NOT found in search Results.");
				Selenium.failTest("Fullfillment ID  NOT found in search Results.");
			}
		}

		else {
			Reporter.log("Fullfillment page is not loaded");
			Selenium.failTest("Fullfillment page is not loaded");
			Log.error("Fullfillment page is not loaded");
		}
		return FullfillmentStatus; 
	}

	public static void editFulFillmentRequest_Option(String StatusOption) {
		//objFulfillmentdata.setFulfillment_Reason(TestDataDictionary.get("FulfillmentReason"));
		String Fullfillment_Status="";
		
		if (Selenium.isElementPresent("Edit Button", PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT)) {
			
			Reporter.log("Edit Button is present on Fullmment Detail Page.");
			Log.info("Edit Button is present on Fullmment Detail Page.");
			
			Selenium.click("Edit Button", PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT);
			
			Selenium.waitForElement(Selenium.driver, PageFullfilment.Fullfillment_Edit_options.SELECT_FULLFILMENT_STATUS);
			Selenium.select("Fulfillment Status",PageFullfilment.Fullfillment_Edit_options.SELECT_FULLFILMENT_STATUS, StatusOption);
			
			//Reason
			Selenium.type("FullFillment Reason", PageFullfilment.Fullfillment_Edit_options.TEXT_FULLFILMENT_REASON, "Test Reason");
			Selenium.click("Submit",PageFullfilment.Fullfillment_Edit_options.BUTTON_SUBMIT);
			
			//Validate CMAPS status
			Fullfillment_Status=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Fullfillment_Status", 2, 2);
			
			if (Fullfillment_Status.equalsIgnoreCase(StatusOption)){
				Reporter.log("CMAPS status is Changed to :" + Fullfillment_Status);
				Selenium.passTest("CMAPS status is Changed to :" + Fullfillment_Status);
			}
			else{
				Reporter.log("CMAPS status is Not Changed to on Hold");
				Selenium.passTest("CMAPS status is Not Changed to on Hold");	
			}
			
			Reporter.log("Fullfillment Status is changed sucessfully");
			Log.info("Fullfillment Status is changed sucessfully");

		}

		else {
			Reporter.log("Edit Button is not Present on Fulfillment Information page");
			Log.error("Edit Button is not Present on Fulfillment Information page");
		}

	}

		


	public static String selectAllFulfillments() {
		
		String RequestID="";
		
		Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.CHECKBOX_ALL);
		
		if (Selenium.isElementPresent("Checkbox All", PageFullfilment.FullfillmentRequests.CHECKBOX_ALL)) {
			RequestID= Selenium.getWebElement(PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_FIRST_REQUEST_ID, "Resquest ID").getText();
			Selenium.click("Select All", PageFullfilment.FullfillmentRequests.CHECKBOX_ALL);
			Reporter.log("Select All Buttont is Clicked Sucessfully");
			Log.info("Select All Buttont is Clicked Sucessfully");
			
		} else {
			Reporter.log("Fullfillment page is not loaded");
			Selenium.failTest("Fullfillment page is not loaded");
			
		}
		
		return RequestID;
	}
	
	
	public static void changeFulfillmentStatusforAll(Map<String, String> TestDataDictionary,String FulfillmentID) {
	try{
		objFulfillmentdata.setFulfillmentStatus(TestDataDictionary.get("FulfillmentRequestStatus"));
		
		
		Selenium.doubleClick("Request Status in Table", PageFullfilment.FullfillmentRequests.ELE_MEDICARE_FULLFILLMENT_FIRST_REQUEST_STATUS);
		
		//Change Fullfillment edit status to CMAPS Completed
		Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.ELE_MEDICARE_FULLFILLMENT_FIRST_REQUEST_STATUS);
		Selenium.select("Fulfillment Status",PageFullfilment.FullfillmentRequests.SELECT_FULLFILLMENT_EDIT_STATUS, objFulfillmentdata.getFulfillmentStatus());
		Selenium.selectCheckBox("Single or Mass Apply Radio", PageFullfilment.FullfillmentRequests.RADIO_MASS_APPLY, 1);
		Selenium.click("Save", PageFullfilment.FullfillmentRequests.BUTTON_SAVE);
		
		//CLICK REFRESH
		Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.BUTTON_REFRESH);
		Selenium.click("Refresh", PageFullfilment.FullfillmentRequests.BUTTON_REFRESH);
		Selenium.sleep(1000);
		Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_FIRST_REQUEST_ID);
		if (!Selenium.getWebElement(PageFullfilment.FullfillmentRequests.ELE_FULLFILLMENT_FIRST_REQUEST_ID, "Fulfillment ID").getText().equalsIgnoreCase(FulfillmentID)){
			Reporter.log("Fulfillment Sttaus is successfully changed to :" + objFulfillmentdata.getFulfillmentStatus());
			Selenium.passTest("Fulfillment Sttaus is successfully changed to :" + objFulfillmentdata.getFulfillmentStatus());
		}
		else{
			Reporter.log("Fulfillment Sttaus is Not changed :" + objFulfillmentdata.getFulfillmentStatus());
			Selenium.failTest("Fulfillment Sttaus is Not changed :" + objFulfillmentdata.getFulfillmentStatus());
		}
	}
	catch(Exception e){
		Log.error("Fulfillment Sttaus is Not changed. Error is :" +e );
		Selenium.failTest("Fulfillment Sttaus is Not changed to CMAPS Complted.");
	}
	
	}
	
	// Select the Fulfillment request operation
	public static void editFulFillmentRequest_Option(Map<String, String> TestDataDictionary) {
		
		objFulfillmentdata.setFulfillmentStatus(TestDataDictionary.get("FulfillmentRequestStatus"));
		
		//Click Edit Button
		if (Selenium.isElementPresent("Edit", PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT)) {
			Selenium.click("Edit Button", PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT);
			Reporter.log("Edit Buttont is Clicked Sucessfully");
			Log.info("Edit Buttont is Clicked Sucessfully");
			
		} else {
			Reporter.log("Fullfillment page is not loaded");
			Selenium.failTest("Fullfillment page is not loaded");
			Log.error("Fullfillment page is not loaded");
		}
		
		Selenium.waitForElement(Selenium.driver, PageFullfilment.Fullfillment_Edit_options.SELECT_FULLFILMENT_STATUS);
		if (Selenium.isElementPresent("Fullfillment Status Edit Screen",PageFullfilment.Fullfillment_Edit_options.BUTTON_SUBMIT)) {

			// Fulfillment Status: Completed
			
			if (objFulfillmentdata.getFulfillmentStatus() != null) {
					
				Selenium.waitForElement(Selenium.driver, PageFullfilment.Fullfillment_Edit_options.SELECT_FULLFILMENT_STATUS);
				Selenium.select("Status",PageFullfilment.Fullfillment_Edit_options.SELECT_FULLFILMENT_STATUS,objFulfillmentdata.getFulfillmentStatus());
					
										
				//Reason
				Selenium.type("FullFillment Reason", PageFullfilment.Fullfillment_Edit_options.TEXT_FULLFILMENT_REASON, "Test Reason");
				Selenium.click("Submit",PageFullfilment.Fullfillment_Edit_options.BUTTON_SUBMIT);
				
				Reporter.log("Fullfillment Status is updated Sucessfully: " + objFulfillmentdata.getFulfillmentStatus());
				Selenium.passTest("Fullfillment Status is updated Sucessfully: " + objFulfillmentdata.getFulfillmentStatus());
			}
			
		}

		else {
			Reporter.log("Fullfillment Status is not updated");
			Selenium.failTest("Fullfillment Status is not updated");
			Log.error("Fullfillment Status is not updated");
		}
	}

	//fulfillmentSearchCriteria
	public static void selectFulfillmentView(Map<String, String> TestDataDictionary){
		objFulfillmentdata.setFulfillmentRequestType(TestDataDictionary.get("FulfillmentRequestType"));
		String strOption=objFulfillmentdata.getFulfillmentRequestType();
		
		//Click Fulfillment Request Tab
		Selenium.waitForElement(Selenium.driver, PageFullfilment.ELE_FULLFILLMENT_TAB);
		Selenium.click("Fullfillment Request Tab", PageFullfilment.ELE_FULLFILLMENT_TAB);
		
		
		Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.SELECT_FULLFILLMENT_VIEW);		
		if (strOption== null)
			Selenium.select("All Fulfillment Type", PageFullfilment.FullfillmentRequests.SELECT_FULLFILLMENT_VIEW, "All");
		else
			Selenium.select("Fulfillment Type", PageFullfilment.FullfillmentRequests.SELECT_FULLFILLMENT_VIEW, strOption);
		

//		Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.BUTTON_GO);
		if (Selenium.isElementPresent(PageFullfilment.FullfillmentRequests.BUTTON_GO)){
			Selenium.click("Go", PageFullfilment.FullfillmentRequests.BUTTON_GO);
			Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.BUTTON_CHANGE_OWNER);
		}
			
		
		if (Selenium.isElementPresent("Chnage Owner Button", PageFullfilment.FullfillmentRequests.BUTTON_CHANGE_OWNER)) {
			
			Reporter.log("Fullfillment Request Search table is Visible");
			Selenium.passTest("Fullfillment Request Search table is Visible");
		} 
		else {
			Reporter.log("Fullfillment Request Search table is not Visible");
			Selenium.failTest("Fullfillment Request Search table is not Visible");
			
		}
	}
	
	public  static String getRowNumFullfillmentRequestID(String FulfillmentRequestID){
		int irow;
		int TableRows=Selenium.getWebElements(PageFullfilment.FullfillmentRequests.TABLE_FULLFILLMENT_REQUEST, "Fulfillment Request Id Table").size();
		
		
		if (!FulfillmentRequestID.equalsIgnoreCase("Dynamic")){
			for (irow=1; irow<= TableRows; irow++){
				String RequestID=Selenium.getTableCellData(PageFullfilment.FullfillmentRequests.TABLE_DYNAMIC_FULLFILLMENT_REQUEST.replace("index", String.valueOf(irow)), "Fulfillment Request ID Table", 1, 4);
				if (FulfillmentRequestID.equalsIgnoreCase(RequestID)){
					Reporter.log("Fulfillment Request ID found at Row Number." + String.valueOf(irow));
					Selenium.passTest("Fulfillment Request ID found at Row Number." + String.valueOf(irow));
					break;
				}
				
			}
			
			if (irow>TableRows){
				Reporter.log("Fulfillment Request ID Not found on Request Table");
				Selenium.failTest("Fulfillment Request ID Not found on Request Table");
			}
		}
		else
			irow=1;
		return String.valueOf(irow);
		
	}
	
	//Validate fulfill ment status from the excel sheet and UI at edit Fillment Request page
	public static void validateFulfillmentStatus(Map<String, String> TestDataDictionary, String ActualStatus){
		objFulfillmentdata.setFulfillmentStatus(TestDataDictionary.get("FulfillmentRequestStatus"));
		
		if (objFulfillmentdata.getFulfillmentStatus() != null){
			if (objFulfillmentdata.getFulfillmentStatus().equalsIgnoreCase(ActualStatus)){
				Reporter.log("Fullfillment Status is validated Sucessfully: " + objFulfillmentdata.getFulfillmentStatus());
				Selenium.passTest("Fullfillment Status is validated Sucessfully: " + objFulfillmentdata.getFulfillmentStatus());
			}
			else{
				Reporter.log("Fullfillment Status validation failed. Actual Status: " + ActualStatus + "Expected Status: " + objFulfillmentdata.getFulfillmentStatus());
				Selenium.failTest("Fullfillment Status validation failed. Actual Status: " + ActualStatus + "Expected Status: " + objFulfillmentdata.getFulfillmentStatus());
			}
		}
	}
	
	public static void openFulfillment( String Fulfillment_ID){
		
		String FullfillmentStatus;
		if (Fulfillment_ID.length()==0){
			
			//Open the First entry in fulfillment view table
			Selenium.waitForElement(Selenium.driver, PageFullfilment.FullfillmentRequests.LINK_FULLFILLMENT_FIRST_REQUEST_ID);
			Selenium.click("Fulfillment ID", PageFullfilment.FullfillmentRequests.LINK_FULLFILLMENT_FIRST_REQUEST_ID);
			
			if (Selenium.isElementPresent("Edit",PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT)) {
				
				Reporter.log("Fullfillment ID opened Sucessfully");
				Selenium.passTest("Fullfillment ID opened Sucessfully");
				
				//Get Fullfillment Request status
				FullfillmentStatus=Selenium.getTableCellData(PageFullfilment.FullfillmentDetail.TABLE_FULLFILMENT_DETAIL, "Fullfillment_Status", 2, 2);
	
			}

			else {
				Reporter.log("Fullfillment ID  NOT opened .");
				Selenium.failTest("Fullfillment ID  NOT opened");
			}

			
		}
		
		else{
			//open the fulfillment on basis of given fulfillment ID
			search_Fulfillment(Fulfillment_ID);
		}
	}
	
	public static void validateNoEditButtonFulfillmentDetailPage(){
		Selenium.waitForElement(Selenium.driver, PageOpportunities.DataFullfillment.LINK_FULLFILLMENT_EDIT);
		Selenium.click("Edit", PageOpportunities.DataFullfillment.LINK_FULLFILLMENT_EDIT);
		if (Selenium.isElementPresent("Edit Button on Fulfillment Detail Page", PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT)){
			Reporter.log("Fullfillment EDIT Button not found on Fulfillment detail Page: Passed");
			Selenium.passTest("Fullfillment EDIT Button not found on Fulfillment detail Page: Passed");
		}
		else
		{
			Reporter.log("Fullfillment EDIT Button found on Fulfillment detail Page: Failed");
			Selenium.passTest("Fullfillment EDIT Button found on Fulfillment detail Page: Failed");
		}
	}
	
	public static void validateKitType_Lead(){
		try{
			//Validate Kit Type List
			String Options="Miscellaneous Pieces;Prospect Medicare Supplement;Celebrating 65;Prospect Medicare Advantage Stride;Broker Medicare Supplement";
			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, Options))
				Selenium.passTest("Kit Type List validation passed. List Items: " + Options);
			else
				Selenium.passTest("Kit Type List validation failed.");
			
			//Validate Kit Name when Kit Type is selected as: Miscellaneous Pieces
			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Miscellaneous Pieces");
			Selenium.sleep(1000);
			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
			Options="2017 PROVIDER DIRECTORY(FULL);2017 PROVIDER DIRECTORY (ABRIDGED);2017 RX FORMULARY;2017 PROVIDER TEAR OFFS (WG INVENTORY TRACKING);2017 MASS_MAINE SLIM JIM(Y0098_17067)MP GRID(Y0098_17009);2017 ME MED ADV COMP GRID(Y0098_17008);2017 MA MED ADV COMP GRID(Y0098_17007);2017 NH MED ADV COMP GRID(Y0098_17009);2017 NEW HAMPSHIRE SLIM JIM(Y0098_17068)";
			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Miscellaneous Pieces. List Items: " + Options);
			else
				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Miscellaneous Pieces.");
			
			
			//Validate Kit Name when Kit Type is selected as: Prospect Medicare Supplement
			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Prospect Medicare Supplement");
			Selenium.sleep(1000);
			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
			Options="2017 CHOOSING MEDIGAP POLICY;2017 NH_MSUP_KIT;2017 MASS_MSUP_KIT;2017 ME_MSUP_KIT;2018 MASS_MSUP_KIT;2018 ME_MSUP_KIT;2018 NH_MSUP_KIT";
			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Prospect Medicare Supplement. List Items: " + Options);
			else
				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Prospect Medicare Supplement.");
			
			//Validate Kit Name when Kit Type is selected as: Celebrating 65
			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Celebrating 65");
			Selenium.sleep(1000);
			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
			Options="2017 C65 BROCHURE NH_ME MA;2017 C65 BROCHURE MA;2018 C65 BROCHURE MA;2018 C65 BROCHURE NH_ME";
			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Celebrating 65. List Items: " + Options);
			else
				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Celebrating 65.");
			
			//Validate Kit Name when Kit Type is selected as: Prospect Medicare Advantage Stride
			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Prospect Medicare Advantage Stride");
			Selenium.sleep(1000);
			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
			Options="2017 ME_MADV_KIT;2017 MASS MADV_KIT;2018 MASS MADV_KIT;2018 ME_MADV_KIT;2018 NH_MADV_KIT;2017 NH_MADV_KIT";
			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Prospect Medicare Advantage Stride. List Items: " + Options);
			else
				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Prospect Medicare Advantage Stride.");
			
			//Validate Kit Name when Kit Type is selected as: Broker Medicare Supplement
			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Broker Medicare Supplement");
			Selenium.sleep(1000);
			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
			Options="2018 CHOOSING MEDIGAP POLICY";
			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Broker Medicare Supplement. List Items: " + Options);
			else
				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Broker Medicare Supplement.");
		}
		catch(Exception ex){
			Selenium.failTest("Kit Type List validation failed. Error: " + ex);
		}
	}
	
	
	public static void validateKitType_Broker(){
		try{
			//Validate Kit Type List
			String Options="Miscellaneous Pieces;Prospect Medicare Supplement;Celebrating 65;Prospect Medicare Advantage Stride;Broker Medicare Supplement";
			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, Options))
				Selenium.passTest("Kit Type List validation passed. List Items: " + Options);
			else
				Selenium.passTest("Kit Type List validation failed.");
			
			//Validate Kit Name when Kit Type is selected as: Miscellaneous Pieces
			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Miscellaneous Pieces");
			Selenium.sleep(1000);
			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
			Options="2017 PROVIDER DIRECTORY (ABRIDGED);2017 PROVIDER DIRECTORY(FULL);2017 PROVIDER TEAR OFFS (WG INVENTORY TRACKING);2017 RX FORMULARY;2017 MASS_MAINE SLIM JIM(Y0098_17067)MP GRID(Y0098_17009);2017 ME MED ADV COMP GRID(Y0098_17008);2017 MA MED ADV COMP GRID(Y0098_17007);2017 NH MED ADV COMP GRID(Y0098_17009);2017 NEW HAMPSHIRE SLIM JIM(Y0098_17068)";
			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Miscellaneous Pieces. List Items: " + Options);
			else
				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Miscellaneous Pieces.");
			
			
			//Validate Kit Name when Kit Type is selected as: Prospect Medicare Supplement
//			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Prospect Medicare Supplement");
//			Selenium.sleep(1000);
//			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
//			Options="2017 CHOOSING MEDIGAP POLICY;2017 NH_MSUP_KIT;2017 MASS_MSUP_KIT;2017 ME_MSUP_KIT;2018 MASS_MSUP_KIT;2018 ME_MSUP_KIT;2018 NH_MSUP_KIT";
//			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
//				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Prospect Medicare Supplement. List Items: " + Options);
//			else
//				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Prospect Medicare Supplement.");
//			
//			//Validate Kit Name when Kit Type is selected as: Celebrating 65
//			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Celebrating 65");
//			Selenium.sleep(1000);
//			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
//			Options="2017 C65 BROCHURE NH_ME MA;2017 C65 BROCHURE MA;2018 C65 BROCHURE MA;2018 C65 BROCHURE NH_ME";
//			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
//				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Celebrating 65. List Items: " + Options);
//			else
//				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Celebrating 65.");
//			
//			//Validate Kit Name when Kit Type is selected as: Prospect Medicare Advantage Stride
//			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Prospect Medicare Advantage Stride");
//			Selenium.sleep(1000);
//			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
//			Options="2017 ME_MADV_KIT;2017 MASS MADV_KIT;2018 MASS MADV_KIT;2018 ME_MADV_KIT;2018 NH_MADV_KIT;2017 NH_MADV_KIT";
//			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
//				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Prospect Medicare Advantage Stride. List Items: " + Options);
//			else
//				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Prospect Medicare Advantage Stride.");
			
			//Validate Kit Name when Kit Type is selected as: Broker Medicare Supplement
			Selenium.select("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "Broker Medicare Supplement");
			Selenium.sleep(1000);
			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1);
			Options="2018 CHOOSING MEDIGAP POLICY";
			if (Selenium.validateAllItemsWebList("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, Options))
				Selenium.passTest("Kit Type List validation passed when Kit Type is selected as: Broker Medicare Supplement. List Items: " + Options);
			else
				Selenium.failTest("Kit Type List validation failed when Kit Type is selected as: Broker Medicare Supplement.");
		}
		catch(Exception ex){
			Selenium.failTest("Kit Type List validation failed. Error: " + ex);
		}
	}
	
	
	public static void validateFulfillmentRequestPage(){
		if (Selenium.isElementPresent("Submit",PageSFDC.FulfillmentKit.BUTTON_SUBMIT)) {
			Reporter.log("New Fulfillment Request Edit page open successfully.");
			Selenium.passTest("New Fulfillment Request Edit page open successfully.");	
			
			Selenium.ValidateObjectAndText("Lead/Member Name", PageSFDC.FulfillmentKit.ELE_FULFILLMENT_MEMBERNAME, "", false,true);
			Selenium.ValidateObjectAndText("Street Address", PageSFDC.FulfillmentKit.TEXT_FULFILLMENT_STREET, "", false,true);
			Selenium.ValidateObjectAndText("Zip Code", PageSFDC.FulfillmentKit.TEXT_FULFILLMENT_ZIP, "", false,true);
			Selenium.ValidateObjectAndText("Kit Type WebList", PageSFDC.FulfillmentKit.SELECT_KIT_TYPE_1, "", false,true);
			Selenium.ValidateObjectAndText("Kit Name WebList", PageSFDC.FulfillmentKit.SELECT_KIT_NAME_1, "", false,true);
			Selenium.ValidateObjectAndText("Quantity", PageSFDC.FulfillmentKit.TEXT_KIT_QUANTITY_1, "", false,true);
			Selenium.ValidateObjectAndText("Submit Button", PageSFDC.FulfillmentKit.BUTTON_SUBMIT, "", false,true);
			Selenium.ValidateObjectAndText("Cancel Button", PageSFDC.FulfillmentKit.BUTTON_CANCEL, "", false,true);
			Selenium.ValidateObjectAndText("Delete Button", PageSFDC.FulfillmentKit.BUTTON_DELETE_1, "", false,true);
			Selenium.ValidateObjectAndText("Requester Comments", PageSFDC.FulfillmentKit.TEXT_KIT_COMMENT, "", false,true);
		}

		else {
			Reporter.log("New Fulfillment Request Edit page did not open.");
			Selenium.failTest("New Fulfillment Request Edit page did not open.");
		}
	}
	
	public static void click_CancelFulfillmentKit(){
		Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.BUTTON_CANCEL);
		if (Selenium.isElementPresent("Cancel Button", PageSFDC.FulfillmentKit.BUTTON_CANCEL)){
			Selenium.click("Cancel Button",PageSFDC.FulfillmentKit.BUTTON_CANCEL);
			Selenium.waitForElement(Selenium.driver, PageOpportunities.BUTTON_NEWFULFILLMENTREQST);
			if (Selenium.isElementPresent(PageOpportunities.BUTTON_NEWFULFILLMENTREQST)){
				Reporter.log("Cancel Button functionality is working properly on FulfillmentKIt request Page.");
				Selenium.passTest("Cancel Button functionality is working properly on FulfillmentKIt request Page.");
			}
			else{
				Reporter.log("Cancel Button functionality failed on FulfillmentKIt request Page.");
				Selenium.failTest("Cancel Button functionality failed on FulfillmentKIt request Page.");
			}
		}
	}
	
	
	
	//Update Street Address for Kit Fulfillment
	public static void updateLeadStreet_FulfillmentKit(){
	Selenium.waitForElement(Selenium.driver, PageOpportunities.BUTTON_NEWFULFILLMENTREQST);
	if (Selenium.isElementPresent(PageOpportunities.BUTTON_NEWFULFILLMENTREQST)){
		
		SFDCLeads.objLeadData.setStreet("21, ABC");
		
		Selenium.clickButtons("Edit Button", PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT);
		Selenium.waitForElement(Selenium.driver, NewLead.TEXTAREASTREET);
		Selenium.type(NewLead.TEXTAREASTREET, SFDCLeads.objLeadData.getStreet());
		
		SFDC.navigateToOpportunities();
		Reporter.log("Sucessfully Updated to Lead's Address.");
		Selenium.passTest("Sucessfully Updated to Lead's Address.");
	}
	else
	{
		Reporter.log("Edit Button is not present on Opportunity Page.");
		Selenium.passTest("Edit Button is not present on Opportunity Page.");
	}
	}
	
	public static void updateAccountMailAddress_FulfillmentKit(Map<String, String> TestDataDictionary){
		Selenium.waitForElement(Selenium.driver, PageOpportunities.BUTTON_NEWFULFILLMENTREQST);
		if (Selenium.isElementPresent(PageOpportunities.BUTTON_NEWFULFILLMENTREQST)){
			AccountDetails.ObjAddressInfo.setMailingAddress(TestDataDictionary.get("AccountMailAddress"));
						
			Selenium.clickButtons("Edit Button", PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT);
			Selenium.waitForElement(Selenium.driver, PageAccounts.TEXT_ACCOUNT_MAILADDRESS);
			Selenium.type(PageAccounts.TEXT_ACCOUNT_MAILADDRESS, AccountDetails.ObjAddressInfo.getMailingAddress());
			
			SFDC.navigateToOpportunities();
			Reporter.log("Sucessfully Updated to Lead's Address.");
			Selenium.passTest("Sucessfully Updated to Lead's Address.");
		}
		else
		{
			Reporter.log("Edit Button is not present on Opportunity Page.");
			Selenium.passTest("Edit Button is not present on Opportunity Page.");
		}
	}
	
	public static void removeAccountMailAddress_FulfillmentKit(){
		Selenium.waitForElement(Selenium.driver, PageOpportunities.BUTTON_NEWFULFILLMENTREQST);
		if (Selenium.isElementPresent(PageOpportunities.BUTTON_NEWFULFILLMENTREQST)){
			AccountDetails.ObjAddressInfo.setMailingAddress("21, TestMail Address");
						
			Selenium.clickButtons("Edit Button", PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT);
			Selenium.waitForElement(Selenium.driver, PageAccounts.TEXT_ACCOUNT_MAILADDRESS);
			Selenium.type(PageAccounts.TEXT_ACCOUNT_MAILADDRESS, "");
			
			SFDC.navigateToOpportunities();
			Reporter.log("Sucessfully removed Account Mailing Address.");
			Selenium.passTest("Sucessfully removed Account Mailing Address.");
		}
		else
		{
			Reporter.log("Edit Button is not present on Opportunity Page.");
			Selenium.passTest("Edit Button is not present on Opportunity Page.");
		}
	}
	
	public static void updateAccountResidentialAddress_FulfillmentKit(Map<String, String> TestDataDictionary){
		Selenium.waitForElement(Selenium.driver, PageOpportunities.BUTTON_NEWFULFILLMENTREQST);
		if (Selenium.isElementPresent(PageOpportunities.BUTTON_NEWFULFILLMENTREQST)){
			AccountDetails.ObjAddressInfo.setStreetAddress(TestDataDictionary.get("AccountResidentialAdddress"));
						
			Selenium.clickButtons("Edit Button", PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT);
			Selenium.waitForElement(Selenium.driver, PageAccounts.TEXT_ACCOUNT_RESIDENTADDRESS);
			Selenium.type(PageAccounts.TEXT_ACCOUNT_RESIDENTADDRESS,AccountDetails.ObjAddressInfo.getStreetAddress() );
			
			SFDC.navigateToOpportunities();
			Reporter.log("Sucessfully removed Account Mailing Address.");
			Selenium.passTest("Sucessfully removed Account Mailing Address.");
		}
		else
		{
			Reporter.log("Edit Button is not present on Opportunity Page.");
			Selenium.passTest("Edit Button is not present on Opportunity Page.");
		}
	}
	
	public static void  fill_FulfillmentKit(Map<String, String> TestDatadictionary){
		objFulfillmentKitdata.setKitType(TestDatadictionary.get("KitType"));
		objFulfillmentKitdata.setKitName(TestDatadictionary.get("KitName"));
		objFulfillmentKitdata.setKitQuantity(TestDatadictionary.get("KitQunatity"));
		String KitType=	objFulfillmentKitdata.getKitType();
		String KitName=objFulfillmentKitdata.getKitName();
		String KitQuantity=objFulfillmentKitdata.getKitQuantity();

		String[] ArrKitType = null;
		String[] ArrKitName= null;
		String[] ArrKitQuantity= null;
		
		try{
			//Store all the Kit Types into Array of String type
			if (!KitType.isEmpty() || KitType!=null){
				if (KitType.contains(";"))
					ArrKitType=KitType.split(";");
				else{
					ArrKitType=new String[1];
					ArrKitType[0]=KitType;
				}
			}
			
			//Store all the Kit Name into Array of String type
			if (!KitName.isEmpty() || KitName!=null){
				if (KitName.contains(";"))
					ArrKitName=KitName.split(";");
				else{
					ArrKitName=new String[1];
					ArrKitName[0]=KitName;
				}
					
			}
			//Store all the Kit Quantity into Array of String type
			if (!KitQuantity.isEmpty() || KitQuantity!=null){
				if (KitQuantity.contains(";"))
					ArrKitQuantity=KitQuantity.split(";");
				else{
					ArrKitQuantity=new String[1];
					ArrKitQuantity[0]=KitQuantity;
				}
					
			}
			
			String KitTypeLocator="";
			String KitNameLocator="";
			String KitQuantityLocator="";
			
			if(ArrKitType.length > 0)
			{
				//Selecting the the required plans
				for(int irow=0; irow<ArrKitType.length; irow++){
					KitTypeLocator=PageSFDC.FulfillmentKit.SELECT_KIT_TYPE.replace("RowIndex", utility.StringUtil.intToString(irow+1));
					KitNameLocator=PageSFDC.FulfillmentKit.SELECT_KIT_NAME.replace("RowIndex", utility.StringUtil.intToString(irow+1));
					KitQuantityLocator=PageSFDC.FulfillmentKit.TEXT_KIT_QUANTITY.replace("RowIndex", utility.StringUtil.intToString(irow+1));
					
					Selenium.select("Kit Type", KitTypeLocator, ArrKitType[irow]);
					Selenium.sleep(1000);
					Selenium.select("Kit Name", KitNameLocator, ArrKitName[irow]);
					Selenium.type("Kit Quantity", KitQuantityLocator, ArrKitQuantity[irow]);
				}
				Selenium.type("Requester Comment",PageSFDC.FulfillmentKit.TEXT_KIT_COMMENT, "This is My request");
				
				Reporter.log("New Fulfillment Request is filled successfully.");
				Selenium.passTest("New Fulfillment Request is filled successfully.");
				
				
			}
		}
		
		
		catch(Exception e){
			Reporter.log("New Fulfillment Request Not is filled. Error:" + e);
			Selenium.failTest("New Fulfillment Request Not is filled. Error:" + e);
		}
	}
	
	public static void  update_FulfillmentKit(Map<String, String> TestDatadictionary){
		objFulfillmentKitdata.setKitType(TestDatadictionary.get("EditKitType"));	
		objFulfillmentKitdata.setKitName(TestDatadictionary.get("EditKitName"));
		objFulfillmentKitdata.setKitQuantity(TestDatadictionary.get("EditKitQunatity"));
		
		String KitType=objFulfillmentKitdata.getKitType();
		String KitName=objFulfillmentKitdata.getKitName();
		String KitQuantity=objFulfillmentKitdata.getKitQuantity();
		
		Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.BUTTON_CANCELREQUEST);
		Selenium.clickButtons("Edit",PageFullfilment.Fullfillment_Edit_options.BUTTON_EDIT);

//		String[] ArrKitType=null;
//		String[] ArrKitName=null;
//		String[] ArrKitQuantity=null;
		
		try{
			//Store all the Kit Types into Array of String type
//			if (KitType.contains(";"))
//				ArrKitType=KitType.split(";");
//			else
//				ArrKitType[0]=KitType;
//			
//			//Store all the Kit Name into Array of String type
//			if (KitName.contains(";"))
//				ArrKitName=KitName.split(";");
//			else
//				ArrKitName[0]=KitName;
//			
//			//Store all the Kit Quantity into Array of String type
//			if (KitQuantity.contains(";"))
//				ArrKitQuantity=KitQuantity.split(";");
//			else
//				ArrKitQuantity[0]=KitQuantity;
			
			Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.SELECT_EDITKIT_TYPE);
			Selenium.select("Kit Type", PageSFDC.FulfillmentKit.SELECT_EDITKIT_TYPE, KitType);
			Selenium.sleep(1000);
			Selenium.select("Kit Name",  PageSFDC.FulfillmentKit.SELECT_EDITKIT_NAME, KitName);
			Selenium.type("Kit Quantity",  PageSFDC.FulfillmentKit.TEXT_EDITKIT_QYANTITY, KitQuantity);
			
			Selenium.type("Requester Comment",PageSFDC.FulfillmentKit.TEXT_KIT_COMMENT, "This is My Updated request");
				
				Reporter.log("Fulfillment Request is updated successfully.");
				Selenium.passTest("Fulfillment Request is updated successfully.");
				
		}
		
		
		catch(Exception e){
			Reporter.log("Fulfillment Request Not is updated. Error:" + e);
			Selenium.failTest("Fulfillment Request Not is updated. Error:" + e);
		}
	}
	
	public static void open_FulfillmentKitRequest(){
		String sLocator=PageOpportunities.DataFullfillment.ELE_FULLFILLMENTTABLE_HEADER;
		int TableCount=Selenium.getWebElements(sLocator, "Fulfillment Table Header Name").size();
		int iloop=0;
		for (iloop=0; iloop<TableCount; TableCount++){
			if (Selenium.getWebElements(sLocator, "Fulfillment Table Header Name").get(iloop).getText().contains("Fulfillment")){
				break;
			}
		}
		
		
//		Selenium.waitForElement(Selenium.driver, PageOpportunities.DataFullfillment.TEXT_FULLFILLMENT_NUMBER);
		try{
		Selenium.getWebElements(PageOpportunities.DataFullfillment.LINK_FULLFILLMENTTABLE_NUMBER, "Fulfillment Number").get(iloop).click();
		}
		catch(Exception e){
			JavascriptExecutor executor = (JavascriptExecutor)Selenium.driver;
			executor.executeScript("arguments[0].click()", Selenium.getWebElements(PageOpportunities.DataFullfillment.LINK_FULLFILLMENTTABLE_NUMBER, "Fulfillment Number").get(iloop));
			Log.info("Click event has been succeeded at " + "Fulfillment Number");
		}
	}
	public static void validateFulfillmentKitDetails(Map<String, String> TestDatadictionary,String Fulfillment_ID){
		 
		
		

//		return Fullfillment_Number;
//		Selenium.click("Fulfillment ID", PageOpportunities.DataFullfillment.LINK_FULLFILLMENT_NUMBER);
		 
		 Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.ELE_FULFILLMENTKIT_FULFILLMENT_ID);
		if (Selenium.isElementPresent(PageSFDC.FulfillmentKit.ELE_FULFILLMENTKIT_FULFILLMENT_ID)){
			Selenium.ValidateObjectAndText("Fulfillment ID",PageSFDC.FulfillmentKit.ELE_FULFILLMENTKIT_FULFILLMENT_ID , Fulfillment_ID, true);
			Selenium.ValidateObjectAndText("Lead Name",PageSFDC.FulfillmentKit.ELE_FULFILLMENTKIT_LEAD_NAME , "", false);
			Selenium.ValidateObjectAndText("Fulfillment Type",PageSFDC.FulfillmentKit.ELE_FULFILLMENTKIT_TYPE , "Kit", true);
			Selenium.ValidateObjectAndText("Status",PageSFDC.FulfillmentKit.ELE_FULFILLMENTKIT_STATUS , "Request Created", true);
			Selenium.ValidateObjectAndText("Comments",PageSFDC.FulfillmentKit.ELE_FULFILLMENTKIT_COMMENTS , "", false);
			
			String KitType=objFulfillmentKitdata.getKitType();
			String KitName=objFulfillmentKitdata.getKitName();
			String KitQuantity=objFulfillmentKitdata.getKitQuantity();
			if (KitType!=null){
				for (int irow=0;irow<Selenium.getTableRowCount(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Detail");irow++ ){
					if (KitType.contains(Selenium.getTableCellData(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Type", irow+1, 1))){
						Reporter.log("Kit Type : '" + Selenium.getTableCellData(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Type", irow+1, 1) + "is displayed under fulfillment section of lead.");
						Selenium.passTest("Kit Type : '" + Selenium.getTableCellData(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Type", irow+1, 1) + "is displayed under fulfillment section of lead.");	
					}
					else{
						Reporter.log("Kit Type : '" + KitType + " is not displayed.");
						Selenium.failTest("Kit Type : '" + KitType + " is not displayed.");
					}
					if (KitName.contains(Selenium.getTableCellData(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Detail Name", irow+1, 2))){
						Reporter.log("Kit Name : '" + Selenium.getTableCellData(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Detail Name", irow+1, 2) + "is displayed under fulfillment section of lead.");
						Selenium.passTest("Kit Name : '" + Selenium.getTableCellData(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Detail Name", irow+1, 2) + "is displayed under fulfillment section of lead.");	
					}
					else{
						Reporter.log("Kit Type : '" + KitName + "is not displayed.");
						Selenium.failTest("Kit Type : '" + KitName + "is not displayed.");
					}
					if (KitQuantity.contains(Selenium.getTableCellData(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Detail Quantity", irow+1, 3))){
						Reporter.log("Kit Quantity : '" + Selenium.getTableCellData(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Detail Quantity", irow+1, 3) + "is displayed under fulfillment section of lead.");
						Selenium.passTest("Kit Quantity : '" + Selenium.getTableCellData(PageSFDC.FulfillmentKit.TABLE_FULFILLMENT_KIT_DETAIL, "Fulfillment Kit Detail Quantity", irow+1, 3) + "is displayed under fulfillment section of lead.");	
					}
					else{
						Reporter.log("Kit Quantity : '" + KitQuantity + "is not displayed.");
						Selenium.failTest("Kit Quantity : '" + KitQuantity + "is not displayed.");
					}
				}
			}
		}
	 }
	
	public static void cancel_FulfillmentKitRequest(){
		Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.BUTTON_CANCELREQUEST);
		 if (Selenium.isElementPresent(PageSFDC.FulfillmentKit.BUTTON_CANCELREQUEST)){
			 Selenium.click("Cancel Request", PageSFDC.FulfillmentKit.BUTTON_CANCELREQUEST);
			 
			 Selenium.waitForElement(Selenium.driver, PageSFDC.FulfillmentKit.ELE_FULFILLMENTKIT_STATUS);
			 Selenium.ValidateObjectAndText("Status",PageSFDC.FulfillmentKit.ELE_FULFILLMENTKIT_STATUS , "Request Created", true);
		 }
		 else{
			 Selenium.failTest("Cancel Request Button is not present.");
		 }
	}
	
}