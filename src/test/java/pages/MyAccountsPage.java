package pages;

import base.TestBase;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyAccountsPage extends TestBase {

    private static final int ACCOUNTS_NUMBER = driver.findElements(By.xpath(OR.getProperty("allAccounts_XPATH"))).size();

    private Account[] accounts = new Account[ACCOUNTS_NUMBER];
    private int accountNumber;


    public int checkAvailability(Account find) {
        if (find == null)
            return 0;

        int count = 0;
        for (int i = 0; i < accountNumber; i++) {
            System.out.println(accounts[i].getAccountName()+" - "+accounts[i].getPhoneNumber());
            if (find.equals(accounts[i]))
                count++;
        }
        return count;
    }

    public void addAllCreatedAccounts() {

        List<WebElement> elements = driver.findElements(By.xpath(OR.getProperty("allAccounts_XPATH")));
        int size = elements.size();
        int i=0;
        for (WebElement row : elements) {

            String accountName = (row.findElement(By.xpath("th/span"))).getText();
            String phone = (row.findElement(By.xpath("td[8]/span"))).getText();
            String phoneNumber = StringUtils.remove(phone,"Click to dial\n");
            accounts[accountNumber] = new Account(accountName,phoneNumber);
            accountNumber++;
        }

    }

    public Account[] getAccounts() {
        Account[] acc = new Account[accountNumber];
        for (int i = 0; i < accountNumber; i++) {

            acc[i] = accounts[i];
        }
        return acc;
    }
}

