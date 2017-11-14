package utility;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class AdvanceReporting {
	public static ExtentReports reports;
	public static ExtentHtmlReporter htmlReporter;
	private static ExtentTest test; 
	
	public static ExtentReports ExtentReport()
	{
		try 
		{
			reports=new ExtentReports();
//			String Projectpath=System.getProperty("user.dir");
			
			htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//extentreporter.html");
			reports.attachReporter(htmlReporter);
			
		
			htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("Detailed Automation Test Report");
	        htmlReporter.config().setReportName("My Own Report");
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	        // htmlReporter.config().setTheme(Theme.DARK);
			
	        //get name of current logged in User
			com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
			String Username=NTSystem.getName();
		
			reports.setSystemInfo("OS", "Windows");
			reports.setSystemInfo("Environment", "QA");
			reports.setSystemInfo("User Name", Username);
	         
			
		}
		catch (Exception exception) 
		{
			Log.error(exception.toString());
		}
		
		return reports;
	}
	
	public static ExtentReports getReports() {
		return reports;
	}

	public static void setReports(ExtentReports reports) {
		AdvanceReporting.reports = reports;
	}

	public static ExtentTest ExtntTests(ExtentReports ExtnReport,String testName)
	{
		try 
		{
			test = reports.createTest(testName);
		}
		catch (Exception exception) 
		{
			Log.error(exception.toString());
		}
		
		return test;
	}
	 

	
	public static ExtentTest getTest(){
		return test;
	}
	
	public static void setTest(ExtentTest eTest){
		test=eTest;
	}
	
	public static void getReportResult(ITestResult reslt,ExtentTest ExtntTests)
    {
        if(reslt.getStatus() == ITestResult.FAILURE)
        {
        	ExtntTests.log(Status.FAIL, MarkupHelper.createLabel(reslt.getTestName()+" Test case FAILED due to below issues:", ExtentColor.RED));
        	ExtntTests.fail(reslt.getThrowable());
        }
        else if(reslt.getStatus() == ITestResult.SUCCESS)
        {
        	ExtntTests.log(Status.PASS, MarkupHelper.createLabel(reslt.getTestName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
        	ExtntTests.log(Status.SKIP, MarkupHelper.createLabel(reslt.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
        	ExtntTests.skip(reslt.getThrowable());
        }
    } 
}
