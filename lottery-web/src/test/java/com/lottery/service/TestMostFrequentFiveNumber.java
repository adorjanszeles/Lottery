package com.lottery.service;

import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.config.LotteryConfig;
import com.lottery.listener.LottoAgendaEventListener;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMostFrequentFiveNumber.class);
    private StatelessKieSession statelessKieSession;
    private WeeklyDrawList weeklyDrawList;
    private MostFrequentFiveNumberResult result;
    private List<Integer> expected;
    private List<Object> facts;
    private String eventName;
    private LottoAgendaEventListener listener;

    /**
     * kie session és teszteléshez használt listák létrehozása a tesztek lefutása előtt
     *
     * @throws MissingKieServicesException hiányzó kie session kivétel
     */

    @Before
    public void setup() throws MissingKieServicesException {
        LotteryConfig lotteryConfig = new LotteryConfig();
        this.statelessKieSession = lotteryConfig.getNewStatelessKieSession();
        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = new MostFrequentFiveNumberResult();
        this.generateWeeklyDrawList();
        this.result = mostFrequentFiveNumberResult;
        this.expected = new ArrayList<Integer>(Arrays.asList(4, 5, 1, 2, 3));
        this.eventName = null;
        this.listener = new LottoAgendaEventListener();

        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.addEventListener(this.listener);
        this.statelessKieSession.execute(this.facts);
    }

    @Test
    public void testIsRuleFired() {
        this.eventName = this.listener.getRuleName();

        assertEquals("Find most frequent five numbers", this.eventName);
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

        firstWeeklyDraw.fillDrawnNumbers(firstDraw);
        secondWeeklyDraw.fillDrawnNumbers(secondDraw);

        drawList.add(firstWeeklyDraw);
        drawList.add(secondWeeklyDraw);
        this.weeklyDrawList.setDrawListPreparedForDrools(drawList);

    }
}
