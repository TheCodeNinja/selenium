package com.codedev.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
class SeleniumApplicationTests {

	// Setting up the WebDriver automatically
	// This method will run once before all the tests in the class
	@BeforeAll
	public static void setup() {
		System.out.println("setting up web driver manager ...");
		WebDriverManager.chromedriver().setup();
		// WebDriverManager.safaridriver().setup();
		// WebDriverManager.edgedriver().setup();
	}

	@Test
	public void testOpenUrl() throws InterruptedException {
		log.info("testOpenUrl ...");
		// create the driver
		WebDriver driver = new ChromeDriver();
		// WebDriver driver = new SafariDriver();

		// navigate to the URL
		driver.get("https://www.saucedemo.com/");

		// Wait website elements to finish loading
		// When the element cannot be found, throw an exception
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
		WebElement element = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("user-name")));

		// let browser keep open for some time
		Thread.sleep(3000);

		assertTrue(element.isDisplayed());

		driver.quit();
	}


}
