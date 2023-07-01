package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    private final WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loader = By.cssSelector(".Modal_modal__loading__3534A");
    private final By exitButton = By.xpath(".//button[contains (text(), 'Выход')]");
    private final By descriptionPageText = By.xpath(".//p[contains (text(), 'В этом разделе вы можете изменить свои персональные данные')]");

    public void clickExitButton(){
        driver.findElement(exitButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public WebElement getDescriptionPageText() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(descriptionPageText));
        return driver.findElement(descriptionPageText);
    }
}