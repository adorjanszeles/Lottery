package com.lottery.service;

import com.lottery.model.MostFrequentFiveNumberResult;

/**
 * interfész a legygakoribb öt szám service-hez
 */

public interface MostFrequentFiveNumberService {

    /**
     * rule futtatása
     *
     * @return result objektum
     */

    MostFrequentFiveNumberResult executeRule();
}
