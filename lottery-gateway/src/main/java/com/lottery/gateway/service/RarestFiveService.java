package com.lottery.gateway.service;

import com.lottery.model.RearestFiveResult;

/**
 * GATEWAY interfész a legritkább 5 szám számítását végző resource szerver végpont hívásához
 */
public interface RarestFiveService {

    /**
     * @param url         Lottery-web resource szerver elérési útvonala
     * @param accessToken jwt access token
     * @return RearestFiveResult objektum
     */
    RearestFiveResult apiCall(String url, String accessToken);

}
