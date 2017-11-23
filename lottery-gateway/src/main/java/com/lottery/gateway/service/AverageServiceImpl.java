package com.lottery.gateway.service;

import com.lottery.model.AverageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * {@link AverageService} interfész implementációja
 */

@Service
public class AverageServiceImpl extends AbstractGetService<AverageResult> implements AverageService {

    private static Logger LOGGER = LoggerFactory.getLogger(AverageServiceImpl.class);

    @Override
    public AverageResult getAverageResult(String url, String accessToken) {
        AverageServiceImpl.LOGGER.debug("average Gateway hivas indul");

        AverageResult result = this.apiCall(url, accessToken, AverageResult.class);

        AverageServiceImpl.LOGGER.debug("AVERAGE: "+ result.getResult());
        AverageServiceImpl.LOGGER.debug("average Gateway hivas vege");
        return result;
    }
}
