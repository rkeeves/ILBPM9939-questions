package io.github.rkeeves.widget.pattern.visitor;

import io.github.rkeeves.WebElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Input implements Widget {

    @Getter
    private final WebElement input;

    @Override
    public <T> T get(Get<T> get) {
        return get.onInput(this);
    }

    @Override
    public Widget is(Should should) {
        return should.on(this);
    }
}
