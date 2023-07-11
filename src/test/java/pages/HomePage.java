package pages;

import data.functions.GetHomePage;
import data.functions.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends GetHomePage {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        TestBase.driver = driver;
        GetHomePage.wait = wait;
    }

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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }
}