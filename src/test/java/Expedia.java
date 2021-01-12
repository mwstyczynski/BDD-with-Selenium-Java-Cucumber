import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Expedia {
    //    Setup & test variables declaration
    WebDriver driver;
    String browserType = "chrome";
    WebElement staysButton, goingToButton, searchInput, checkIn, checkOut, addFlightCheckBox, addCarCheckbox, startingLocation, checkInDate_currentMonth, checkOutDate_currentMonth, datesDoneButton, travelers, increaseChildren, startingLocationButton, travelersDoneButton, startingLocationPick, submitButton;
    //    Test data variables
    String city = "New York, NY";
    String startingLocationCity = "Warsaw";


    @Test
    public void hotelReservation() {
        defineWebElements();

        // 1. Search on page
        staysButton.click();
        goingToButton.click();
        searchInput.sendKeys(city);
        searchInput.sendKeys(Keys.ENTER);

        checkIn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        checkInDate_currentMonth = driver.findElement(By.xpath("//button[@class='uitk-new-date-picker-day'][@data-day='13']"));
        wait.until(ExpectedConditions.elementToBeClickable(checkInDate_currentMonth));
        checkInDate_currentMonth.click();

        checkOutDate_currentMonth = driver.findElement(By.xpath("//button[@class='uitk-new-date-picker-day'][@data-day='20']"));
        wait.until(ExpectedConditions.elementToBeClickable(checkOutDate_currentMonth));
        checkOutDate_currentMonth.click();

        datesDoneButton = driver.findElement(By.xpath("//button[@data-stid='apply-date-picker']"));
        datesDoneButton.click();

        addCarCheckbox.click();

        addFlightCheckBox.click();
        startingLocationButton = driver.findElement(By.xpath("//button[@aria-label='Leaving from']"));
        wait.until(ExpectedConditions.elementToBeClickable(startingLocationButton));
        startingLocationButton.click();

        startingLocation = driver.findElement(By.xpath("//input[@id='location-field-origin']"));
        wait.until(ExpectedConditions.elementToBeClickable(startingLocation));
        startingLocation.sendKeys(startingLocationCity);
        startingLocationPick = driver.findElement(By.xpath("(//button[@data-stid='location-field-origin-result-item-button'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(startingLocationPick));
        startingLocationPick.click();

        travelers.click();
         //  selector used to be:    *[@aria-labelledby='uitk-step-increase-children-2-title']
        increaseChildren = driver.findElement(By.xpath("(//button[@class='uitk-button uitk-button-small uitk-flex-item uitk-step-input-button'])[4]"));
        wait.until(ExpectedConditions.elementToBeClickable(increaseChildren));
        increaseChildren.click();

        travelersDoneButton = driver.findElement(By.xpath("//button[@data-testid='guests-done-button']"));
        travelersDoneButton.click();

        submitButton.click();

        // 2. Modify search results, give criteria and filters

        // 3. Analyze the results and make selection

        // 4. Book reservation

        // 5. Fill out contact / billing info

        // 6. Get confirmation

    }

    @BeforeMethod
    public void setUp() {
        driver = utilities.DriverFactory.setDriver(browserType);
        String homePage = "https://www.expedia.com/";
        driver.get(homePage);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void defineWebElements() {
//        no WebElement declaration is needed - already declared on the top of the class
        staysButton = driver.findElement(By.xpath("//div[@class='uitk-tabs-container']/ul/li[1]"));
        goingToButton = driver.findElement(By.xpath("//button[@aria-label='Going to']"));
        searchInput = driver.findElement(By.id("location-field-destination"));
        checkIn = driver.findElement(By.id("d1-btn"));
        checkOut = driver.findElement(By.id("d2"));
        addFlightCheckBox = driver.findElement(By.id("add-flight-switch"));
        addCarCheckbox = driver.findElement(By.id("add-car-switch"));
        travelers = driver.findElement(By.xpath("//div[@id='adaptive-menu']"));
        submitButton = driver.findElement(By.xpath("//button[@data-testid='submit-button']"));
//        elements that are not visible on the page from the start can not be declared in that method
    }
}
