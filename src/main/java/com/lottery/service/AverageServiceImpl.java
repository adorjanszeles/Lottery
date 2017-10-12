package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.AverageResult;
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
 * {@link AverageService interfész implementációja.
 */

@Service
public class AverageServiceImpl implements AverageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AverageServiceImpl.class);
    private Lottery lottery;
    private StatelessKieSession kieSession;

    @Autowired
    public AverageServiceImpl(@Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
                              Lottery lottery) {
        this.kieSession = kieSession;
        this.lottery = lottery;
    }

    @Override
    public AverageResult executeRule() {
        AverageServiceImpl.LOGGER.debug("szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList;
        AverageResult averageResult;
        weeklyDrawList = new WeeklyDrawList();
        weeklyDrawList.setDrawListPreparedForDrools(this.lottery.getLotteryList());
        averageResult = new AverageResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, averageResult));
        this.kieSession.execute(facts);
        AverageServiceImpl.LOGGER.debug("szabály futtatása befejeződött...");
        return averageResult;
    }
}
