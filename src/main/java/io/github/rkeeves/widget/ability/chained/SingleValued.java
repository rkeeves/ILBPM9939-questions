package io.github.rkeeves.widget.ability.chained;

public interface SingleValued<S, T> extends Self<S> {

    S is(T expected);

    T get();

    S set(T actual);
}
