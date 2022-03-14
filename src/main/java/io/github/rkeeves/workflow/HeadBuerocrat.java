package io.github.rkeeves.workflow;

public interface HeadBuerocrat {
    void assignsApplicationToBottomBuerocrat();

    void confirmsApplicationState(InternalApplicationState jobAssigned);
}
