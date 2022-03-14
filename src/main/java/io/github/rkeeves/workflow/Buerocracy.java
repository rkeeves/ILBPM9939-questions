package io.github.rkeeves.workflow;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Buerocracy {

    Buerocracy asApplicationCreator(Function<ApplicationCreator, String> function);

    Buerocracy asHeadBuerocrat(Consumer<HeadBuerocrat> consumer);

    Buerocracy asBottomBuerocrat(Consumer<BottomBuerocrat> consumer);

    Buerocracy asApplicationReceiver(Consumer<ApplicationReceiver> consumer);
}
