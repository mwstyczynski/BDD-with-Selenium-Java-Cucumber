package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ExpediaMainPage {

    // Class level WebDriver
    WebDriver driver;
    WebDriverWait wait15;

    // Define WebElements at the class level (need to initialize them in the constructor)
    @FindBy(xpath = "//div[@class='uitk-tabs-container']/ul/li[1]")
    WebElement stays;

    @FindBy(xpath = "//button[@aria-label='Going to']")
    WebElement goingTo;

    @FindBy(id = "location-field-destination")
    WebElement destination;

    @FindBy(xpath = "//button[@class='uitk-new-date-picker-day'][@data-day='13']")
    WebElement checkInDate_currentMonth;

    @FindBy(xpath = "//button[@class='uitk-new-date-picker-day'][@data-day='20']")
    WebElement checkOutDate_currentMonth;

    @FindBy(xpath = "//button[@data-stid='apply-date-picker']")
    WebElement applyDate;


    //    methods used in test classes
    public void setDestinationAndDates(String destinationCity) {
        wait15 = new WebDriverWait(driver, 15);
        stays.click();
        goingTo.click();
        destination.sendKeys(destinationCity);
        destination.sendKeys(Keys.ENTER);

        driver.findElement(By.id("d1-btn")).click();
        wait15.until(ExpectedConditions.elementToBeClickable(checkInDate_currentMonth));
        checkInDate_currentMonth.click();

        wait15.until(ExpectedConditions.elementToBeClickable(checkOutDate_currentMonth));
        checkOutDate_currentMonth.click();
        applyDate.click();
    }

    public void addFlightAndCar(String startingLocationCity) {
        wait15 = new WebDriverWait(driver, 15);
        driver.findElement(By.id("add-car-switch")).click();
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

    public void setTravelers() {
        wait15 = new WebDriverWait(driver, 15);
        driver.findElement(By.xpath("//div[@id='adaptive-menu']")).click();
        WebElement increaseChildren = driver.findElement(By.xpath("(//button[@class='uitk-button uitk-button-small uitk-flex-item uitk-step-input-button'])[4]"));
        wait15.until(ExpectedConditions.elementToBeClickable(increaseChildren));
        increaseChildren.click();

        WebElement travelersDoneButton = driver.findElement(By.xpath("//button[@data-testid='guests-done-button']"));
        travelersDoneButton.click();
    }

    public void submitMainPage() {
        WebElement submitButton = driver.findElement(By.xpath("//button[@data-testid='submit-button']"));
        submitButton.click();
    }

    public void waitForResultsPage(){
        wait15 = new WebDriverWait(driver, 15);
        wait15.until(ExpectedConditions.urlContains("https://www.expedia.com/Hotel-Search?adults"));
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.expedia.com/Hotel-Search?adults"));
    }

    //    Constructor
    public ExpediaMainPage(WebDriver driver) {
        // Set class level WebDriver as input and as a to local constructor variable level
        // Need to pass the instance of a driver to each method instance
        this.driver = driver;
        //Initialize WebElements from the class level definition in the constructor
        PageFactory.initElements(driver, this);
    }
}
