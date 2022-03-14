package io.github.rkeeves.scene.lambdas;

import java.util.function.Consumer;

public interface Visitable<T> extends Self<T> {

    default void on(Consumer<T> consumer) {
        consumer.accept(self());
    }
}
