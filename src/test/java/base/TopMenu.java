package base;

import org.openqa.selenium.WebDriver;
import pages.HomePageNavBar;

public  class TopMenu  {

    WebDriver driver;
    public TopMenu(WebDriver driver){
        this.driver=driver;
    }

    public HomePageNavBar goToHomePageNavBar(){
        return new HomePageNavBar();
    }

}
