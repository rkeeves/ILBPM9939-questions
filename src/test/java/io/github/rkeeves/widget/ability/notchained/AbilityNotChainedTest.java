package io.github.rkeeves.widget.ability.notchained;

import io.github.rkeeves.WebElement;
import org.junit.Test;

public class AbilityNotChainedTest {

    @Test
    void test() {
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
