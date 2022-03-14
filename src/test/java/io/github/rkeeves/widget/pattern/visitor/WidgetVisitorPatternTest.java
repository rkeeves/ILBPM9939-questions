package io.github.rkeeves.widget.pattern.visitor;

import io.github.rkeeves.WebElement;
import org.junit.Test;
public class WidgetVisitorPatternTest {

    @Test
    void test() {
        Input input = new Input(WebElement.FAKE);
        input.has(Shoulds.value("a"))
                .is(Shoulds.enabled(true))
                .get(Gets.value);

        Select select = new Select(WebElement.FAKE);
        select.has(Shoulds.value("a"))
                .is(Shoulds.enabled(true))
                .get(Gets.value);
    }
}
