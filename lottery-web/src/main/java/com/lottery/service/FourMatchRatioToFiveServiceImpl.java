package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.FourMatchRatioToFiveMatchResult;
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
 * {@link FourMatchRatioToFiveService} interfész implementációja. {@link LotteryService} osztály extendálása.
 */
@Service
public class FourMatchRatioToFiveServiceImpl extends LotteryService implements FourMatchRatioToFiveService {
    private StatelessKieSession kieSession;
    private WeeklyDrawJPARepository weeklyDrawJPARepository;

    @Autowired
    public FourMatchRatioToFiveServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
            Lottery lottery,
            WeeklyDrawJPARepository weeklyDrawJPARepository) {
        super(lottery);
        this.kieSession = kieSession;
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
    }

    @Override
    public FourMatchRatioToFiveMatchResult executeRule() {
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("Négyesek aránya az ötösökhöz szabály futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = this.init();
        FourMatchRatioToFiveMatchResult fourMatchRatioToFiveMatchResult = this.execute(weeklyDrawList);
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("Négyesek aránya az ötösökhöz szabály futtatása befejeződött...");
        return fourMatchRatioToFiveMatchResult;
    }

    @Override
    public FourMatchRatioToFiveMatchResult executeRuleFilterByDate(String from, String to) throws ParseException {
        FourMatchRatioToFiveServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett négyesek aránya az ötösökhöz service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = this.init();
        Date fromDate = this.parseDate(from);
        Date toDate = this.parseDate(to);
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("Datum alapjan filterezett húzások query-je elkezdődött...");
        List<WeeklyDraw> filteredList = weeklyDrawJPARepository.findWeeklyDrawByDrawDateAfterAndDrawDateBefore(fromDate,
                                                                                                               toDate);
        FourMatchRatioToFiveServiceImpl.LOGGER.debug("Datum alapjan filterezett húzások query-je befejeződött...");
        weeklyDrawList.setDrawListPreparedForDrools(filteredList);
        FourMatchRatioToFiveMatchResult fourMatchRatioToFiveMatchResult = this.execute(weeklyDrawList);
        AverageServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett négyesek aránya az ötösökhöz service futtatása befejeződött...");
        return fourMatchRatioToFiveMatchResult;
    }

    private FourMatchRatioToFiveMatchResult execute(WeeklyDrawList weeklyDrawList) {
        FourMatchRatioToFiveMatchResult fourMatchRatioToFiveMatchResult = new FourMatchRatioToFiveMatchResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, fourMatchRatioToFiveMatchResult));
        this.kieSession.execute(facts);
        return fourMatchRatioToFiveMatchResult;
    }
}
