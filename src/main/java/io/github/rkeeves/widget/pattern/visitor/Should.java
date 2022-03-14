package io.github.rkeeves.widget.pattern.visitor;

public interface Should {

    Widget on(final Input input);

    Widget on(final Select select);
}