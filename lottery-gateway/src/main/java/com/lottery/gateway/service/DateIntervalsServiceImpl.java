package com.lottery.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * {@link DateIntervalsService} interfész implementációja {@link AbstractGetService} abstract class kiterjesztése
 */
@Service
public class DateIntervalsServiceImpl extends AbstractGetService<String> implements DateIntervalsService {

    private static Logger LOGGER = LoggerFactory.getLogger(DateIntervalsServiceImpl.class);

    @Override
    public String getDateIntervals(String url, String accessToken) {
        DateIntervalsServiceImpl.LOGGER.debug("Date intervals Gateway hivas indul");

        String result = this.apiCall(url, accessToken, String.class);

        DateIntervalsServiceImpl.LOGGER.debug("Date intervals Gateway hivas vege");
        return result;
    }
}
