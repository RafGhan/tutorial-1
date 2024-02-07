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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void productListPage_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement createButton = driver.findElement(By.className("btn"));
        createButton.click();

        String title = driver.getTitle();
        assertEquals("Product List", title);
    }

    @Test
    void createProductPage_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        WebElement checkInput = driver.findElement(By.className("btn"));
        checkInput.click();

        WebElement createProductButton = driver.findElement(By.cssSelector(".btn.btn-primary.btn-sm.mb-3"));
        createProductButton.click();

        String titleCreateProduct = driver.getTitle();
        assertEquals("Create New Product", titleCreateProduct);
    }

    @Test
    void createProductSubmit_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement checkInput = driver.findElement(By.className("btn"));
        checkInput.click();

        WebElement createProductButton = driver.findElement(By.cssSelector(".btn.btn-primary.btn-sm.mb-3"));
        createProductButton.click();

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.clear();
        nameInput.sendKeys("test");

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.clear();
        quantityInput.sendKeys("2");

        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        assertEquals("Product List", driver.getTitle());

        WebElement nameInTable = driver.findElement(By.xpath("//tr[last()]/td[1]"));
        String productName = nameInTable.getText();
        assertEquals("test", productName);

        WebElement quantityInTable = driver.findElement(By.xpath("//tr[last()]/td[2]"));
        String productQuantity = quantityInTable.getText();
        assertEquals("2", productQuantity);
    }
}