package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class SearchResultsPage {

//    Class level WebDriver
    WebDriver driver;
    WebDriverWait wait15;

//    WebElements
    @FindBy(id = "paymentType-0-FREE_CANCELLATION")
    WebElement freeCancelTickBox;

    @FindBy(xpath = "//label[@for='star-4']//span")
    WebElement star5hotel;

    @FindBy(xpath = "(//section[@class='results']/ol/li)[last()-1]")
    WebElement pickHotel;

    public void addFreeCancelFiltering() {
        wait15 = new WebDriverWait(driver, 15);
        freeCancelTickBox.click();
        wait15.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='playback-filter-pill-paymentType-FREE_CANCELLATION']"))));
    }

    public void addOnlyFiveStarsFiltering() {
        wait15 = new WebDriverWait(driver, 15);
        star5hotel.click();
        wait15.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='playback-filter-pill-star-50']"))));
    }

    public void sortByPrice(){
        wait15 = new WebDriverWait(driver, 15);
        new Select(driver.findElement(By.xpath("//select[@id='sort']"))).selectByValue("PRICE_LOW_TO_HIGH");
        wait15.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@id='playback-filter-pill-sort-PRICE_LOW_TO_HIGH']"))));
    }

    public void pickHotel(){
        wait15 = new WebDriverWait(driver, 15);
        //always get the 2nd most expensive hotel on the page
        pickHotel.click();
    }

    public void goToHotelTabAndGetHotelName(){
        // As second window was opened we need to switch to second window
        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        // Print hotel name as confirmation
        WebElement hotelName = driver.findElement(By.xpath("//h1[@class='uitk-type-display-700']"));
        System.out.println("A 5 star hotel: " +hotelName.getText() + " was picked");
    }

    public void bookReservation(){
        WebElement reserveHotelButton = driver.findElement(By.xpath("//button[contains(text(), 'Reserve a room')]"));
        reserveHotelButton.click();
        WebElement reserveRoomButton = driver.findElement(By.xpath("(//button[@data-stid='submit-hotel-reserve'])[1]"));
        reserveRoomButton.click();
    }


    //    Constructor receiving a WebDriver
    public SearchResultsPage(WebDriver driver){
        // Set class level WebDriver as input and as a to local constructor variable level
        // Need to pass the instance of a driver to each method instance
        this.driver = driver;
        //Initialize WebElements from the class level definition in the constructor
        PageFactory.initElements(driver, this);

    }
}
