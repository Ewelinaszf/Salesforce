package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.LogStatus;
import listeners.CustomListeners;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Cookie;
import utilities.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestBase {
    String username = "ewelinabachata-usbg@force.com";
    String password="Ly5hnn86";
    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\excel.xlsx");
    public JavascriptExecutor js = (JavascriptExecutor) driver;
//    public static WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));;
    //  public static ExtentReports extent = ExtentManager.getInstance();
 //   public static ExtentTest extentTest;

    public static Logger log = Logger.getLogger("devpinoyLogger");

    @BeforeSuite
    public void setUp() {

        if (driver == null) {

            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.debug("Config file loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.debug("OR file loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (config.getProperty("browser").equals("firefox")) {
            System.getProperty("user.dir" + "src\\test\\resources\\executables\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (config.getProperty("browser").equals("chrome")) {
            System.getProperty("user.dir" + "src\\test\\resources\\executables\\chromedriver.exe");
            driver = new ChromeDriver();
            log.debug("Chrome Launched ");
        } else if (config.getProperty("browser").equals("ie")) {
            System.getProperty("user.dir" + "src\\test\\resources\\executables\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();

        }

        driver.get(config.getProperty("testsiteurl"));
        log.debug("Navigate to : "+config.getProperty("testsiteurl"));
        driver.manage().

    window().

    maximize();
        driver.manage().

    timeouts().

    implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);

}


    @AfterSuite
    public void tearDown() {

//        if (driver != null) {
//          driver.quit();
//        }
        log.debug("TEST EXECUTION COMPLETED !!!");
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void verifyEquals(String expected, String actual) throws IOException {

        try {

            Assert.assertEquals(actual, expected);

        } catch (Throwable t) {

            TestUtilities.captureScreenshot();
            // ReportNG
            Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
            Reporter.log("<a target=\"_blank\" href=" + TestUtilities.screenshotName + "><img src=" + TestUtilities.screenshotName
                    + " height=200 width=200></img></a>");
            Reporter.log("<br>");
            Reporter.log("<br>");
            // Extent Reports
            CustomListeners.testReport.get().log(Status.FAIL, " Verification failed with exception : " + t.getMessage());
            CustomListeners.testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(TestUtilities.screenshotName)
                            .build());

        }

    }
    public void click(String locator) {
        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).click();
        }
       Reporter.log( "Click on: " + locator);

    }

    public void type(String locator, String value) {
        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
        }
        Reporter.log("Typing in: " + locator + " entered value as " + value);
    }




}


