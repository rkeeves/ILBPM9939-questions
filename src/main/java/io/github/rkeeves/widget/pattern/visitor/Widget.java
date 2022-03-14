package io.github.rkeeves.widget.pattern.visitor;

public interface Widget {

    <T> T get(Get<T> get);

    Widget is(Should should);

    default Widget has(Should should) {
        return is(should);
    }
}
