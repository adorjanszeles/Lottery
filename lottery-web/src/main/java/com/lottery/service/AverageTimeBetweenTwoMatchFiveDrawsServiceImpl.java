package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;
import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import com.lottery.repository.WeeklyDrawJPARepository;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * {@link AverageTimeBetweenTwoMatchFiveDrawsService} interfész implementációja. {@link LotteryService} osztály
 * extendálása.
 */
@Service
public class AverageTimeBetweenTwoMatchFiveDrawsServiceImpl extends LotteryService
        implements AverageTimeBetweenTwoMatchFiveDrawsService {
    private StatelessKieSession kieSession;
    private WeeklyDrawJPARepository weeklyDrawJPARepository;

    @Autowired
    public AverageTimeBetweenTwoMatchFiveDrawsServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
            Lottery lottery,
            WeeklyDrawJPARepository weeklyDrawJPARepository) {
        super(lottery);
        this.kieSession = kieSession;
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
    }

    @Override
    public AverageTimeBetweenTwoMatchFiveDrawsResult executeRule() {
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "Két öt találatos között eltelt idő szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = this.init();
        AverageTimeBetweenTwoMatchFiveDrawsResult averageTimeBetweenTwoMatchFiveDrawsResult = this.execute(
                weeklyDrawList);
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "Két öt találatos között eltelt idő szabály futtatása befejeződött...");
        return averageTimeBetweenTwoMatchFiveDrawsResult;
    }

    @Override
    public AverageTimeBetweenTwoMatchFiveDrawsResult executeRuleFilterByDate(String from, String to)
            throws ParseException {
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett két öttalálatos között eltelt időt számító service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = this.init();
        Date fromDate = this.parseDate(from);
        Date toDate = this.parseDate(to);
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett húzások query-je elkezdődött...");
        List<WeeklyDraw> filteredList = weeklyDrawJPARepository.findWeeklyDrawByDrawDateAfterAndDrawDateBefore(fromDate,
                                                                                                               toDate);
        AverageTimeBetweenTwoMatchFiveDrawsServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett húzások query-je befejeződött...");
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
