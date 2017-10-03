package com.lottery.model;

import com.lottery.service.LotteryFileReader;
import com.lottery.service.LotteryFileReaderImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Input adatok beolvasásához írt tesztek
 */

public class TestLotteryFileReader {

    private static final String FILE_PATH = "src\\test\\resources\\LotteryWeeklyDraws.csv";
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
        calendarFirstDraw.set(Calendar.MONTH, 8);
        calendarFirstDraw.set(Calendar.DAY_OF_MONTH, 23);

        Calendar calendarSecondDraw = Calendar.getInstance();
        calendarSecondDraw.set(Calendar.YEAR, 2017);
        calendarSecondDraw.set(Calendar.MONTH, 8);
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
        firstWeeklyDraw.setDrawnNumbers(new Integer[]{1, 2, 3, 4, 5});

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
        secondWeeklyDraw.setDrawnNumbers(new Integer[]{6, 7, 8, 9, 10});

        this.result.add(firstWeeklyDraw);
        this.result.add(secondWeeklyDraw);
    }

    private boolean compareWeeklyDraws(WeeklyDraw drawOne, WeeklyDraw drawTwo) {
        if (drawOne.getYear().equals(drawTwo.getYear()) &&
                (drawOne.getWeek()).equals(drawTwo.getWeek()) &&
                (drawOne.getDrawDate().equals(drawTwo.getDrawDate())) &&
                (drawOne.getFiveMatch().equals(drawTwo.getFiveMatch())) &&
                (drawOne.getFiveMatchPrize().equals(drawTwo.getFiveMatchPrize())) &&
                (drawOne.getFourMatch().equals(drawTwo.getFourMatch())) &&
                (drawOne.getFourMatchPrize().equals(drawTwo.getFourMatchPrize())) &&
                (drawOne.getThreeMatch().equals(drawTwo.getThreeMatch())) &&
                (drawOne.getThreeMatchPrize().equals(drawTwo.getThreeMatchPrize())) &&
                (drawOne.getTwoMatch().equals(drawTwo.getTwoMatch())) &&
                (drawOne.getTwoMatchPrize().equals(drawTwo.getTwoMatchPrize())) &&
                (Arrays.equals(drawOne.getDrawnNumbers(), drawTwo.getDrawnNumbers()))) {
            return true;
        }
        return false;
    }


    @Before
    public void setup() {
        this.lotteryFileReader = new LotteryFileReaderImpl();
        this.lotteryList = TestLotteryFileReader.LOTTERY.getLotteryList();
        this.lotteryList.clear();
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileNotFound() throws FileNotFoundException {
        this.lotteryFileReader.readFromFile("/bad_path");
    }

    @Test
    public void testLotteryListSize() throws FileNotFoundException {
        this.lotteryFileReader.readFromFile(TestLotteryFileReader.FILE_PATH);
        assertEquals(2, this.lotteryList.size());
    }

    @Test
    public void testLotteryWeeklyDrawnNumbers() throws FileNotFoundException {
        this.lotteryFileReader.readFromFile(TestLotteryFileReader.FILE_PATH);
        this.generateResult();
        System.out.println(this.lotteryList.get(0).getDrawDate());
        System.out.println(this.result.get(0).getDrawDate());
        assertTrue(compareWeeklyDraws(this.result.get(0), this.lotteryList.get(0)) &&
                compareWeeklyDraws(this.result.get(1), this.lotteryList.get(1)));
    }


}
