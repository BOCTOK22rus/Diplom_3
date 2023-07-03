package pages;

import data.functions.GetHomePage;
import data.functions.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends GetHomePage {

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        TestBase.driver = driver;
        GetHomePage.wait = wait;
    }

    private final By nameInputField = By.xpath(".//label[contains (text(), 'Имя')]/parent::*/input[@class = 'text input__textfield text_type_main-default']");
    private final By emailInputField = By.xpath(".//label[contains (text(), 'Email')]/parent::*/input[@class = 'text input__textfield text_type_main-default']");
    private final By passwordInputField = By.xpath(".//label[contains (text(), 'Пароль')]/parent::*/input[@class = 'text input__textfield text_type_main-default']");
    private final By registerButton = By.xpath(".//button[contains (text(), 'Зарегистрироваться')]");
    private final By entryLinkButton = By.xpath(".//a[contains (text(), 'Войти')]");
    private final By passwordErrorMessage = By.xpath(".//p[contains (text(), 'Некорректный пароль')]");

    public void fillInputFieldsRegister(String name, String email, String password){
        driver.findElement(nameInputField).click();
        driver.findElement(nameInputField).sendKeys(name);
        driver.findElement(emailInputField).click();
        driver.findElement(emailInputField).sendKeys(email);
        driver.findElement(passwordInputField).click();
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(emailInputField));
    }

    public void clickEntryLinkButton(){
        driver.findElement(entryLinkButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public WebElement getErrorMessage(){
        return driver.findElement(passwordErrorMessage);
    }
}