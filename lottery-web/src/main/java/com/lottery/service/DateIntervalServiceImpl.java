package com.lottery.service;

import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * {@link DateIntervalService interfész implementációja.
 */

@Service
public class DateIntervalServiceImpl extends LotteryService implements DateIntervalService {

    public DateIntervalServiceImpl(Lottery lottery) {
        super(lottery);
    }

    @Override
    public Date getStart() {
        WeeklyDrawList drawList = super.init();
        return drawList.getDrawListPreparedForDrools().stream().map(WeeklyDraw::getDrawDate).min(Date::compareTo).get();
    }

    @Override
    public Date getEnd() {
        WeeklyDrawList drawList = super.init();

        return drawList.getDrawListPreparedForDrools().stream().map(WeeklyDraw::getDrawDate).max(Date::compareTo).get();
    }
}
