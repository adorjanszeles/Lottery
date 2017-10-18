package com.lottery.service;

import com.lottery.model.MostFrequentlyOccurringPairsResult;

/**
 * interfész a leggyakrabban előforduló számpárok kiszámítására írt service-hez
 */
public interface MostFrequentlyOccuringPairsService {

    /**
     * Leggyakrabban előforduló számpárok kiszámításához írt rule futtatása
     *
     * @return result objektum
     */

    MostFrequentlyOccurringPairsResult executeRule();

    /**
     * Dátum alapján filterezett leggyakrabban előforduló számpárok kiszámításához írt rule futtatása
     *
     * @return result objektum
     */
    MostFrequentlyOccurringPairsResult executeRuleFilterByDate(String from, String to);

}
