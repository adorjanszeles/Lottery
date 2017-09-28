package com.lottery.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A leggyakoribb öt lottószám szabály eredményének wrapper osztálya
 */

public class MostFrequentFiveNumberResult {

    private List<Integer> result;

    public MostFrequentFiveNumberResult() {
        this.result = new ArrayList<Integer>();
    }

    public List<Integer> getResult() {
        return result;
    }
}
