package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.Lottery;
import com.lottery.model.MostFrequentFiveNumberResult;
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
 * {@link MostFrequentFiveNumberService} interfész implementációja. {@link LotteryService} osztály extendálása.
 */

@Service
public class MostFrequentFiveNumberServiceImpl extends LotteryService implements MostFrequentFiveNumberService {
    private StatelessKieSession kieSession;
    private WeeklyDrawJPARepository weeklyDrawJPARepository;

    @Autowired
    public MostFrequentFiveNumberServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
            Lottery lottery,
            WeeklyDrawJPARepository weeklyDrawJPARepository) {
        super(lottery);
        this.kieSession = kieSession;
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
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
    public MostFrequentFiveNumberResult executeRuleFilterByDate(String from, String to) throws ParseException {
        MostFrequentFiveNumberServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett leggyakoribb ot szam service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        Date fromDate = super.parseDate(from);
        Date toDate = super.parseDate(to);
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Datum alapjan filterezett húzások query-je elkezdődött...");
        List<WeeklyDraw> filteredList = weeklyDrawJPARepository.findWeeklyDrawByDrawDateAfterAndDrawDateBefore(fromDate,
                                                                                                               toDate);
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Datum alapjan filterezett húzások query-je befejeződött...");
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
