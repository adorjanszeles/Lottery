package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;
import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDrawList;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link AverageTimeBetweenTwoMatchFiveDrawsService interfész implementációja.
 */

@Service
public class AverageTimeBetweenTwoMatchFiveDrawsServiceImpl implements AverageTimeBetweenTwoMatchFiveDrawsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.class);
    private Lottery lottery;
    private StatelessKieSession kieSession;

    @Autowired
    public AverageTimeBetweenTwoMatchFiveDrawsServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession, Lottery lottery) {
        this.kieSession = kieSession;
        this.lottery = lottery;
    }

    @Override
    public AverageTimeBetweenTwoMatchFiveDrawsResult executeRule() {
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug("szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList;
        AverageTimeBetweenTwoMatchFiveDrawsResult averageTimeBetweenTwoMatchFiveDrawsResult;
        weeklyDrawList = new WeeklyDrawList();
        weeklyDrawList.setDrawListPreparedForDrools(this.lottery.getLotteryList());
        System.out.println(weeklyDrawList.getDrawListPreparedForDrools().size());
        averageTimeBetweenTwoMatchFiveDrawsResult = new AverageTimeBetweenTwoMatchFiveDrawsResult();
        List<Object> facts = new ArrayList<>(
                Arrays.asList(weeklyDrawList, averageTimeBetweenTwoMatchFiveDrawsResult));
        this.kieSession.execute(facts);
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug("szabály futtatása befejeződött...");
        return averageTimeBetweenTwoMatchFiveDrawsResult;
    }
}
