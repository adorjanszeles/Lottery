package kie;

import common.enums.KieSessionName;
import common.exceptions.MissingKieServicesException;
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


    public KieSession generateNewKieSession(KieSessionName kieSessionName) throws MissingKieServicesException {
        KieServices kieServices = KieServices.Factory.get();
        if (kieServices == null) {
            KieServiceImpl.LOGGER.debug("Hiányzó kie service");
            throw new MissingKieServicesException("Hiányzó kie service");
        }
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        if (kContainer == null) {
            KieServiceImpl.LOGGER.debug("Hiányzó kie konténer");
            throw new MissingKieServicesException("Hiányzó kie konténer");
        }
        this.kSession = kContainer.newKieSession(kieSessionName.getKieSessionName());
        if (this.kSession == null) {
            KieServiceImpl.LOGGER.debug("Hiányzó kie session");
            throw new MissingKieServicesException("Hiányzó kie session");
        }
        KieServiceImpl.LOGGER.debug("kieSession sikeresen létrejött");
        return this.kSession;

    }
}