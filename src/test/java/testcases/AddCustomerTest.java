package testcases;

import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void addCustomer(String accountName, String Type) throws InterruptedException {
        Thread.sleep(5000);

        driver.findElement(By.xpath(OR.getProperty("accounts"))).click();
        driver.findElement(By.xpath(OR.getProperty("newAccount"))).click();
        driver.findElement(By.xpath(OR.getProperty("accountName"))).sendKeys(accountName);
        Thread.sleep(5000);
//        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//        Assert.assertTrue(alert.getText().contains(alertText));
//        Thread.sleep(5000);
//        alert.accept();

    }

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

