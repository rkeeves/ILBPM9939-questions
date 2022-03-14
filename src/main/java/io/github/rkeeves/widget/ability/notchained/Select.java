package io.github.rkeeves.widget.ability.notchained;

import io.github.rkeeves.WebElement;
import lombok.Getter;

public class Select implements Visible, Enabled, SingleValued<String> {

    @Getter
    private final WebElement widgetDiv;

    @Getter
    private final WebElement someOtherElement;

    public Select(WebElement widgetDiv) {
        this.widgetDiv = widgetDiv;
        this.someOtherElement = widgetDiv.findChild();
    }

    @Override
    public WebElement locateEnabledElement() {
        return someOtherElement;
    }

    @Override
    public WebElement locateVisibleElement() {
        return widgetDiv;
    }

    @Override
    public void is(String expected) {
        someOtherElement.assertSomething(expected);
    }

    @Override
    public String get() {
        return someOtherElement.getSomething();
    }

    @Override
    public void set(String actual) {
        widgetDiv.something();
        someOtherElement.sideEffectWithArg(actual);
        widgetDiv.something();
    }

    public void hasOptions(String... optionTexts) {
        widgetDiv.something();
        someOtherElement.assertSomethingWithVarArgs(optionTexts);
        widgetDiv.something();
    }
}