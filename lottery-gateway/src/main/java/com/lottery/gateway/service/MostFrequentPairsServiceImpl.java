package com.lottery.gateway.service;

import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.model.mfop_utils.DrawsInTwoDimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * {@link MostFrequentPairsService} interfész implementációja
 * {@link AbstractGetService} abstract class kiterjesztése
 */
@Service
public class MostFrequentPairsServiceImpl extends AbstractGetService<DrawsInTwoDimension>
        implements MostFrequentPairsService {

    private static Logger LOGGER = LoggerFactory.getLogger(MostFrequentFiveNumberServiceImpl.class);

    @Override
    public DrawsInTwoDimension getMostFrequentPairs(String url, String accessToken) {
        MostFrequentPairsServiceImpl.LOGGER.debug("Most frequent pairs Gateway hivas indul");

        DrawsInTwoDimension result = this.apiCall(url, accessToken, DrawsInTwoDimension.class);

        MostFrequentPairsServiceImpl.LOGGER.debug("Most frequent pairs Gateway hivas vege");
        return result;
    }
}
