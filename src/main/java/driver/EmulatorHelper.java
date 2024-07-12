package driver;

import io.appium.java_client.MobileBy;
import utils.WaitManager;

/**
 * Класс помощник для Page страниц
 */
public class EmulatorHelper extends EmulatorDriver {

    /**
     * Нажимает кнопку назад
     */
    public static void goBack() {
        driver.navigate().back();
    }

    /**
     * Листает к элементу по его тексту
     *
     * @param text текст на элементе
     */
    public static void androidScrollToAnElementByText(String text) {
        // Ожидание перед началом скроллинга
        WaitManager.pause(2);

        // Пробуем найти элемент перед скроллом
        if (driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")")).isEmpty()) {
            try {
                // Скроллим до элемента вниз
                driver.findElementByAndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                                ".scrollForward(5).scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0));"
                );
            } catch (Exception e) {
                System.err.println("Element not found after scrolling: " + text);
            }
        } else {
            System.err.println("Element found without scrolling: " + text);
        }
    }
}
