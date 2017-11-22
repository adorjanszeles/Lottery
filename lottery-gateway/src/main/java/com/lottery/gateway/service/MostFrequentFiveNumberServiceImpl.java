package com.lottery.gateway.service;

import com.lottery.model.MostFrequentFiveNumberResult;
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
 * {@link MostFrequentFiveNumberService} interfész implementációja
 */
@Service
public class MostFrequentFiveNumberServiceImpl implements MostFrequentFiveNumberService {

    private static Logger LOGGER = LoggerFactory.getLogger(MostFrequentFiveNumberServiceImpl.class);

    @Override
    public MostFrequentFiveNumberResult apiCall(String url, String accessToken) {
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Most frequent five number Gateway hivas indul");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<MostFrequentFiveNumberResult> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                                                                                      new ParameterizedTypeReference<MostFrequentFiveNumberResult>() {
                                                                                      });
        MostFrequentFiveNumberResult result = response.getBody();

        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Most frequent five number Gateway hivas vege");
        return result;
    }
}
