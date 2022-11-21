package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import listeners.CustomListeners;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import pages.AccountsForm;
import pages.HomePage;
import utilities.*;

public class TestBase {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\excel.xlsx");
    public JavascriptExecutor js = (JavascriptExecutor) driver;
    public HomePage homePage;
    AccountsForm accountsForm;


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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
        homePage = new HomePage();
        accountsForm = new AccountsForm();
}


    @AfterSuite
    public void tearDown() {

        if (driver != null) {
          driver.quit();
        }
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

    public String get(String locator) {
        String value="null";
        if (locator.endsWith("_CSS")) {
             value = driver.findElement(By.cssSelector(OR.getProperty(locator))).getText();
        } else if (locator.endsWith("_XPATH")) {
            value =driver.findElement(By.xpath(OR.getProperty(locator))).getText();
        } else if (locator.endsWith("_ID")) {
            value = driver.findElement(By.id(OR.getProperty(locator))).getText();
        }
        Reporter.log( "Value: " + locator);
        return value;
    }


    public void selectValueFromList(String nameFromSelectList) {
        {
            WebElement element = driver.findElement(By.xpath("//a[contains(text(),'"+nameFromSelectList+"')]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div/div/div/a"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.linkText(nameFromSelectList)).click();
    }

}


