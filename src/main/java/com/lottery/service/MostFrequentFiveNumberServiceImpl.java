package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.Lottery;
import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.model.WeeklyDrawList;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link MostFrequentFiveNumberService interfész implementációja.
 */

@Service
public class MostFrequentFiveNumberServiceImpl implements MostFrequentFiveNumberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MostFrequentFiveNumberServiceImpl.class);
    private Lottery lottery;
    private StatelessKieSession kieSession;
    private WeeklyDrawList weeklyDrawList;
    private MostFrequentFiveNumberResult mostFrequentFiveNumberResult;

    @Autowired
    public MostFrequentFiveNumberServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession, Lottery lottery) {
        this.kieSession = kieSession;
        this.lottery = lottery;
    }

    @Override
    public MostFrequentFiveNumberResult executeRule() {
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("szabály futtatása elkezdődött...");
        this.weeklyDrawList = new WeeklyDrawList();
        this.weeklyDrawList.setDrawListPreparedForDrools(this.lottery.getLotteryList());
        this.mostFrequentFiveNumberResult = new MostFrequentFiveNumberResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(this.weeklyDrawList, this.mostFrequentFiveNumberResult));
        this.kieSession.execute(facts);
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("szabály futtatása befejeződött...");
        return this.mostFrequentFiveNumberResult;
    }
}
