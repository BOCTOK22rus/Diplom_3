package data.functions;

import data.specs.RequestSpec;
import data.user.Login;
import data.user.User;
import io.restassured.response.Response;

import static data.specs.Constants.*;
import static io.restassured.RestAssured.given;

public class UserClient extends RequestSpec {

    public void createUser(String email, String password, String name) {
        User user = new User(email, password, name);
        given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(CREATE_USER);
    }

    public Response loginUser(String email, String password) {
        Login login = new Login(email, password);
        return given()
                .spec(getSpec())
                .body(login)
                .when()
                .post(LOGIN_USER);
    }

    public void deleteUser(String accessToken) {
        given()
                .spec(getSpec())
                .auth().oauth2(accessToken)
                .delete(USER);
    }
}