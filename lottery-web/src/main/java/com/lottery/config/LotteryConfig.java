package com.lottery.config;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.model.Lottery;
import com.lottery.repository.WeeklyDrawJPARepository;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Spring konfigurációs osztály stateless kiesession bean létrehozásához
 */
@Configuration
public class LotteryConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryConfig.class);
    private static final String FILE_PATH = "otos.csv";
    private WeeklyDrawJPARepository wrepo;

    public LotteryConfig() {
    }

    @Autowired
    public LotteryConfig(WeeklyDrawJPARepository wrepo) {
        this.wrepo = wrepo;
    }

    @Bean
    public Lottery generateLottery() throws IOException {
        Lottery lottery = new Lottery();
        lottery.setLotteryList();
        lottery.getLotteryList().addAll(wrepo.findAll());
        return lottery;
    }

    @Bean
    @Qualifier(LotteryQualifier.statelessKieSessionName)
    public StatelessKieSession getNewStatelessKieSession() throws MissingKieServicesException {
        KieServices kieServices = KieServices.Factory.get();
        if (kieServices == null) {
            LotteryConfig.LOGGER.debug("Hiányzó com.lottery.kie service");
            throw new MissingKieServicesException("Hiányzó com.lottery.kie service");
        }

        // A drools rule-ok behúzása külső jar-ból történik. jelen esetben: drules-1.0.jar
        ReleaseId releaseId = kieServices.newReleaseId("com.lottery", "lottery-rules", "1.0");
        KieContainer kContainer = kieServices.newKieContainer(releaseId);
        if (kContainer == null) {
            LotteryConfig.LOGGER.debug("Hiányzó com.lottery.kie konténer");
            throw new MissingKieServicesException("Hiányzó com.lottery.kie konténer");
        }
        StatelessKieSession statelesskSession = kContainer.newStatelessKieSession(
                KieSessionName.STATELESS_KIE_SESSION.getKieSessionName());
        if (statelesskSession == null) {
            LotteryConfig.LOGGER.debug("Hiányzó com.lottery.kie session");
            throw new MissingKieServicesException("Hiányzó com.lottery.kie session");
        }
        LotteryConfig.LOGGER.debug("kieSession sikeresen létrejött");
        statelesskSession.setGlobal("LOGGER", LotteryConfig.LOGGER);
        return statelesskSession;
    }

}
