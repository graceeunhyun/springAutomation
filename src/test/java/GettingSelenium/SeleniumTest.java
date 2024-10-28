package GettingSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest
{
    public static void main(String[] args)
    {
        System.out.println("Hello, Selenium!");
        //create web driver with chrome drvier object
        // launch the browser
        WebDriver webDriver = new ChromeDriver();
        webDriver.navigate().to("https://ww.google.com");

        webDriver.manage().window().fullscreen();

        //click the login test box
        By locator = By.linkText("로그인");

        WebElement element = webDriver.findElement(locator);
        element.click();
    }
}
