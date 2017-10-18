package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.Lottery;
import com.lottery.model.RearestFiveResult;
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
 * {@link RearestFiveService interfész implementációja. {@link LotteryService osztály extendálása.
 */
@Service
public class RearestFiveServiceImpl extends LotteryService implements RearestFiveService {
    private StatelessKieSession kieSession;

    @Autowired
    public RearestFiveServiceImpl(@Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession,
                                  Lottery lottery) {
        super(lottery);
        this.kieSession = kieSession;
    }

    @Override
    public RearestFiveResult executeRule() {
        RearestFiveServiceImpl.LOGGER.debug("Legritkábban kihúzott számok szabályának futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        RearestFiveResult rearestFiveResult = this.execute(weeklyDrawList);
        RearestFiveServiceImpl.LOGGER.debug("Legritkábban kihúzott számok szabályának futtatása befejeződött...");
        return rearestFiveResult;
    }

    @Override
    public RearestFiveResult executeRuleFilterByDate(String from, String to) {
        RearestFiveServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett legritkábban kihúzott számok service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = super.init();
        List<WeeklyDraw> filteredList = weeklyDrawList.getDrawListPreparedForDrools()
                                                      .stream()
                                                      .filter(draw -> super.filterByDate(draw, from, to) != null)
                                                      .collect(Collectors.toList());
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
