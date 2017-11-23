package com.lottery.gateway.service;

import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;

/**
 * GATEWAY interfész 2 ötös húzás között eltelt időt számító resource szerver végpont hívásához
 */
public interface AverageTimeBetweenTwoMatchFiveDrawsService {

    /**
     * @param url         Lottery-web resource szerver elérési útvonala
     * @param accessToken jwt access token
     * @return AverageTimeBetweenTwoMatchFiveDrawsResult objektum
     */
    AverageTimeBetweenTwoMatchFiveDrawsResult getAverageTimeBetweenTwoMatchFiveDraws(String url, String accessToken);

}
