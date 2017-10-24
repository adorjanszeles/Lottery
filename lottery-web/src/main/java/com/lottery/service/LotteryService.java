package com.lottery.service;

import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;
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
     * Weeklydraw objektum szűrése dátumok közötti intervallumra
     *
     * @param weeklyDraw objektum
     * @param from       datum kezdeze
     * @param to         datum vege
     * @return Weeklydraw objektum, ha a datum kozott van, ha nem null-t ad vissza
     */
    protected WeeklyDraw filterByDate(WeeklyDraw weeklyDraw, String from, String to) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = format.parse(from);
            Date date2 = format.parse(to);
            Date date = weeklyDraw.getDrawDate();
            if (date.before(date2) && date.after(date1)) {
                return weeklyDraw;
            }
        } catch (ParseException e) {
            LotteryService.LOGGER.debug("Datum parszolasi hiba");
        } catch (NullPointerException e) {
            LotteryService.LOGGER.debug("Nincs datum.");
        }
        return null;
    }

    /**
     * WeeklydrawList inicializálása a servicekhez
     *
     * @return WeeklydrawList, amiben az összes sorsolás eredeménye van
     */
    protected WeeklyDrawList init() {
        WeeklyDrawList weeklyDrawList = new WeeklyDrawList();
        weeklyDrawList.setDrawListPreparedForDrools(this.lottery.getLotteryList());
        return weeklyDrawList;
    }

}
