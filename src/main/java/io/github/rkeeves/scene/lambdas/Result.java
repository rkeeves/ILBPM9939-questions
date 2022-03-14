package io.github.rkeeves.scene.lambdas;

import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class Result<R> {

    private final R row;

    void result(Consumer<R> consumer) {
        consumer.accept(row);
    }
}
