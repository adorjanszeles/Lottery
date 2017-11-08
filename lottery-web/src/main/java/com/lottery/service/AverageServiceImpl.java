package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.AverageResult;
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
 * {@link AverageService interfész implementációja. {@link LotteryService osztály extendálása.
 */

@Service
public class AverageServiceImpl extends LotteryService implements AverageService {
    private StatelessKieSession kieSession;
    private WeeklyDrawJPARepository weeklyDrawJPARepository;

    @Autowired
    public AverageServiceImpl(@Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
                              Lottery lottery,
                              WeeklyDrawJPARepository weeklyDrawJPARepository) {
        super(lottery);
        this.kieSession = kieSession;
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
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
    public AverageResult executeRuleFilterByDate(String from, String to) throws ParseException {
        AverageServiceImpl.LOGGER.debug("Datum alapjan filterezett átlagot számító service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        Date fromDate = super.parseDate(from);
        Date toDate = super.parseDate(to);
        AverageServiceImpl.LOGGER.debug("Datum alapjan filterezett húzások query-je elkezdődött...");
        List<WeeklyDraw> filteredList = weeklyDrawJPARepository.findWeeklyDrawByDrawDateAfterAndDrawDateBefore(fromDate,
                                                                                                               toDate);
        AverageServiceImpl.LOGGER.debug("Datum alapjan filterezett húzások query-je befejeződött...");
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
