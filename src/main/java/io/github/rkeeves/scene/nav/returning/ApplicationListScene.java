package io.github.rkeeves.scene.nav.returning;

public class ApplicationListScene {

    public ApplicationCreateScene startApplication() {
        return new ApplicationCreateScene();
    }

    public ApplicationListScene hasApplications(int count) {
        return this;
    }
}
