package com.lottery.common.enums;

/**
 * Enum konstans oszlop nevek az input adatok beolvasasahoz
 */

public enum InputColumn {

    YEAR(0), WEEK(1), DATE(2), FIVE_MATCH(3), FIVE_MATCH_PRIZE(4), FOUR_MATCH(5), FOUR_MATCH_PRIZE(6), THREE_MATCH(
            7), THREE_MATCH_PRIZE(8), TWO_MATCH(9), TWO_MATCH_PRIZE(10), FIRST_DRAW_NUM(11), SECOND_DRAW_NUM(
            12), THIRD_DRAW_NUM(13), FOURTH_DRAW_NUM(14), FIFTH_DRAW_NUM(15);

    private final int colNum;

    InputColumn(int colNum) {
        this.colNum = colNum;
    }

    public int getColNum() {
        return this.colNum;
    }

}
