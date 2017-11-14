package appModules;

import java.sql.Driver;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import testData.PDFrepository_Fields;
import pageObjects.PageAdminTool;
import pageObjects.PageAdminTool.PagePDFRepository;
import utility.Log;
import utility.Selenium;

public class SBCpdfRepository {
	
	public static PDFrepository_Fields objPDFrepository_Fields= new PDFrepository_Fields();

	public static void searchSBCpdfbyFileName (Map<String,String> TestDataDictionary) {
		
		Selenium.click("Search_Box", PagePDFRepository.Search_Box);
		//fetching the file name from excel
		objPDFrepository_Fields.setfilename(TestDataDictionary.get("Filename"));
		String strOption=objPDFrepository_Fields.getfilename();
		Selenium.type(PageAdminTool.PagePDFRepository.Search_Box, strOption);
		Selenium.click("Go_Button", PageAdminTool.PagePDFRepository.Go_button);
		
		if (Selenium.isElementPresent("filelink",PageAdminTool.PagePDFRepository.FILELINK)) {
			
			Reporter.log(strOption+ "file is available in repository");
			Log.info(strOption+ "file is available in repository");
		}
		else {
			Reporter.log(strOption+ "file is not available in repository");
			Log.info(strOption+ "file is not available in repository");
		}
		
		
			/*//Click Fulfillment Request Tab
			Selenium.waitForElement(Selenium.driver, PageFullfilment.ELE_FULLFILLMENT_TAB);
			Selenium.click("Fullfillment Request Tab", PageFullfilment.ELE_FULLFILLMENT_TAB);*/
	}
	
}
