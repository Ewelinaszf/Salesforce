package testcases;



import base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TestData extends TestBase {

    @Test(dataProvider="org")
    public void loginToApplication(String username, String password)

    {
        log.debug("Inside Login Test");
        driver.findElement(By.id(OR.getProperty("username"))).sendKeys(username);
        driver.findElement(By.id(OR.getProperty("password"))).sendKeys(password);
        driver.findElement(By.id(OR.getProperty("logInToSandboxButton"))).click();
    //    Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomerButton", "Login not successful"))));
//        log.debug("User successfully login");
 //       Alert alert = wait.until(ExpectedConditions.alertIsPresent());
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
