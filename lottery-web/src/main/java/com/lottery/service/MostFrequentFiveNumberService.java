package com.lottery.service;

import com.lottery.model.MostFrequentFiveNumberResult;

import java.text.ParseException;

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

    /**
     * Leggyakrabban előforduló számok dátum alapján filterezett kiszámításához írt rule futtatása
     *
     * @param from dátum intervallum kezdete
     * @param to   dátum intervallum vége
     * @return result objektum
     * @throws ParseException parszolási kivétel
     */
    MostFrequentFiveNumberResult executeRuleFilterByDate(String from, String to) throws ParseException;
}
