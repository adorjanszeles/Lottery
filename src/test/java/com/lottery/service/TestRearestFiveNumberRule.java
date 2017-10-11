package com.lottery.service;

import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.config.LotteryConfig;
import com.lottery.model.RearestFiveResult;
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
 * Teszt osztály az 5 legritkábban kihúzott lottószám implementációjának teszteléséhez.
 */
public class TestRearestFiveNumberRule {

    private StatelessKieSession statelessKieSession;
    private WeeklyDrawList weeklyDrawList;
    private RearestFiveResult rearestFiveResult;
    private List<Object> facts;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRearestFiveNumberRule.class);

    /**
     * Heti lottószám húzás példányok generálása teszteléshez.
     */
    private void generateWeeklyDrawList() {
        this.weeklyDrawList = new WeeklyDrawList();
        List<WeeklyDraw> drawList = new ArrayList<>();
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
        LotteryConfig lotteryConfig = new LotteryConfig();
        try {
            this.statelessKieSession = lotteryConfig.getNewStatelessKieSession();
        } catch (MissingKieServicesException e) {
            TestRearestFiveNumberRule.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }
        this.generateWeeklyDrawList();
        this.rearestFiveResult = new RearestFiveResult();

        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.rearestFiveResult));
        this.statelessKieSession.execute(this.facts);
    }



    @Test
    public void testResultNotEmpty() {

        assertEquals(5, this.rearestFiveResult.getResult().size());
    }

    @Test
    public void testResultRearestNumbers() {

        assertEquals(new ArrayList<Integer>(Arrays.asList(11,12,13,14,15)), this.rearestFiveResult.getResult());
    }

}
