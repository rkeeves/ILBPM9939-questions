package io.github.rkeeves.widget.methodchaining;

public class SelectMethodChained {

    public SelectMethodChained isEnabled() {

        return this;
    }

    public SelectMethodChained is(String currentText) {

        return this;
    }

    public SelectMethodChained hasOptions(String... optionTexts) {

        return this;
    }

    public void set(String optionText) {

    }
}
