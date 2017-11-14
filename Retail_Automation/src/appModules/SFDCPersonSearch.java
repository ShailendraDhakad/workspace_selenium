package appModules;

import org.testng.Reporter;

import pageObjects.PagePersonSearch;
import pageObjects.PageLeads.LeadDeatils;
import utility.Log;
import utility.Selenium;

public class SFDCPersonSearch {

	public static void navigateToNewMedicareQuote()  {
		if (Selenium.isElementPresent("Medicare Enroll button", PagePersonSearch.BUTTON_QUESTION_INDVQUOTE))
		{
			Reporter.log("Button to create a new medicare advantage quote is displayed successfully.");
			Selenium.passTest("Button to create a new medicare advantage quote is displayed successfully.");
			Selenium.click("Medicare Enroll button", PagePersonSearch.BUTTON_QUESTION_INDVQUOTE);
			Selenium.sleep(5000);
		}
		else{
			Reporter.log("Button to create a new medicare advantage quote is not displayed : Failed");
			Selenium.failTest("Button to create a new medicare advantage quote is not displayed : Failed");
		}
	}
	
	public static String searchLead()  {
		String LocatorLink="";
		try{
			if (Selenium.isElementPresent("Search button", PagePersonSearch.BUTTON_SEARCH))
			{
				Reporter.log("Person Search Page displayed successfully.");
				Selenium.passTest("Person Search Page displayed successfully.");
				
				String FirstName=SFDCLeads.objLeadData.getFirstName();
				String LastName=SFDCLeads.objLeadData.getLastName();
				String DOB=SFDCLeads.objLeadData.getDateOfBirth();
				String LeadName=FirstName + " " + LastName;
				
				
				Selenium.type("First Name", PagePersonSearch.TEXT_SEARCH_FIRSTNAME,FirstName);
				Selenium.type("Last Name", PagePersonSearch.TEXT_SEARCH_LASTNAME,LastName);
				
				Selenium.click("Search button", PagePersonSearch.BUTTON_SEARCH);
				Selenium.sleep(5000);
								
				Selenium.waitForElement(Selenium.driver, PagePersonSearch.TABLE_LEAD);
				int TotalRows=Selenium.getTableRowCount(PagePersonSearch.TABLE_LEAD, "Lead Search table");
				
				
				for (int iRow=1;iRow<=TotalRows ;iRow++ ){
					String ItemLeadName=Selenium.getTableCellData(PagePersonSearch.TABLE_LEAD, "Lead Search table", iRow, 1);
					String ItemDOB=Selenium.getTableCellData(PagePersonSearch.TABLE_LEAD, "Lead Search table", iRow, 2);
					
					if ((ItemLeadName.equalsIgnoreCase(LeadName)) && (ItemDOB.equalsIgnoreCase(DOB))){
						LocatorLink=PagePersonSearch.TABLE_LEAD + "/tr["+ iRow + "]/td[1]/a";
						Reporter.log("Lead is found successfully in search .");
						Selenium.passTest("Lead is found successfully in search.");
						break;
					}
				}
				
			}
			else{
				Reporter.log("Button to Search is not displayed : Failed");
				Selenium.failTest("Button to Search is not displayed : Failed");
			}
			
			
		}
		catch(Exception e){
			Reporter.log("Search Lead Failed. :" +e );
			Selenium.failTest("Search Lead Failed. :" +e );
		}
		return LocatorLink;
		
		
		
	}
	
	public static void openLeadInfo(String LeadLocator)  {
		try{
			if (Selenium.isElementPresent("Lead Name Link", LeadLocator))
			{
				Reporter.log("Lead Name displayed successfully.");
				Selenium.passTest("Lead Name displayed successfully.");
				
				Selenium.click("Lead Name", LeadLocator);
										
				if (Selenium.isElementPresent(LeadDeatils.BUTTON_TOPCONVERTLEAD)){
					Reporter.log("Lead is Sucessfully and Opened.");
					Selenium.passTest("Lead is Sucessfully and Opened.");
				}
				else{
					Reporter.log("Lead info is not Opened correctly.");
					Selenium.failTest("Lead info is not Opened correctly.",true);
					
				}
			}
			else{
				Reporter.log("Lead Name Link is not displayed : Failed");
				Selenium.failTest("Lead Name Link is not displayed : Failed");
			}
		}
		catch(Exception e){
			Reporter.log("Open Lead Failed. :" +e );
			Selenium.failTest("Open Lead Failed. :" +e );
		}
	}
	
}
