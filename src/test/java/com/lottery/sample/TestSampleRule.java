package com.lottery.sample;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.kie.KieServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Példa Szabály teszt osztály
 */
public class TestSampleRule {

    private KieSession kieSession;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestSampleRule.class);

    @Before
    public void setup(){
        KieServiceImpl kieService = new KieServiceImpl();
        try {
            this.kieSession = kieService.generateNewKieSession(KieSessionName.KIE_SESSION);
        }
        catch (MissingKieServicesException e){
            TestSampleRule.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }
    }

    @After
    public void tearDown() {
        if (this.kieSession != null) {
            this.kieSession.dispose();
        }
    }

    @Test
    public void testSampleRule() {
        int activation = this.kieSession.fireAllRules();
        assertEquals(1,activation);
    }

}
