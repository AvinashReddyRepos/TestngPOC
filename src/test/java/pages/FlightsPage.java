package pages;

import common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FlightsPage extends BaseClass {

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    By ele_passengers = By.name("passCount");
    By ele_departingFrom = By.name("fromPort");
    By ele_fromMonth = By.name("fromMonth");
    By ele_fromDate = By.name("fromDay");
    By ele_arrivingIn = By.name("toPort");
    By ele_returnMonth = By.name("toMonth");
    By ele_returndate = By.name("toDay");
    By ele_continueButton = By.name("findFlights");

    By ele_searchOutput = By.xpath("(//b/font)[1]");

    public void selectTriptype(String triptype) {
        By ele_triptype = By.xpath("//input[@name='tripType'  and @value = '" + triptype + "']");
        clickElement(ele_triptype, "Trip type");
    }

    public void selectServiceClass(String serviceclass) {
        By ele_serviceclass = By.xpath("//input[@name='servClass' and @value='" + serviceclass + "']");
        clickElement(ele_serviceclass, "Service class");
    }

    public void selectAirline(String airline) {
        By ele_airline = By.xpath("//select[@name='airline']/option[text()='" + airline + "']");
        clickElement(ele_airline, "Airline");
    }

    public void searchFlights(String triptype, String passengersCount, String departerLocation, String fromMonth, String fromDate, String arrivingIn,
                              String returnMonth, String returnDate, String serviceclass, String airlineName) {
        selectTriptype(triptype);
        select(ele_passengers, passengersCount, "Passengers count");
        select(ele_departingFrom, departerLocation, "Departing From");
        select(ele_fromMonth, fromMonth, "From month");
        select(ele_fromDate, fromDate, "From date");
        select(ele_arrivingIn, arrivingIn, "Arriving In");
        select(ele_returnMonth, returnMonth, "Return Month");
        select(ele_returndate, returnDate, "Return Date");
        selectServiceClass(serviceclass);
        selectAirline(airlineName);
        clickElement(ele_continueButton, "Continue Button");

    }

    public void verifySearchResults() {
        String actualNoSeatsMsg = getText(ele_searchOutput, "Search Output");
        if (!actualNoSeatsMsg.contains("After flight finder - No Seats Avaialble")) {
            Assert.assertTrue(false, "No Seats Avaialble message is not displaying");
        }
    }


}
