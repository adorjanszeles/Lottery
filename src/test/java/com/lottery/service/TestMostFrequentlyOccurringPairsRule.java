package com.lottery.service;

import com.lottery.common.enums.KieSessionName;
import com.lottery.common.exceptions.MissingKieServicesException;
import com.lottery.kie.KieService;
import com.lottery.model.MostFrequentlyOccurringPairsResult;
import com.lottery.model.WeeklyDraw;
import com.lottery.kie.KieServiceImpl;
import com.lottery.model.WeeklyDrawList;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Ez a teszt osztály kizárólag a MostFrequentlyOccurringPairs service-hez tartozó rule-t teszteli
 */
public class TestMostFrequentlyOccurringPairsRule {

    private KieSession kieSession;
    private WeeklyDrawList weeklyDrawList;
    private MostFrequentlyOccurringPairsResult result;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestMostFrequentlyOccurringPairsRule.class);

    @Before
    public void setup(){
        KieService kieService = new KieServiceImpl();
        try {
            this.kieSession = kieService.generateNewKieSession(KieSessionName.KIE_SESSION);
        }
        catch (MissingKieServicesException e){
            TestMostFrequentlyOccurringPairsRule.LOGGER.debug("Hiányzó com.lottery.kie service", e);
        }

        this.weeklyDrawList  = new WeeklyDrawList();
        this.weeklyDrawList.setDrawListPreparedForDrools(getStubbedDrawList());
        this.result = new MostFrequentlyOccurringPairsResult();

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
        this.kieSession.insert(this.result);
        int fire = this.kieSession.fireAllRules();
        assertTrue(fire > 0);
    }

    @Test
    public void testRuleFiredOnce() {
        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        int fire = this.kieSession.fireAllRules();
        assertEquals(1,fire);
    }

    @Test
    public void testMostFrequentlyOccurringPairRuleFiredOnly() throws Exception {

        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        int rulesFired = this.kieSession.fireAllRules( new RuleNameEqualsAgendaFilter( "Most Frequented Occurring Pair" ) );
        assertEquals( 1, rulesFired );
    }

    @Test
    public void testResultIsNotEmpty() throws Exception {

        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        Integer[][] resultArray = this.result.getResult();
        assertTrue(resultArray.length > 0);
    }

    @Test
    public void testResultIsOneAndTwo() throws Exception {

        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        Integer[][] resultArray = this.result.getResult();
        assertEquals(1 , resultArray[0][0].intValue());
        assertEquals(2, resultArray[0][1].intValue());
    }

    @Test
    public void testPairOfOneAndTwoOccurTenTimes() throws Exception {

        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        Integer[][] resultArray = this.result.getResult();
        assertEquals( 10,resultArray[0][0].intValue() );
    }

    @Test
    public void testResultIsContainingMorePairsWithSameOccurring() throws Exception {

        this.settWeeklyDrawsForContainingTwoPairsWithSameOccurring();
        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        Integer[][] resultArray = this.result.getResult();
        assertEquals(2, resultArray.length);
    }

    @Test
    public void testResultContainingPairOfOneWithTwoAndThreeWithFour() throws Exception {

        this.settWeeklyDrawsForContainingTwoPairsWithSameOccurring();
        this.kieSession.insert(this.weeklyDrawList);
        this.kieSession.insert(this.result);
        this.kieSession.fireAllRules();
        Integer[][] resultArray = this.result.getResult();
        assertEquals(1 , resultArray[0][0].intValue());
        assertEquals(2, resultArray[0][1].intValue());
        assertEquals(3 , resultArray[1][0].intValue());
        assertEquals(4, resultArray[1][1].intValue());
    }


    /**
     * Hard code-olt húzás listát állít elő, amiben a legtöbbször előforduló számpár az 1,2.
     * @return WeeklyDraw objektum List
     */
    private List<WeeklyDraw> getStubbedDrawList(){

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

        weeklyDraw0.setDrawnNumbers(new Integer[]{1,2,3,4,5});
        weeklyDraw1.setDrawnNumbers(new Integer[]{1,2,34,45,54});
        weeklyDraw2.setDrawnNumbers(new Integer[]{1,2,45,67,90});
        weeklyDraw3.setDrawnNumbers(new Integer[]{1,2,56,78,5});
        weeklyDraw4.setDrawnNumbers(new Integer[]{1,2,23,45,90});
        weeklyDraw5.setDrawnNumbers(new Integer[]{1,2,78,43,51});
        weeklyDraw6.setDrawnNumbers(new Integer[]{1,2,33,48,9});
        weeklyDraw7.setDrawnNumbers(new Integer[]{1,2,31,44,59});
        weeklyDraw8.setDrawnNumbers(new Integer[]{1,2,67,87,59});
        weeklyDraw9.setDrawnNumbers(new Integer[]{1,2,90,4,5});

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
    private void settWeeklyDrawsForContainingTwoPairsWithSameOccurring(){

        List<Integer[]> newDraws = new LinkedList<Integer[]>();
        newDraws.add(new Integer[]{15,26,3,4,5});
        newDraws.add(new Integer[]{18,29,3,4,54});
        newDraws.add(new Integer[]{13,27,3,4,90});
        newDraws.add(new Integer[]{11,12,3,4,5});
        newDraws.add(new Integer[]{41,27,3,4,90});
        newDraws.add(new Integer[]{1,2,32,44,51});
        newDraws.add(new Integer[]{1,2,73,84,9});
        newDraws.add(new Integer[]{1,2,33,64,59});
        newDraws.add(new Integer[]{1,2,53,74,59});
        newDraws.add(new Integer[]{1,2,23,64,5});

        for(int n = 0; n < 10; n++){
            this.weeklyDrawList.getDrawListPreparedForDrools().get(n).setDrawnNumbers(newDraws.get(n));
        }
    }
}
