package com.lottery.gateway.service;

import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;
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
 * {@link AverageTimeBetweenTwoMatchFiveDrawsService} interfész implementációja
 */
@Service
public class AverageTimeBetweenTwoMatchFiveDrawsServiceImpl implements AverageTimeBetweenTwoMatchFiveDrawsService {

    private static Logger LOGGER = LoggerFactory.getLogger(AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.class);

    @Override
    public AverageTimeBetweenTwoMatchFiveDrawsResult apiCall(String url, String accessToken) {
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "average time between two match five draws Gateway hivas indul");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<AverageTimeBetweenTwoMatchFiveDrawsResult> response = restTemplate.exchange(url, HttpMethod.GET,
                                                                                                   entity,
                                                                                                   new ParameterizedTypeReference<AverageTimeBetweenTwoMatchFiveDrawsResult>() {
                                                                                                   });
        AverageTimeBetweenTwoMatchFiveDrawsResult result = response.getBody();

        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "average time between two match five draws Gateway hivas vege");
        return result;
    }
}
