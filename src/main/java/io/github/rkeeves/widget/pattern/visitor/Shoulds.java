package io.github.rkeeves.widget.pattern.visitor;

import lombok.RequiredArgsConstructor;

public class Shoulds {

    public static Should value(String expected) {
        return new ShouldHaveValue(expected);
    }

    public static Should enabled(boolean expected) {
        return new ShouldBeEnabled(expected);
    }

    @RequiredArgsConstructor
    static class ShouldHaveValue implements Should {

        private final String expected;

        @Override
        public Widget on(Input input) {
            input.getInput().assertSomething(expected);
            return input;
        }

        @Override
        public Widget on(Select select) {
            select.getWidgetDiv().something();
            select.getSomeOtherElement().assertSomething(expected);
            return select;
        }
    }

    @RequiredArgsConstructor
    static class ShouldBeEnabled implements Should {

        private final boolean expected;

        @Override
        public Widget on(Input input) {
            input.getInput().assertSomething(expected ? "true" : "false");
            return input;
        }

        @Override
        public Widget on(Select select) {
            select.getWidgetDiv().something();
            select.getSomeOtherElement().assertSomething(expected ? "true" : "false");
            return select;
        }
    }
}
