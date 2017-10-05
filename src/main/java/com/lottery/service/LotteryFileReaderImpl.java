package com.lottery.service;

import com.lottery.common.enums.InputColumn;
import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * {@link LotteryFileReader} interfész implementációja.
 */

public class LotteryFileReaderImpl implements LotteryFileReader {

    private List<WeeklyDraw> weeklyDrawList;
    private static final int COL_NUM = 16;
    private static String CURRENCY = "Ft";
    private static String BREAK_LINE = "\n";
    private static String LINE_DELIMITER;
    private static String LINE_SEPARATOR = ";";
    private static String DATE_SEPARATOR = "\\.";
    private static final int DATE_YEAR_IDX = 0;
    private static final int DATE_MONTH_IDX = 1;
    private static final int DATE_DAY_IDX = 2;

    public LotteryFileReaderImpl() {
        String osType = System.getProperty("os.name");
        String osTypeLower = osType.toLowerCase();
        if (osTypeLower.startsWith("mac") || osTypeLower.startsWith("linux") || osTypeLower.startsWith("unix")) {
            LotteryFileReaderImpl.LINE_DELIMITER = "\n";
        }
        else if (osTypeLower.startsWith("windows")) {
            LotteryFileReaderImpl.LINE_DELIMITER = "\r\n";
        }
        else {
            LotteryFileReaderImpl.LINE_DELIMITER = "\r";
        }
    }

    /**
     * az input fileból beolvasott sorokból kivesszük a Ft toldalékot, szóközöket és az új sor karaktert
     *
     * @param line string tömb, a beolvasott input file egy sora
     * @return a felesleges karakterektől megtisztított sor
     */
    private String[] cleanLine(String[] line) {
        for (int i = 0; i < line.length; i++) {
            if (line[i].contains(" ")) {
                line[i] = line[i].replace(" ", "");
            }
            if (line[i].contains(LotteryFileReaderImpl.CURRENCY)) {
                line[i] = line[i].replace(LotteryFileReaderImpl.CURRENCY, "");
            }
            if (line[i].contains(LotteryFileReaderImpl.BREAK_LINE)) {
                line[i] = line[i].replace(LotteryFileReaderImpl.BREAK_LINE, "");
            }
        }
        return line;
    }

    /**
     * a datum parsolasa Date objektummá Calendar segítségével
     *
     * @param date az input fileból érkező string dátum
     * @return date objektum
     */
    private SimpleDateFormat generateDateFromString(String date) {
        if (date.equals("")) {
            return null;
        }
        String[] dateElement = date.split(LotteryFileReaderImpl.DATE_SEPARATOR);
        String year = dateElement[LotteryFileReaderImpl.DATE_YEAR_IDX];
        String month = dateElement[LotteryFileReaderImpl.DATE_MONTH_IDX];
        String day = dateElement[LotteryFileReaderImpl.DATE_DAY_IDX];
        SimpleDateFormat drawDate = new SimpleDateFormat(year + "-" + month + "-" + day);
        return drawDate;
    }

    /**
     * az input fileból beolvasott string elemek parsolása Integerré
     *
     * @param element string adat az input file-ból
     * @return Integer
     */
    private Integer parseStringToInt(String element) {
        if (element.equals("")) {
            return null;
        }
        return Integer.parseInt(element);
    }

    /**
     * az input fileból beolvasott string elemek parsolása Longgá
     *
     * @param element string adat az input file-ból
     * @return Long
     */
    private Long parseStringToLong(String element) {
        if (element.equals("")) {
            return null;
        }
        return Long.parseLong(element);
    }

