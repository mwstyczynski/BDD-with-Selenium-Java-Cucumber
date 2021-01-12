package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public static WebDriver setDriver(String browserType) {
        if (browserType.equalsIgnoreCase("chrome")){
            System.out.println("Testing " + browserType);
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\mateusz.styczynski\\Desktop\\untitled\\src\\main\\resources\\chromedriver.exe");
            return new ChromeDriver();
        }
        else if (browserType.equalsIgnoreCase("firefox")){
            System.out.println("Testing " + browserType);
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\mateusz.styczynski\\Desktop\\untitled\\src\\main\\resources\\geckodriver.exe");
            return new ChromeDriver();
        }
        else {
            System.out.println("Testing " + browserType);
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\mateusz.styczynski\\Desktop\\untitled\\src\\main\\resources\\chromedriver.exe");
            return new ChromeDriver();
        }

    }
}