package com.lottery.gateway.service;

/**
 * GATEWAY interfész elérhető dátum intervallumot számító resource szerver végpont hívásához
 */
public interface DateIntervalsService {

    /**
     * @param url Lottery-web resource szerver elérési útvonala
     * @param accessToken jwt access token
     * @return String karakterek sorozata
     */
    String apiCall(String url, String accessToken);

}