    /**
     * az input file-ban '98 előtti találatokról nincs adat, a csv-ben 0-val jelölik ezt.
     * ez a metódus ellenőrzi, hogy az adott fieldek mindegyike 0 értékkel rendelkezik-e és ha igen, akkor 0 helyett üres stringgel jelöljük a továbbiakban, ha nincs adatunk
     *
     * @param cleanedLine sor az input fileból
     * @return String tömb, amiben a megtisztított adatok vannak, a hiányzó húzások pedig null-ra állítva
     */
    private String[] checkForZeros(String[] cleanedLine) {
        int from = InputColumn.FIVE_MATCH.getColNum();
        int to = InputColumn.TWO_MATCH_PRIZE.getColNum();
        int counter = 0;
        for (int i = from; i <= to; i++) {
            if (cleanedLine[i].equals("0")) {
                counter++;
            }
        }
        if (counter == 8) {
            for (int i = from; i <= to; i++) {
                cleanedLine[i] = "";
            }
        }
        return cleanedLine;
    }

    /**
     * az input fileból beolvasott és megtisztított adatok WeeklyDraw objektummá konvertálása
     *
     * @param cleanedLine input fileból beolvasott és megtisztított sorok
     * @return WeeklyDraw objektum
     */
    private WeeklyDraw createWeeklyDraws(String[] cleanedLine) {
        WeeklyDraw weeklyDraw = new WeeklyDraw();
        weeklyDraw.setYear(parseStringToInt(cleanedLine[InputColumn.YEAR.getColNum()]));
        weeklyDraw.setWeek(parseStringToInt(cleanedLine[InputColumn.WEEK.getColNum()]));
        weeklyDraw.setDrawDate(generateDateFromString(cleanedLine[InputColumn.DATE.getColNum()]));
        weeklyDraw.setFiveMatch(parseStringToInt(cleanedLine[InputColumn.FIVE_MATCH.getColNum()]));
        weeklyDraw.setFiveMatchPrize(parseStringToLong(cleanedLine[InputColumn.FIVE_MATCH_PRIZE.getColNum()]));
        weeklyDraw.setFourMatch(parseStringToInt(cleanedLine[InputColumn.FOUR_MATCH.getColNum()]));
        weeklyDraw.setFourMatchPrize(parseStringToLong(cleanedLine[InputColumn.FOUR_MATCH_PRIZE.getColNum()]));
        weeklyDraw.setThreeMatch(parseStringToInt(cleanedLine[InputColumn.THREE_MATCH.getColNum()]));
        weeklyDraw.setThreeMatchPrize(parseStringToLong(cleanedLine[InputColumn.THREE_MATCH_PRIZE.getColNum()]));
        weeklyDraw.setTwoMatch(parseStringToInt(cleanedLine[InputColumn.TWO_MATCH.getColNum()]));
        weeklyDraw.setTwoMatchPrize(parseStringToLong(cleanedLine[InputColumn.TWO_MATCH_PRIZE.getColNum()]));
        weeklyDraw.setDrawnNumbers(new Integer[]{Integer.parseInt(cleanedLine[InputColumn.FIRST_DRAW_NUM.getColNum()]),
                Integer.parseInt(cleanedLine[InputColumn.SECOND_DRAW_NUM.getColNum()]),
                Integer.parseInt(cleanedLine[InputColumn.THIRD_DRAW_NUM.getColNum()]),
                Integer.parseInt(cleanedLine[InputColumn.FOURTH_DRAW_NUM.getColNum()]),
                Integer.parseInt(cleanedLine[InputColumn.FIFTH_DRAW_NUM.getColNum()])});


        return weeklyDraw;
    }

    /**
     * az adatok input fileból való beolvasása
     *
     * @param filePath az input file elérési útja
     */
    @Override
    public void readFromFile(String filePath) throws FileNotFoundException {
        this.weeklyDrawList = Lottery.getInstance().getLotteryList();
        try(Scanner lotteryFile = new Scanner(new File(filePath))){
            lotteryFile.useDelimiter(LotteryFileReaderImpl.LINE_DELIMITER);
            while (lotteryFile.hasNext()) {
                String nextLine = lotteryFile.next();
                String[] splitLines = nextLine.split(LotteryFileReaderImpl.LINE_SEPARATOR);
                if (splitLines.length == LotteryFileReaderImpl.COL_NUM) {
                    String[] lines = this.cleanLine(splitLines);
                    String[] drawLines = this.checkForZeros(lines);
                    this.weeklyDrawList.add(this.createWeeklyDraws(drawLines));
                }
            }
        }
    }
}
