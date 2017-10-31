package com.lottery.service;

import com.lottery.model.MostFrequentlyOccurringPairsResult;
import com.lottery.model.mfop_utils.DrawsInTwoDimension;

/**
 * interfész a leggyakrabban előforduló számpárok kiszámítására írt service-hez
 */
public interface MostFrequentlyOccuringPairsService {

    /**
     * Leggyakrabban előforduló számpárok kiszámításához írt rule futtatása
     *
     * @return result objektum resultArray-field-je
     */

    DrawsInTwoDimension executeRule();

    /**
     * Dátum alapján filterezett leggyakrabban előforduló számpárok kiszámításához írt rule futtatása
     *
     * @return result objektum resultArray-field-je
     */
    DrawsInTwoDimension executeRuleFilterByDate(String from, String to);

}
