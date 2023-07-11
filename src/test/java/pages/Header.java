package pages;

import data.functions.GetHomePage;
import data.functions.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header extends GetHomePage {

    public Header(WebDriver driver, WebDriverWait wait) {
        TestBase.driver = driver;
        GetHomePage.wait = wait;
    }

    private final By builderButton = By.xpath(".//p[contains (text(), 'Конструктор')]");
    private final By orderFeedButton = By.xpath(".//p[contains (text(), 'Лента Заказов')]");
    private final By logoButton = By.cssSelector(".AppHeader_header__logo__2D0X2");
    private final By personalAccountButton = By.xpath(".//p[contains (text(), 'Личный Кабинет')]");

    public void clickBuilderButton(){
        driver.findElement(builderButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(GetHomePage.loader));
    }

    public void clickOrderFeedButton(){
        driver.findElement(orderFeedButton).click();
    }

    public void clickLogoButton(){
        driver.findElement(logoButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public void clickPersonalAccountButton(){
        driver.findElement(personalAccountButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }
}