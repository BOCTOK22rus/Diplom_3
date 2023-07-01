package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loader = By.cssSelector(".Modal_modal__loading__3534A");
    private final By bunsButton = By.xpath(".//span[contains (text(), 'Булки')]/parent::*");
    private final By saucesButton = By.xpath(".//span[contains (text(), 'Соусы')]/parent::*");
    private final By fillingsButton = By.xpath(".//span[contains (text(), 'Начинки')]/parent::*");
    private final By entryInAccountButton = By.xpath(".//button[contains (text(), 'Войти в аккаунт')]");
    private final By activeTab = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/child::*");
    private final By placeOrderButton = By.xpath(".//button[contains (text(), 'Оформить заказ')]");

    public WebElement getSauces() {
        return driver.findElement(saucesButton);
    }

    public WebElement getBuns() {
        return driver.findElement(bunsButton);
    }

    public WebElement getFillings() {
        return driver.findElement(fillingsButton);
    }

    public WebElement getActiveTab() {
        return driver.findElement(activeTab);
    }

    public WebElement getOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(loader));
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        return driver.findElement(placeOrderButton);
    }

    public void clickBunsButton(){
        driver.findElement(bunsButton).click();

    }

    public void clickSaucesButton(){
        driver.findElement(saucesButton).click();
    }

    public void clickFillingsButton(){
        driver.findElement(fillingsButton).click();
    }

    public void clickEntryInAccountButton(){
        driver.findElement(entryInAccountButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }
}