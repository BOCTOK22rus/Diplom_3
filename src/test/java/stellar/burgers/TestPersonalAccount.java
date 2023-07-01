package stellar.burgers;

import data.functions.UserClient;
import data.functions.UserData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

public class TestPersonalAccount {

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
    @DisplayName("Проверка перехода по клику на кнопку \"Конструктор\"")
    public void testGetInConstructor() {
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        HomePage homePage = new HomePage(driver);
        String[] newUser = new UserData().registrationUser();
        String email = newUser[0];
        String password = newUser[1];

        header.clickPersonalAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();

        header.clickPersonalAccountButton();
        assertEquals(
                "Вход не выполнен",
                "В этом разделе вы можете изменить свои персональные данные",
                personalAccountPage.getDescriptionPageText().getText());

        header.clickBuilderButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());

        Response response = new UserClient().loginUser(email, password);
        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        new UserClient().deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка перехода по клику на логотип \"Stellar Burgers\"")
    public void testGetInLogo() {
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        HomePage homePage = new HomePage(driver);
        String[] newUser = new UserData().registrationUser();
        String email = newUser[0];
        String password = newUser[1];

        header.clickPersonalAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();

        header.clickPersonalAccountButton();
        assertEquals(
                "Вход не выполнен",
                "В этом разделе вы можете изменить свои персональные данные",
                personalAccountPage.getDescriptionPageText().getText());

        header.clickLogoButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());

        Response response = new UserClient().loginUser(email, password);
        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        new UserClient().deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета")
    public void testOutInPersonalAccount() {
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        String[] newUser = new UserData().registrationUser();
        String email = newUser[0];
        String password = newUser[1];

        header.clickPersonalAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();

        header.clickPersonalAccountButton();
        header.clickPersonalAccountButton();
        assertEquals(
                "Вход не выполнен",
                "В этом разделе вы можете изменить свои персональные данные",
                personalAccountPage.getDescriptionPageText().getText());

        personalAccountPage.clickExitButton();
        assertEquals(
                "Выход не выполнен",
                "Вход",
                loginPage.getInputText().getText());

        Response response = new UserClient().loginUser(email, password);
        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        new UserClient().deleteUser(accessToken);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}