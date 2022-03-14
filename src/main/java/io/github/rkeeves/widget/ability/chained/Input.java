package io.github.rkeeves.widget.ability.chained;

import io.github.rkeeves.WebElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Input implements Visible<Input>, Enabled<Input>, SingleValued<Input, String> {

    @Getter
    private final WebElement input;

    @Override
    public WebElement locateVisibleElement() {
        return input;
    }

    @Override
    public WebElement locateEnabledElement() {
        return input;
    }

    @Override
    public Input is(String expected) {
        input.assertSomething(expected);
        return this;
    }

    @Override
    public String get() {
        return input.getSomething();
    }

    @Override
    public Input set(String actual) {
        input.sideEffectWithArg(actual);
        return this;
    }

    @Override
    public Input self() {
        return this;
    }
}
