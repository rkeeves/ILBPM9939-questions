package io.github.rkeeves.workflow;

import java.util.function.Consumer;

public interface ApplicationCreator {
    
    void startsApplication();

    void sendsApplication();

    void fillsApplication(Consumer<Application> consumer);

    String readsApplicationId();

    void attachesDocuments(Documents... documents);

    void confirmsApplicationState(ClientSideApplicationState visaRequested);
}
