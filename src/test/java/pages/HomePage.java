package pages;

import base.TestBase;


public class HomePage  extends TestBase{

        public HomePageNavBar goToNavBar() {
                return new HomePageNavBar();
        }

        public NewCreatedAccount goToNewCreatedAccount(){
                return new NewCreatedAccount();
        }


}
