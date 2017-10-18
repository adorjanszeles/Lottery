package com.lottery.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Result segédosztály a Drools működésének könnyítéséhez, amely a legritkább 5 számból álló listát tartalmazza
 */
public class RearestFiveResult {

    private List<Integer> result;

    public RearestFiveResult() {
        this.result = new ArrayList<Integer>();
    }

    public List<Integer> getResult() {
        return this.result;
    }

    public void setResult(List<Integer> result) {
        this.result = result;
    }
}
