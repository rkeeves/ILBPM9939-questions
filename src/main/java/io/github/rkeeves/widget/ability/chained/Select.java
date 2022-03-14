package io.github.rkeeves.widget.ability.chained;

import io.github.rkeeves.WebElement;
import lombok.Getter;

public class Select implements Visible<Select>, Enabled<Select>, SingleValued<Select, String> {

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
    public Select is(String expected) {
        someOtherElement.assertSomething(expected);
        return this;
    }

    @Override
    public String get() {
        return someOtherElement.getSomething();
    }

    @Override
    public Select set(String actual) {
        widgetDiv.something();
        someOtherElement.sideEffectWithArg(actual);
        widgetDiv.something();
        return this;
    }

    public Select hasOptions(String... optionTexts) {
        widgetDiv.something();
        someOtherElement.assertSomethingWithVarArgs(optionTexts);
        widgetDiv.something();
        return this;
    }

    @Override
    public Select self() {
        return this;
    }
}