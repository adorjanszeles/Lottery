package com.lottery.service;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.kie.KieService;
import com.lottery.kie.KieServiceImpl;
import com.lottery.model.Lottery;
import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
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

/**
 * A leggyakoribb öt kihúzott szám keresésére írt szabály tesztelése
 */

public class TestMostFrequentFiveNumber {

    private KieSession kieSession;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestMostFrequentFiveNumber.class);
    private WeeklyDrawList weeklyDrawList;
    private MostFrequentFiveNumberResult result;
    private List<Integer> expected;

    /**
     * heti lottószám húzás példányok létrehozása teszteléshez
     */

    private void generateWeeklyDrawList() {
        this.weeklyDrawList = new WeeklyDrawList();
        List<WeeklyDraw> drawList = new ArrayList<>();
        Integer[] firstDraw = {1, 2, 3, 4, 5};
        Integer[] secondDraw = {4, 5, 6, 7, 8};

        WeeklyDraw firstWeeklyDraw = new WeeklyDraw();
        WeeklyDraw secondWeeklyDraw = new WeeklyDraw();

        firstWeeklyDraw.setDrawnNumbers(firstDraw);
        secondWeeklyDraw.setDrawnNumbers(secondDraw);

        drawList.add(firstWeeklyDraw);
        drawList.add(secondWeeklyDraw);
        this.weeklyDrawList.setDrawListPreparedForDrools(drawList);

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
            TestMostFrequentFiveNumber.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }

        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = new MostFrequentFiveNumberResult();
        this.generateWeeklyDrawList();
        this.result = mostFrequentFiveNumberResult;
        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.expected = new ArrayList<Integer>(Arrays.asList(4, 5, 1, 2, 3));
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
        int activation = this.kieSession.fireAllRules();
        assertEquals(1, activation);
    }

    @Test
    public void testMostFrequentFiveNumbersListSize() {
        this.kieSession.fireAllRules();
        assertEquals(5, this.result.getResult().size());
    }

    @Test
    public void testResultNumbersAreSubsetOfWeeklyDrawList() {
        this.kieSession.fireAllRules();
        assertEquals(true, (!this.result.getResult().isEmpty() && this.expected.containsAll(this.result.getResult())));
    }

    @Test
    public void testMostFrequentFiveNumbersValues() {
        this.kieSession.fireAllRules();
        assertEquals(this.expected, this.result.getResult());
    }
}
