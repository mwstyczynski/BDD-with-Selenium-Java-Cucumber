import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RandomAllegroTest {

    WebDriver driver;

    @Test
    public void AutomationTestTemplate() {
        System.out.println("Executing the test");

        WebElement dodajDoKoszyka = driver.findElement(By.id("add-to-cart-button"));
        dodajDoKoszyka.click();

        WebDriverWait wait = new WebDriverWait(driver, 7);
        WebElement przejdzDoKoszyka = driver.findElement(By.xpath("//a[@data-analytics-click-label='goToCart']"));
//
        wait.until(ExpectedConditions.visibilityOf(przejdzDoKoszyka));
        Assert.assertTrue(przejdzDoKoszyka.isDisplayed());
    }

    @BeforeClass
    public void setUp(){
        System.out.println("Setup procedure started");
        driver = utilities.DriverFactory.setDriver("chrome");
        String homePage = "https://allegro.pl/oferta/lampa-sufitowa-coba-antique-3xe27-60w-industrial-8384410730?utm_feed=aa34192d-eee2-4419-9a9a-de66b9dfae24";
        driver.get(homePage);
        driver.manage().window().maximize();
        WebElement okZgadzamSie = driver.findElement(By.xpath("//button[starts-with(@class, 'm7er_0k')][1]"));
        okZgadzamSie.click();

    }

    @AfterClass
    public void tareDown(){
        System.out.println("Finishing the test");
        driver.close();
    }
}
