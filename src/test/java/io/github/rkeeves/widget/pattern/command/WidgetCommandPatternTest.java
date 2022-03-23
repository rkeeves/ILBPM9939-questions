package io.github.rkeeves.widget.pattern.command;

import lombok.RequiredArgsConstructor;
import org.junit.Test;

import java.util.function.Function;

import static io.github.rkeeves.widget.pattern.command.WidgetCommandPatternTest.Click.click;
import static io.github.rkeeves.widget.pattern.command.WidgetCommandPatternTest.CommandButtonImpl.commandButton;
import static io.github.rkeeves.widget.pattern.command.WidgetCommandPatternTest.CommandButtonImpl.commandButtonByLabel;
import static io.github.rkeeves.widget.pattern.command.WidgetCommandPatternTest.Get.get;
import static io.github.rkeeves.widget.pattern.command.WidgetCommandPatternTest.InputTextImpl.inputText;
import static io.github.rkeeves.widget.pattern.command.WidgetCommandPatternTest.Select.select;
import static io.github.rkeeves.widget.pattern.command.WidgetCommandPatternTest.SelectOneMenuImpl.selectOneMenu;
import static io.github.rkeeves.widget.pattern.command.WidgetCommandPatternTest.Set.set;

public class WidgetCommandPatternTest {

    interface WebElement {

        String attr(String attrName);

        String id();

        WebElement $(String css);

        WebElement $x(String xpath);
    }

    interface InputText extends Function<WebElement, InputTextImpl> {

    }

    @RequiredArgsConstructor
    static class InputTextImpl implements Settable, Clickable, Gettable {

        private final WebElement input;

        public static InputText inputText(String idTail) {
            return root -> new InputTextImpl(root.$("input[id$='" + idTail + "']"));
        }

        @Override
        public void click() {
            //
        }

        @Override
        public void set(String value) {
            //
        }

        @Override
        public String get() {
            return "";
        }
    }

    interface SelectOneMenu extends Function<WebElement, SelectOneMenuImpl> {

    }

    @RequiredArgsConstructor
    static class SelectOneMenuImpl implements Clickable, Selectable {

        private final WebElement divContainer;

        public static SelectOneMenu selectOneMenu(String idTail) {
            return root -> new SelectOneMenuImpl(root.$("div[id$='" + idTail + "']"));
        }

        @Override
        public void click() {
            //
        }

        @Override
        public void select(String selectableText) {
            //
        }
    }

    interface CommandButton extends Function<WebElement, CommandButtonImpl> {

    }

    @RequiredArgsConstructor
    static class CommandButtonImpl implements Clickable {

        private final WebElement button;

        public static CommandButton commandButton(String idTail) {
            return root -> new CommandButtonImpl(root.$("button[id$='" + idTail + "']"));
        }

        public static CommandButton commandButtonByLabel(String labelText) {
            return root -> {
                final WebElement label = root.$x(".//label[text()='" + labelText + "'");
                final String id = label.attr("for");
                return new CommandButtonImpl(root.$("button[id='" + id + "']"));
            };
        }

        public void click() {
            //
        }
    }

    interface Clickable {

        void click();
    }

    interface Gettable {

        String get();
    }


    interface Settable {

        void set(String value);
    }


    @FunctionalInterface
    interface Selectable {

        void select(String selectableText);
    }

    interface Action<R> {
        R execute(WebElement webElement);
    }

    @RequiredArgsConstructor
    static class Get implements Action<String> {

        private final Function<WebElement, ? extends Gettable> func;

        static Get get(Function<WebElement, ? extends Gettable> func) {
            return new Get(func);
        }

        @Override
        public String execute(WebElement webElement) {
            return func.apply(webElement).get();
        }
    }

    @RequiredArgsConstructor
    static class Click implements Action<Void> {

        private final Function<WebElement, ? extends Clickable> func;

        static Click click(Function<WebElement, ? extends Clickable> func) {
            return new Click(func);
        }

        @Override
        public Void execute(WebElement webElement) {
            func.apply(webElement).click();
            return null;
        }
    }

    @RequiredArgsConstructor
    static class Set implements Action<Void> {

        private final Function<WebElement, ? extends Settable> func;

        private final String value;

        static Set set(Function<WebElement, ? extends Settable> func, String value) {
            return new Set(func, value);
        }

        @Override
        public Void execute(WebElement webElement) {
            func.apply(webElement).set(value);
            return null;
        }
    }

    @RequiredArgsConstructor
    static class Select implements Action<Void> {

        private final Function<WebElement, ? extends Selectable> func;

        private final String value;

        static Select select(Function<WebElement, ? extends Selectable> func, String value) {
            return new Select(func, value);
        }

        @Override
        public Void execute(WebElement webElement) {
            func.apply(webElement).select(value);
            return null;
        }
    }

    @RequiredArgsConstructor
    static class Bot {

        // You could also make this thing into a Function<WebDriver, WebElement>
        private final WebElement scope;

        static Bot create() {
            return new Bot(new WebElement() {
                @Override
                public String attr(String attrName) {
                    return null;
                }

                @Override
                public String id() {
                    return null;
                }

                @Override
                public WebElement $(String css) {
                    return null;
                }

                @Override
                public WebElement $x(String xpath) {
                    return null;
                }
            });
        }

        public Bot $(String css) {
            return new Bot(scope.$(css));
        }

        public <T> Bot act(Action<T> action) {
            // any listeners, or proxy-like behavior can be injected
            action.execute(scope);
            return this;
        }

        public <T> T actAndReturn(Action<T> action) {
            // any listeners, or proxy-like behavior can be injected
            return action.execute(scope);
        }

        public <T> Bot act(String intent, Action<T> action) {
            // any listeners, or proxy-like behavior can be injected
            action.execute(scope);
            return this;
        }
    }

    static class Scope {

    }

    static class Scopes {

    }

    // You can make your dom model entirely stateless
    // (not dependent on driver instance etc.)
    static CommandButton START = commandButton("start");

    static InputText NAME = inputText("name");

    static InputText AGE = inputText("age");

    static SelectOneMenu GENDER = selectOneMenu("gender");

    static CommandButton OK = commandButtonByLabel("Ok");

    @Test
    void test() {
        final Bot bot = Bot.create();
        bot.$("outerId")
                .$("innerId")
                .act(click(START))
                // Code line below does not produce compile time error (input is clickable)
                // Also, the intent (given as a string argument) can be used in error logs etc.
                .act("The input field must be clicked first, to make it editable", click(NAME))
                .act(set(NAME, "Someone"))
                .act(set(AGE, "Gloomer Zoomer"))
                .act(select(GENDER, "All"))
                // Code line below - when uncommented - produces compile time error (input is not selectable)
                // .act(select(NAME, "a"))
                // Code line below - when uncommented - produces compile time error (button is not settable)
                // .act(set(START))
                .act(click(OK));

        // You can also return values
        final String result = bot.$("outerId")
                .$("innerId")
                .actAndReturn(get(NAME));

    }
}
