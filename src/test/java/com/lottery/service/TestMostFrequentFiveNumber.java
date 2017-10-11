package com.lottery.service;

import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.config.LotteryConfig;
import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;
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

    private StatelessKieSession statelessKieSession;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestMostFrequentFiveNumber.class);
    private WeeklyDrawList weeklyDrawList;
    private MostFrequentFiveNumberResult result;
    private List<Integer> expected;
    private List<Object> facts;

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
        LotteryConfig lotteryConfig = new LotteryConfig();
        try {
            this.statelessKieSession = lotteryConfig.getNewStatelessKieSession();
        } catch (MissingKieServicesException e) {
            TestMostFrequentFiveNumber.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }
        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = new MostFrequentFiveNumberResult();
        this.generateWeeklyDrawList();
        this.result = mostFrequentFiveNumberResult;
        this.expected = new ArrayList<Integer>(Arrays.asList(4, 5, 1, 2, 3));

        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.execute(this.facts);
    }


    @Test
    public void testMostFrequentFiveNumbersListSize() {

        assertEquals(5, this.result.getResult().size());
    }

    @Test
    public void testResultNumbersAreSubsetOfWeeklyDrawList() {

        assertEquals(true, (!this.result.getResult().isEmpty() && this.expected.containsAll(this.result.getResult())));
    }

    @Test
    public void testMostFrequentFiveNumbersValues() {

        assertEquals(this.expected, this.result.getResult());
    }
}
