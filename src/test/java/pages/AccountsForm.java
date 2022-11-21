package pages;

import base.TestBase;
import data.Industry;
import data.Types;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountsForm extends TestBase {
    private String accountName;
    private String phoneNumber;
    private Types types;


    public AccountsForm fillAccountNameField(String name) {
        type("accountName_XPATH", name);

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@title,/'"+name+"/')]")));

        } catch (TimeoutException e) {
            log.debug(name + " - Element wasn't clicked");
        }
        click("accountNameChoice_XPATH");
        return new AccountsForm();

    }

    public AccountsForm fillTypeField(Types types) {
        click("type_XPATH");
        selectValueFromList(types.getDescription());
        return new AccountsForm();
    }

    public AccountsForm fillIndustryField(Industry industry) {
        click("industry_XPATH");
        selectValueFromList(industry.getDescription());
        return new AccountsForm();
    }

    public Account getAccountData() {
        accountName = get("accountNameInput_XPATH").replace("Press Delete to Remove", "").trim();
        phoneNumber = driver.findElement((By.xpath(OR.getProperty("phone_XPATH")))).getAttribute("value").trim();
        return new Account(accountName, phoneNumber);

    }

    public AccountsForm fillBillingAddress() {
        log.debug("Filling Address Information ");
        click("billingAddress_XPATH");
        click("addressSearch_XPATH");
        driver.findElement(By.xpath("//span[contains(.,\'Search Address\')]")).click();
        driver.findElement(By.xpath("//div[2]/div/div[2]/div/input")).click();
        driver.findElement(By.xpath("//div[2]/div/div[2]/div/input")).sendKeys("Berlin, Germany");
        driver.findElement(By.xpath("//div[4]/div[2]/div/div[2]/div/div/button/lightning-primitive-icon")).click();
        return new AccountsForm();
    }

    public HomePage saveNewAccountForm(){
        click("saveButton_XPATH");
        return new HomePage();
    }


    }

