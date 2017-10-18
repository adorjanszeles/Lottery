package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;
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
 * {@link AverageTimeBetweenTwoMatchFiveDrawsService interfész implementációja. {@link LotteryService osztály
 * extendálása.
 */
@Service
public class AverageTimeBetweenTwoMatchFiveDrawsServiceImpl extends LotteryService
        implements AverageTimeBetweenTwoMatchFiveDrawsService {
    private StatelessKieSession kieSession;

    @Autowired
    public AverageTimeBetweenTwoMatchFiveDrawsServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession, Lottery lottery) {
        super(lottery);
        this.kieSession = kieSession;
    }

    @Override
    public AverageTimeBetweenTwoMatchFiveDrawsResult executeRule() {
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "Két öttalálatos között eltelt idő szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        AverageTimeBetweenTwoMatchFiveDrawsResult averageTimeBetweenTwoMatchFiveDrawsResult = this.execute(
                weeklyDrawList);
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "Két öttalálatos között eltelt idő szabály futtatása befejeződött...");
        return averageTimeBetweenTwoMatchFiveDrawsResult;
    }

    @Override
    public AverageTimeBetweenTwoMatchFiveDrawsResult executeRuleFilterByDate(String from, String to) {
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett két öttalálatos között eltelt időt számító service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        List<WeeklyDraw> filteredList = weeklyDrawList.getDrawListPreparedForDrools()
                                                      .stream()
                                                      .filter(draw -> super.filterByDate(draw, from, to) != null)
                                                      .collect(Collectors.toList());
        weeklyDrawList.setDrawListPreparedForDrools(filteredList);
        AverageTimeBetweenTwoMatchFiveDrawsResult averageTimeBetweenTwoMatchFiveDrawsResult = this.execute(
                weeklyDrawList);
        AverageServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett két öttalálatos között eltelt időt számító service futtatása befejeződött...");
        return averageTimeBetweenTwoMatchFiveDrawsResult;
    }

    private AverageTimeBetweenTwoMatchFiveDrawsResult execute(WeeklyDrawList weeklyDrawList) {
        AverageTimeBetweenTwoMatchFiveDrawsResult averageTimeBetweenTwoMatchFiveDrawsResult = new AverageTimeBetweenTwoMatchFiveDrawsResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, averageTimeBetweenTwoMatchFiveDrawsResult));
        this.kieSession.execute(facts);
        return averageTimeBetweenTwoMatchFiveDrawsResult;
    }
}
