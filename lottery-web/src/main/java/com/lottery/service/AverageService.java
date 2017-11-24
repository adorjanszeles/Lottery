package com.lottery.service;

import com.lottery.model.AverageResult;

import java.text.ParseException;

/**
 * interfész átlagot számító szabály futtatásához
 */
public interface AverageService {

    /**
     * Átlagot számító rule futtatása
     *
     * @return result objektum
     */

    AverageResult executeRule();

    /**
     * kihúzott számok átlagának dátum alapján filterezett kiszámításához írt rule futtatása
     * @param from dátum intervallum kezdete
     * @param to dátum intervallum vége
     * @throws ParseException parszolási kivétel
     * @return result objektum
     */
    AverageResult executeRuleFilterByDate(String from, String to) throws ParseException;
}
