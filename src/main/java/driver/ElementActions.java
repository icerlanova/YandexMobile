package driver;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class ElementActions {
    public ElementActions() {
    }

    public ElementActions click(SelenideElement element) {
        element.should(appear).hover()
                .should(visible).click();
        return this;
    }


    public String getText(SelenideElement element) {
        return element.should(visible).getText();
    }
}
