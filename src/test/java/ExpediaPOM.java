import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.ExpediaMainPage;
import pages.FlightAndCarPage;
import pages.PaymentAndContactPage;
import pages.SearchResultsPage;

import java.util.concurrent.TimeUnit;

public class ExpediaPOM {

    WebDriver driver;
    String browserType = "chrome";

    @Test
    public void ExpediaPOMTest() {
        // Make a mainPage instance of the ExpediaMainPage class
        ExpediaMainPage mainPage = new ExpediaMainPage(driver);
        mainPage.setDestinationAndDates("New York, NY");
        mainPage.addFlightAndCar("Warsaw");
        mainPage.setTravelers();
        mainPage.submitMainPage();
        mainPage.waitForResultsPage();

        // Make a searchPage instance of the SearchResultsPage class
        SearchResultsPage searchPage = new SearchResultsPage(driver);
        searchPage.addFreeCancelFiltering();
        searchPage.addOnlyFiveStarsFiltering();
        searchPage.sortByPrice();
        searchPage.pickHotel();
        searchPage.goToHotelTabAndGetHotelName();
        searchPage.bookReservation();

        // Make a flightPage instance of the FlightAndCarPage class
        FlightAndCarPage flightPage = new FlightAndCarPage(driver);
        flightPage.pickTheFlights();
        flightPage.pickTheCar();

        // Make a payPage instance of the PaymentAndContactPage class
        PaymentAndContactPage payPage = new PaymentAndContactPage(driver);
        payPage.fillOutFirstPersonData();
        payPage.fillOutSecondPersonData();
        payPage.fillOutThirdPersonData();
        payPage.fillOutPaymentAndContactData();
        payPage.getPriceAndConfirmation();

    }


    @Before
    public void setUp() {
        driver = utilities.DriverFactory.setDriver(browserType);
        driver.manage().timeouts().implicitlyWait(17, TimeUnit.SECONDS);
        String homePage = "https://www.expedia.com/";
        driver.get(homePage);
        driver.manage().window().maximize();
    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }

}
