package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class BaseClass {

    public static Logger logger = LoggerFactory.getLogger(BaseClass.class);
    public static Random rand = new Random();
    static final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final Set<String> identifiers = new HashSet<String>();

    public static WebDriver driver;
    public static Properties properties;
    public static String mini, medium, max;
    public static int minimumWait, mediumWait, maximumWait;


    public BaseClass() {
        try {
            logger.info("Read property file");
            properties = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Properties/config.properties");
            properties.load(ip);

            logger.info("Read wait times");
            mini = properties.getProperty("minimumWait");
            minimumWait = Integer.parseInt(mini);
            medium = properties.getProperty("mediumWait");
            mediumWait = Integer.parseInt(medium);
            max = properties.getProperty("maximumWait");
            maximumWait = Integer.parseInt(max);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Unable to read the property file");
        }
    }

    @BeforeTest
    public void beforeTest() {

        if (properties.getProperty("browserName").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);


        } else if (properties.getProperty("browserName").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        waitUtil(30);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest() {
        closeBrowser();
    }

    public void launchUrl() {
        driver.get(properties.getProperty("url"));
        waitForPageToLoad();
    }

    /***** Common Functions *******/

    /*** This function will wait until ui page loads max 30 sec***/
    public static void waitForPageToLoad() {

        try {
            Thread.sleep(1000);
            logger.info("wait for page load");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            for (int count = 0; count < 40; count++) {
                String pageLoadStatus = jse.executeScript("return document.readyState").toString();
                if (pageLoadStatus.equalsIgnoreCase("complete")) {
                    break;
                } else {
                    Thread.sleep(3000);
                }
            }

        } catch (Exception e) {
            Assert.fail("Unable to perform wait for page load. " + e.getMessage());
        }
    }


    /************** selenium generic utilities*******************/

    /******** This function will check web element is displaying or not and return boolean value*********/
    public boolean isDisplayElement(By locator, String elementName) {
        boolean result = false;

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            result = true;

        } catch (Exception e) {
            logger.info("Unable to perform isDisplayElement for " + elementName + e.getMessage());
            return result;
        }
        return result;
    }

    /*public boolean isDisplayed(By locator, String elementName) {

        boolean result = false;

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            result = true;

        } catch (Exception e) {
            logger.info("Unable to perform isDisplayElement for " + elementName+ e.getMessage());
        }
        return result;
    }*/

    /************ This function will click on web element ************/

    public void clickElement(By locator, String elementName) {

        try {
            logger.info("Click on " + elementName + " field.");
            driver.findElement(locator).click();
            waitUtil(maximumWait);
            waitForPageToLoad();
        } catch (Exception e) {
            Assert.fail("Unable to perform click on " + elementName + " . " + e.getMessage());
        }
    }

    /************ This function will enter the value on text field ************/

    public void setInput(By locator, String inputValue, String elementName) {

        try {
            logger.info("Enter value in " + elementName + " input field.");
            driver.findElement(locator).clear();
            waitUtil(maximumWait);
            driver.findElement(locator).sendKeys(inputValue);
            waitUtil(maximumWait);
        } catch (Exception e) {
            Assert.fail("Unable to perform enter the text in " + elementName + " field. " + e.getMessage());
        }
    }

    /************ This function will select the value from dropdown ************/

    public void select(By locator, String inputValue, String elementName) {

        try {
            logger.info("Select value from " + elementName + " dropdown");
            Select dp = new Select(driver.findElement(locator));
            dp.selectByValue(inputValue);
            waitUtil(maximumWait);
        } catch (Exception e) {
            Assert.fail("Unable to select the value from " + elementName + " dropdown" + e.getMessage());
        }
    }

    /************ This function will accept the pop up ************/

    public boolean acceptpopup() {

        try {
            logger.info("Accept popup");
            waitUtil(mediumWait);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            logger.info("Unable to click on accept pop up" + e.getMessage());
            //Assert.fail("Unable to click on accept pop up" + e.getMessage());
        }
        return true;
    }

    /************ This function will dismiss the pop up ************/

    public void dismisspopup() {

        try {
            logger.info("Dismiss popup");
            waitUtil(mediumWait);
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (Exception e) {
            Assert.fail("Unable to click on dismiss pop up" + e.getMessage());
        }
    }

    /************ This function will dismiss the pop up ************/

    public void closeAdd() {

        try {
            //Thread.sleep(mediumWait);
            //waitForPageToLoad();
            logger.info("Close add popup");
            Thread.sleep(mediumWait);
            int size = driver.findElements(By.tagName("iframe")).size();

            boolean b = false;
            By ele_dismissbutton = By.id("dismiss-button");
            By frame1 = By.xpath("//iframe[@title='3rd party ad content']");
            By frame2 = By.id("ad_iframe");

            if (size > 8) {
                if (isDisplayElement(frame1, "ads frame")) {

                    driver.switchTo().frame(driver.findElement(frame1));

                    if (isDisplayElement(ele_dismissbutton, "ads")) {
                        clickElement(ele_dismissbutton, "Close add");
                        b = true;
                    }
                    if (b == false) {
                        driver.switchTo().frame(driver.findElement(frame2));
                        if (isDisplayElement(ele_dismissbutton, "ads")) {
                            clickElement(ele_dismissbutton, "Close add");
                        }
                    }
                }
                Thread.sleep(minimumWait);
            }
            driver.switchTo().defaultContent();
        } catch (NoSuchFrameException e) {
            logger.info("Unable to identify the ads iframe");
        } catch (Exception e) {
            Assert.fail("Unable to click on close add" + e.getMessage());
        }
    }

    public void waitUntilElementDisappears(By locator) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Waiting till the " + locator + " disappears");

        } catch (Exception e) {
            Assert.fail("Failed to wait until element disappears due to exception " + e.getMessage());
        }

    }

    /************ This function will read data from UI and returns String ************/

    public String getText(By locator, String elementName) {

        String txt = null;
        try {
            logger.info("Get text from " + elementName + " locator");
            txt = driver.findElement(locator).getText().trim();
            waitUtil(maximumWait);
            return txt;
        } catch (Exception e) {
            Assert.fail("Unable to perform get text from " + elementName + " field. " + e.getMessage());
        }
        return txt;
    }

    /************ This function will wait for the web element as the given time in milli secounds ************/
    public void waitUtil(int milliSecounds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(milliSecounds));
    }

    /************ This function will close the browser ************/
    public void closeBrowser() {
        logger.info("Close browser");
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            Assert.fail("Unable to close the browser " + e.getMessage());
        }

    }

    /************ This function will return random string ************/
    public static String randomName() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }


}
