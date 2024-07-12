package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.Condition;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;
import pages.CartPage;
import pages.CatalogPage;
import pages.MainPage;
import utils.WaitManager;

public class YandexTest extends BaseTest {

    private static MainPage mainPage;
    private static CatalogPage catalogPage;
    private static CartPage cartPage;
    private static String bookName;

    @BeforeAll
    public static void init() {
        mainPage = new MainPage();
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
    }

    @BeforeEach
    public void addItemToCart() {
        mainPage.closeRegPage()
                .closeCookie() // Закрываем регистрационную страницу и куки файлы
                .openCatalog(); // Открываем каталог

        catalogPage.chooseCatalogByText("Книги") // Ищем каталог Книги по тексту и открываем его
                .putFirstElementToShopCart(); // Ложим первый элемент в корзину

        bookName = catalogPage.extractFirstItemName(); // Сохраняем имя элемента для дальнейшего assert

        WaitManager.pause(3); // wait until item will added to cart - можно настроить ожидание пока не появится иконка в корзине

        mainPage.openCart(); // Открываем корзину
    }

    @Test
    @Description("Проверка что, выбранный товар отображается в корзине")
    public void checkAvailabilityOfItemInCart() {
        assertFalse(
                cartPage.getElementByName(bookName)
                        .should(Condition.visible)
                        .exists()
        ); // Проверяем что в корзине отображается выбранный товар
    }

    @Test
    @Description("Удаление товара из корзины и проверка что корзина пуста")
    public void deleteItemFromCart() {
        cartPage.deleteItemsFromCart(); // Удаляем выбранный товар

        WaitManager.pause(3); // wait until item will deleted - можно настроить ожидание пока не появится иконка в корзине

        assertFalse(
                cartPage.getElementByName(bookName)
                        .shouldNot(Condition.visible)
                        .exists(), "Selected item in cart not deleted"
        ); // проверяем что выбранного товара нет
    }
}
