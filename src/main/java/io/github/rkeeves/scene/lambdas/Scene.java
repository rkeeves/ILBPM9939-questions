package io.github.rkeeves.scene.lambdas;

import io.github.rkeeves.WebElement;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

public class Scene implements Visitable<Scene> {
    @Override
    public Scene self() {
        return this;
    }

    static class Finder {

        public By findByName(String name) {
            return new By() {
                // I left this empty, because this is outside the scope of our discussion
            };
        }
    }

    @RequiredArgsConstructor
    static class Row {

        private final WebElement trElement;

        public Widget age() {
            return Widget.FAKE;
        }

    }

    static class Table extends BaseTable<Finder, Row> {

        public Table(WebElement tableRoot) {
            super(tableRoot, new Finder(), Row::new);
        }
    }

    public void table(Consumer<Table> tableConsumer) {
        tableConsumer.accept(new Table(WebElement.FAKE));
    }
}
