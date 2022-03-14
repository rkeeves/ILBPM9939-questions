package io.github.rkeeves.scene.feature.feature;

import io.github.rkeeves.scene.feature.Widget;
import lombok.Data;

@Data
public class CreateUserWidgets {

    private final Widget title = Widget.FAKE;

    private final Widget name = Widget.FAKE;

    private final Widget age = Widget.FAKE;

    private final Widget email = Widget.FAKE;

    private final Widget country = Widget.FAKE;

    private final Widget zip = Widget.FAKE;

    private final Widget city = Widget.FAKE;

    private final Widget street = Widget.FAKE;

    private final Widget wantsAds = Widget.FAKE;

    private final Widget confirm = Widget.FAKE;

    private final Widget cancel = Widget.FAKE;

    private final Widget errorMessages = Widget.FAKE;
}
