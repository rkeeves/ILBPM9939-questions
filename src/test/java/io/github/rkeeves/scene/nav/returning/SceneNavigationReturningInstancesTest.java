package io.github.rkeeves.scene.nav.returning;

import org.junit.Test;

public class SceneNavigationReturningInstancesTest {

    @Test
    void test() {
        new ApplicationListScene()
                .hasApplications(0)
                .startApplication()
                .fill("asd")
                .save()
                .hasApplications(1);
    }
}
