package kie;

import common.enums.KieSessionName;
import common.exceptions.MissingKieServicesException;
import org.kie.api.runtime.KieSession;

public interface KieService {

    /**
     * Új Kie Session létrehozása
     *
     * @param kieSessionName enum session név {@link KieSessionName}.
     * @return kieSession
     */

    KieSession generateNewKieSession(KieSessionName kieSessionName) throws MissingKieServicesException;
}
