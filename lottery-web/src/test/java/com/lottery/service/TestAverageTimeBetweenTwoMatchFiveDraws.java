package com.lottery.service;

import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.config.LotteryConfig;
import com.lottery.listener.LottoAgendaEventListener;
import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;

import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Ez a teszt osztály kizárólag a AverageTimeBetweenTwoMatchFiveDraws service-hez tartozó rule-t teszteli
 */
public class TestAverageTimeBetweenTwoMatchFiveDraws {

    private StatelessKieSession statelessKieSession;
    private WeeklyDrawList weeklyDrawList;
    private AverageTimeBetweenTwoMatchFiveDrawsResult result;
    private List<Object> facts;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestAverageTimeBetweenTwoMatchFiveDraws.class);
    private String eventName;
    private LottoAgendaEventListener listener;

    @Before
    public void setup() throws MissingKieServicesException, ParseException {
        LotteryConfig lotteryConfig = new LotteryConfig();
        this.statelessKieSession = lotteryConfig.getNewStatelessKieSession();
        this.weeklyDrawList = new WeeklyDrawList();
        this.weeklyDrawList.setDrawListPreparedForDrools(this.getStubbedDrawList());
        this.result = new AverageTimeBetweenTwoMatchFiveDrawsResult();
        this.eventName = null;
        this.listener = new LottoAgendaEventListener();

        this.facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.result));
        this.statelessKieSession.addEventListener(this.listener);
        this.statelessKieSession.execute(this.facts);
    }

    @Test
    public void testRuleFired() {
        this.eventName = this.listener.getRuleName();

        assertEquals("Average Time Between Two Match Five Draws", this.eventName);
    }

    @Test
    public void testRuleFiredOnce() {
        int fire = this.listener.getCountFire();

        assertEquals(1, fire);
    }

    @Test
    public void testAverageTimeBetweenTwoMatchFiveDrawsFiredOnly() throws Exception {
        this.eventName = this.listener.getRuleName();

        assertEquals("Average Time Between Two Match Five Draws", this.eventName);
    }

    @Test
    public void testResultIsNotEmpty() throws Exception {

        Float resultInt = this.result.getResult();
        assertTrue(resultInt != null);
    }

    @Test
    public void testResultIsMoreThanZero() throws Exception {

        Float resultInt = this.result.getResult();
        assertTrue(resultInt > 0);
    }

    @Test
    public void testResultIsFiftyTwoWithStubbedDraws() throws Exception {

        float resultFloat = this.result.getResult();
        assertEquals(0.5, resultFloat, 0.0001);
    }

    /**
     * Hard code-olt húzás listát állít elő ötös 3db ötös találattal,
     * 3db egyéb találattal, beállított dátummal és húzási évvel, átlag 52 eltelt évvel.
     */
    private List<WeeklyDraw> getStubbedDrawList() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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

        weeklyDraw0.setFiveMatch(1);
        weeklyDraw0.setThreeMatch(56);
        weeklyDraw0.setTwoMatch(250);

        weeklyDraw1.setThreeMatch(78);
        weeklyDraw1.setTwoMatch(320);

        weeklyDraw2.setFiveMatch(1);
        weeklyDraw2.setFourMatch(2);
        weeklyDraw2.setThreeMatch(46);
        weeklyDraw2.setTwoMatch(200);

        weeklyDraw3.setFourMatch(1);
        weeklyDraw3.setThreeMatch(25);
        weeklyDraw3.setTwoMatch(309);

        weeklyDraw4.setThreeMatch(65);
        weeklyDraw4.setTwoMatch(456);

        weeklyDraw5.setFiveMatch(1);
        weeklyDraw5.setThreeMatch(41);
        weeklyDraw5.setTwoMatch(246);

        weeklyDraw8.setFiveMatch(1);
        weeklyDraw8.setThreeMatch(41);
        weeklyDraw8.setTwoMatch(246);


        weeklyDraw0.setDrawDate(format.parse("31-08-1993"));
        weeklyDraw1.setDrawDate(format.parse("12-07-1994"));
        weeklyDraw2.setDrawDate(format.parse("23-05-1995"));
        weeklyDraw3.setDrawDate(format.parse("11-08-1986"));
        weeklyDraw4.setDrawDate(format.parse("04-02-1997"));
        weeklyDraw5.setDrawDate(format.parse("01-04-1998"));

        List<WeeklyDraw> weeklyDrawList = new ArrayList<WeeklyDraw>();

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
}
