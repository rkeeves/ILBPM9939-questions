package io.github.rkeeves.scene.feature.feature;

public class CreateUserControls {

    private final CreateUserWidgets createUserWidgets;


    public CreateUserControls(CreateUserWidgets createUserWidgets) {
        this.createUserWidgets = createUserWidgets;
    }

    public void cancel() {
        createUserWidgets.getCancel().something();
    }

    public void saveNoErrors() {
        createUserWidgets.getConfirm().something();
        createUserWidgets.getErrorMessages().shouldAssertSomething(0);
    }

    public void saveError(String expected) {
        createUserWidgets.getConfirm().something();
        createUserWidgets.getErrorMessages().shouldAssertSomething(expected);
    }
}
