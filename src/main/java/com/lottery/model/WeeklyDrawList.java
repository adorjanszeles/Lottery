package com.lottery.model;

import java.util.List;

/**
 * Wrapper osztály a Drools egy speciális gyengeségének kezelésére,
 * mivel nem kezeli rendesen az insert-elt collection-öket. Ellenben ha egy wrapper
 * osztály field-jén kell iterálnia, úgy tökéltesen működik.
 */
public class WeeklyDrawList {

    private List<WeeklyDraw> weeklyDrawList;

    public List<WeeklyDraw> getDrawListPreparedForDrools() {
        return this.weeklyDrawList;
    }
    public void setDrawListPreparedForDrools(List<WeeklyDraw> weeklyDrawList) {
        this.weeklyDrawList = weeklyDrawList;
    }
}
