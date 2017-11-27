package com.lottery.gateway.service;

import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * {@link AverageTimeBetweenTwoMatchFiveDrawsService} interfész implementációja {@link AbstractGetService} abstract
 * class kiterjesztése
 */
@Service
public class AverageTimeBetweenTwoMatchFiveDrawsServiceImpl
        extends AbstractGetService<AverageTimeBetweenTwoMatchFiveDrawsResult>
        implements AverageTimeBetweenTwoMatchFiveDrawsService {

    private static Logger LOGGER = LoggerFactory.getLogger(AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.class);

    @Override
    public AverageTimeBetweenTwoMatchFiveDrawsResult getAverageTimeBetweenTwoMatchFiveDraws(String url,
                                                                                            String accessToken) {
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "average time between two match five draws Gateway hivas indul");

        AverageTimeBetweenTwoMatchFiveDrawsResult result = this.apiCall(url, accessToken,
                                                                        AverageTimeBetweenTwoMatchFiveDrawsResult.class);

        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "average time between two match five draws Gateway hivas vege");
        return result;
    }
}
