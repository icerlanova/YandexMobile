package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static driver.EmulatorHelper.androidScrollToAnElementByText;

import com.codeborne.selenide.ElementsCollection;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class CatalogPage extends BasePage {

    private ElementsCollection items = $$(MobileBy.id("description"));
    private ElementsCollection toCartButtons = $$(MobileBy.id("cartButtonProgressButtonLayout"));

    @Step("Open books catalog")
    public CatalogPage chooseCatalogByText(String text) {
        androidScrollToAnElementByText(text);
        actions.click($(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")")));
        return this;
    }

    @Step("Put first element to shop cart")
    public void putFirstElementToShopCart() {
        androidScrollToAnElementByText("В корзину");
        actions.click(toCartButtons.get(0));
    }

    @Step("Save name of first element")
    public String extractFirstItemName() {
        return actions.getText(items.get(0));
    }

}
