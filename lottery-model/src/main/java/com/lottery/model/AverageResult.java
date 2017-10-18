package com.lottery.model;

/**
 * Result segédosztály a Drools működésének könnyítéséhez, amely a kihúzott számok átlagát tartalmazza
 */
public class AverageResult {

    private Float result;

    public AverageResult() {
        this.result = null;
    }

    public Float getResult() {
        return this.result;
    }

    public void setResult(Float result) {
        this.result = result;
    }
}
