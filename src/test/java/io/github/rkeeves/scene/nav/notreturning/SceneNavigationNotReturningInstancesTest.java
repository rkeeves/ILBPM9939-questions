package io.github.rkeeves.scene.nav.notreturning;

import org.junit.Test;

public class SceneNavigationNotReturningInstancesTest {

    @Test
    void test() {
        new ApplicationListScene()
                .hasApplications(0)
                .startApplication();
        new ApplicationCreateScene().fill("asd")
                .save();
        new ApplicationListScene()
                .hasApplications(1);
    }
}
