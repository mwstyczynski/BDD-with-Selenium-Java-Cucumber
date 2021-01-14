package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PaymentAndContactPage {
    WebDriver driver;
    WebDriverWait wait15;

    public PaymentAndContactPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElements


    // Test input data
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


    public void fillOutFirstPersonData(){
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

    }

    public void fillOutSecondPersonData(){
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
    }

    public void fillOutThirdPersonData(){
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
    }

    public void fillOutPaymentAndContactData(){
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
    }

    public void getPriceAndConfirmation(){
        WebElement price = driver.findElement(By.xpath("//span[@class='prod-total-amount']"));
        System.out.println("Total due today: " + price.getText());

        WebElement confirmationButton = driver.findElement(By.xpath("//button[@data-cko-rfrr-id='MCKO.CKO.COMPLETEBOOKING']"));
        Assert.assertTrue(confirmationButton.isDisplayed());
//        confirmationButton.click();
    }

}
