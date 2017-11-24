package com.lottery.gateway.service;

import java.util.Date;

/**
 * minimum dátum lekéréséhez tartozó interfész
 */
public interface MinDateService {

    /**
     * @param url resource szerver elérési útvonala
     * @param accessToken JWT access token
     * @return String dátum objektum
     */
    String getMinDate(String url, String accessToken);
}
