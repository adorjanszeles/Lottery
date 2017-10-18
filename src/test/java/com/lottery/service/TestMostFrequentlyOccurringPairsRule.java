package com.lottery.service;

import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.config.LotteryConfig;
import com.lottery.listener.LottoAgendaEventListener;
import com.lottery.model.MostFrequentlyOccurringPairsResult;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import com.lottery.model.mfop_utils.DrawsInTwoDimension;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Ez a teszt osztály kizárólag a MostFrequentlyOccurringPairs service-hez tartozó rule-t teszteli
 */
public class TestMostFrequentlyOccurringPairsRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMostFrequentlyOccurringPairsRule.class);
    private StatelessKieSession statelessKieSession;
    private WeeklyDrawList weeklyDrawList;
    private MostFrequentlyOccurringPairsResult result;
    private List<Object> facts;
    private String eventName;
    private LottoAgendaEventListener listener;

    @Before
    public void setup() throws MissingKieServicesException {
        LotteryConfig lotteryConfig = new LotteryConfig();
        this.statelessKieSession = lotteryConfig.getNewStatelessKieSession();
        this.weeklyDrawList = new WeeklyDrawList();
        this.weeklyDrawList.setDrawListPreparedForDrools(getStubbedDrawList());
        this.result = new MostFrequentlyOccurringPairsResult();
        this.eventName = null;
        this.listener = new LottoAgendaEventListener();
    }

    @Test
    public void testRuleFired() {
        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.addEventListener(this.listener);
        this.statelessKieSession.execute(this.facts);
        this.eventName = this.listener.getRuleName();
        assertEquals( "Most Frequently Occurring Pairs", this.eventName );
    }

    @Test
    public void testRuleFiredOnce() {
        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.addEventListener(this.listener);
        this.statelessKieSession.execute(this.facts);
        int fire = this.listener.getCountFire();

        assertEquals(1, fire);
    }

    @Test
    public void testMostFrequentlyOccurringPairRuleFiredOnly() throws Exception {

        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.addEventListener(this.listener);
        this.statelessKieSession.execute(this.facts);
        this.eventName = this.listener.getRuleName();
        assertEquals( "Most Frequently Occurring Pairs", this.eventName );
    }


    @Test
    public void testResultIsNotEmpty() throws Exception {

        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.execute(this.facts);
        DrawsInTwoDimension resultArray = this.result.getResultArray();
        assertTrue(this.result.getResultArray().getRows().size() > 0);
    }

    @Test
    public void testResultIsOneAndTwo() throws Exception {

        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.execute(this.facts);
        DrawsInTwoDimension resultArray = this.result.getResultArray();
        assertEquals(1, resultArray.getRows().get(0).getColumns()[0].intValue());
        assertEquals(2, resultArray.getRows().get(0).getColumns()[1].intValue());
    }

    @Test
    public void testResultIsContainingMorePairsWithSameOccurring() throws Exception {

        this.settWeeklyDrawsForContainingTwoPairsWithSameOccurring();
        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.execute(this.facts);
        DrawsInTwoDimension resultArray = this.result.getResultArray();
        assertEquals(2, resultArray.getRows().size());
    }

    @Test
    public void testResultContainingPairOfOneWithTwoAndThreeWithFour() throws Exception {

        this.settWeeklyDrawsForContainingTwoPairsWithSameOccurring();
        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.execute(this.facts);
        DrawsInTwoDimension resultArray = this.result.getResultArray();
        assertEquals(1, resultArray.getRows().get(0).getColumns()[0].intValue());
        assertEquals(2, resultArray.getRows().get(0).getColumns()[1].intValue());
        assertEquals(3, resultArray.getRows().get(1).getColumns()[0].intValue());
        assertEquals(4, resultArray.getRows().get(1).getColumns()[1].intValue());
    }

    /**
     * Hard code-olt húzás listát állít elő, amiben a legtöbbször előforduló számpár az 1,2.
     *
     * @return WeeklyDraw objektum List
     */
    private List<WeeklyDraw> getStubbedDrawList() {

        List<WeeklyDraw> weeklyDrawList = new ArrayList<WeeklyDraw>();
        WeeklyDraw weeklyDraw0 = new WeeklyDraw();
        WeeklyDraw weeklyDraw1 = new WeeklyDraw();
        WeeklyDraw weeklyDraw2 = new WeeklyDraw();
        WeeklyDraw weeklyDraw3 = new WeeklyDraw();
        WeeklyDraw weeklyDraw4 = new WeeklyDraw();
        WeeklyDraw weeklyDraw5 = new WeeklyDraw();
        WeeklyDraw weeklyDraw6 = new WeeklyDraw();
        WeeklyDraw weeklyDraw7 = new WeeklyDraw();
        WeeklyDraw weeklyDraw8 = new WeeklyDraw();
        WeeklyDraw weeklyDraw9 = new WeeklyDraw();

        weeklyDraw0.setDrawnNumbers(new Integer[]{1, 2, 3, 4, 5});
        weeklyDraw1.setDrawnNumbers(new Integer[]{1, 2, 34, 45, 54});
        weeklyDraw2.setDrawnNumbers(new Integer[]{1, 2, 45, 67, 90});
        weeklyDraw3.setDrawnNumbers(new Integer[]{1, 2, 56, 78, 5});
        weeklyDraw4.setDrawnNumbers(new Integer[]{1, 2, 23, 45, 90});
        weeklyDraw5.setDrawnNumbers(new Integer[]{1, 2, 78, 43, 51});
        weeklyDraw6.setDrawnNumbers(new Integer[]{1, 2, 33, 48, 9});
        weeklyDraw7.setDrawnNumbers(new Integer[]{1, 2, 31, 44, 59});
        weeklyDraw8.setDrawnNumbers(new Integer[]{1, 2, 67, 87, 59});
        weeklyDraw9.setDrawnNumbers(new Integer[]{1, 2, 90, 4, 5});

        weeklyDrawList.add(weeklyDraw0);
        weeklyDrawList.add(weeklyDraw1);
        weeklyDrawList.add(weeklyDraw2);
        weeklyDrawList.add(weeklyDraw3);
        weeklyDrawList.add(weeklyDraw4);
        weeklyDrawList.add(weeklyDraw5);
        weeklyDrawList.add(weeklyDraw6);
        weeklyDrawList.add(weeklyDraw7);
        weeklyDrawList.add(weeklyDraw8);
        weeklyDrawList.add(weeklyDraw9);

        return weeklyDrawList;
    }

    /**
     * Olyan húzás listát állít elő, amiben több, azonos gyakorisággal előforduló pár is található.
     */
    private void settWeeklyDrawsForContainingTwoPairsWithSameOccurring() {

        List<Integer[]> newDraws = new LinkedList<Integer[]>();
        newDraws.add(new Integer[]{15, 26, 3, 4, 5});
        newDraws.add(new Integer[]{18, 29, 3, 4, 54});
        newDraws.add(new Integer[]{13, 27, 3, 4, 90});
        newDraws.add(new Integer[]{11, 12, 3, 4, 5});
        newDraws.add(new Integer[]{41, 27, 3, 4, 90});
        newDraws.add(new Integer[]{1, 2, 32, 44, 51});
        newDraws.add(new Integer[]{1, 2, 73, 84, 9});
        newDraws.add(new Integer[]{1, 2, 33, 64, 59});
        newDraws.add(new Integer[]{1, 2, 53, 74, 59});
        newDraws.add(new Integer[]{1, 2, 23, 64, 5});

        for (int n = 0; n < 10; n++) {
            this.weeklyDrawList.getDrawListPreparedForDrools().get(n).setDrawnNumbers(newDraws.get(n));
        }
    }

}
