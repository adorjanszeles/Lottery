package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.FourMatchRatioToFiveMatchResult;
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
 * {@link FourMatchRatioToFiveService interfész implementációja.
 */
@Service
public class FourMatchRatioToFiveServiceImpl implements FourMatchRatioToFiveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FourMatchRatioToFiveServiceImpl.class);
    private Lottery lottery;
    private StatelessKieSession kieSession;

    @Autowired
    public FourMatchRatioToFiveServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession, Lottery lottery) {
        this.kieSession = kieSession;
        this.lottery = lottery;
    }

    @Override
    public FourMatchRatioToFiveMatchResult executeRule() {
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = new WeeklyDrawList();
        weeklyDrawList.setDrawListPreparedForDrools(this.lottery.getLotteryList());
        System.out.println(weeklyDrawList.getDrawListPreparedForDrools().size());
        FourMatchRatioToFiveMatchResult fourMatchRatioToFiveMatchResult = new FourMatchRatioToFiveMatchResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, fourMatchRatioToFiveMatchResult));
        this.kieSession.execute(facts);
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("szabály futtatása befejeződött...");
        return fourMatchRatioToFiveMatchResult;
    }
}
