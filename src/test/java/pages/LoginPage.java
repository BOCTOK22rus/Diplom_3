package pages;

import data.functions.GetHomePage;
import data.functions.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends GetHomePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        TestBase.driver = driver;
        GetHomePage.wait = wait;
    }

    private final By emailInputField = By.xpath(".//label[contains (text(), 'Email')]/parent::*/input[@class = 'text input__textfield text_type_main-default']");
    private final By passwordInputField = By.xpath(".//label[contains (text(), 'Пароль')]/parent::*/input[@class = 'text input__textfield text_type_main-default']");
    private final By entryButton = By.xpath(".//button[contains (text(), 'Войти')]");
    private final By recoverPasswordButton = By.xpath(".//a[contains (text(), 'Восстановить пароль')]");
    private final By registerButton = By.xpath(".//a[contains (text(), 'Зарегистрироваться')]");
    private final By inputText = By.xpath(".//h2[contains (text(), 'Вход')]");

    public void fillInputFieldsLogin(String email, String password){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        wait.until(ExpectedConditions.visibilityOfElementLocated(entryButton));
        driver.findElement(emailInputField).click();
        driver.findElement(emailInputField).sendKeys(email);
        driver.findElement(passwordInputField).click();
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public void clickEntryButton(){
        driver.findElement(entryButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public void clickRecoverPasswordButton(){
        driver.findElement(recoverPasswordButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public WebElement getInputText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputText));
        return driver.findElement(inputText);
    }
}