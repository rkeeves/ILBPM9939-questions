package io.github.rkeeves.widget.pattern.classic;

import io.github.rkeeves.WebElement;
import lombok.Getter;

public class Select {

    @Getter
    private final WebElement widgetDiv;

    @Getter
    private final WebElement someOtherElement;

    public Select(WebElement widgetDiv) {
        this.widgetDiv = widgetDiv;
        this.someOtherElement = widgetDiv.findChild();
    }

    public void isEnabled() {

    }

    public void is(String currentText) {

    }

    public void set(String optionText) {

    }

    public void hasOptions(String... optionTexts) {

    }
}
