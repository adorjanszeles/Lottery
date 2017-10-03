package com.lottery.service;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.kie.KieService;
import com.lottery.kie.KieServiceImpl;
import com.lottery.model.AverageResult;
import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Teszt osztály a "lottószám húzások átlaga" feladat teszteléséhez
 */
public class TestDrawAverage {

    private KieSession kieSession;
    private WeeklyDrawList weeklyDrawList;
    private AverageResult averageResult;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDrawAverage.class);

    /**
     * Heti lottószám húzás példányok generálása teszteléshez.
     */
    private void generateWeeklyDrawList() {
        this.weeklyDrawList = new WeeklyDrawList();
        this.weeklyDrawList.setLotteryPreparedForDrools(Lottery.getInstance());
        List<WeeklyDraw> drawList = this.weeklyDrawList.getLotteryPreparedForDrools();
        Lottery.getInstance().getLotteryList().clear();

        Integer[] firstDraw = {1,2,3,4,5};
        Integer[] secondDraw = {6,7,8,9,10};
        Integer[] thirdDraw = {1,2,3,4,5};

        WeeklyDraw firstWeeklyDraw = new WeeklyDraw();
        WeeklyDraw secondWeeklyDraw = new WeeklyDraw();
        WeeklyDraw thirdWeeklyDraw = new WeeklyDraw();

        firstWeeklyDraw.setDrawnNumbers(firstDraw);
        secondWeeklyDraw.setDrawnNumbers(secondDraw);
        thirdWeeklyDraw.setDrawnNumbers(thirdDraw);

        drawList.add(firstWeeklyDraw);
        drawList.add(secondWeeklyDraw);
        drawList.add(thirdWeeklyDraw);
    }

    @Before
    public void setup(){
        KieService kieService = new KieServiceImpl();
        try {
            this.kieSession = kieService.generateNewKieSession(KieSessionName.KIE_SESSION);
        }
        catch (MissingKieServicesException e){
            TestDrawAverage.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }
        this.generateWeeklyDrawList();

        this.kieSession.insert(this.weeklyDrawList);
        this.averageResult = new AverageResult();
        this.kieSession.insert(this.averageResult);
    }

    @After
    public void tearDown() {
        if (this.kieSession != null) {
            this.kieSession.dispose();
        }
    }

    @Test
    public void testRuleFired() throws Exception {
        int rulesFired = this.kieSession.fireAllRules();

        assertTrue(rulesFired > 0);
    }

    @Test
    public void testRuleFiredOnce() throws Exception {
        int rulesFired = this.kieSession.fireAllRules(10);

        assertEquals(1, rulesFired);
    }

    @Test
    public void testAverageNumberRuleFired() throws Exception {
        int rulesFired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Find average number"));

        assertEquals(1, rulesFired);
    }

    @Test
    public void testResultNotZero() throws Exception {
        this.kieSession.fireAllRules();

        assertTrue(this.averageResult.getResult() > 0f);
    }

    @Test
    public void testDrawAverageResult() throws Exception {
        this.kieSession.fireAllRules();

        assertEquals(4.6666665f, this.averageResult.getResult(), 0.0001);
    }

}
