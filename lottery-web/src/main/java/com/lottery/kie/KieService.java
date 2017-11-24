package com.lottery.kie;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import org.kie.api.runtime.KieSession;

/**
 * Kie Service létrehozása
 */
public interface KieService {

    /**
     * Új Kie Session létrehozása
     *
     * @param kieSessionName enum session név {@link KieSessionName}.
     * @return kieSession
     * @throws MissingKieServicesException hiányzó kie session kivétel
     */

    KieSession generateNewKieSession(KieSessionName kieSessionName) throws MissingKieServicesException;
}
