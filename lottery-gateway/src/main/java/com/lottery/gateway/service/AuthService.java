package com.lottery.gateway.service;

import org.springframework.util.MultiValueMap;

/**
 * GATEWAY interfész auth szerver felé történő access token kéréshez
 */
public interface AuthService {

    /**
     * @param url         Auth szerver elérési útvonala
     * @param accessToken jwt access token
     * @param object      request objektum a resource szerver felől
     * @return Json objektum
     */
    Object apiCall(String url, String accessToken, MultiValueMap object);

}
