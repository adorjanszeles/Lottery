package com.lottery.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate Abstract Service abstract osztály GET típusú http kérésekhez
 */
public abstract class AbstractGetService<R> {
    private static Logger LOGGER = LoggerFactory.getLogger(AbstractGetService.class);

    /**
     * GET HTTP request a Lottery resource szerver felé
     *
     * @param url         Resource szerver elérési útvonala
     * @param accessToken jwt access token
     * @param resultClass az elvárt visszatérési érték osztálya
     * @return Eredmény
     */
    protected R apiCall(String url, String accessToken, Class<R> resultClass) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<R> response = restTemplate.exchange(url, HttpMethod.GET, entity, resultClass);
        R result = response.getBody();
        return result;
    }

}
