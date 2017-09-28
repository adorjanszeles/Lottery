package com.lottery.model;

import com.lottery.service.LotteryFileReader;
import com.lottery.service.LotteryFileReaderImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Input adatok beolvasásához írt tesztek
 */

public class TestLotteryFileReader {

    private static final String FILE_PATH = "resources/LotteryWeeklyDraws.csv";
    private static final Lottery LOTTERY = Lottery.getInstance();
    private LotteryFileReader lotteryFileReader;
    private List<WeeklyDraw> lotteryList;
    private List<WeeklyDraw> result;

    /**
     * heti lottószám húzás eredmények létrehozása teszteléshez
     */
    private void generateResult() {
        this.result = new ArrayList<WeeklyDraw>();

        WeeklyDraw firstWeeklyDraw = new WeeklyDraw();
        WeeklyDraw secondWeeklyDraw = new WeeklyDraw();

        Calendar calendarFirstDraw = Calendar.getInstance();
        calendarFirstDraw.set(Calendar.YEAR, 2017);
        calendarFirstDraw.set(Calendar.MONTH, 9);
        calendarFirstDraw.set(Calendar.DAY_OF_MONTH, 23);

        Calendar calendarSecondDraw = Calendar.getInstance();
        calendarSecondDraw.set(Calendar.YEAR, 2017);
        calendarSecondDraw.set(Calendar.MONTH, 9);
        calendarSecondDraw.set(Calendar.DAY_OF_MONTH, 16);

        firstWeeklyDraw.setYear(2017);
        firstWeeklyDraw.setWeek(38);
        firstWeeklyDraw.setDrawDate(calendarFirstDraw.getTime());
        firstWeeklyDraw.setFiveMatch(0);
        firstWeeklyDraw.setFiveMatchPrize(0L);
        firstWeeklyDraw.setFourMatch(19);
        firstWeeklyDraw.setFourMatchPrize(2587980L);
        firstWeeklyDraw.setThreeMatch(2112);
        firstWeeklyDraw.setThreeMatchPrize(24835L);
        firstWeeklyDraw.setTwoMatch(60124);
        firstWeeklyDraw.setTwoMatchPrize(1910L);
        firstWeeklyDraw.setDrawnNumbers(new Integer[]{1,2,3,4,5});

        secondWeeklyDraw.setYear(2017);
        secondWeeklyDraw.setWeek(37);
        secondWeeklyDraw.setDrawDate(calendarSecondDraw.getTime());
        secondWeeklyDraw.setFiveMatch(0);
        secondWeeklyDraw.setFiveMatchPrize(0L);
        secondWeeklyDraw.setFourMatch(11);
        secondWeeklyDraw.setFourMatchPrize(4679010L);
        secondWeeklyDraw.setThreeMatch(2052);
        secondWeeklyDraw.setThreeMatchPrize(26755L);
        secondWeeklyDraw.setTwoMatch(63976);
        secondWeeklyDraw.setTwoMatchPrize(1875L);
        secondWeeklyDraw.setDrawnNumbers(new Integer[]{6,7,8,9,10});

        this.result.add(firstWeeklyDraw);
        this.result.add(secondWeeklyDraw);
    }

    @Before
    public void setup() {
        this.lotteryFileReader = new LotteryFileReaderImpl();
        this.lotteryList = TestLotteryFileReader.LOTTERY.getLotteryList();
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileNotFound() {
            this.lotteryFileReader.readFromFile(TestLotteryFileReader.FILE_PATH);
    }

    @Test
    public void testLotteryListSize() {
        this.lotteryFileReader.readFromFile(TestLotteryFileReader.FILE_PATH);
        assertEquals(2,this.lotteryList.size());
    }

    @Test
    public void testLotteryWeeklyDrawnNumbers() {
        this.lotteryFileReader.readFromFile(TestLotteryFileReader.FILE_PATH);
        this.generateResult();
        assertEquals(this.result,this.lotteryList);
    }


}
