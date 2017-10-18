package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.Lottery;
import com.lottery.model.MostFrequentlyOccurringPairsResult;
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
 * {@link MostFrequentlyOccuringPairsService interfész implementációja. {@link LotteryService abstract osztály
 * extendálása.
 */
@Service
public class MostFrequentlyOccuringPairsServiceImpl extends LotteryService
        implements MostFrequentlyOccuringPairsService {
    private StatelessKieSession kieSession;

    @Autowired
    public MostFrequentlyOccuringPairsServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession, Lottery lottery) {
        super(lottery);
        this.kieSession = kieSession;
    }

    @Override
    public MostFrequentlyOccurringPairsResult executeRule() {
        MostFrequentlyOccuringPairsServiceImpl.LOGGER.debug("Leggyakoribb számpárok szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        MostFrequentlyOccurringPairsResult mostFrequentlyOccurringPairsResult = this.execute(weeklyDrawList);
        AverageServiceImpl.LOGGER.debug("Leggyakoribb számpárok szabály futtatása befejeződött...");
        return mostFrequentlyOccurringPairsResult;
    }

    @Override
    public MostFrequentlyOccurringPairsResult executeRuleFilterByDate(String from, String to) {
        MostFrequentlyOccuringPairsServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett leggyakoribb számpárokat számító service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        List<WeeklyDraw> filteredList = weeklyDrawList.getDrawListPreparedForDrools()
                                                      .stream()
                                                      .filter(draw -> super.filterByDate(draw, from, to) != null)
                                                      .collect(Collectors.toList());
        weeklyDrawList.setDrawListPreparedForDrools(filteredList);
        MostFrequentlyOccurringPairsResult mostFrequentlyOccurringPairsResult = this.execute(weeklyDrawList);
        AverageServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett leggyakoribb számpárokat számító service futtatása befejeződött...");
        return mostFrequentlyOccurringPairsResult;
    }

    private MostFrequentlyOccurringPairsResult execute(WeeklyDrawList weeklyDrawList) {
        MostFrequentlyOccurringPairsResult mostFrequentlyOccurringPairsResult = new MostFrequentlyOccurringPairsResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, mostFrequentlyOccurringPairsResult));
        this.kieSession.execute(facts);
        return mostFrequentlyOccurringPairsResult;
    }

}
