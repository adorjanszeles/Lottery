package com.lottery.service;

import com.lottery.model.MostFrequentFiveNumberResult;

/**
 * interfész a leggyakoribb öt szám service-hez
 */

public interface MostFrequentFiveNumberService {

    /**
     * Leggyakrabban előforduló számok kiszámításához írt rule futtatása
     *
     * @return result objektum
     */

    MostFrequentFiveNumberResult executeRule();
}
