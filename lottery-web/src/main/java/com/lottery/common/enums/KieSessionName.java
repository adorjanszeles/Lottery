package com.lottery.common.enums;

/**
 * Enum konstans nevek com.lottery.kie sessiönökhöz
 */

public enum KieSessionName {

    //default com.lottery.kie session, for new session names add enums below
    KIE_SESSION("ksession"),
    STATELESS_KIE_SESSION("statelessKsession");

    private final String name;

    KieSessionName(String name) {
        this.name = name;
    }

    public String getKieSessionName() {
        return this.name;
    }

}



