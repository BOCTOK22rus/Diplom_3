package stellar.burgers;

import data.functions.GetHomePage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPersonalAccount extends GetHomePage {

    private String email;
    private String password;

    @Before
    public void registrationUser() {
        String[] newUser = userData.registrationUser();
        email = newUser[0];
        password = newUser[1];
    }

    @Test
    @DisplayName("Проверка перехода по клику на кнопку \"Конструктор\"")
    public void testGetInConstructor() {

        header.clickPersonalAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        header.clickPersonalAccountButton();
        header.clickBuilderButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());
    }

    @Test
    @DisplayName("Проверка перехода по клику на логотип \"Stellar Burgers\"")
    public void testGetInLogo() {

        header.clickPersonalAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        header.clickPersonalAccountButton();
        header.clickLogoButton();
        assertEquals(
                "Вход не выполнен",
                "Оформить заказ",
                homePage.getOrderButton().getText());
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета")
    public void testOutInPersonalAccount() {

        header.clickPersonalAccountButton();
        loginPage.clickEntryButton();
        loginPage.fillInputFieldsLogin(email, password);
        loginPage.clickEntryButton();
        header.clickPersonalAccountButton();
        header.clickPersonalAccountButton();
        personalAccountPage.clickExitButton();
        assertEquals(
                "Выход не выполнен",
                "Вход",
                loginPage.getInputText().getText());
    }

    @After
    public void deleteUser() {
        Response response = userClient.loginUser(email, password);
        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        userClient.deleteUser(accessToken);
    }
}