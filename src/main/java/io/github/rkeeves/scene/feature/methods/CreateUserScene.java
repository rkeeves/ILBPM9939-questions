package io.github.rkeeves.scene.feature.methods;

import io.github.rkeeves.scene.feature.Widget;
import lombok.Getter;

@Getter
public class CreateUserScene {

    private final Widget title = Widget.FAKE;

    private final Widget name = Widget.FAKE;

    private final Widget age = Widget.FAKE;

    private final Widget email = Widget.FAKE;

    private final Widget country = Widget.FAKE;

    private final Widget zip = Widget.FAKE;

    private final Widget city = Widget.FAKE;

    private final Widget street = Widget.FAKE;

    private final Widget wantsAds = Widget.FAKE;

    private final Widget confirm = Widget.FAKE;

    private final Widget cancel = Widget.FAKE;

    private final Widget errorMessages = Widget.FAKE;

    public CreateUserScene setName(String nameValue) {
        name.set(nameValue);
        return this;
    }

    public CreateUserScene setAge(String ageValue) {
        age.set(ageValue);
        return this;
    }

    public CreateUserScene fillOnlyRequired(PojoForSomeClassExplosionInYourCodeBase nameValue) {

        return this;
    }

    public CreateUserScene fillAll(AnotherPojoForSomeClassExplosion nameValue) {

        return this;
    }

    public CreateUserScene cancel() {
        cancel.something();
        return this;
    }

    public CreateUserScene saveWithoutError() {
        confirm.something();
        errorMessages.shouldAssertSomething(0);
        return this;
    }

    public CreateUserScene saveWithError(String error) {
        errorMessages.shouldAssertSomething(error);
        return this;
    }

    public CreateUserScene andMore() {
        return this;
    }

    public CreateUserScene andMoreMore() {
        return this;
    }

    public CreateUserScene asTheBaKeepsInventingNewThings() {
        return this;
    }
}
