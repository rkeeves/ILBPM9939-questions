package io.github.rkeeves.workflow;

public interface Widget {
    Widget FAKE = new Widget() {
        @Override
        public Widget isVisible() {
            return null;
        }

        @Override
        public Widget notVisible() {
            return null;
        }

        @Override
        public Widget is(int value) {
            return null;
        }

        @Override
        public Widget is(String value) {
            return null;
        }

        @Override
        public void set(String value) {

        }

        @Override
        public void shouldAssertSomething(String value) {

        }

        @Override
        public void shouldAssertSomething(int value) {

        }

        @Override
        public String get() {
            return null;
        }

        @Override
        public void something() {

        }
    };

    Widget isVisible();

    Widget notVisible();

    Widget is(int value);

    Widget is(String value);

    void set(String value);

    void shouldAssertSomething(String value);

    void shouldAssertSomething(int value);

    String get();

    void something();
}
