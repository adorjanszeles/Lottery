package com.lottery.service;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.kie.KieService;
import com.lottery.kie.KieServiceImpl;
import com.lottery.model.Lottery;
import com.lottery.model.RearestFiveResult;
import com.lottery.model.WeeklyDraw;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Teszt osztály az 5 legritkábban kihúzott lottószám implementációjának teszteléséhez.
 */
public class TestRearestFiveNumberRule {

    private KieSession kieSession;
    private List<WeeklyDraw> weeklyDrawList;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRearestFiveNumberRule.class);

    /**
     * Heti lottószám húzás példányok generálása teszteléshez.
     */
    private void generateWeeklyDrawList() {
        this.weeklyDrawList = Lottery.getInstance().getLotteryList();

        Integer[] firstDraw = {1,2,3,4,5};
        Integer[] secondDraw = {6,7,8,9,10};
        Integer[] thirdDraw = {1,2,3,4,5};

        WeeklyDraw firstWeeklyDraw = new WeeklyDraw();
        WeeklyDraw secondWeeklyDraw = new WeeklyDraw();
        WeeklyDraw thirdWeeklyDraw = new WeeklyDraw();

        firstWeeklyDraw.setDrawnNumbers(firstDraw);
        secondWeeklyDraw.setDrawnNumbers(secondDraw);
        thirdWeeklyDraw.setDrawnNumbers(thirdDraw);

        this.weeklyDrawList.add(firstWeeklyDraw);
        this.weeklyDrawList.add(secondWeeklyDraw);
        this.weeklyDrawList.add(thirdWeeklyDraw);
    }

    @Before
    public void setup(){
        KieService kieService = new KieServiceImpl();
        try {
            this.kieSession = kieService.generateNewKieSession(KieSessionName.KIE_SESSION);
        }
        catch (MissingKieServicesException e){
            TestRearestFiveNumberRule.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }
        this.generateWeeklyDrawList();
    }

    @After
    public void tearDown() {
        if (this.kieSession != null) {
            this.kieSession.dispose();
        }
    }

    @Test
    public void testRuleFired() {
        this.kieSession.insert(this.weeklyDrawList);
        int fire = this.kieSession.fireAllRules();

        assertTrue(fire > 0);
    }

    @Test
    public void testRuleFiredOnce() {
        this.kieSession.insert(this.weeklyDrawList);
        int fire = this.kieSession.fireAllRules(10);

        assertTrue(fire == 1);
    }

    @Test
    public void testFrequentNumbersRuleFired() throws Exception {
        this.kieSession.insert(this.weeklyDrawList);
        int rulesFired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Find the five most frequent number"));

        assertEquals(1, rulesFired);
    }

    @Test
    public void testResultNotEmpty() {
        RearestFiveResult rearestFiveResult = new RearestFiveResult();
        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(rearestFiveResult);
        this.kieSession.fireAllRules();

        assertTrue(rearestFiveResult.getResult().size() == 5);
    }

    @Test
    public void testResultRearestNumbers() {
        RearestFiveResult rearestFiveResult = new RearestFiveResult();
        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(rearestFiveResult);

        assertEquals(new ArrayList<Integer>(Arrays.asList(6,7,8,9,10)), rearestFiveResult.getResult());
    }

}
