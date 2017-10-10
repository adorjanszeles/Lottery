package com.lottery.config;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LotteryConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryConfig.class);

    @Bean
    @Qualifier(LotteryQualifier.statelessKieSessionName)
    public StatelessKieSession getNewStatelessKieSession() throws MissingKieServicesException {
        KieServices kieServices = KieServices.Factory.get();
        if (kieServices == null) {
            LotteryConfig.LOGGER.debug("Hiányzó com.lottery.kie service");
            throw new MissingKieServicesException("Hiányzó com.lottery.kie service");
        }
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        if (kContainer == null) {
            LotteryConfig.LOGGER.debug("Hiányzó com.lottery.kie konténer");
            throw new MissingKieServicesException("Hiányzó com.lottery.kie konténer");
        }
        StatelessKieSession statelesskSession = kContainer.newStatelessKieSession(KieSessionName.STATELESS_KIE_SESSION.getKieSessionName());
        if (statelesskSession == null) {
            LotteryConfig.LOGGER.debug("Hiányzó com.lottery.kie session");
            throw new MissingKieServicesException("Hiányzó com.lottery.kie session");
        }
        LotteryConfig.LOGGER.debug("kieSession sikeresen létrejött");
        statelesskSession.setGlobal("LOGGER", LOGGER);
        return statelesskSession;
    }

}
