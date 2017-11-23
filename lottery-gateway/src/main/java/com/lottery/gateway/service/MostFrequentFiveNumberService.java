package com.lottery.gateway.service;

import com.lottery.model.MostFrequentFiveNumberResult;

/**
 * GATEWAY interfész leggyakoribb 5 számot számító resource szerver végpont hívásához
 */
public interface MostFrequentFiveNumberService {

    /**
     * @param url          Lottery-web resource szerver elérési útvonala
     * @param access_token jwt access token
     * @return MostFrequentFiveNumberResult objektum
     */
    MostFrequentFiveNumberResult getMostFrequentFiveNumbers(String url, String access_token);

}
