package io.github.rkeeves.widget.ability.chained;

import io.github.rkeeves.WebElement;
import org.junit.Test;

public class AbilityChainedTest {

    @Test
    void test() {
        new Input(WebElement.FAKE)
                .is("a")
                .isEnabled()
                .set("b");
        new Select(WebElement.FAKE)
                .is("a")
                .isEnabled()
                .hasOptions("a")
                .set("b");
    }
}
