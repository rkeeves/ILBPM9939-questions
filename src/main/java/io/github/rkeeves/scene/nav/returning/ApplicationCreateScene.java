package io.github.rkeeves.scene.nav.returning;

public class ApplicationCreateScene {

    public ApplicationCreateScene fill(String data) {

        return this;
    }

    public ApplicationListScene save() {
        return new ApplicationListScene();
    }
}
