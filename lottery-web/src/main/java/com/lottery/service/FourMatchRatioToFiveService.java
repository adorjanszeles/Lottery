package com.lottery.service;

import com.lottery.model.FourMatchRatioToFiveMatchResult;

import java.text.ParseException;

/**
 * interfész a négyes találatok aránya az ötöshöz szabályhoz írt service-hez
 */
public interface FourMatchRatioToFiveService {

    /**
     * Négyes találatok aránya az ötöshöz rule futtatása
     *
     * @return result objektum
     */

    FourMatchRatioToFiveMatchResult executeRule();

    /**
     * Dátum alapján filterezett négyes találatok aránya az ötöshöz rule futtatása
     *
     * @return result objektum
     */
    FourMatchRatioToFiveMatchResult executeRuleFilterByDate(String from, String to) throws ParseException;
}
