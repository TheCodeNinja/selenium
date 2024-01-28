package com.codedev.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class WebDriverMethodsTest {

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
    public void testSelectDropdownOption() throws InterruptedException {
        log.info("testSelectDropdownOption ...");

        // navigate to the webpage
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Thread.sleep(1000);

        // find the dropdown element
        WebElement dropdownEl = driver.findElement(By.id("dropdown"));
        Thread.sleep(1000);

        // click the dropdown element
        dropdownEl.click();
        Thread.sleep(1000);

        // find all the dropdown options
        WebElement option1 = dropdownEl.findElement(By.cssSelector("option[value='1']"));
        WebElement option2 = dropdownEl.findElement(By.cssSelector("option[value='2']"));

        // click the option1
        option1.click();
        Thread.sleep(1000);

        // assert option1 is selected, option2 is not selected
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
    }

    @Test
    public void testMouseHover() throws InterruptedException {
        log.info("testMouseHover ...");

        // navigate to the webpage
        driver.get("https://the-internet.herokuapp.com/hovers");
        Thread.sleep(1000);

        // find the 1st element with className 'figure'
        WebElement element = driver.findElement(By.className("figure"));
        Thread.sleep(1000);

        // perform mouse hover event
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        Thread.sleep(1000);

        // find the element (display when the above element is hovered)
        driver.findElement(By.xpath("//*[contains(text(), 'name: user1')]"));

        assertTrue(element.isDisplayed(), "user1 should appear because we hovered over that image");
    }

    @Test
    public void testMouseRightClick() throws InterruptedException {
        log.info("testMouseRightClick ...");

        // navigate to the url
        driver.navigate().to("https://the-internet.herokuapp.com/context_menu");
        Thread.sleep(1000);

        // find the element
        WebElement element = driver.findElement(By.id("hot-spot"));

        // perform mouse right click
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        Thread.sleep(1000);

        // show the alert when mouse right click,
        // then click alert ok button
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
    }

    @Test
    public void testKeyPress() throws InterruptedException {
        log.info("testKeyPress ...");

        // navigate to the url
        driver.navigate().to("https://the-internet.herokuapp.com/key_presses");
        Thread.sleep(1000);

        // file the element
        WebElement element = driver.findElement(By.id("target"));

        // click the element
        element.click();
        Thread.sleep(1000);

        // perform keyboard key press event
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_RIGHT)
                .pause(1000)
                .perform();
        Thread.sleep(1000);

        //
        WebElement element2 = driver.findElement(By.id("result"));

        // assertEquals(expected, actual, message)
        assertEquals("You entered: RIGHT", element2.getText(), "Right arrow key should be pressed!");
    }

    @Test
    public void testGetCssValue() {
        log.info("testGetCssValue ...");

        // navigate to the url
        driver.navigate().to("https://ultimateqa.com/simple-html-elements-for-automation/");

        // find the element
        WebElement element = driver.findElement(By.linkText("Clickable Icon"));

        // get the attribute of the element
        String link = element.getAttribute("href");

        // assertEquals(expected, actual)
        assertEquals("https://ultimateqa.com/link-success/", link);
        assertEquals("padding-box", element.getCssValue("background-origin"));
    }

    // Assert an element is focused
    @Test
    public void testMouseFocus() throws InterruptedException {
        log.info("testMouseFocus ...");

        // navigate to the url
        driver.get("https://example.cypress.io/commands/actions");
        Thread.sleep(1000);

        // find the element
        WebElement element = driver.findElement(By.cssSelector(".action-focus"));

        // perform mouse focus event
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        Thread.sleep(2000);

        // find the element
        WebElement element2 = driver.findElement(By.xpath("//*[@for='password1']"));

        // assertTrue(condition)
        assertTrue(element2.getAttribute("style").contains("color: orange;"));
    }

    @Test
    public void testCookies() throws InterruptedException {
        log.info("testCookies ...");

        // navigate to the url
        driver.get("https://example.cypress.io/commands/cookies");
        Thread.sleep(2000);

        // find the element
        WebElement element = driver.findElement(By.cssSelector(".set-a-cookie"));

        // click the element
        element.click();
        Thread.sleep(2000);

        // get the cookie
        var cookie = driver.manage().getCookieNamed("token");

        // assertEquals(expected, actual, message)
        assertEquals("123ABC", cookie.getValue(), "cookie value should be '123ABC'");
    }
}
