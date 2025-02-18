package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isSuccessful(ChromeDriver driver) throws Exception {
        // Navigate to create product page
        driver.get(baseUrl + "/product/create");

        // Debug: Print page source
        System.out.println("Page source: " + driver.getPageSource());

        // Find form elements by name instead of id
        WebElement nameInput = driver.findElement(By.name("productName"));
        WebElement quantityInput = driver.findElement(By.name("productQuantity"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Fill in the product details
        nameInput.sendKeys("Sample Product");
        quantityInput.sendKeys("100");
        submitButton.click();

        // Should redirect to product list page
        assertTrue(driver.getCurrentUrl().contains("/product/list"));

        // Verify product appears in the list
        String pageContent = driver.findElement(By.tagName("body")).getText();
        assertTrue(pageContent.contains("Sample Product"));
        assertTrue(pageContent.contains("100"));
    }

    @Test
    void createProduct_invalidQuantity_staysOnCreatePage(ChromeDriver driver) throws Exception {
        // Navigate to create product page
        driver.get(baseUrl + "/product/create");

        // Find elements by name
        WebElement nameInput = driver.findElement(By.name("productName"));
        WebElement quantityInput = driver.findElement(By.name("productQuantity"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Fill in invalid quantity
        nameInput.sendKeys("Sample Product");
        quantityInput.sendKeys("-1");
        submitButton.click();

        // Should stay on create page
        assertTrue(driver.getCurrentUrl().contains("/product/create"));
    }

    @Test
    void createProductPage_hasCorrectElements(ChromeDriver driver) throws Exception {
        // Navigate to create product page
        driver.get(baseUrl + "/product/create");

        // Find elements using multiple strategies
        assertTrue(driver.findElement(By.name("productName")).isDisplayed(), "Product name field should be visible");
        assertTrue(driver.findElement(By.name("productQuantity")).isDisplayed(), "Product quantity field should be visible");
        assertTrue(driver.findElement(By.cssSelector("button[type='submit']")).isDisplayed(), "Submit button should be visible");

        // Check for form existence
        assertTrue(driver.findElement(By.tagName("form")).isDisplayed(), "Form should be visible");
    }
}