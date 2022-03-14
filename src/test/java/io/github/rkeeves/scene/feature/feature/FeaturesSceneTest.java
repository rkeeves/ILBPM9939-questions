package io.github.rkeeves.scene.feature.feature;

import io.github.rkeeves.scene.feature.methods.AnotherPojoForSomeClassExplosion;
import io.github.rkeeves.scene.feature.methods.PojoForSomeClassExplosionInYourCodeBase;
import lombok.Builder;
import lombok.Value;
import org.junit.Test;

public class FeaturesSceneTest {
    @Value
    @Builder
    static class RequiredData implements PojoForSomeClassExplosionInYourCodeBase {

        String name;

        String age;
    }

    @Value
    @Builder
    static class AllData implements AnotherPojoForSomeClassExplosion {

        String name;

        String age;

        String city;
    }

    @Test
    void test1() {
        ListUsersScene list = new ListUsersScene();
        list.getUsersTable().shouldAssertSomething(0);
        list.getCreateNewUser().something();

        CreateUserScene createUserScene = new CreateUserScene();
        createUserScene.fields().fillOnlyRequired(RequiredData.builder()
                .age("a")
                .name("b")
                .build());
        createUserScene.controls().saveNoErrors();

        list.getUsersTable().shouldAssertSomething(1);
    }

    @Test
    void test2() {
        ListUsersScene list = new ListUsersScene();
        list.getUsersTable().shouldAssertSomething(0);
        list.getCreateNewUser().something();

        CreateUserScene createUserScene = new CreateUserScene();
        createUserScene.fields().fillAll(AllData.builder()
                .age("a")
                .name("b")
                .city("USA")
                .build());
        createUserScene.controls().saveError("USA is not a city");

        list.getUsersTable().shouldAssertSomething(0);
    }
}
