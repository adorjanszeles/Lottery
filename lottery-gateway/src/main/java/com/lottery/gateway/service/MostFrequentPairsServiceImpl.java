package com.lottery.gateway.service;

import com.lottery.model.mfop_utils.DrawsInTwoDimension;
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
 * {@link MostFrequentPairsService} interfész implementációja
 */
@Service
public class MostFrequentPairsServiceImpl implements MostFrequentPairsService {

    private static Logger LOGGER = LoggerFactory.getLogger(MostFrequentFiveNumberServiceImpl.class);

    @Override
    public DrawsInTwoDimension apiCall(String url, String accessToken) {
        MostFrequentPairsServiceImpl.LOGGER.debug("Most frequent pairs Gateway hivas indul");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<DrawsInTwoDimension> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                                                                             new ParameterizedTypeReference<DrawsInTwoDimension>() {
                                                                             });
        DrawsInTwoDimension result = response.getBody();

        MostFrequentPairsServiceImpl.LOGGER.debug("Most frequent pairs Gateway hivas vege");
        return result;
    }
}
