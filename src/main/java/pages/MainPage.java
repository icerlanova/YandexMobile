package pages;

import static com.codeborne.selenide.Selenide.$;
import static driver.EmulatorHelper.goBack;

import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class MainPage extends BasePage {
    private SelenideElement closePopupButton = $(MobileBy.id("closeButton"));
    private SelenideElement cookieSkip = $(MobileBy.id("negativeButton"));
    private SelenideElement catalog = $(MobileBy.id("nav_catalog"));
    private SelenideElement cart = $(MobileBy.id("nav_cart"));

    @Step("Close registration page and popup")
    public MainPage closeRegPage() {
        goBack();
        actions.click(closePopupButton);
        return this;
    }

    @Step("Close cookie")
    public MainPage closeCookie() {
        actions.click(cookieSkip);
        return this;
    }

    @Step("Open catalog")
    public void openCatalog() {
        actions.click(catalog);
    }


    @Step("Open cart")
    public void openCart() {
        actions.click(cart);
    }
}
