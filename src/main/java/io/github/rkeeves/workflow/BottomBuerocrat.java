package io.github.rkeeves.workflow;

import java.util.function.Consumer;

public interface BottomBuerocrat {
    void opensApplication();

    void confirmsApplicationState(InternalApplicationState jobAssigned);

    void takesJob();

    void seesDocuments(Documents... documents);

    void doesAction(String deny_visa);

    void fillsDynamicFields(Consumer<DynamicFields> consumer);
}
