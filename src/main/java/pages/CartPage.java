package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

import groovy.util.logging.Slf4j;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class CartPage extends BasePage {

    @Step("Extract element from cart by name")
    public SelenideElement getElementByName(String itemName) {
        try {
            return $(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + itemName + "\")"));
        } catch (Exception e) {
            return null; // Нужно обработать ошибку - чтобы тест не останавливался и мы могли проверить отстутствие элемента
        }

    }

    @Step("Delete item from cart")
    public void deleteItemsFromCart() {
        actions.click($(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Удалить выбранные\")")))
                .click($(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Удалить товары из корзины\")")));
    }
}
