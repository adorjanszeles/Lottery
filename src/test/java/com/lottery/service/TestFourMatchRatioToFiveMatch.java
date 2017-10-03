package com.lottery.service;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.kie.KieService;
import com.lottery.kie.KieServiceImpl;
import com.lottery.model.FourMatchRatioToFiveMatchResult;
import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFourMatchRatioToFiveMatch {

    private KieSession kieSession;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestFourMatchRatioToFiveMatch.class);
    private WeeklyDrawList weeklyDrawList;
    private FourMatchRatioToFiveMatchResult result;

    /**
     * Négyes lóttó találatok aránya az ötösökhöz szabály teszteléshez
     */

    private void generateWeeklyDrawList() {
        this.weeklyDrawList = new WeeklyDrawList();
        this.weeklyDrawList.setLotteryPreparedForDrools(Lottery.getInstance());
        List<WeeklyDraw> drawList = this.weeklyDrawList.getLotteryPreparedForDrools();
        Lottery.getInstance().getLotteryList().clear();

        Integer firstDrawFiveMatch = 0;
        Integer secondDrawFiveMatch = 1;
        Integer thirdDrawFiveMatch = 0;
        Integer fourthDrawFiveMatch = 1;
        Integer fifthDrawFiveMatch = 0;

        Integer firstDrawFourMatch = 1;
        Integer secondDrawFourMatch = 1;
        Integer thirdDrawFourMatch = 1;
        Integer fourthDrawFourMatch = 0;
        Integer fifthDrawFourMatch = 0;

        WeeklyDraw firstWeeklyDraw = new WeeklyDraw();
        WeeklyDraw secondWeeklyDraw = new WeeklyDraw();
        WeeklyDraw thirdWeeklyDraw = new WeeklyDraw();
        WeeklyDraw fourthWeeklyDraw = new WeeklyDraw();
        WeeklyDraw fifthWeeklyDraw = new WeeklyDraw();

        firstWeeklyDraw.setFiveMatch(firstDrawFiveMatch);
        firstWeeklyDraw.setFourMatch(firstDrawFourMatch);
        secondWeeklyDraw.setFiveMatch(secondDrawFiveMatch);
        secondWeeklyDraw.setFourMatch(secondDrawFourMatch);
        thirdWeeklyDraw.setFiveMatch(thirdDrawFiveMatch);
        thirdWeeklyDraw.setFourMatch(thirdDrawFourMatch);
        fourthWeeklyDraw.setFiveMatch(fourthDrawFiveMatch);
        fourthWeeklyDraw.setFourMatch(fourthDrawFourMatch);
        fifthWeeklyDraw.setFiveMatch(fifthDrawFiveMatch);
        fifthWeeklyDraw.setFourMatch(fifthDrawFourMatch);

        drawList.add(firstWeeklyDraw);
        drawList.add(secondWeeklyDraw);
        drawList.add(thirdWeeklyDraw);
        drawList.add(fourthWeeklyDraw);
        drawList.add(fifthWeeklyDraw);

    }

    private void generateWeeklyDrawListDivisionByZero() {
        this.weeklyDrawList = new WeeklyDrawList();
        this.weeklyDrawList.setLotteryPreparedForDrools(Lottery.getInstance());
        List<WeeklyDraw> drawList = this.weeklyDrawList.getLotteryPreparedForDrools();
        Lottery.getInstance().getLotteryList().clear();

        Integer firstDrawFiveMatch = 0;
        Integer firstDrawFourMatch = 1;

        WeeklyDraw firstWeeklyDraw = new WeeklyDraw();

        firstWeeklyDraw.setFiveMatch(firstDrawFiveMatch);
        firstWeeklyDraw.setFourMatch(firstDrawFourMatch);

        drawList.add(firstWeeklyDraw);
    }

    /**
     * kie session és teszteléshez használt listák létrehozása a tesztek lefutása előtt
     */

    @Before
    public void setup() {
        KieService kieService = new KieServiceImpl();
        try {
            this.kieSession = kieService.generateNewKieSession(KieSessionName.KIE_SESSION);
        } catch (MissingKieServicesException e) {
            TestFourMatchRatioToFiveMatch.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }

        this.result = new FourMatchRatioToFiveMatchResult();
    }

    /**
     * kie session bezárása tesztek futása után
     */

    @After
    public void tearDown() {
        if (this.kieSession != null) {
            this.kieSession.dispose();
        }
    }

    @Test
    public void testIsRuleFired() {
        this.kieSession.insert(this.weeklyDrawList);
        int activation = this.kieSession.fireAllRules();
        assertEquals(1, activation);
    }

    @Test
    public void testRuleFiredOnce() {
        this.kieSession.insert(this.weeklyDrawList);
        int fire = this.kieSession.fireAllRules(10);
        assertEquals(1,fire);
    }

    @Test
    public void testFourRatioToFiveResultIsLargerThanZero() throws Exception {
        this.generateWeeklyDrawList();
        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        assertTrue(this.result.getResult() > 0F);
    }

    @Test
    public void testFourRatioToFiveResultDivisionByZero() throws Exception {
        this.generateWeeklyDrawListDivisionByZero();
        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        //TODO
        assertTrue(((this.result.getFiveMatchNumber() == 0 || this.result.getFourMatchNumber() == 0) && this.result.getResult() == null));
    }

    @Test
    public void testFourRatioToFiveResultValue() throws Exception {
        this.generateWeeklyDrawList();
        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        assertEquals(3F / 2F,this.result.getResult().floatValue(),0.0001);
    }


}
