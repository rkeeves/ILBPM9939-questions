package io.github.rkeeves.widget.ability.chained;

import io.github.rkeeves.WebElement;

public interface Visible<S> extends Self<S> {

    WebElement locateVisibleElement();

    default S isVisible() {
        locateVisibleElement().something();
        return self();
    }

    default S notVisible() {
        locateVisibleElement().something();
        return self();
    }

    default boolean getVisible() {
        return "true".equals(locateVisibleElement().getSomething());
    }
}
