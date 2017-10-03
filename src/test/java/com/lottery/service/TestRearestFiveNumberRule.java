package com.lottery.service;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.kie.KieService;
import com.lottery.kie.KieServiceImpl;
import com.lottery.model.Lottery;
import com.lottery.model.RearestFiveResult;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
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
    private WeeklyDrawList weeklyDrawList;
    private RearestFiveResult rearestFiveResult;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRearestFiveNumberRule.class);

    /**
     * Heti lottószám húzás példányok generálása teszteléshez.
     */
    private void generateWeeklyDrawList() {
        this.weeklyDrawList = new WeeklyDrawList();
        List<WeeklyDraw> drawList = new ArrayList<>();
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
        this.weeklyDrawList.setDrawListPreparedForDrools(drawList);
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

        this.rearestFiveResult = new RearestFiveResult();
        this.kieSession.insert(this.rearestFiveResult);
        this.kieSession.insert(this.weeklyDrawList);
    }

    @After
    public void tearDown() {
        if (this.kieSession != null) {
            this.kieSession.dispose();
        }
    }

    @Test
    public void testRuleFired() {
        int fire = this.kieSession.fireAllRules();

        assertTrue(fire > 0);
    }

    @Test
    public void testRuleFiredOnce() {
        int fire = this.kieSession.fireAllRules(10);

        assertEquals(1, fire);
    }

    @Test
    public void testRearesNumbersRuleFired() throws Exception {
        int rulesFired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Find rearest five numbers"));

        assertEquals(1, rulesFired);
    }

    @Test
    public void testResultNotEmpty() {
        this.kieSession.fireAllRules();

        assertEquals(5, this.rearestFiveResult.getResult().size());
    }

    @Test
    public void testResultRearestNumbers() {
        this.kieSession.fireAllRules();

        assertEquals(new ArrayList<Integer>(Arrays.asList(11,12,13,14,15)), this.rearestFiveResult.getResult());
    }

}
