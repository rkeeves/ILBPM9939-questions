package io.github.rkeeves.widget.ability.notchained;

public interface SingleValued<T> {

    void is(T expected);

    T get();

    void set(T actual);
}
