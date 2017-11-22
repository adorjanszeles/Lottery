package com.lottery.gateway.service;

import com.lottery.model.RearestFiveResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RarestFiveServiceImpl implements RarestFiveService {

    private static Logger LOGGER = LoggerFactory.getLogger(RarestFiveServiceImpl.class);

    @Override
    public RearestFiveResult apiCall(String url, String accessToken) {
        RarestFiveServiceImpl.LOGGER.debug("Rarest five number Gateway hivas indul");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<RearestFiveResult> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                                                                           new ParameterizedTypeReference<RearestFiveResult>() {
                                                                           });
        RearestFiveResult result = response.getBody();

        RarestFiveServiceImpl.LOGGER.debug("Rarest five number Gateway hivas vege");
        return result;
    }
}
