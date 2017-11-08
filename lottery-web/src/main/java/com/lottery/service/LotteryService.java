package com.lottery.service;

import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDrawList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Lottery Servicek absztakt osztálya
 */
public abstract class LotteryService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(LotteryService.class);
    protected Lottery lottery;

    public LotteryService(Lottery lottery) {
        this.lottery = lottery;
    }

    /**
     * Input dátumok dátummá parsolása
     *
     * @param dateInput datum kezdeze
     * @return Date objektum
     */
    protected Date parseDate(String dateInput) throws ParseException {
        LotteryService.LOGGER.debug("Input dátummá parszolása elkezdődöt...");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateInput);
        LotteryService.LOGGER.debug("Input dátummá parszolása befejeződött...");
        return date;
    }

    /**
     * WeeklydrawList inicializálása a servicekhez
     *
     * @return WeeklydrawList, amiben az összes sorsolás eredeménye van
     */
    protected WeeklyDrawList init() {
        LotteryService.LOGGER.debug("weeklyDraw lista létrehozása elkezdődött...");
        WeeklyDrawList weeklyDrawList = new WeeklyDrawList();
        weeklyDrawList.setDrawListPreparedForDrools(this.lottery.getLotteryList());
        LotteryService.LOGGER.debug("weeklyDraw lista létrehozása befejeződött...");
        return weeklyDrawList;
    }

}
