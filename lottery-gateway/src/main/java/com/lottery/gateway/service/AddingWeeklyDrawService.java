package com.lottery.gateway.service;

import com.lottery.model.WeeklyDrawDTO;

/**
 * GATEWAY interfész új weeklyDraw objektumok létrehozásához
 */
public interface AddingWeeklyDrawService {

    /**
     * @param url           Lottery-web resource szerver elérési útvonala
     * @param accessToken   access token
     * @param weeklyDrawDTO weeklyDrawDTO objektum
     * @return WeeklyDrawDTO objektum
     */
    WeeklyDrawDTO apiCall(String url, String accessToken, WeeklyDrawDTO weeklyDrawDTO);
}