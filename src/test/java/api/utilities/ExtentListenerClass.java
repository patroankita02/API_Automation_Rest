package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListenerClass implements ITestListener
{
    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public void configureReport()
    {
        extentSparkReporter  = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//"+"extentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
        extentSparkReporter.config().setReportName("Test Report");
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }
    public void onStart(ITestContext Result)
    {
        configureReport();
        System.out.println("On start Method Invoked .....");
    }
    public void onFinish(ITestContext Result)
    {
        System.out.println("On finish method invoked .....");
        extentReports.flush();
    }

    public void onTestFailure(ITestResult Result)
    {
        System.out.println("Name of test method failed :"+Result.getName());
        extentTest = extentReports.createTest(Result.getName());
        extentTest.log(Status.FAIL,Result.getThrowable());
    }
    public void onTestSuccess(ITestResult Result)
    {
        System.out.println("Name of test method passed :"+Result.getName());
        extentTest = extentReports.createTest(Result.getName());
        extentTest.log(Status.PASS,Result.getThrowable());
    }

    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, result.getTestName());
        }
        else {
            extentTest.log(Status.SKIP, result.getTestName());
        }
    }
}
