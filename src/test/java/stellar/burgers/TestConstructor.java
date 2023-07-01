package stellar.burgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

import java.time.Duration;

import static data.specs.Constants.BASE_URL;
import static org.junit.Assert.assertEquals;

public class TestConstructor {

    private static WebDriver driver;

    @Before
    public void getHomePage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(
                                By.cssSelector(".Modal_modal__loading__3534A")));
    }

    @Test
    @DisplayName("Проверка переключения вкладок в конструкторе")
    public void testTabConstructor() {

        HomePage homePage = new HomePage(driver);

        assertEquals(
                "Раздел \"Булки\" неактивен",
                homePage.getBuns().getText(),
                homePage.getActiveTab().getText());

        homePage.clickSaucesButton();
        assertEquals(
                "Раздел \"Соусы\" неактивен",
                homePage.getSauces().getText(),
                homePage.getActiveTab().getText());

        homePage.clickFillingsButton();
        assertEquals(
                "Раздел \"Начинки\" неактивен",
                homePage.getFillings().getText(),
                homePage.getActiveTab().getText());

        homePage.clickBunsButton();
        assertEquals(
                "Раздел \"Булки\" неактивен",
                homePage.getBuns().getText(),
                homePage.getActiveTab().getText());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}