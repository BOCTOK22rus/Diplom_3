package pages;

import data.functions.GetHomePage;
import data.functions.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage extends GetHomePage {

    public RecoverPasswordPage(WebDriver driver, WebDriverWait wait) {
        TestBase.driver = driver;
        GetHomePage.wait = wait;
    }

    private final By emailInputField = By.xpath(".//label[contains (text(), 'Email')]");
    private final By recoverButton = By.xpath(".//button[contains (text(), 'Восстановить')]");
    private final By entryLinkButton = By.xpath(".//a[contains (text(), 'Войти')]");

    public void clickEmailInputField(){
        driver.findElement(emailInputField).click();
    }

    public void clickRecoverButton(){
        driver.findElement(recoverButton).click();
    }

    public void clickEntryLinkButton(){
        driver.findElement(entryLinkButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }
}