package data.functions;

import org.apache.commons.lang3.RandomStringUtils;

import static data.specs.Constants.DOMAIN_EMAIL;

public class UserData {

    private final String email = RandomStringUtils.randomAlphabetic(6) + DOMAIN_EMAIL;
    private final String password = RandomStringUtils.randomAlphabetic(8);
    private final String name = RandomStringUtils.randomAlphabetic(8);

    public String getUserName() {
        return name;
    }

    public String getUserEmail() {
        return email;
    }

    public String getUserPassword() {
        return password;
    }

    public String[] registrationUser() {
        UserClient userClient = new UserClient();
        userClient.createUser(email, password, name);
        return new String[]{email, password};
    }
}