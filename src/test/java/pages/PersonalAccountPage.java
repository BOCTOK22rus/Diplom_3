package pages;

import data.functions.GetHomePage;
import data.functions.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage extends GetHomePage {

    public PersonalAccountPage(WebDriver driver, WebDriverWait wait) {
        TestBase.driver = driver;
        GetHomePage.wait = wait;
    }

    private final By exitButton = By.xpath(".//button[contains (text(), 'Выход')]");
    private final By descriptionPageText = By.xpath(".//p[contains (text(), 'В этом разделе вы можете изменить свои персональные данные')]");

    public void clickExitButton(){
        driver.findElement(exitButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public WebElement getDescriptionPageText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionPageText));
        return driver.findElement(descriptionPageText);
    }
}