package com.lottery.service;


import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.kie.KieService;
import com.lottery.kie.KieServiceImpl;
import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;
import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Ez a teszt osztály kizárólag a AverageTimeBetweenTwoMatchFiveDraws service-hez tartozó rule-t teszteli
 */
public class TestAverageTimeBetweenTwoMatchFiveDraws {

    private KieSession kieSession;
    private WeeklyDrawList weeklyDrawList;
    private AverageTimeBetweenTwoMatchFiveDrawsResult result;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestAverageTimeBetweenTwoMatchFiveDraws.class);

    @Before
    public void setup(){
        KieService kieService = new KieServiceImpl();
        try {
            this.kieSession = kieService.generateNewKieSession(KieSessionName.KIE_SESSION);
        }
        catch (MissingKieServicesException e){
            TestAverageTimeBetweenTwoMatchFiveDraws.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }

        this.weeklyDrawList  = new WeeklyDrawList();
        this.weeklyDrawList.setDrawListPreparedForDrools(this.getStubbedDrawList());
        this.result = new AverageTimeBetweenTwoMatchFiveDrawsResult();
    }

    @After
    public void tearDown() {
        if (this.kieSession != null) {
            this.kieSession.dispose();
        }
    }

    @Test
    public void testRuleFired() {
        this.kieSession.insert(this.weeklyDrawList);
        int fire = this.kieSession.fireAllRules();
        assertTrue(fire > 0);
    }

    @Test
    public void testRuleFiredOnce() {
        this.kieSession.insert(this.weeklyDrawList);
        int fire = this.kieSession.fireAllRules();
        assertEquals(1, fire);
    }

    @Test
    public void testAverageTimeBetweenTwoMatchFiveDrawsFiredOnly() throws Exception {

        this.kieSession.insert(this.weeklyDrawList);
        int rulesFired = this.kieSession.fireAllRules( new RuleNameEqualsAgendaFilter( "Average Time Between Two Match Five Draws" ) );
        assertEquals( 1, rulesFired );
    }

    @Test
    public void testResultIsNotEmpty() throws Exception {

        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        Integer resultInt = this.result.getResult();
        assertTrue(resultInt != null);
    }

    @Test
    public void testResultIsMoreThanZero() throws Exception {

        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        Integer resultInt = this.result.getResult();
        assertTrue(resultInt > 0);
    }

    @Test
    public void testResultIsFiftyTwoWithStubbedDraws() throws Exception {

        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        Integer resultInt = this.result.getResult();
        assertEquals(52, resultInt.intValue());
    }

    /**
     * Hard code-olt húzás listát állít elő ötös 3db ötös találattal,
     * 3db egyéb találattal, beállított dátummal és húzási évvel, átlag 52 eltelt évvel.
     */
    private List<WeeklyDraw> getStubbedDrawList() {

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

        weeklyDraw0.setYear(1993);
        weeklyDraw1.setYear(1994);
        weeklyDraw2.setYear(1995);
        weeklyDraw3.setYear(1996);
        weeklyDraw4.setYear(1997);
        weeklyDraw5.setYear(1998);

        weeklyDraw0.setWeek(7);
        weeklyDraw0.setWeek(8);
        weeklyDraw0.setWeek(9);
        weeklyDraw0.setWeek(10);
        weeklyDraw0.setWeek(11);
        weeklyDraw0.setWeek(12);

        weeklyDraw0.setFiveMatch(1);
        weeklyDraw0.setThreeMatch(56);
        weeklyDraw0.setTwoMatch(250);

        weeklyDraw1.setFiveMatch(2);
        weeklyDraw1.setThreeMatch(78);
        weeklyDraw1.setTwoMatch(320);

        weeklyDraw2.setFiveMatch(1);
        weeklyDraw2.setFourMatch(2);
        weeklyDraw2.setThreeMatch(46);
        weeklyDraw2.setTwoMatch(200);

        weeklyDraw3.setFourMatch(1);
        weeklyDraw3.setThreeMatch(25);
        weeklyDraw3.setTwoMatch(309);

        weeklyDraw4.setFourMatch(1);
        weeklyDraw4.setThreeMatch(65);
        weeklyDraw4.setTwoMatch(456);

        weeklyDraw4.setThreeMatch(41);
        weeklyDraw4.setTwoMatch(246);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
            String dateInString0 = "31_08_1993";
            String dateInString1 = "12_07_1994";
            String dateInString2 = "23_05_1995";
            String dateInString3 = "11_08_1986";
            String dateInString4 = "04_02_1997";
            String dateInString5 = "01_04_1998";
            Date date0 = sdf.parse(dateInString0);
            Date date1 = sdf.parse(dateInString1);
            Date date2 = sdf.parse(dateInString2);
            Date date3 = sdf.parse(dateInString3);
            Date date4 = sdf.parse(dateInString4);
            Date date5 = sdf.parse(dateInString5);
            weeklyDraw0.setDrawDate(date0);
            weeklyDraw1.setDrawDate(date1);
            weeklyDraw2.setDrawDate(date2);
            weeklyDraw3.setDrawDate(date3);
            weeklyDraw4.setDrawDate(date4);
            weeklyDraw5.setDrawDate(date5);
        } catch (ParseException e) {
            TestAverageTimeBetweenTwoMatchFiveDraws.LOGGER.debug("Dátum formátum parse-olásánál fellépő probléma.", e);
        }

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
