package com.lottery.model.mfop_utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A lottóhúzásokban előforduló 2D-s struktúrában való tárolását valósítja meg.
 * A 'rows' lista Row elemei a lehetséges húzások.
 */
public class DrawsInTwoDimension {

    private List<Row> rows;

    public DrawsInTwoDimension(){
        this.rows = new ArrayList<>();
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public void addRow(Row row){
        this.rows.add(row);
    }
}
