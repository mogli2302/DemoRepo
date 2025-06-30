
package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilities.ReadConfig;

public class BaseClass {
    public WebDriver driver;
    public ExtentReports extent;
    public static ExtentTest test;
    ReadConfig config = new ReadConfig();
    

    @BeforeSuite
    public void setupReport() {
        extent = new ExtentReports();
        
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/OrangeHRMReport.html");
        extent.attachReporter(spark);
        
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(config.getURL());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = captureScreenshot(driver, result.getName());
            test.fail("Test Failed: " + result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        }
        driver.quit();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }

    public String captureScreenshot(WebDriver driver, String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = "screenshots/" + testName + "_" + new Date().getTime() + ".png";
        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
