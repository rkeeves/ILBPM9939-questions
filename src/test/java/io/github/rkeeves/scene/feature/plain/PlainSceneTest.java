package io.github.rkeeves.scene.feature.plain;

import org.junit.Test;

public class PlainSceneTest {

    @Test
    void test1() {
        ListUsersScene list = new ListUsersScene();
        list.getUsersTable().shouldAssertSomething(0);
        list.getCreateNewUser().something();

        CreateUserScene createUserScene = new CreateUserScene();
        createUserScene.getAge().set("a");
        createUserScene.getName().set("b");
        // ... 320 more fields
        createUserScene.getCancel().something();

        list.getUsersTable().shouldAssertSomething(0);
    }

    @Test
    void test2() {
        ListUsersScene list = new ListUsersScene();
        list.getUsersTable().shouldAssertSomething(0);
        list.getCreateNewUser().something();

        CreateUserScene createUserScene = new CreateUserScene();
        createUserScene.getAge().set("a");
        createUserScene.getName().set("b");
        // ... 320 more fields
        createUserScene.getConfirm().something();
        createUserScene.getErrorMessages().shouldAssertSomething(0);

        list.getUsersTable().shouldAssertSomething(0);
    }
}
