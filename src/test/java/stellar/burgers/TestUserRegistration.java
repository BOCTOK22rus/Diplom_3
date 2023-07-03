package stellar.burgers;

import data.functions.GetHomePage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class TestUserRegistration extends GetHomePage {

    private String email;
    private String password;
    private String name;

    @Before
    public void getUserData() {
        email = userData.getUserEmail();
        password = userData.getUserPassword();
        name = userData.getUserName();
    }

    @Test
    @DisplayName("Проверка регистрации пользователя")
    public void testUserRegistration() {

        homePage.clickEntryInAccountButton();
        loginPage.clickRegisterButton();
        registerPage.fillInputFieldsRegister(name, email, password);
        registerPage.clickRegisterButton();

        Response response = userClient.loginUser(email, password);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(email.toLowerCase()))
                .body("user.name", equalTo(name));

        String accessToken = response.jsonPath().getString("accessToken").substring(7);
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка регистрации пользователя с некорректным паролем")
    public void testUserRegistrationWrongPassword() {

        homePage.clickEntryInAccountButton();
        loginPage.clickRegisterButton();
        registerPage.fillInputFieldsRegister(name, email, password.substring(3));
        registerPage.clickRegisterButton();
        assertTrue(
                "Текст ошибки не отображается",
                registerPage.getErrorMessage().isDisplayed());
    }
}