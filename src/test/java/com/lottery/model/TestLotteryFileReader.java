package com.lottery.model;

import com.lottery.service.LotteryFileReader;
import com.lottery.service.LotteryFileReaderImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
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

    private static final String FILE_PATH = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "LotteryWeeklyDraws.csv";
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

        SimpleDateFormat drawOneDate = new SimpleDateFormat("2017-09-23");
        SimpleDateFormat drawTwoDate = new SimpleDateFormat("2017-09-16");

        firstWeeklyDraw.setYear(2017);
        firstWeeklyDraw.setWeek(38);
        firstWeeklyDraw.setDrawDate(drawOneDate);
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
        secondWeeklyDraw.setDrawDate(drawTwoDate);
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
    public void setup() throws FileNotFoundException {
        Lottery lottery = new Lottery();
        lottery.setLotteryList();
        this.lotteryFileReader = new LotteryFileReaderImpl(lottery);
        this.lotteryList = lottery.getLotteryList();
        //this.lotteryList.clear();
        this.lotteryFileReader.readFromFile(TestLotteryFileReader.FILE_PATH);
        this.lotteryList = lottery.getLotteryList();
        this.generateResult();
    }

//    @Test(expected = FileNotFoundException.class)
//    public void testFileNotFound() throws FileNotFoundException {
//        this.lotteryFileReader.readFromFile("/bad_path");
//    }

    @Test
    public void testLotteryListSize() throws FileNotFoundException {
        assertEquals(2, this.lotteryList.size());
    }

    @Test
    public void testLotteryWeeklyDrawnNumbers() throws FileNotFoundException {
        assertTrue(this.compareWeeklyDraws(this.result.get(0), this.lotteryList.get(0)) &&
                this.compareWeeklyDraws(this.result.get(1), this.lotteryList.get(1)));
    }


}
