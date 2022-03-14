package io.github.rkeeves.scene.feature.methods;

import lombok.Builder;
import lombok.Value;
import org.junit.Test;

public class MethodsSceneTest {

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
        createUserScene.fillOnlyRequired(RequiredData.builder()
                .age("a")
                .name("b")
                .build());
        createUserScene.saveWithoutError();

        list.getUsersTable().shouldAssertSomething(1);
    }

    @Test
    void test2() {
        ListUsersScene list = new ListUsersScene();
        list.getUsersTable().shouldAssertSomething(0);
        list.getCreateNewUser().something();

        CreateUserScene createUserScene = new CreateUserScene();
        createUserScene.fillAll(AllData.builder()
                .age("a")
                .name("b")
                .city("USA")
                .build());
        createUserScene.saveWithError("USA is not a city");

        list.getUsersTable().shouldAssertSomething(0);
    }
}
