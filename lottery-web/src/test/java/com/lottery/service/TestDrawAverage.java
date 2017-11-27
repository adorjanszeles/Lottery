package com.lottery.service;

import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.config.LotteryConfig;
import com.lottery.listener.LottoAgendaEventListener;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDrawAverage.class);
    private StatelessKieSession statelessKieSession;
    private WeeklyDrawList weeklyDrawList;
    private AverageResult averageResult;
    private List<Object> facts;
    private String eventName;
    private LottoAgendaEventListener listener;

    @Before
    public void setup() throws MissingKieServicesException {
        LotteryConfig lotteryConfig = new LotteryConfig();
        this.statelessKieSession = lotteryConfig.getNewStatelessKieSession();

        this.generateWeeklyDrawList();
        this.averageResult = new AverageResult();
        this.eventName = null;
        this.listener = new LottoAgendaEventListener();

        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.averageResult));

        this.statelessKieSession.addEventListener(this.listener);
        this.statelessKieSession.execute(this.facts);
    }

    @Test
    public void testResultNotZero() throws Exception {
        this.statelessKieSession.execute(this.facts);

        assertTrue(this.averageResult.getResult() > 0f);
    }

    @Test
    public void testRuleFired() throws Exception {
        this.eventName = listener.getRuleName();

        assertTrue(!this.eventName.isEmpty());
    }

    @Test
    public void testRuleFiredOnce() throws Exception {
        int fire = this.listener.getCountFire();

        assertEquals(1, fire);
    }

    @Test
    public void testAverageNumberRuleFired() throws Exception {
        this.eventName = listener.getRuleName();

        assertEquals("Find average number", eventName);
    }

    @Test
    public void testDrawAverageResult() throws Exception {
        this.statelessKieSession.execute(this.facts);

        assertEquals(4.6666665f, this.averageResult.getResult(), 0.0001);
    }

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

        firstWeeklyDraw.fillDrawnNumbers(firstDraw);
        secondWeeklyDraw.fillDrawnNumbers(secondDraw);
        thirdWeeklyDraw.fillDrawnNumbers(thirdDraw);

        drawList.add(firstWeeklyDraw);
        drawList.add(secondWeeklyDraw);
        drawList.add(thirdWeeklyDraw);
        this.weeklyDrawList.setDrawListPreparedForDrools(drawList);
    }

}
