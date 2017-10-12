package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.Lottery;
import com.lottery.model.RearestFiveResult;
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
public class RearestFiveServiceImpl implements RearestFiveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RearestFiveServiceImpl.class);
    private Lottery lottery;
    private StatelessKieSession kieSession;

    @Autowired
    public RearestFiveServiceImpl(@Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
                                  Lottery lottery) {
        this.kieSession = kieSession;
        this.lottery = lottery;
    }

    @Override
    public RearestFiveResult executeRule() {
        RearestFiveServiceImpl.LOGGER.debug("szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = new WeeklyDrawList();
        weeklyDrawList.setDrawListPreparedForDrools(this.lottery.getLotteryList());
        System.out.println(weeklyDrawList.getDrawListPreparedForDrools().size());
        RearestFiveResult rearestFiveResult = new RearestFiveResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, rearestFiveResult));
        this.kieSession.execute(facts);
        RearestFiveServiceImpl.LOGGER.debug("szabály futtatása befejeződött...");
        return rearestFiveResult;
    }
}
