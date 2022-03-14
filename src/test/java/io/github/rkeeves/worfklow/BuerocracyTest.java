package io.github.rkeeves.worfklow;

import io.github.rkeeves.workflow.*;
import org.junit.Test;

public class BuerocracyTest {

    @Test
    void test() {
        Buerocracy buerocracy /* implementation */;
        buerocracy.asApplicationCreator(applicationCreator -> {
            applicationCreator.startsApplication();
            applicationCreator.fillsApplication(application -> {
                application.kind().set(ApplicationKind.STUDENT_VISA.toString());
                application.buerocraticEthics().set(Region.HUN.toString());
                application.fastTrackWithBribery().isVisible().set("Hell, no");
            });
            applicationCreator.attachesDocuments(Documents.PHOTO, Documents.PASSPORT);
            applicationCreator.sendsApplication();
            applicationCreator.confirmsApplicationState(ClientSideApplicationState.VISA_REQUESTED);
            return applicationCreator.readsApplicationId();
        }).asHeadBuerocrat(headBuerocrat -> {
            headBuerocrat.assignsApplicationToBottomBuerocrat();
            headBuerocrat.confirmsApplicationState(InternalApplicationState.JOB_ASSIGNED);
        }).asBottomBuerocrat(bottomBuerocrat -> {
            bottomBuerocrat.opensApplication();
            bottomBuerocrat.confirmsApplicationState(InternalApplicationState.JOB_ASSIGNED);
            bottomBuerocrat.takesJob();
            bottomBuerocrat.confirmsApplicationState(InternalApplicationState.JOB_STARTED);
            bottomBuerocrat.seesDocuments(Documents.PHOTO, Documents.PASSPORT);
            bottomBuerocrat.doesAction("Deny Visa");
            bottomBuerocrat.fillsDynamicFields(fields -> {
                fields.select("Denial verdict").set("Full Deny");
                fields.input("Denial description").set("He's a spy");
            });
            bottomBuerocrat.doesAction("Send denial");
            bottomBuerocrat.confirmsApplicationState(InternalApplicationState.JOB_DONE);
        }).asApplicationReceiver(applicationReceiver -> {
            applicationReceiver.opensTheApplication();
            applicationReceiver.confirmsApplicationState(ClientSideApplicationState.VISA_DENIED);
        });
    }
}
