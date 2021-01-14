package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class ExpediaFeatures {
    WebDriver driver;

    @Given("the user is on the booking page")
    public void the_user_is_on_the_booking_page() {
        System.out.println("user is on the booking page");
        driver = utilities.DriverFactory.setDriver("chrome");
        driver.get("https://www.expedia.com/");

    }

    @When("user sets the destination on trip details section of the booking page")
    public void user_sets_the_destination_on_trip_details_section_of_the_booking_page() {
        System.out.println("user sets the destination");

    }

    @When("user sets the starting location on trip details section of the booking page")
    public void user_sets_the_starting_location_on_trip_details_section_of_the_booking_page() {
        System.out.println("user sets the starting location");

    }

    @When("user adds a car rent to be included to the bill")
    public void user_adds_a_car_rent_to_be_included_to_the_bill() {
        System.out.println("user adds the car");

    }

    @When("user adds one child in travelers section of the booking page")
    public void user_adds_one_child_in_travelers_section_of_the_booking_page() {
        System.out.println("user adds one child");

    }

    @When("user submits the inserted data")
    public void user_submits_the_inserted_data() {
        System.out.println("data submitted");

    }

}
