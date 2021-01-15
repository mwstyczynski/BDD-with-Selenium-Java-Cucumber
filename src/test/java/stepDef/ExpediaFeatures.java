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


    @Given("^the user is on the booking page$")
    public void the_user_is_on_the_booking_page() {
        driver = utilities.DriverFactory.setDriver("chrome");
        driver.manage().timeouts().implicitlyWait(17, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//div[@class='uitk-tabs-container']/ul/li[1]")).click();
        System.out.println("Reached the booking page");
    }

    @When("user is in {string}")
    public void user_is_in (String city){
        System.out.println("Starting from: " + city);
        WebDriverWait wait15 = new WebDriverWait(driver, 15);
        driver.findElement(By.id("add-flight-switch")).click();
        WebElement startingLocationButton = driver.findElement(By.xpath("//button[@aria-label='Leaving from']"));
        wait15.until(ExpectedConditions.elementToBeClickable(startingLocationButton));
        startingLocationButton.click();

        WebElement startingLocation = driver.findElement(By.xpath("//input[@id='location-field-origin']"));
        wait15.until(ExpectedConditions.elementToBeClickable(startingLocation));
        startingLocation.sendKeys(city);

        WebElement startingLocationPick = driver.findElement(By.xpath("(//button[@data-stid='location-field-origin-result-item-button'])[1]"));
        wait15.until(ExpectedConditions.elementToBeClickable(startingLocationPick));
        startingLocationPick.click();
    }

    @When("user wants to go to {string}")
    public void user_wants_to_go(String whereTo) {
        System.out.println("Going to: " + whereTo);
        driver.findElement(By.xpath("//button[@aria-label='Going to']")).click();
        driver.findElement(By.id("location-field-destination")).sendKeys(whereTo);
        driver.findElement(By.id("location-field-destination")).sendKeys(Keys.ENTER);
    }

    @When("^user sets the destination city as (.*)$")
    public void the_destination(String destination) {
        System.out.println("Going to: " + destination);
        driver.findElement(By.xpath("//button[@aria-label='Going to']")).click();
        driver.findElement(By.id("location-field-destination")).sendKeys(destination);
        driver.findElement(By.id("location-field-destination")).sendKeys(Keys.ENTER);
    }

    @When("^user sets starting city as (.*)$")
    public void the_starting_location(String startingLocationCity) {
        System.out.println("Starting from: " + startingLocationCity);
        WebDriverWait wait15 = new WebDriverWait(driver, 15);
        driver.findElement(By.id("add-flight-switch")).click();
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

    @When("^user will need a car$")
    public void user_will_need_a_car() {
        System.out.println("user adds the car");

    }

    @When("^user is traveling with one child$")
    public void user_is_traveling_with_one_child() {
        System.out.println("user adds one child");

    }

    @When("^user submits the inserted data$")
    public void user_submits_the_inserted_data() {
        System.out.println("data submitted");

    }

}
