package com.lottery.service;

import com.lottery.config.LotteryQualifier;
import com.lottery.model.Lottery;
import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link MostFrequentFiveNumberService interfész implementációja.
 */

@Service
public class MostFrequentFiveNumberServiceImpl implements MostFrequentFiveNumberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MostFrequentFiveNumberServiceImpl.class);
    private Lottery lottery;
    private StatelessKieSession kieSession;

    @Autowired
    public MostFrequentFiveNumberServiceImpl(
            @Qualifier(LotteryQualifier.statelessKieSessionName) StatelessKieSession kieSession, Lottery lottery) {
        this.kieSession = kieSession;
        this.lottery = lottery;
    }

    @Override
    public MostFrequentFiveNumberResult executeRule() {
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Leggyakoribb öt szám service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = this.init();
        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = this.execute(weeklyDrawList);
        MostFrequentFiveNumberServiceImpl.LOGGER.debug("Leggyakoribb öt szám service futtatása befejeződött...");
        return mostFrequentFiveNumberResult;
    }

    @Override
    public MostFrequentFiveNumberResult executeRuleFilterByDate(String from, String to) {
        MostFrequentFiveNumberServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett leggyakoribb ot szam service futtatása elkezdődött...");
        WeeklyDrawList weeklyDrawList = this.init();
        List<WeeklyDraw> filteredList = weeklyDrawList.getDrawListPreparedForDrools()
                                                      .stream()
                                                      .filter(draw -> filterListByDate(draw, from, to) != null)
                                                      .collect(Collectors.toList());
        weeklyDrawList.setDrawListPreparedForDrools(filteredList);
        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = this.execute(weeklyDrawList);
        MostFrequentFiveNumberServiceImpl.LOGGER.debug(
                "Datum alapjan filterezett leggyakoribb ot szam service futtatása befejeződött...");
        return mostFrequentFiveNumberResult;
    }

    private WeeklyDrawList init() {
        WeeklyDrawList weeklyDrawList = new WeeklyDrawList();
        weeklyDrawList.setDrawListPreparedForDrools(this.lottery.getLotteryList());
        return weeklyDrawList;
    }

    private MostFrequentFiveNumberResult execute(WeeklyDrawList weeklyDrawList) {
        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = new MostFrequentFiveNumberResult();
        List<Object> facts = new ArrayList<>(Arrays.asList(weeklyDrawList, mostFrequentFiveNumberResult));
        this.kieSession.execute(facts);
        return mostFrequentFiveNumberResult;
    }

    private WeeklyDraw filterListByDate(WeeklyDraw weeklyDraw, String from, String to) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = format.parse(from);
            Date date2 = format.parse(to);
            Date date = format.parse(weeklyDraw.getDrawDate().toPattern());
            if (date.before(date2) && date.after(date1)) {
                return weeklyDraw;
            }
        } catch (ParseException e) {
            MostFrequentFiveNumberServiceImpl.LOGGER.debug("Datum parszolasi hiba");;
        } catch (NullPointerException e) {
            MostFrequentFiveNumberServiceImpl.LOGGER.debug("Nincs datum.");
        }
        return null;
    }

}
