package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.Lottery;
import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link MostFrequentFiveNumberService interfész implementációja. {@link LotteryService osztály extendálása.
 */

@Service
public class MostFrequentFiveNumberServiceImpl extends LotteryService implements MostFrequentFiveNumberService {
    private StatelessKieSession kieSession;

    @Autowired
    public MostFrequentFiveNumberServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession, Lottery lottery) {
        super(lottery);
        this.kieSession = kieSession;
    }

    @Override
    public MostFrequentFiveNumberResult executeRule() {
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Leggyakoribb öt szám service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = this.execute(weeklyDrawList);
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Leggyakoribb öt szám service futtatása befejeződött...");
        return mostFrequentFiveNumberResult;
    }

    @Override
    public MostFrequentFiveNumberResult executeRuleFilterByDate(String from, String to) {
        MostFrequentFiveNumberServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett leggyakoribb ot szam service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        List<WeeklyDraw> filteredList = weeklyDrawList.getDrawListPreparedForDrools()
                                                      .stream()
                                                      .filter(draw -> super.filterByDate(draw, from, to) != null)
                                                      .collect(Collectors.toList());
        weeklyDrawList.setDrawListPreparedForDrools(filteredList);
        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = this.execute(weeklyDrawList);
        MostFrequentFiveNumberServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett leggyakoribb ot szam service futtatása befejeződött...");
        return mostFrequentFiveNumberResult;
    }

    private MostFrequentFiveNumberResult execute(WeeklyDrawList weeklyDrawList) {
        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = new MostFrequentFiveNumberResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, mostFrequentFiveNumberResult));
        this.kieSession.execute(facts);
        return mostFrequentFiveNumberResult;
    }
}
