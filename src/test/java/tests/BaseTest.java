package tests;

import static helper.DeviceHelper.executeBash;
import static helper.RunHelper.runHelper;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.qameta.allure.Allure.step;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import config.ConfigReader;
import driver.EmulatorDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.AllureListener;

@ExtendWith(AllureListener.class)
public class BaseTest {
    @BeforeAll
    public static void setup() {
        //добавляем логирование действий для аллюр отчета в виде степов
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        //инициализируем андройд драйвер
        Configuration.browser = runHelper().getDriverClass().getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    /**
     * Отключение анимаций на эмуляторе чтобы не лагало
     */
    private static void disableAnimationOnEmulator() {
        executeBash("adb -s shell settings put global transition_animation_scale 0.0");
        executeBash("adb -s shell settings put global window_animation_scale 0.0");
        executeBash("adb -s shell settings put global animator_duration_scale 0.0");
    }

    /**
     * Очистки данных и кэша перед запуском приложения.
     */

    private static void clearAppData() {
        executeBash("adb -s shell pm clear ru.beru.android");
        executeBash("adb -s shell pm clear-cache ru.beru.android");
    }

    /**
     * Перед каждый тестом открываем приложение
     */
    @BeforeEach
    public void startDriver() {
        disableAnimationOnEmulator();
        clearAppData();
        step("Открыть приложение", (Allure.ThrowableRunnableVoid) Selenide::open);
    }

    /**
     * После каждого теста закрываем AndroidDriver чтобы тест атомарным был
     */
    @AfterEach
    public void afterEach() {
        step("Закрыть приложение", Selenide::closeWebDriver);
    }

}
