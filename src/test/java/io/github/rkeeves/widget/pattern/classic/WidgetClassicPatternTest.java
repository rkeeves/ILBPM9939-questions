package io.github.rkeeves.widget.pattern.classic;

import io.github.rkeeves.WebElement;
import org.junit.Test;

public class WidgetClassicPatternTest {

    @Test
    void classic() {
        Input input = new Input(WebElement.FAKE);
        input.is("a");
        input.isEnabled();
        input.set("b");
        Select select = new Select(WebElement.FAKE);
        select.is("a");
        select.isEnabled();
        select.hasOptions("a");
        select.set("b");
    }
}
