package testcases;

import base.TestBase;
import data.Industry;
import data.Types;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;




public class AddNewAccount extends TestBase {

    @Test(priority = 2)
    public void goToMyAccounts() throws InterruptedException {

        Account account = homePage.goToNavBar().clickOnAccounts().goToAccountsForm().
                fillAccountNameField("Gazprom")
                .fillTypeField(Types.COMPETITOR)
                .fillIndustryField(Industry.APPAREL)
                .getAccountData();
        AccountsForm accountsForm = new AccountsForm();

        Account accountData = accountsForm.saveNewAccountForm().goToNewCreatedAccount().getAccountData();
        System.out.println("account.equals(accountData = " + account.equals(accountData));

        Assert.assertTrue(account.equals(accountData), "The Account wasn't created");



//        MyAccountsPage myAccountsPage = new MyAccountsPage();
//        homePage.goToNavBar().clickOnAccounts().goToMyAccount().addAllCreatedAccounts();
//        myAccountsPage.addAllCreatedAccounts();
//        myAccountsPage.checkAvailability(account);


        NewCreatedAccount newCreatedAccount = new NewCreatedAccount();
        newCreatedAccount.getAccountData();
        newCreatedAccount.editNewCreatedAccount();

    }

    @Test(priority = 3)
    public void editNewCreatedAccount() throws InterruptedException {
        NewCreatedAccount newCreatedAccount = new NewCreatedAccount();
        newCreatedAccount.editNewCreatedAccount()
                .fillTypeField(Types.ANALYST)
                .saveNewAccountForm();
        boolean ifTypesWasEdited = newCreatedAccount.verifyIfTypesWasEdited(Types.ANALYST);
        Assert.assertTrue(ifTypesWasEdited);
   

    }
}


