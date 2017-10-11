package com.lottery.service;

import com.lottery.model.MostFrequentlyOccurringPairsResult;

public interface MostFrequentlyOccuringPairsService {

    /**
     * rule futtatása
     *
     * @return result objektum
     */

    MostFrequentlyOccurringPairsResult executeRule();

}
