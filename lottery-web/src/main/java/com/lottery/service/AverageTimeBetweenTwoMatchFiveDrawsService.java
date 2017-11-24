package com.lottery.service;

import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;

import java.text.ParseException;

/**
 * interfész ötös találatok közti átlagos idő kiszámítására írt service-hez
 */
public interface AverageTimeBetweenTwoMatchFiveDrawsService {

    /**
     * Két ötös találat közti átlagos idő kiszámítására írt rule futtatása
     *
     * @return result objektum
     */

    AverageTimeBetweenTwoMatchFiveDrawsResult executeRule();

    /**
     * Két ötös találat közti átlagos idő filterezett kiszámításához írt rule futtatása
     * @param from dátum intervallum kezdete
     * @param to dátum intervallum vége
     * @throws ParseException parszolási kivétel
     * @return result objektum
     */
    AverageTimeBetweenTwoMatchFiveDrawsResult executeRuleFilterByDate(String from, String to) throws ParseException;
}
