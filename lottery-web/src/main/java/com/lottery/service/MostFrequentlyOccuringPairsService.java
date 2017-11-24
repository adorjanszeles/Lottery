package com.lottery.service;

import com.lottery.model.mfop_utils.DrawsInTwoDimension;

import java.text.ParseException;

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
     * @param from dátum intervallum kezdete
     * @param to   dátum intervallum vége
     * @return result objektum resultArray-field-je
     * @throws ParseException parszolási kivétel
     */
    DrawsInTwoDimension executeRuleFilterByDate(String from, String to) throws ParseException;

}
