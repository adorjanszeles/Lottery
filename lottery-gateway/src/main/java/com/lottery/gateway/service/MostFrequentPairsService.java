package com.lottery.gateway.service;

import com.lottery.model.mfop_utils.DrawsInTwoDimension;

/**
 * GATEWAY interfész leggyakoribb 5 számot számító resource szerver végpont hívásához
 */
public interface MostFrequentPairsService {

    /**
     * @param url         Lottery-web resource szerver elérési útvonala
     * @param accessToken jwt access token
     * @return 2 dimenziós tömb objektum
     */
    DrawsInTwoDimension getMostFrequentPairs(String url, String accessToken);

}
