package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.AverageResult;
import com.lottery.model.Lottery;
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
 * {@link AverageService interfész implementációja. {@link LotteryService osztály extendálása.
 */

@Service
public class AverageServiceImpl extends LotteryService implements AverageService {
    private StatelessKieSession kieSession;

    @Autowired
    public AverageServiceImpl(@Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
                              Lottery lottery) {
        super(lottery);
        this.kieSession = kieSession;
    }

    @Override
    public AverageResult executeRule() {
        AverageServiceImpl.LOGGER.debug("Átlagot számító szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        AverageResult averageResult = this.execute(weeklyDrawList);
        AverageServiceImpl.LOGGER.debug("Átlagot számító szabály futtatása befejeződött...");
        return averageResult;
    }

    @Override
    public AverageResult executeRuleFilterByDate(String from, String to) {
        AverageServiceImpl.LOGGER.debug("Datum alapjan filterezett átlagot számító service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        List<WeeklyDraw> filteredList = weeklyDrawList.getDrawListPreparedForDrools()
                                                      .stream()
                                                      .filter(draw -> super.filterByDate(draw, from, to) != null)
                                                      .collect(Collectors.toList());
        weeklyDrawList.setDrawListPreparedForDrools(filteredList);
        AverageResult averageResult = this.execute(weeklyDrawList);
        AverageServiceImpl.LOGGER.debug("Datum alapjan filterezett átlagot számító service futtatása befejeződött...");
        return averageResult;
    }

    private AverageResult execute(WeeklyDrawList weeklyDrawList) {
        AverageResult averageResult = new AverageResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, averageResult));
        this.kieSession.execute(facts);
        return averageResult;
    }
}
