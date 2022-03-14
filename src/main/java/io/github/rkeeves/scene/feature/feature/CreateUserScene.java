package io.github.rkeeves.scene.feature.feature;

public class CreateUserScene {

    private final CreateUserWidgets createUserWidgets = new CreateUserWidgets();

    private final CreateUserControls createUserControls = new CreateUserControls(createUserWidgets);

    private final CreateUserFields createUserFields = new CreateUserFields(createUserWidgets);

    public CreateUserFields fields() {
        return createUserFields;
    }

    public CreateUserControls controls() {
        return createUserControls;
    }
}
