package com.lottery.gateway.service;

import com.lottery.model.RearestFiveResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * {@link RarestFiveService} interfész implementációja {@link AbstractGetService} abstract class kiterjesztése
 */
@Service
public class RarestFiveServiceImpl extends AbstractGetService<RearestFiveResult> implements RarestFiveService {

    private static Logger LOGGER = LoggerFactory.getLogger(RarestFiveServiceImpl.class);

    @Override
    public RearestFiveResult getRearestFiveNumber(String url, String accessToken) {
        RarestFiveServiceImpl.LOGGER.debug("Rarest five number Gateway hivas indul");

        RearestFiveResult result = this.apiCall(url, accessToken, RearestFiveResult.class);

        RarestFiveServiceImpl.LOGGER.debug("Rarest five number Gateway hivas vege");
        return result;
    }
}
