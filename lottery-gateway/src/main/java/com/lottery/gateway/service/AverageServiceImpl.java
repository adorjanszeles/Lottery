package com.lottery.gateway.service;

import com.lottery.model.AverageResult;
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
 * {@link AverageService} interfész implementációja
 */

@Service
public class AverageServiceImpl implements AverageService {

    private static Logger LOGGER = LoggerFactory.getLogger(AverageServiceImpl.class);

    @Override
    public AverageResult apiCall(String url, String accessToken) {
        AverageServiceImpl.LOGGER.debug("average Gateway hivas indul");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<AverageResult> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                                                                       new ParameterizedTypeReference<AverageResult>() {
                                                                       });
        AverageResult result = response.getBody();

        AverageServiceImpl.LOGGER.debug("average Gateway hivas vege");
        return result;
    }
}
