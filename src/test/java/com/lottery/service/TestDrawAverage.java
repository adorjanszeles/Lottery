package com.lottery.service;

import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.config.LotteryConfig;
import com.lottery.model.AverageResult;
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
import static org.junit.Assert.assertTrue;

/**
 * Teszt osztály a "lottószám húzások átlaga" feladat teszteléséhez
 */
public class TestDrawAverage {

    private StatelessKieSession statelessKieSession;
    private WeeklyDrawList weeklyDrawList;
    private AverageResult averageResult;
    private List<Object> facts;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDrawAverage.class);

    /**
     * Heti lottószám húzás példányok generálása teszteléshez.
     */
    private void generateWeeklyDrawList() {
        this.weeklyDrawList = new WeeklyDrawList();
        List<WeeklyDraw> drawList = new ArrayList<>();
        Integer[] firstDraw = {1, 2, 3, 4, 5};
        Integer[] secondDraw = {6, 7, 8, 9, 10};
        Integer[] thirdDraw = {1, 2, 3, 4, 5};

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
    public void setup() {
        LotteryConfig lotteryConfig = new LotteryConfig();
        try {
            this.statelessKieSession = lotteryConfig.getNewStatelessKieSession();
        } catch (MissingKieServicesException e) {
            TestDrawAverage.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }
        this.generateWeeklyDrawList();
        this.averageResult = new AverageResult();

        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.averageResult));
        this.statelessKieSession.execute(this.facts);
    }

    @Test
    public void testResultNotZero() throws Exception {

        assertTrue(this.averageResult.getResult() > 0f);
    }

    @Test
    public void testDrawAverageResult() throws Exception {

        assertEquals(4.6666665f, this.averageResult.getResult(), 0.0001);
    }

}
