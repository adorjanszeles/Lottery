package com.lottery.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * {@link DateIntervalsService} interfész implementációja
 */
@Service
public class DateIntervalsServiceImpl implements DateIntervalsService {

    private static Logger LOGGER = LoggerFactory.getLogger(DateIntervalsServiceImpl.class);

    @Override
    public String apiCall(String url, String accessToken) {
        DateIntervalsServiceImpl.LOGGER.debug("Date intervals Gateway hivas indul");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                                                                new ParameterizedTypeReference<String>() {
                                                                });
        String result = response.getBody();

        DateIntervalsServiceImpl.LOGGER.debug("Date intervals Gateway hivas vege");
        return result;
    }
}
