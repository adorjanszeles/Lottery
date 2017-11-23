package com.lottery.gateway.service;

import com.lottery.model.AverageResult;

/**
 * GATEWAY interfész átlagot számító resource szerver végpont hívásához
 */
public interface AverageService {

    /**
     * @param url         Lottery-web resource szerver elérési útvonala
     * @param accessToken jwt access token
     * @return AverageResult objektum
     */
    AverageResult getAverageResult(String url, String accessToken);
}
