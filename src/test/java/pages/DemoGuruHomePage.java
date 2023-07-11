package pages;

import common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DemoGuruHomePage extends BaseClass {

    public DemoGuruHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Side navigation
    By ele_homelink = By.xpath("//a[text()='Home']");
    By ele_flightslink = By.xpath("//a[text()='Flights']");
    By ele_hotelslink = By.xpath("//a[text()='Hotels']");
    By ele_carRentalslink = By.xpath("//a[text()='Car Rentals']");
    By ele_cruiseslink = By.xpath("//a[text()='Cruises']");
    By ele_destinationslink = By.xpath("//a[text()='Destinations']");
    By ele_vacationslink = By.xpath("//a[text()='Vacations']");

    // Top Menu
    By ele_signoOnlink = By.xpath("//a[text()='SIGN-ON']");
    By ele_registerlink = By.xpath("//a[text()='REGISTER']");
    By ele_supportlink = By.xpath("//a[text()='SUPPORT']");
    By ele_contactlink = By.xpath("//a[text()='CONTACT']");

    //
    By ele_userName = By.name("userName");
    By ele_password = By.name("password");
    By ele_submitButton = By.name("submit");
    By ele_flightsPageHeader = By.xpath("//img[@src='images/mast_flightfinder.gif']");
    By ele_underconstructionMsg = By.xpath("//font[contains(text(),'Sorry         for any inconvienece.')]");
    By ele_registerPage = By.xpath("//img[@src='images/mast_register.gif']");


    public void clickOnRegisterLink() {
        clickElement(ele_registerlink, "Register link");
    }

    public void clickOnFlights() {
        clickElement(ele_flightslink, "Flights menu link");
        //dismisspopup();
        closeAdd();
        Assert.assertTrue(isDisplayElement(ele_flightsPageHeader, "Flights page header image"), "Flights page is not displaying");
    }

    public void verifyNavigationToAllMenus() {

        clickElement(ele_homelink, "Home menu link");
        Assert.assertTrue(isDisplayElement(ele_userName, "User name field"), "Home page is not displaying");

        clickOnFlights();

        clickElement(ele_hotelslink, "Hotels menu link");
        Assert.assertTrue(isDisplayElement(ele_underconstructionMsg, "Hotels page header image"), "Hotels page is not displaying");

        clickElement(ele_carRentalslink, "Car rentals menu link");
        Assert.assertTrue(isDisplayElement(ele_underconstructionMsg, "Car rentals page header image"), "Car rentals page is not displaying");

        clickElement(ele_cruiseslink, "Cruises menu link");
        Assert.assertTrue(isDisplayElement(ele_userName, "Cruises page header image"), "Cruises page is not displaying");

        clickElement(ele_destinationslink, "Destinations menu link");
        Assert.assertTrue(isDisplayElement(ele_underconstructionMsg, "Destinations page header image"), "Destinations page is not displaying");

        clickElement(ele_vacationslink, "Vacations menu link");
        Assert.assertTrue(isDisplayElement(ele_underconstructionMsg, "Vacations page header image"), "Vacations page is not displaying");

        clickElement(ele_signoOnlink, "SigOn menu link");
        Assert.assertTrue(isDisplayElement(ele_userName, "User name field"), "SigOn page is not displaying");

        clickElement(ele_registerlink, "Register menu link");
        Assert.assertTrue(isDisplayElement(ele_registerPage, "Register page header image"), "Register page is not displaying");

        clickElement(ele_supportlink, "Support menu link");
        Assert.assertTrue(isDisplayElement(ele_underconstructionMsg, "Support page header image"), "Support page is not displaying");

        clickElement(ele_contactlink, "Contact menu link");
        Assert.assertTrue(isDisplayElement(ele_underconstructionMsg, "Contact page header image"), "Contact page is not displaying");

    }


}
