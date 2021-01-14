package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ExpediaFeatures {
    WebDriver driver;


    @Given("the user is on the booking page")
    public void the_user_is_on_the_booking_page() {
        driver = utilities.DriverFactory.setDriver("chrome");
        driver.manage().timeouts().implicitlyWait(17, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//div[@class='uitk-tabs-container']/ul/li[1]")).click();
        System.out.println("Reached the booking page");
    }

    @When("user sets the destination city as (.*)")
    public void the_destination(String destination) {
        System.out.println("Destination:" + destination);
        driver.findElement(By.xpath("//button[@aria-label='Going to']")).click();
        driver.findElement(By.id("location-field-destination")).sendKeys(destination);
        driver.findElement(By.id("location-field-destination")).sendKeys(Keys.ENTER);
    }

    @When("user sets starting city as (.*)")
    public void the_starting_location(String startingLocationCity) {
        System.out.println("Starting location:" + startingLocationCity);
        WebDriverWait wait15 = new WebDriverWait(driver, 15);
        WebElement startingLocationButton = driver.findElement(By.xpath("//button[@aria-label='Leaving from']"));
        wait15.until(ExpectedConditions.elementToBeClickable(startingLocationButton));
        startingLocationButton.click();

        WebElement startingLocation = driver.findElement(By.xpath("//input[@id='location-field-origin']"));
        wait15.until(ExpectedConditions.elementToBeClickable(startingLocation));
        startingLocation.sendKeys(startingLocationCity);

        WebElement startingLocationPick = driver.findElement(By.xpath("(//button[@data-stid='location-field-origin-result-item-button'])[1]"));
        wait15.until(ExpectedConditions.elementToBeClickable(startingLocationPick));
        startingLocationPick.click();

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
