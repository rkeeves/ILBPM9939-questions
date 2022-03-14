package io.github.rkeeves.widget.pattern.visitor;

public interface Get<T> {

    T onInput(final Input input);

    T onSelect(final Select select);
}
