package pages;

import common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterPage extends BaseClass {

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    By ele_registerPage = By.xpath("//img[@src='images/mast_register.gif']");

    // Contact Information
    By ele_firstName = By.name("firstName");
    By ele_lastName = By.name("lastName");
    By ele_phone = By.name("phone");
    By ele_email = By.name("userName");
    //  Mailing Information
    By ele_address = By.name("address1");
    By ele_city = By.name("city");
    By ele_state = By.name("state");
    By ele_postalCode = By.name("postalCode");
    By ele_countryDp = By.name("country");
    //   User Information
    By ele_userName = By.id("email");
    By ele_password = By.name("password");
    By ele_confirmPassword = By.name("confirmPassword");

    By ele_submitButton = By.name("submit");
    By ele_signInLink = By.xpath("//a[contains(text(),'sign-in')]");

    // Setter and getter methods
    public void isDisplayRegisterPageHeader() {
        Assert.assertTrue(isDisplayElement(ele_registerPage, "Register page Header"), "Register page Header is not displaying");
    }

    public void registerUser(String firstName, String lastName, String phone, String email, String address, String city,
                             String state, String postalcode, String country, String userName, String password) {

        setInput(ele_firstName, firstName, "first name");
        setInput(ele_lastName, lastName, "last name");
        setInput(ele_phone, phone, "phone number");
        setInput(ele_email, email, "email");
        setInput(ele_address, address, "address");
        setInput(ele_city, city, "city");
        setInput(ele_state, state, "state");
        setInput(ele_postalCode, postalcode, "postal code");
        select(ele_countryDp, country, "Country");
        setInput(ele_userName, userName, "user name");
        setInput(ele_password, password, "password");
        setInput(ele_confirmPassword, password, "password");
        clickElement(ele_submitButton, "Submit button");

        // Popup
        acceptpopup();
        // Success message
        verifySuccessMessage(firstName, userName);

    }

    public void verifySuccessMessage(String firstName, String userName) {
        By ele_successMsg1 = By.xpath("//b[contains(text(),' Dear " + firstName + "')]");
        By ele_successMsg2 = By.xpath("//font[contains(text(),'Thank you for registering.')]");
        By ele_successMsg3 = By.xpath("//b[contains(text(),' Note: Your user name is " + userName + ".')]");

        Assert.assertTrue(isDisplayElement(ele_successMsg1, "Success message 1"), "Success message 1 is not displaying as expected.");
        Assert.assertTrue(isDisplayElement(ele_successMsg2, "Success message 2"), "Success message 2 is not displaying as expected.");
        Assert.assertTrue(isDisplayElement(ele_successMsg3, "Success message 3"), "Success message 3 is not displaying as expected.");
    }

    public void clickOnSignIn() {
        clickElement(ele_signInLink, "Sign in link");
        closeAdd();
    }


}
