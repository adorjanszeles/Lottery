package com.lottery.gateway.service;

import com.lottery.model.FourMatchRatioToFiveMatchResult;

/**
 * GATEWAY interfész négyes találatok aránya az ötöshöz eredményt számító resource szerver végpont hívásához
 */
public interface FourMatchRatioToFiveService {

    /**
     * @param url Lottery-web resource szerver elérési útvonala
     * @param accessToken jwt access token
     * @return FourMatchRatioToFiveMatchResult objektum
     */
    FourMatchRatioToFiveMatchResult apiCall(String url, String accessToken);

}
