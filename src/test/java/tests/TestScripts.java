package tests;

import org.testng.annotations.Test;
import pages.PageObjects;

public class TestScripts extends PageObjects {

    public static String userName = randomName();
    public static String password = "Test123";

    // This test is to verify all navigations are working as expected or not
    @Test
    public void verifyAllLinksInHomePage() {
        launchUrl();
        demoGuruHomePage.verifyNavigationToAllMenus();
    }

    // This test is to verify the user registration functionality
    @Test
    public void verifyRegisterUser() {
        launchUrl();
        demoGuruHomePage.clickOnRegisterLink();
        registerPage.isDisplayRegisterPageHeader();
        registerPage.registerUser(userName, userName, "1234567890", userName+"@t.com", "address 1", "City", "State",
                "12345", "INDIA", userName, password);
        registerPage.clickOnSignIn();
        signOnPage.isDisplaySignOnHeaderImg();
        signOnPage.signIn(userName, password);
        signOnPage.verifyLoginSuccessMsg();
    }


    // Search for flights
    @Test
    public void verifySearchFlights() {
        launchUrl();
        demoGuruHomePage.clickOnFlights();
        flightsPage.searchFlights("roundtrip", "2", "London", "7", "12", "New York",
                "8", "15", "Business", "Blue Skies Airlines");
        flightsPage.verifySearchResults();
    }

    // This test is to verify the signIn functionality with invalid data
    //@Test
    public void verifySignInWithInvalidData() {
        launchUrl();
        signOnPage.verifySign(properties.getProperty("userName"), properties.getProperty("passowrd"));
    }


}
