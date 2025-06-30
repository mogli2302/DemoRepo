package listeners;

import org.testng.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.BaseClass;

public class TestListener extends BaseClass implements ITestListener {

    public static ExtentReports extent = new ExtentReports(); // ✅ initialize here directly
//    public static ExtentTest test = new ExtentTest ();

    static {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myReport.html");
        extent.attachReporter(reporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//        test.set(extentTest);
    	
    	test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
