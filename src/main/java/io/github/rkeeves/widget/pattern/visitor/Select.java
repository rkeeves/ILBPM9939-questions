package io.github.rkeeves.widget.pattern.visitor;

import io.github.rkeeves.WebElement;
import lombok.Getter;

public class Select implements Widget {

    @Getter
    private final WebElement widgetDiv;

    @Getter
    private final WebElement someOtherElement;

    public Select(WebElement widgetDiv) {
        this.widgetDiv = widgetDiv;
        this.someOtherElement = widgetDiv.findChild();
    }

    @Override
    public <T> T get(Get<T> get) {
        return get.onSelect(this);
    }

    @Override
    public Widget is(Should should) {
        return should.on(this);
    }
}
