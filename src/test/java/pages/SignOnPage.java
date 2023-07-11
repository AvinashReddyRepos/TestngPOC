package pages;

import common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SignOnPage extends BaseClass {

    public SignOnPage(WebDriver driver) {
        this.driver = driver;
    }

    By ele_signOnHeader = By.xpath("//img[@src='images/mast_signon.gif']");
    By ele_userName = By.name("userName");
    By ele_password = By.name("password");
    By ele_submitButton = By.name("submit");
    By ele_siginSuccessMsg1 = By.xpath("//h3[text()='Login Successfully']");
    By ele_siginSuccessMsg2 = By.xpath("//b[text()=' Thank you for Loggin. ']");
    By ele_signErrMsg = By.xpath("//span[contains(text(),'Enter your userName and password correct')]");
    By ele_signOff = By.xpath("//a[text()='SIGN-OFF']");
    By ele_SigOnLink = By.xpath("//a[text()='SIGN-ON']");

    public void isDisplaySignOnHeaderImg() {
        isDisplayElement(ele_signOnHeader, "Sign on page header image");
    }

    public void signIn(String userName, String password) {
        closeAdd();
        setInput(ele_userName, userName, "User name");
        setInput(ele_password, password, "Password");
        clickElement(ele_submitButton, "Submit button");
        closeAdd();
    }

    public void verifyLoginSuccessMsg() {
        Assert.assertTrue(isDisplayElement(ele_siginSuccessMsg1, "Login Successfully message"), "Login Successfully message is not displaying");
        Assert.assertTrue(isDisplayElement(ele_siginSuccessMsg2, "Thank you for Loggin. message"), "Thank you for Loggin. message is not displaying");
    }

    public void clickOnSignOff() {

        if (isDisplayElement(ele_signOff, "Sigin link")){
            clickElement(ele_signOff, "Sign off button");
            closeAdd();
        }
    }

    public void verifySign(String userName, String password) {
        SoftAssert softassert = new SoftAssert();
        //Case Insensitive User with valid password
        signIn(userName.toLowerCase(), password);
        boolean errorMsg = isDisplayElement(ele_signErrMsg, "Enter your userName and password correct");
        softassert.assertTrue(errorMsg, "Sign in error message is not displaying When we enter Case Insensitive User");
        clickOnSignOff();
        //Case Insensitive User with Invalid password
        signIn(userName.toLowerCase(), password.toUpperCase() + "test!@#$%");
        errorMsg = isDisplayElement(ele_signErrMsg, "Enter your userName and password correct");
        softassert.assertTrue(errorMsg, "Sign in error message is not displaying When we enter Case Insensitive User and invalid password");
        clickOnSignOff();
        //User with valid Case Insensitive password
        signIn(userName, password.toLowerCase());
        errorMsg = isDisplayElement(ele_signErrMsg, "Enter your userName and password correct");
        softassert.assertTrue(errorMsg, "Sign in error message is not displaying When we enter Case Insensitive password");
        clickOnSignOff();
        //Invalid User with valid password
        signIn(userName + "test!@#$", password);
        errorMsg = isDisplayElement(ele_signErrMsg, "Enter your userName and password correct");
        softassert.assertTrue(errorMsg, "Sign in error message is not displaying When we enter incorrect user");
        clickOnSignOff();
        //User with Invalid password
        signIn(userName, password + "test!@#$");
        errorMsg = isDisplayElement(ele_signErrMsg, "Enter your userName and password correct");
        softassert.assertTrue(errorMsg, "Sign in error message is not displaying When we enter incorrect password");
        clickOnSignOff();
        softassert.assertAll();
    }

}
