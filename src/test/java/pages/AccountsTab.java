package pages;

import base.TestBase;


public class AccountsTab extends TestBase {


    public AccountsForm goToAccountsForm(){
        log.debug("Creating new Account");
        click("newAccount_XPATH");
        return new AccountsForm();
    }
    public MyAccountsPage goToMyAccount() {
        click("myAccounts_XPATH");
        return new MyAccountsPage();
    }
}
