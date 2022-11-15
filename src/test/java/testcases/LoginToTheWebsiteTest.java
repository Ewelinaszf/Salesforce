package testcases;

import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.util.Arrays;

public class LoginToTheWebsiteTest extends TestBase {

    @Test(dataProvider="org")
    public void loginToApplication(String username, String password) throws InterruptedException {
        log.debug("Inside Login Test");
        driver.findElement(By.id(OR.getProperty("username"))).sendKeys(username);
        driver.findElement(By.id(OR.getProperty("password"))).sendKeys(password);
        driver.findElement(By.id(OR.getProperty("logInToSandboxButton"))).click();
        driver.switchTo().defaultContent();


        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ZERO );
         //   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
         //   String text = alert.getText();
          //  alert.accept();

        } catch (NoAlertPresentException ex) {}

    }


        //    Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomerButton", "Login not successful"))));
//        log.debug("User successfully login");
        //       Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//        Assert.assertTrue(alert.getText().contains(alertText));
//        Thread.sleep(5000);
//        alert.accept();
    


    @DataProvider(name="org")
    public Object[][] getData() {

        String sheetName = "LoginToTheWebsiteTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);
        Object[][] data = new Object[rows - 1][cols];

        for (int rowNum = 2; rowNum <= rows; rowNum++) {
            for (int colNum = 0; colNum < cols; colNum++) {
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
            }
        }
        System.out.println(Arrays.deepToString(data));
        return data;
    }

}
