package io.github.rkeeves.scene.feature;

public interface Widget {

    Widget FAKE = new Widget() {
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

    void set(String value);

    void shouldAssertSomething(String value);

    void shouldAssertSomething(int value);

    String get();

    void something();
}
