import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Expedia {
    // Setup & test variables declaration
    WebDriver driver;
    String browserType = "chrome";
    WebElement staysButton, goingToButton, searchInput, checkIn, checkOut, addFlightCheckBox, addCarCheckbox, travelers;

    // Test data variables
    String city = "New York, NY";
    String startingLocationCity = "Warsaw";
    String myFirstName = "Mateusz";
    String myLastName = "Styczynski";
    String myPhoneNumber = "570886837";
    String myGender = "male";
    String myMail = "mw.styczynski@gmail.com";
    String myCreditCardNumber = "1234432112344321";
    String myCreditCardSecurityCode = "123";
    String myStreet = "Whatever";
    String myCity = "Cracow";
    String myZipCode = "30-134";
    String herFirstName = "Icant";
    String herLastName = "TellYa";
    String bbFirstName = "Lidia";
    String bbLastName = "WhoIsSmall";


    @Test
    public void hotelReservation() {
        defineWebElements();
        WebDriverWait wait15 = new WebDriverWait(driver, 15);

        // 1. Search on page
        staysButton.click();
        goingToButton.click();
        searchInput.sendKeys(city);
        searchInput.sendKeys(Keys.ENTER);

        checkIn.click();


        WebElement checkInDate_currentMonth = driver.findElement(By.xpath("//button[@class='uitk-new-date-picker-day'][@data-day='13']"));
        wait15.until(ExpectedConditions.elementToBeClickable(checkInDate_currentMonth));
        checkInDate_currentMonth.click();

        WebElement checkOutDate_currentMonth = driver.findElement(By.xpath("//button[@class='uitk-new-date-picker-day'][@data-day='20']"));
        wait15.until(ExpectedConditions.elementToBeClickable(checkOutDate_currentMonth));
        checkOutDate_currentMonth.click();

        WebElement datesDoneButton = driver.findElement(By.xpath("//button[@data-stid='apply-date-picker']"));
        datesDoneButton.click();

        addCarCheckbox.click();
        addFlightCheckBox.click();
        WebElement startingLocationButton = driver.findElement(By.xpath("//button[@aria-label='Leaving from']"));
        wait15.until(ExpectedConditions.elementToBeClickable(startingLocationButton));
        startingLocationButton.click();

        WebElement startingLocation = driver.findElement(By.xpath("//input[@id='location-field-origin']"));
        wait15.until(ExpectedConditions.elementToBeClickable(startingLocation));
        startingLocation.sendKeys(startingLocationCity);
        WebElement startingLocationPick = driver.findElement(By.xpath("(//button[@data-stid='location-field-origin-result-item-button'])[1]"));
        wait15.until(ExpectedConditions.elementToBeClickable(startingLocationPick));
        startingLocationPick.click();

        travelers.click();
        WebElement increaseChildren = driver.findElement(By.xpath("(//button[@class='uitk-button uitk-button-small uitk-flex-item uitk-step-input-button'])[4]"));
        wait15.until(ExpectedConditions.elementToBeClickable(increaseChildren));
        increaseChildren.click();

        WebElement travelersDoneButton = driver.findElement(By.xpath("//button[@data-testid='guests-done-button']"));
        travelersDoneButton.click();

        WebElement submitButton = driver.findElement(By.xpath("//button[@data-testid='submit-button']"));
        submitButton.click();


        // 2. Modify search results, give criteria and filters
        wait15.until(ExpectedConditions.urlContains("https://www.expedia.com/Hotel-Search?adults"));
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.expedia.com/Hotel-Search?adults"));

        // 2b. Adding filters for the search
        WebElement freeCancelTickBox = driver.findElement(By.id("paymentType-0-FREE_CANCELLATION"));
        freeCancelTickBox.click();
        wait15.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='playback-filter-pill-paymentType-FREE_CANCELLATION']"))));

        WebElement star5hotel = driver.findElement(By.xpath("//label[@for='star-4']//span"));
        star5hotel.click();
        wait15.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='playback-filter-pill-star-50']"))));

        new Select(driver.findElement(By.xpath("//select[@id='sort']"))).selectByValue("PRICE_LOW_TO_HIGH");
        wait15.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='playback-filter-pill-sort-PRICE_LOW_TO_HIGH']"))));


        // 3. Analyze the results and make selection
        WebElement pickHotel = driver.findElement(By.xpath("(//section[@class='results']/ol/li)[last()-1]"));
        //always get the 2nd most expensive hotel on the page
        pickHotel.click();

        // As second window was opened we need to switch to second window
        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        // Print hotel name as confirmation
        WebElement hotelName = driver.findElement(By.xpath("//h1[@class='uitk-type-display-700']"));
        System.out.println(hotelName.getText() + "was picked");


        // 4. Book reservation
        WebElement reserveHotelButton = driver.findElement(By.xpath("//button[contains(text(), 'Reserve a room')]"));
        reserveHotelButton.click();
        WebElement reserveRoomButton = driver.findElement(By.xpath("(//button[@data-stid='submit-hotel-reserve'])[1]"));
        reserveRoomButton.click();

        wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-test-id='select-button']")));
        new Select(driver.findElement(By.xpath("//select[@id='sortDropdown']"))).selectByValue("price:desc");

        // One step flight selection to destination
        wait15.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-test-id='select-button']")));
        WebElement selectFlightThere = driver.findElement(By.xpath("(//button[@data-test-id='select-button'])[1]"));
        selectFlightThere.click();

        // Two steps flight back selection
        wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-test-id='select-button']")));
        WebElement selectFlightBack = driver.findElement(By.xpath("(//button[contains(@data-expand-text, 'restrictions apply')])[1]"));
        selectFlightBack.click();

        wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-test-id='select-button-1']")));
        WebElement selectThisFare = driver.findElement(By.xpath("(//button[@data-test-id='select-button-1'])[1]"));
        selectThisFare.click();

        // Car selection
        wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-book-button]")));
        WebElement selectCar = driver.findElement(By.xpath("(//a[@data-book-button])[4]"));
        selectCar.click();


        // 5. Fill out contact / billing info. Only the mandatory fields were put into WebDriver variable
        // First person
        WebElement firstName1 = driver.findElement(By.xpath("//input[@id='firstname[0]']"));
        firstName1.sendKeys(myFirstName);
        WebElement lastName1 = driver.findElement(By.xpath("//input[@id='lastname[0]']"));
        lastName1.sendKeys(myLastName);

        // Phone number and phone country code
        new Select(driver.findElement(By.xpath("//select[@data-cko-rfrr-id='MCKO.CKO.PHONECOUNTRYCODE']"))).selectByValue("48");
        WebElement phoneNumber = driver.findElement(By.xpath("//input[@data-cko-rfrr-id='MCKO.CKO.Phone.NumberEntered']"));
        phoneNumber.sendKeys(myPhoneNumber);

        // Country
        new Select(driver.findElement(By.xpath("//select[@data-cko-rfrr-id='MCKO.CKO.PASSPORTCOUNTRY']"))).selectByValue("5001040");

        // Gender algorithm
        WebElement male = driver.findElement(By.xpath("//input[@id='maleRadio0']"));
        WebElement female = driver.findElement(By.xpath("//input[@id='femaleRadio0']"));
        if (myGender.equalsIgnoreCase("male")) {
            male.click();
        } else
            female.click();

        // Birthdate selects
        new Select(driver.findElement(By.xpath("//select[@data-cko-rfrr-id='MCKO.CKO.DOBMONTH']"))).selectByValue("12");
        new Select(driver.findElement(By.xpath("//select[@data-cko-rfrr-id='MCKO.CKO.DOBDAY']"))).selectByValue("16");
        new Select(driver.findElement(By.xpath("//select[@data-cko-rfrr-id='MCKO.CKO.DOBYEAR']"))).selectByValue("1990");

        // Credit card details
        WebElement creditCardNumber = driver.findElement(By.xpath("//input[@id='creditCardInput']"));
        creditCardNumber.sendKeys(myCreditCardNumber);
        new Select(driver.findElement(By.xpath("//select[@data-tealeaf-name='expirationMonth_1']"))).selectByValue("1");
        new Select(driver.findElement(By.xpath("//select[@data-tealeaf-name='expirationYear_1']"))).selectByValue("2023");
        WebElement creditCardSecurityCode = driver.findElement(By.xpath("//input[@id='new_cc_security_code']"));
        creditCardSecurityCode.sendKeys(myCreditCardSecurityCode);

        // Address and billing details
        new Select(driver.findElement(By.xpath("//select[@data-cko-rfrr-id='MCKO.CKO.BILLINGCOUNTRY']"))).selectByValue("POL");
        WebElement street = driver.findElement(By.xpath("//input[@name='creditCards[0].street']"));
        street.sendKeys(myStreet);
        WebElement zipCode = driver.findElement(By.xpath("//input[@name='creditCards[0].zipcode']"));
        zipCode.sendKeys(myZipCode);
        WebElement city = driver.findElement(By.xpath("//input[@name='creditCards[0].city']"));
        city.sendKeys(myCity);

        // Email input (last field of the page)
        WebElement email = driver.findElement(By.xpath("//input[@data-tealeaf-name='email']"));
        email.sendKeys(myMail);

        // Second person
        WebElement firstName2 = driver.findElement(By.xpath("//input[@id='firstname[1]']"));
        firstName2.sendKeys(herFirstName);
        WebElement lastName2 = driver.findElement(By.xpath("//input[@id='lastname[1]']"));
        lastName2.sendKeys(herLastName);
        WebElement female2 = driver.findElement(By.xpath("//input[@id='femaleRadio1']"));
        female2.click();
        new Select(driver.findElement(By.xpath("//select[contains(@name, 'travelerPreferences[1].passportCountryId')]"))).selectByValue("5001040");
        new Select(driver.findElement(By.xpath("(//select[contains(@name, 'dateOfBirth.day')])[2]"))).selectByValue("01");
        new Select(driver.findElement(By.xpath("(//select[contains(@name, 'dateOfBirth.month')])[2]"))).selectByValue("01");
        new Select(driver.findElement(By.xpath("(//select[contains(@name, 'dateOfBirth.year')])[2]"))).selectByValue("1900");

        // Third person
        WebElement firstName3 = driver.findElement(By.xpath("//input[@id='firstname[2]']"));
        firstName3.sendKeys(bbFirstName);
        WebElement lastName3 = driver.findElement(By.xpath("//input[@id='lastname[2]']"));
        lastName3.sendKeys(bbLastName);
        WebElement bbFemale = driver.findElement(By.xpath("//input[@id='femaleRadio2']"));
        bbFemale.click();
        new Select(driver.findElement(By.xpath("//select[contains(@name, 'travelerPreferences[2].passportCountryId')]"))).selectByValue("5001040");
        new Select(driver.findElement(By.xpath("(//select[contains(@name, 'dateOfBirth.day')])[3]"))).selectByValue("01");
        new Select(driver.findElement(By.xpath("(//select[contains(@name, 'dateOfBirth.month')])[3]"))).selectByValue("01");
        new Select(driver.findElement(By.xpath("(//select[contains(@name, 'dateOfBirth.year')])[3]"))).selectByValue("2020");

        // 6. Get confirmation
        WebElement price = driver.findElement(By.xpath("//span[@class='prod-total-amount']"));
        System.out.println("Total due today: " + price.getText());

        WebElement confirmationButton = driver.findElement(By.xpath("//button[@data-cko-rfrr-id='MCKO.CKO.COMPLETEBOOKING']"));
        Assert.assertTrue(confirmationButton.isDisplayed());
//        confirmationButton.click();

    }

    @BeforeMethod
    public void setUp() {
        driver = utilities.DriverFactory.setDriver(browserType);
        driver.manage().timeouts().implicitlyWait(17, TimeUnit.SECONDS);
        String homePage = "https://www.expedia.com/";
        driver.get(homePage);
        driver.manage().window().maximize();
    }

//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }

    public void defineWebElements() {
        // no WebElement declaration is needed - already declared on the top of the class
        staysButton = driver.findElement(By.xpath("//div[@class='uitk-tabs-container']/ul/li[1]"));
        goingToButton = driver.findElement(By.xpath("//button[@aria-label='Going to']"));
        searchInput = driver.findElement(By.id("location-field-destination"));
        checkIn = driver.findElement(By.id("d1-btn"));
        checkOut = driver.findElement(By.id("d2"));
        addFlightCheckBox = driver.findElement(By.id("add-flight-switch"));
        addCarCheckbox = driver.findElement(By.id("add-car-switch"));
        travelers = driver.findElement(By.xpath("//div[@id='adaptive-menu']"));
        // elements that are not visible on the page from the start can not be declared in that method
        // same applies to dynamic elements which might be visible on the page from the start but their selectors are being refreshed after changes on page
    }
}
