package io.github.rkeeves.scene.feature.plain;

import io.github.rkeeves.scene.feature.Widget;
import lombok.Getter;

@Getter
public class ListUsersScene {

    private final Widget usersTable = Widget.FAKE;

    private final Widget createNewUser = Widget.FAKE;
}
