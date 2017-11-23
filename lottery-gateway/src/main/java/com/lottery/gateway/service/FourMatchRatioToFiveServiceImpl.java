package com.lottery.gateway.service;

import com.lottery.model.FourMatchRatioToFiveMatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * {@link FourMatchRatioToFiveService} interfész implementációja
 * {@link AbstractGetService} abstract class kiterjesztése
 */
@Service
public class FourMatchRatioToFiveServiceImpl extends AbstractGetService<FourMatchRatioToFiveMatchResult>
        implements FourMatchRatioToFiveService {

    private static Logger LOGGER = LoggerFactory.getLogger(FourMatchRatioToFiveServiceImpl.class);

    @Override
    public FourMatchRatioToFiveMatchResult getFourMatchRatioToFive(String url, String accessToken) {
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("Four match ratio to five Gateway hivas indul");

        FourMatchRatioToFiveMatchResult result = this.apiCall(url, accessToken, FourMatchRatioToFiveMatchResult.class);

        FourMatchRatioToFiveServiceImpl.LOGGER.debug("Four match ratio to five Gateway hivas vege");
        return result;
    }
}
