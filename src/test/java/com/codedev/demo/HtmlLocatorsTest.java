package com.codedev.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class HtmlLocatorsTest {

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
    public void navigateToUrl() {
        log.info("navigateToUrl ...");

        String url = "https://www.saucedemo.com/";

        // create the driver
        driver = new ChromeDriver();

        // navigate to the URL
        driver.get(url);
    }

    @AfterEach
    public void cleanDriver() {
        log.info("clean driver ...");
        driver.quit();
    }

    @Test
    public void testIdLocator() throws InterruptedException {
        log.info("testIdLocator ...");

        // find element
        WebElement element = driver.findElement(By.id("user-name")); // by id

        // let browser keep open for some time
        Thread.sleep(3000);
        assertTrue(element.isDisplayed());
    }

    @Test
    public void testTagNameLocator() throws InterruptedException {
        log.info("testTagNameLocator ...");

        // find element
        WebElement element = driver.findElement(By.tagName("input")); // by tag

        // let browser keep open for some time
        Thread.sleep(3000);
        assertTrue(element.isDisplayed());
    }

    @Test
    public void testNameLocator() throws InterruptedException {
        log.info("testNameLocator ...");

        // find element
        WebElement element = driver.findElement(By.name("name of locator")); // by name

        // let browser keep open for some time
        Thread.sleep(3000);
        assertTrue(element.isDisplayed());
    }

    @Test
    public void testClassNameLocator() throws InterruptedException {
        log.info("testClassNameLocator ...");

        // find element
        WebElement element = driver.findElement(By.className("form_input")); // by classname

        // let browser keep open for some time
        Thread.sleep(3000);
        assertTrue(element.isDisplayed());
    }

    @Test
    public void testCssSelectorLocator() throws InterruptedException {
        log.info("testCssSelectorLocator ...");

        // find element
        WebElement element = driver.findElement(By.cssSelector("#user-name"));

        // let browser keep open for some time
        Thread.sleep(3000);
        assertTrue(element.isDisplayed());
    }

    @Test
    public void testXpathLocator() throws InterruptedException {
        log.info("testXpathLocator ...");

        // find element
        WebElement element = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));

        // let browser keep open for some time
        Thread.sleep(3000);
        assertTrue(element.isDisplayed());
    }

    @Test
    public void testLinkTextLocator() throws InterruptedException {
        log.info("testLinkTextLocator ...");

        // find element
        WebElement element = driver.findElement(By.linkText("Click me using this link text!"));
        // WebElement element2 = driver.findElement(By.partialLinkText("link text!"));

        // let browser keep open for some time
        Thread.sleep(3000);
        assertTrue(element.isDisplayed());
    }

}
