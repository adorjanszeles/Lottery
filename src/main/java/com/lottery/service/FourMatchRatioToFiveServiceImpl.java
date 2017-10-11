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

@Service
public class FourMatchRatioToFiveServiceImpl implements FourMatchRatioToFiveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FourMatchRatioToFiveServiceImpl.class);
    private Lottery lottery;
    private StatelessKieSession kieSession;
    private WeeklyDrawList weeklyDrawList;
    private FourMatchRatioToFiveMatchResult fourMatchRatioToFiveMatchResult;

    @Autowired
    public FourMatchRatioToFiveServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession, Lottery lottery) {
        this.kieSession = kieSession;
        this.lottery = lottery;
    }

    @Override
    public FourMatchRatioToFiveMatchResult executeRule() {
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("szabály futtatása elkezdődött...");
        this.weeklyDrawList = new WeeklyDrawList();
        this.weeklyDrawList.setDrawListPreparedForDrools(this.lottery.getLotteryList());
        System.out.println(this.weeklyDrawList.getDrawListPreparedForDrools().size());
        this.fourMatchRatioToFiveMatchResult = new FourMatchRatioToFiveMatchResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.fourMatchRatioToFiveMatchResult));
        this.kieSession.execute(facts);
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("szabály futtatása befejeződött...");
        return this.fourMatchRatioToFiveMatchResult;
    }
}
