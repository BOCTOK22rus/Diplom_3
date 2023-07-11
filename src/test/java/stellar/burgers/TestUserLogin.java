package stellar.burgers;

import data.functions.GetHomePage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestUserLogin extends GetHomePage {

    private String email;
    private String password;

    @Before
    public void registrationUser() {
        String[] newUser = userData.registrationUser();
        email = newUser[0];
        password = newUser[1];
    }

    @Test
    @DisplayName("Проверка входа через кнопку \"Войти в аккаунт\"")
    public void testLoginInAccount() {

        homePage.clickEntryInAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());
    }

    @Test
    @DisplayName("Проверка входа через кнопку \"Личный кабинет\"")
    public void testLoginInAccountPersonalAccountPage() {

        header.clickPersonalAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void testLoginInRegistrationPage() {

        homePage.clickEntryInAccountButton();
        loginPage.clickRegisterButton();
        registerPage.clickEntryLinkButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void testLoginInRecoveryPasswordPage() {

        homePage.clickEntryInAccountButton();
        loginPage.clickRecoverPasswordButton();
        recoverPasswordPage.clickEntryLinkButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());
    }

    @After
    public void deleteUser() {
        Response response = userClient.loginUser(email, password);
        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        userClient.deleteUser(accessToken);
    }
}