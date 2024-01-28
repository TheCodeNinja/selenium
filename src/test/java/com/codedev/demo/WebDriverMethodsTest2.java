package com.codedev.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class WebDriverMethodsTest2 {

    WebDriver driver;

    // Setting up the WebDriver automatically
    // This method will run once before all the tests in the class
    @BeforeAll
    public static void setup() {
        System.out.println("setting up web driver manager ...");
        WebDriverManager.chromedriver().setup();
        // WebDriverManager.safaridriver().setup();
        // WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    public void createWebDriver() {
        log.info("create web driver ...");

        // create the driver
        driver = new ChromeDriver();
    }

    @AfterEach
    public void cleanDriver() {
        log.info("clean driver ...");
        driver.quit();
    }

    @Test
    public void testJs() throws InterruptedException {
        // navigate to the url
        driver.navigate().to("https://the-internet.herokuapp.com/windows");
        Thread.sleep(1500);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // open a new blank window
        js.executeScript("window.open('https://the-internet.herokuapp.com/windows/new');");
        Thread.sleep(1500);

        // get the 1st opened window handle
        // (CDwindow-8902FAA37CC3C4C4F8234365F54B49AE)
        String handle = driver.getWindowHandle();
        log.info(handle);

        // get all opened window handles
        // [CDwindow-8902FAA37CC3C4C4F8234365F54B49AE, CDwindow-9D128A3F36C90D15D7577C25B584E0BD]
        Set handles = driver.getWindowHandles();
        log.info(handles.toString());

        // remove the 1st window handle
        handles.remove(handle);

        // get the next window from handles
        String nextWindow = String.valueOf(handles.iterator().next());

        // switch to this window
        driver.switchTo().window(nextWindow);
        Thread.sleep(1500);
        assertEquals("New Window", driver.getTitle());

        // close
        driver.close();

        // switch to
        driver.switchTo().window(handle);
        Thread.sleep(1500);
        assertEquals("The Internet", driver.getTitle());
    }
}
