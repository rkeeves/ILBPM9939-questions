package io.github.rkeeves.widget.methodchaining;

import org.junit.Test;

public class MethodChainingTest {

    @Test
    void notMethodChained() {
        final SelectNotMethodChained select = new SelectNotMethodChained();
        select.isEnabled();
        select.is("a");
        select.hasOptions("a", "b");
        select.set("b");
    }

    @Test
    void methodChained() {
        new SelectMethodChained().isEnabled()
                .is("a")
                .hasOptions("a", "b")
                .set("b");
    }
}
