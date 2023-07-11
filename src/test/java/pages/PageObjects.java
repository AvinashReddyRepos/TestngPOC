package pages;

import common.BaseClass;

public class PageObjects extends BaseClass {

    public DemoGuruHomePage demoGuruHomePage = new DemoGuruHomePage(driver);
    public RegisterPage registerPage = new RegisterPage(driver);
    public SignOnPage signOnPage= new SignOnPage(driver);
    public FlightsPage flightsPage = new FlightsPage(driver);

}
