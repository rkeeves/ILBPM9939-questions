package io.github.rkeeves.scene.feature.feature;

import io.github.rkeeves.scene.feature.methods.AnotherPojoForSomeClassExplosion;
import io.github.rkeeves.scene.feature.methods.PojoForSomeClassExplosionInYourCodeBase;
import lombok.Getter;

@Getter
public class CreateUserFields {

    private final CreateUserWidgets createUserWidgets;

    public CreateUserFields(CreateUserWidgets createUserWidgets) {
        this.createUserWidgets = createUserWidgets;
    }

    public CreateUserFields setName(String nameValue) {
        createUserWidgets.getName().set(nameValue);
        return this;
    }

    public CreateUserFields setAge(String ageValue) {
        createUserWidgets.getAge().set(ageValue);
        return this;
    }

    public CreateUserFields fillOnlyRequired(PojoForSomeClassExplosionInYourCodeBase nameValue) {

        return this;
    }

    public CreateUserFields fillAll(AnotherPojoForSomeClassExplosion nameValue) {

        return this;
    }
    //...
}
