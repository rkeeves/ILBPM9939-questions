package io.github.rkeeves.widget.pattern.visitor;

import lombok.RequiredArgsConstructor;

public class Gets {

    public static Get<String> value = new GetValue();

    @RequiredArgsConstructor
    static class GetValue implements Get<String> {

        @Override
        public String onInput(Input input) {
            return input.getInput().getSomething();
        }

        @Override
        public String onSelect(Select select) {
            return select.getSomeOtherElement().getSomething();
        }
    }
}
