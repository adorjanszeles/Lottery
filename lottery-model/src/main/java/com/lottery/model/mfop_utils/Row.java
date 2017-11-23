package com.lottery.model.mfop_utils;

/**
 * A lottóhúzásokban előforduló 2D-s struktúrában való tárolásához kell. Az egyes számokhoz tartozó sorokat
 * (oszlopokkal/mezőkkel)írja le.
 */
public class Row {

    public Row(){}

    private Integer[] columns;

    public Row(Integer[] columns) {
        this.columns = columns;
    }

    public Integer[] getColumns() {
        return columns;
    }

    public void setColumns(Integer[] columns) {
        this.columns = columns;
    }
}
