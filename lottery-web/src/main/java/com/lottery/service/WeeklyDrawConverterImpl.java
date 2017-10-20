package com.lottery.service;

import com.lottery.common.enums.InputColumn;
import com.lottery.model.RawWeeklyDraw;
import com.lottery.model.WeeklyDraw;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A class feladata a CSV-ből feltöltött RawWeeklyDraw instanco-ok WeeklyDraw instance-okká konvertálása.
 */
public class WeeklyDrawConverterImpl implements WeeklyDrawConverter {

    private static final int COL_NUM = 16;
    private static String CURRENCY = "Ft";
    private static String BREAK_LINE = "\n";
    private static String LINE_DELIMITER;
    private static String LINE_SEPARATOR = ";";
    private static String DATE_SEPARATOR = "\\.";
    private static final int DATE_YEAR_IDX = 0;
    private static final int DATE_MONTH_IDX = 1;
    private static final int DATE_DAY_IDX = 2;

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
            if (line[i].contains(WeeklyDrawConverterImpl.CURRENCY)) {
                line[i] = line[i].replace(WeeklyDrawConverterImpl.CURRENCY, "");
            }
            if (line[i].contains(WeeklyDrawConverterImpl.BREAK_LINE)) {
                line[i] = line[i].replace(WeeklyDrawConverterImpl.BREAK_LINE, "");
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
        String[] dateElement = date.split(WeeklyDrawConverterImpl.DATE_SEPARATOR);
        String year = dateElement[WeeklyDrawConverterImpl.DATE_YEAR_IDX];
        String month = dateElement[WeeklyDrawConverterImpl.DATE_MONTH_IDX];
        String day = dateElement[WeeklyDrawConverterImpl.DATE_DAY_IDX];
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
     * RawWeeklyDraw instance-ok átalakítása WeeklyDraw instanc-okká.
     *
     * @param rawWeeklyDraws RawWeeklyDraw lista
     */
    public List<WeeklyDraw> convertRawsToWeeklyDraws(List<RawWeeklyDraw> rawWeeklyDraws) throws FileNotFoundException {

        List<WeeklyDraw> weeklyDrawList = new ArrayList<>();

        for(RawWeeklyDraw rawDraw : rawWeeklyDraws){

            WeeklyDraw draw = new WeeklyDraw();

            String[] rawLine = new String[11];

            rawLine[0] = rawDraw.getYear();
            rawLine[1] = rawDraw.getWeek();
            rawLine[2] = rawDraw.getDrawDate();
            rawLine[3] = rawDraw.getFiveMatch();
            rawLine[4] = rawDraw.getFiveMatchPrize();
            rawLine[5] = rawDraw.getFourMatch();
            rawLine[6] = rawDraw.getFourMatchPrize();
            rawLine[7] = rawDraw.getThreeMatch();
            rawLine[8] = rawDraw.getThreeMatchPrize();
            rawLine[9] = rawDraw.getTwoMatch();
            rawLine[10] = rawDraw.getTwoMatchPrize();

            cleanLine(rawLine);
            checkForZeros(rawLine);

            Integer[] rawDrawNumbers = new Integer[5];
            rawDrawNumbers[0] = parseStringToInt(rawDraw.getFirst());
            rawDrawNumbers[1] = parseStringToInt(rawDraw.getSecond());
            rawDrawNumbers[2] = parseStringToInt(rawDraw.getThird());
            rawDrawNumbers[3] = parseStringToInt(rawDraw.getSecond());
            rawDrawNumbers[4] = parseStringToInt(rawDraw.getFirst());


            draw.setYear(parseStringToInt(rawLine[0]));
            draw.setWeek(parseStringToInt(rawLine[1]));
            draw.setDrawDate(generateDateFromString(rawLine[2]));
            draw.setFiveMatch(parseStringToInt(rawLine[3]));
            draw.setFiveMatchPrize(parseStringToLong(rawLine[4]));
            draw.setFourMatch(parseStringToInt(rawLine[5]));
            draw.setFourMatchPrize(parseStringToLong(rawLine[6]));
            draw.setThreeMatch(parseStringToInt(rawLine[7]));
            draw.setThreeMatchPrize(parseStringToLong(rawLine[8]));
            draw.setTwoMatch(parseStringToInt(rawLine[9]));
            draw.setTwoMatchPrize(parseStringToLong(rawLine[10]));
            draw.setDrawnNumbers(rawDrawNumbers);

            weeklyDrawList.add(draw);
        }

        return weeklyDrawList;
    }

}
