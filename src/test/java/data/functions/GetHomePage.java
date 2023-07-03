package data.functions;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

import static data.specs.Constants.BASE_URL;

public class GetHomePage extends TestBase {

    protected static By loader = By.cssSelector(".Modal_modal__loading__3534A");
    protected static WebDriverWait wait;
    protected static HomePage homePage;
    protected static LoginPage loginPage;
    protected static RegisterPage registerPage;
    protected static RecoverPasswordPage recoverPasswordPage;
    protected static PersonalAccountPage personalAccountPage;
    protected static Header header;
    protected static UserData userData;
    protected static UserClient userClient;

    @Before
    public void getHomePage() {
        driver.get(BASE_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions
                .invisibilityOfElementLocated(loader));

        homePage = new HomePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        registerPage = new RegisterPage(driver, wait);
        recoverPasswordPage = new RecoverPasswordPage(driver, wait);
        personalAccountPage = new PersonalAccountPage(driver, wait);
        header = new Header(driver, wait);
        userData = new UserData();
        userClient = new UserClient();
    }
}