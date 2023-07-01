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
import pages.*;

import java.time.Duration;

import static data.specs.Constants.BASE_URL;
import static org.junit.Assert.assertEquals;

public class TestUserLogin {

    private WebDriver driver;

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
    @DisplayName("Проверка входа через кнопку \"Войти в аккаунт\"")
    public void testLoginInAccount() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        String[] newUser = new UserData().registrationUser();
        String email = newUser[0];
        String password = newUser[1];

        homePage.clickEntryInAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());

        Response response = new UserClient().loginUser(email, password);
        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        new UserClient().deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка входа через кнопку \"Личный кабинет\"")
    public void testLoginInAccountPersonalAccountPage() {
        Header header = new Header(driver);
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        String[] newUser = new UserData().registrationUser();
        String email = newUser[0];
        String password = newUser[1];

        header.clickPersonalAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());

        Response response = new UserClient().loginUser(email, password);
        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        new UserClient().deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void testLoginInRegistrationPage() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        String[] newUser = new UserData().registrationUser();
        String email = newUser[0];
        String password = newUser[1];

        homePage.clickEntryInAccountButton();
        loginPage.clickRegisterButton();
        registerPage.clickEntryLinkButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());

        Response response = new UserClient().loginUser(email, password);
        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        new UserClient().deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void testLoginInRecoveryPasswordPage() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        String[] newUser = new UserData().registrationUser();
        String email = newUser[0];
        String password = newUser[1];

        homePage.clickEntryInAccountButton();
        loginPage.clickRecoverPasswordButton();
        recoverPasswordPage.clickEntryLinkButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());

        Response response = new UserClient().loginUser(email, password);
        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        new UserClient().deleteUser(accessToken);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}