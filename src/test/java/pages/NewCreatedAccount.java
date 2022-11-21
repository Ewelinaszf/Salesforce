package pages;

import base.TestBase;
import data.Types;
import org.openqa.selenium.ElementClickInterceptedException;

public class NewCreatedAccount extends TestBase {

    public Account getAccountData(){
        String nameOfTheCreatedAccount = get("nameOfTheCreatedAccount_XPATH");
        String phoneOfTheCreatedAccount = get("phoneOfTheCreatedAccount_XPATH");
        return new Account(nameOfTheCreatedAccount,phoneOfTheCreatedAccount);
    }

    public AccountsForm editNewCreatedAccount() throws InterruptedException {
        log.debug("Editing new created account");
        try {
            click("editNewCreatedAccount_XPATH");
        }catch(ElementClickInterceptedException e) {}
        return new AccountsForm();
    }

    public boolean verifyIfTypesWasEdited(Types type) {
        log.debug("Verifing if types was edited");
        click("detailsTab_XPATH");
        String typeInDetailsTab = get("typeInDetails_XPATH");
        boolean isTypesWasEdited = typeInDetailsTab.equals(type.getDescription());
        return  isTypesWasEdited;

    }
}
