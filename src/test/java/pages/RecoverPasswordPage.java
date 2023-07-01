package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage {

    private final WebDriver driver;

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loader = By.cssSelector(".Modal_modal__loading__3534A");
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
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }
}