package com.codedev.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class HtmlLocatorsExamTest {

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

        // String url = "https://www.saucedemo.com/";
        String url = "http://localhost:3000";

        // create the driver
        driver = new ChromeDriver();

        driver.manage()
                .timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);

        // navigate to the URL
        driver.get(url);
    }

    @AfterEach
    public void cleanDriver() {
        log.info("clean driver ...");
        driver.quit();
    }

    @Test
    public void testReactForm() throws InterruptedException {
        log.info("Start ...");
        Thread.sleep(2000);

        driver.findElement(By.id("Create Note")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("noteTitle")).sendKeys("Love kubernetes");
        Thread.sleep(2000);

        driver.findElement(By.id("size")).sendKeys("5");
        Thread.sleep(2000);
    }

    @Test
    public void testAutoOnlineShopping() throws InterruptedException {
        log.info("testAutoOnlineShopping ...");
        Thread.sleep(2000);

        // login
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();
        Thread.sleep(2000);

        // click `ADD TO CART` button of the specified item
        driver.findElement(By.xpath("//*[@class='btn btn_primary btn_small btn_inventory']")).click();
        Thread.sleep(2000);

        // click the `shopping-cart` icon
        driver.findElement(By.cssSelector("#shopping_cart_container")).click();
        Thread.sleep(2000);

        // click the `checkout` button
        driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();
        Thread.sleep(2000);

        // fill in the `checkout` form
        driver.findElement(By.id("first-name")).sendKeys("first name");
        driver.findElement(By.id("last-name")).sendKeys("last name");
        driver.findElement(By.id("postal-code")).sendKeys("zip");
        Thread.sleep(2000);

        // click the `continue` button
        driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();
        Thread.sleep(2000);

        // click the `finish` button
        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);

        assertTrue(driver.findElement(By.cssSelector("#checkout_complete_container")).isDisplayed());
    }
}
