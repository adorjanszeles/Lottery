package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.Lottery;
import com.lottery.model.MostFrequentlyOccurringPairsResult;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import com.lottery.model.mfop_utils.DrawsInTwoDimension;
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
 * {@link MostFrequentlyOccuringPairsService interfész implementációja. {@link LotteryService abstract osztály
 * extendálása.
 */
@Service
public class MostFrequentlyOccuringPairsServiceImpl extends LotteryService
        implements MostFrequentlyOccuringPairsService {
    private StatelessKieSession kieSession;
    private WeeklyDrawJPARepository weeklyDrawJPARepository;

    @Autowired
    public MostFrequentlyOccuringPairsServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
            Lottery lottery,
            WeeklyDrawJPARepository weeklyDrawJPARepository) {
        super(lottery);
        this.kieSession = kieSession;
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
    }

    @Override
    public DrawsInTwoDimension executeRule() {
        MostFrequentlyOccuringPairsServiceImpl.LOGGER.debug("Leggyakoribb számpárok szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        MostFrequentlyOccurringPairsResult mostFrequentlyOccurringPairsResult = this.execute(weeklyDrawList);
        AverageServiceImpl.LOGGER.debug("Leggyakoribb számpárok szabály futtatása befejeződött...");
        return mostFrequentlyOccurringPairsResult.getResultArray();
    }

    @Override
    public DrawsInTwoDimension executeRuleFilterByDate(String from, String to) throws ParseException {
        MostFrequentlyOccuringPairsServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett leggyakoribb számpárokat számító service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        Date fromDate = super.parseDate(from);
        Date toDate = super.parseDate(to);
        MostFrequentlyOccuringPairsServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett húzások query-je elkezdődött...");
        List<WeeklyDraw> filteredList = weeklyDrawJPARepository.findWeeklyDrawByDrawDateAfterAndDrawDateBefore(fromDate,
                                                                                                               toDate);
        MostFrequentlyOccuringPairsServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett húzások query-je befejeződött...");
        weeklyDrawList.setDrawListPreparedForDrools(filteredList);
        MostFrequentlyOccurringPairsResult mostFrequentlyOccurringPairsResult = this.execute(weeklyDrawList);
        AverageServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett leggyakoribb számpárokat számító service futtatása befejeződött...");
        return mostFrequentlyOccurringPairsResult.getResultArray();
    }

    private MostFrequentlyOccurringPairsResult execute(WeeklyDrawList weeklyDrawList) {
        MostFrequentlyOccurringPairsResult mostFrequentlyOccurringPairsResult = new MostFrequentlyOccurringPairsResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, mostFrequentlyOccurringPairsResult));
        this.kieSession.execute(facts);
        return mostFrequentlyOccurringPairsResult;
    }

}
