package io.github.rkeeves.workflow;

public interface ApplicationReceiver {
    void opensTheApplication();

    void confirmsApplicationState(ClientSideApplicationState visaDenied);
}
