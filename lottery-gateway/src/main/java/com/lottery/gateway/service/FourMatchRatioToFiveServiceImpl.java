package com.lottery.gateway.service;

import com.lottery.model.FourMatchRatioToFiveMatchResult;
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
 * {@link FourMatchRatioToFiveService} interfész implementációja
 */
@Service
public class FourMatchRatioToFiveServiceImpl implements FourMatchRatioToFiveService {

    private static Logger LOGGER = LoggerFactory.getLogger(FourMatchRatioToFiveServiceImpl.class);

    @Override
    public FourMatchRatioToFiveMatchResult apiCall(String url, String accessToken) {
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("Four match ratio to five Gateway hivas indul");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<FourMatchRatioToFiveMatchResult> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                                                                                         new ParameterizedTypeReference<FourMatchRatioToFiveMatchResult>() {
                                                                                         });
        FourMatchRatioToFiveMatchResult result = response.getBody();

        FourMatchRatioToFiveServiceImpl.LOGGER.debug("Four match ratio to five Gateway hivas vege");
        return result;
    }
}
