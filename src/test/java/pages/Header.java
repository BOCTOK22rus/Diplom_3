package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {

    private final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    private final By loader = By.cssSelector(".Modal_modal__loading__3534A");
    private final By builderButton = By.xpath(".//p[contains (text(), 'Конструктор')]");
    private final By orderFeedButton = By.xpath(".//p[contains (text(), 'Лента Заказов')]");
    private final By logoButton = By.cssSelector(".AppHeader_header__logo__2D0X2");
    private final By personalAccountButton = By.xpath(".//p[contains (text(), 'Личный Кабинет')]");

    public void clickBuilderButton(){
        driver.findElement(builderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public void clickOrderFeedButton(){
        driver.findElement(orderFeedButton).click();
    }

    public void clickLogoButton(){
        driver.findElement(logoButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public void clickPersonalAccountButton(){
        driver.findElement(personalAccountButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }
}