package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import java.util.NoSuchElementException;

public class AddCustomerTest extends TestBase {


    @Test
    public void add() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.xpath(OR.getProperty("accountTab"))).click();
        driver.findElement(By.xpath(OR.getProperty("newAccount"))).click();

        type("accountName_XPATH", "Gazprom");
        try {
            click("accountNameChoice_XPATH");

        } catch (NoAlertPresentException ex) {
        }catch(NoSuchElementException e) {}

        Thread.sleep(4000);
        driver.findElement(By.xpath("//span[contains(text(),'Type')]/../../div/div/div/div/a")).click();

        {
            WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Competitor')]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div/div/div/a"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.linkText("Competitor")).click();



        driver.findElement(By.xpath("//fieldset/div/div/button/span")).click();
    //    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Enter address')]")));
        driver.findElement(By.xpath("//input[contains(@placeholder,'Enter address')]")).click();
   //     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Enter address')]")));
        driver.findElement(By.xpath("//input[contains(@placeholder,'Enter address')]")).sendKeys("Essen, Germany");
 //       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'firstLine')]")));
        driver.findElement(By.xpath("//div[contains(@class,'firstLine')]")).click();
//        click("billingAddress_XPATH");
//        Thread.sleep(10000);
//        type("addressSearch_XPATH", "ess");
        Thread.sleep(50000);





    }
}

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
