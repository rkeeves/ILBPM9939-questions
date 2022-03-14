package io.github.rkeeves.widget.ability.notchained;

import io.github.rkeeves.WebElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Input implements Visible, Enabled, SingleValued<String> {

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
    public void is(String expected) {
        input.assertSomething(expected);
    }

    @Override
    public String get() {
        return input.getSomething();
    }

    @Override
    public void set(String actual) {
        input.sideEffectWithArg(actual);
    }
}
