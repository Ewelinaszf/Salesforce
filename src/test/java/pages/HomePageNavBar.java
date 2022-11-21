package pages;

import base.TestBase;

public class HomePageNavBar extends HomePage {

   public AccountsTab clickOnAccounts() {
       click("accountTab_XPATH");
        return new AccountsTab();
   }
}
