package com.lottery.kie;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link KieService} interfész implementációja.
 */

public class KieServiceImpl implements KieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KieServiceImpl.class);

    private KieSession kSession;

    public KieServiceImpl() {
    }


    @Override
    public KieSession generateNewKieSession(KieSessionName kieSessionName) throws MissingKieServicesException {
        KieServices kieServices = KieServices.Factory.get();
        if (kieServices == null) {
            KieServiceImpl.LOGGER.debug("Hiányzó com.lottery.kie service");
            throw new MissingKieServicesException("Hiányzó com.lottery.kie service");
        }
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        if (kContainer == null) {
            KieServiceImpl.LOGGER.debug("Hiányzó com.lottery.kie konténer");
            throw new MissingKieServicesException("Hiányzó com.lottery.kie konténer");
        }
        this.kSession = kContainer.newKieSession(kieSessionName.getKieSessionName());
        if (this.kSession == null) {
            KieServiceImpl.LOGGER.debug("Hiányzó com.lottery.kie session");
            throw new MissingKieServicesException("Hiányzó com.lottery.kie session");
        }
        KieServiceImpl.LOGGER.debug("kieSession sikeresen létrejött");
        this.kSession.setGlobal("LOGGER", KieServiceImpl.LOGGER);
        return this.kSession;

    }
}