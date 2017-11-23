package com.lottery.gateway.service;

import com.lottery.model.MostFrequentFiveNumberResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * {@link MostFrequentFiveNumberService} interfész implementációja
 * {@link AbstractGetService} abstract class kiterjesztése
 */
@Service
public class MostFrequentFiveNumberServiceImpl extends AbstractGetService<MostFrequentFiveNumberResult>
        implements MostFrequentFiveNumberService {

    private static Logger LOGGER = LoggerFactory.getLogger(MostFrequentFiveNumberServiceImpl.class);

    @Override
    public MostFrequentFiveNumberResult getMostFrequentFiveNumbers(String url, String accessToken) {
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Most frequent five number Gateway hivas indul");

        MostFrequentFiveNumberResult result = this.apiCall(url, accessToken, MostFrequentFiveNumberResult.class);

        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Most frequent five number Gateway hivas vege");
        return result;
    }
}
