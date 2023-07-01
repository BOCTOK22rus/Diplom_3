package stellar.burgers;

import data.functions.UserClient;
import data.functions.UserData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

import java.time.Duration;

import static data.specs.Constants.BASE_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class TestUserRegistration {

    private static WebDriver driver;

    @Before
    public void getHomePage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(
                                By.cssSelector(".Modal_modal__loading__3534A")));
    }

    @Test
    @DisplayName("Проверка регистрации пользователя")
    public void testUserRegistration() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        String email = new UserData().getUserEmail();
        String password = new UserData().getUserPassword();
        String name = new UserData().getUserName();

        homePage.clickEntryInAccountButton();
        loginPage.clickRegisterButton();
        registerPage.fillInputFieldsRegister(name, email, password);
        registerPage.clickRegisterButton();

        Response response = new UserClient().loginUser(email, password);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(email.toLowerCase()))
                .body("user.name", equalTo(name));

        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        new UserClient().deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка регистрации пользователя с некорректным паролем")
    public void testUserRegistrationWrongPassword() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        String email = new UserData().getUserEmail();
        String password = new UserData().getUserPassword().substring(3);
        String name = new UserData().getUserName();

        homePage.clickEntryInAccountButton();
        loginPage.clickRegisterButton();
        registerPage.fillInputFieldsRegister(name, email, password);
        registerPage.clickRegisterButton();
        assertTrue(
                "Текст ошибки не отображается",
                registerPage.getErrorMessage().isDisplayed());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}