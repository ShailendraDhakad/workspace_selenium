package utility;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseExtentReport {
	
	    public static ExtentHtmlReporter htmlReporter;
	    public static ExtentReports extent;
	    public static ExtentTest test;
	     
	    @BeforeSuite
	    public void setUp()
	    {
	        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//extentreporter.html");
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        
	      //get name of current logged in User
			com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
			String Username=NTSystem.getName();
			
	        extent.setSystemInfo("OS", "Windows");
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("User Name", Username);
	         
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("Detailed Automation Test Report");
	        htmlReporter.config().setReportName("My Own Report");
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	       // htmlReporter.config().setTheme(Theme.DARK);
	        
	       // extent.config().insertJs("$('.test.warning').each(function() { $(this).addClass('pass').removeClass('warning'); }); $('.test-status.warning').each(function() { $(this).addClass('pass').removeClass('warning').text('pass'); });$('.tests-quick-view .status.warning').each(function() { $(this).addClass('pass').removeClass('warning').text('PASS'); }); testSetChart(); ");
	    }
	     
	    @AfterMethod
	    public void getResult(ITestResult result)
	    {
	        if(result.getStatus() == ITestResult.FAILURE)
	        {
	            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
	            test.fail(result.getThrowable());
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS)
	        {
	            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	        }
	        
	        else
	        {
	            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
	            test.skip(result.getThrowable());
	        }
	    }
	     
	    @AfterSuite
	    public void tearDown()
	    {
	        extent.flush();
	    }
	}

