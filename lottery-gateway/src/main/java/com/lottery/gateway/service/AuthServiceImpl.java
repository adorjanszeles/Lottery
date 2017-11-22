package com.lottery.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * {@link AuthService} interfész implementációja
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public Object apiCall(String url, String clientSecret, MultiValueMap requestBody) {
        AuthServiceImpl.LOGGER.debug("Auth access token Gateway hivas indul");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", clientSecret);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                                                                new ParameterizedTypeReference<Object>() {
                                                                });

        AuthServiceImpl.LOGGER.debug("Auth access token Gateway hivas vege");

        return response.getBody();
    }
}
