package stellar.burgers;

import data.functions.GetHomePage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class TestConstructor extends GetHomePage {

    @Test
    @DisplayName("Проверка переключения на вкладку \"Булки\" в конструкторе")
    public void testTabBunsConstructor() {

        if (!Objects.equals(homePage.getActiveTab().getText(), homePage.getBuns().getText())) {
            homePage.clickBunsButton();
            assertEquals(
                    "Раздел \"Булки\" неактивен",
                    homePage.getBuns().getText(),
                    homePage.getActiveTab().getText());
        }
    }

    @Test
    @DisplayName("Проверка переключения на вкладку \"Соусы\" в конструкторе")
    public void testTabSaucesConstructor() {

        if (!Objects.equals(homePage.getActiveTab().getText(), homePage.getSauces().getText())) {
            homePage.clickSaucesButton();
            assertEquals(
                    "Раздел \"Соусы\" неактивен",
                    homePage.getSauces().getText(),
                    homePage.getActiveTab().getText());
        }
    }

    @Test
    @DisplayName("Проверка переключения на вкладку \"Начинки\" в конструкторе")
    public void testTabFillingsConstructor() {

        if (!Objects.equals(homePage.getActiveTab().getText(), homePage.getFillings().getText())) {
            homePage.clickFillingsButton();
            assertEquals(
                    "Раздел \"Начинки\" неактивен",
                    homePage.getFillings().getText(),
                    homePage.getActiveTab().getText());
        }
    }
}