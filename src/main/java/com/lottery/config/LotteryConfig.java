package com.lottery.config;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.model.Lottery;
import com.lottery.service.LotteryFileReader;
import com.lottery.service.LotteryFileReaderImpl;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * Spring konfigurációs osztály stateless kiesession bean létrehozásához
 */
@Configuration
public class LotteryConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryConfig.class);

    @Bean
    public Lottery generateLottery() throws IOException {
        String filePath = "otos.csv";
        Lottery lottery = new Lottery();
        lottery.setLotteryList();
        File file = new ClassPathResource(filePath).getFile();
        LotteryFileReader lotteryFileReader = new LotteryFileReaderImpl(lottery);
        lotteryFileReader.readFromFile(file);
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
        KieContainer kContainer = kieServices.getKieClasspathContainer();
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
        statelesskSession.setGlobal("LOGGER", LOGGER);
        return statelesskSession;
    }

}
