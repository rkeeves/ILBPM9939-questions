package io.github.rkeeves;

public interface WebElement {

    WebElement FAKE = new WebElement() {
        @Override
        public void something() {

        }

        @Override
        public WebElement findChild() {
            return null;
        }

        @Override
        public WebElement sideEffectWithArg(String expected) {
            return null;
        }

        @Override
        public WebElement assertSomething(String expected) {
            return null;
        }

        @Override
        public String getSomething() {
            return "something";
        }

        @Override
        public WebElement assertSomethingWithVarArgs(String... optionTexts) {
            return null;
        }
    };

    void something();

    WebElement findChild();

    WebElement sideEffectWithArg(String expected);

    WebElement assertSomething(String expected);

    String getSomething();

    WebElement assertSomethingWithVarArgs(String... optionTexts);
}
