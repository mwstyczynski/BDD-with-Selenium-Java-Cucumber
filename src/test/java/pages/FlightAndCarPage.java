package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightAndCarPage {
    WebDriver driver;
    WebDriverWait wait15;

    public FlightAndCarPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //WebElements

    public void pickTheFlights(){
        wait15 = new WebDriverWait(driver, 15);
        // Setting a price filter of a flight to destination
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
    }

    public void pickTheCar(){
        wait15 = new WebDriverWait(driver, 15);
        // Car selection
        wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-book-button]")));
        WebElement selectCar = driver.findElement(By.xpath("(//a[@data-book-button])[4]"));
        selectCar.click();
    }
}
