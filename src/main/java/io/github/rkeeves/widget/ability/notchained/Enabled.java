package io.github.rkeeves.widget.ability.notchained;

import io.github.rkeeves.WebElement;

public interface Enabled {

    WebElement locateEnabledElement();

    default void isEnabled() {
        locateEnabledElement().something();
    }

    default void notEnabled() {
        locateEnabledElement().something();
    }

    default boolean getEnabled() {
        return "true".equals(locateEnabledElement().getSomething());
    }
}
