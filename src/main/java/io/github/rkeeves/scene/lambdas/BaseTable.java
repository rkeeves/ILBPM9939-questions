package io.github.rkeeves.scene.lambdas;

import io.github.rkeeves.WebElement;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class BaseTable<F, R> {

    private final WebElement tableRoot;

    private final F finder;

    private final Function<WebElement, R> rowCtor;


    public void hasRowCount(int expected) {

    }

    public Result<R> find(Function<F, By> function) {
        By by = function.apply(finder);
        // search relative from tableRoot
        WebElement trElement = tableRoot.findChild();
        return new Result<>(rowCtor.apply(trElement));
    }
}
