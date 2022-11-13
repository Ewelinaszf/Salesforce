package testcases;

import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class AddCustomerTest extends TestBase {


    @Test
    public void add() {
        driver.findElement(By.xpath(OR.getProperty("accountTab"))).click();
        driver.findElement(By.xpath(OR.getProperty("newAccount"))).click();
        driver.findElement(By.xpath("//div/div/div/div/div/input")).sendKeys("Hel");
        driver.findElement(By.xpath("//li/a/div[2]/div[2]")).click();

        driver.findElement(By.linkText("Analyst")).click();
       {
            WebElement element = driver.findElement(By.linkText("Analyst"));
           Actions builder = new Actions(driver);
          builder.moveToElement(element).perform();
        }

   Assert.assertFalse(true);
        Assert.assertFalse(false);
//
//        {
//            WebElement element = driver.findElement(By.xpath("//li/a/div[2]/div[2]"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).perform();
//        }
//      {
//           WebElement element = driver.findElement(By.tagName("body"));
//          Actions builder = new Actions(driver);
//           builder.moveToElement(element, 0, 0).perform();
//        }
    }
}


//        driver.findElement(By.xpath("//div/div/div/div/div/input")).sendKeys("Hello");
//        driver.findElement(By.linkText("--None--")).click();
//        {
//            WebElement element = driver.findElement(By.linkText("--None--"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).perform();
//        }
//     //   driver.findElement(By.xpath(OR.getProperty("accountName"))).sendKeys("accountName");
//    }
//}

//    @Test(dataProvider = "getData")
//
//    public void addCustomer(String accountName, String type) throws InterruptedException {
//        Thread.sleep(5000);
//
//        driver.findElement(By.xpath(OR.getProperty("accountTab"))).click();
//        driver.findElement(By.xpath(OR.getProperty("newAccount"))).click();
//        driver.findElement(By.xpath(OR.getProperty("accountName"))).sendKeys(accountName);
//        driver.findElement(By.xpath(OR.getProperty("accountType"))).sendKeys(type);
//        Thread.sleep(5000);
////        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
////        Assert.assertTrue(alert.getText().contains(alertText));
////        Thread.sleep(5000);
////        alert.accept();
//
//    }
//
//    @DataProvider(name="org")
//    public Object[][] getData() {
//
//        String sheetName = "AddCustomerTest";
//        int rows = excel.getRowCount(sheetName);
//        int cols = excel.getColumnCount(sheetName);
//        Object[][] data = new Object[rows - 1][cols];
//
//        for (int rowNum = 2; rowNum <= rows; rowNum++) {
//            for (int colNum = 0; colNum < cols; colNum++) {
//                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//            }
//        }
//        System.out.println(Arrays.deepToString(data));
//        return data;
//    }
//}
//
