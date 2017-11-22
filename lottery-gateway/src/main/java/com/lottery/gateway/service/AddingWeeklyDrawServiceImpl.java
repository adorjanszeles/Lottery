package com.lottery.gateway.service;

import com.lottery.model.WeeklyDrawDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * {@link AddingWeeklyDrawService} interfész implementációja
 */
@Service
public class AddingWeeklyDrawServiceImpl implements AddingWeeklyDrawService {

    private static Logger LOGGER = LoggerFactory.getLogger(AddingWeeklyDrawServiceImpl.class);

    @Override
    public WeeklyDrawDTO apiCall(String url, String accessToken, WeeklyDrawDTO weeklyDrawDTO) {
        AddingWeeklyDrawServiceImpl.LOGGER.debug("Adding new weekly draw Gateway hivas indul");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);
        HttpEntity<WeeklyDrawDTO> entity = new HttpEntity<>(weeklyDrawDTO, headers);

        ResponseEntity<WeeklyDrawDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                                                                       new ParameterizedTypeReference<WeeklyDrawDTO>() {
                                                                       });
        WeeklyDrawDTO result = response.getBody();

        AddingWeeklyDrawServiceImpl.LOGGER.debug("Adding new weekly draw Gateway hivas vege");

        return result;
    }
}
