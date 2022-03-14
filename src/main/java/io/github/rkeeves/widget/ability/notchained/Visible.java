package io.github.rkeeves.widget.ability.notchained;

import io.github.rkeeves.WebElement;

public interface Visible {

    WebElement locateVisibleElement();

    default void isVisible() {
        locateVisibleElement().something();
    }

    default void notVisible() {
        locateVisibleElement().something();
    }

    default boolean getVisible() {
        return "true".equals(locateVisibleElement().getSomething());
    }
}
