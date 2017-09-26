package common.enums;

/**
 * Enum konstans nevek kie sessiönökhöz
 */

public enum KieSessionName {

    //default kie session, for new session names add enums below
    KIE_SESSION("ksession");

    private final String name;

    KieSessionName(String name) {
        this.name = name;
    }

    public String getKieSessionName() {
        return this.name;
    }

}



