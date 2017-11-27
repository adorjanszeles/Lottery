package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.Lottery;
import com.lottery.model.RearestFiveResult;
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
 * {@link RearestFiveService} interfész implementációja. {@link LotteryService} osztály extendálása.
 */
@Service
public class RearestFiveServiceImpl extends LotteryService implements RearestFiveService {
    private StatelessKieSession kieSession;
    private WeeklyDrawJPARepository weeklyDrawJPARepository;

    @Autowired
    public RearestFiveServiceImpl(@Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
                                  Lottery lottery,
                                  WeeklyDrawJPARepository weeklyDrawJPARepository) {
        super(lottery);
        this.kieSession = kieSession;
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
    }

    @Override
    public RearestFiveResult executeRule() {
        RearestFiveServiceImpl.LOGGER.debug("Legritkábban kihúzott számok szabályának futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = this.init();
        RearestFiveResult rearestFiveResult = this.execute(weeklyDrawList);
        RearestFiveServiceImpl.LOGGER.debug("Legritkábban kihúzott számok szabályának futtatása befejeződött...");
        return rearestFiveResult;
    }

    @Override
    public RearestFiveResult executeRuleFilterByDate(String from, String to) throws ParseException {
        RearestFiveServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett legritkábban kihúzott számok service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = this.init();
        Date fromDate = this.parseDate(from);
        Date toDate = this.parseDate(to);
        RearestFiveServiceImpl.LOGGER.debug("Datum alapjan filterezett húzások query-je elkezdődött...");
        List<WeeklyDraw> filteredList = weeklyDrawJPARepository.findWeeklyDrawByDrawDateAfterAndDrawDateBefore(fromDate,
                                                                                                               toDate);
        RearestFiveServiceImpl.LOGGER.debug("Datum alapjan filterezett húzások query-je befejeződött...");
        weeklyDrawList.setDrawListPreparedForDrools(filteredList);
        RearestFiveResult rearestFiveResult = this.execute(weeklyDrawList);
        RearestFiveServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett legritkábban kihúzott számok service futtatása befejeződött...");
        return rearestFiveResult;
    }

    private RearestFiveResult execute(WeeklyDrawList weeklyDrawList) {
        RearestFiveResult rearestFiveResult = new RearestFiveResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, rearestFiveResult));
        this.kieSession.execute(facts);
        return rearestFiveResult;
    }
}
