package io.github.rkeeves.widget.ability.chained;

import io.github.rkeeves.WebElement;

public interface Enabled<S> extends Self<S> {

    WebElement locateEnabledElement();

    default S isEnabled() {
        locateEnabledElement().something();
        return self();
    }

    default S notEnabled() {
        locateEnabledElement().something();
        return self();
    }

    default boolean getEnabled() {
        return "true".equals(locateEnabledElement().getSomething());
    }
}
