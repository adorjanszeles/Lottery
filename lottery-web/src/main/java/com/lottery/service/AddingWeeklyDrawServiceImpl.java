package com.lottery.service;

import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link AddingWeeklyDrawService interfész implementációja.
 */

@Service
public class AddingWeeklyDrawServiceImpl implements AddingWeeklyDrawService {

    private Lottery lottery;

    @Autowired
    public AddingWeeklyDrawServiceImpl(Lottery lottery) {
        this.lottery = lottery;
    }

    @Override
    public void AddNewWeeklyDraw(WeeklyDraw weeklyDraw) {
        this.lottery.getLotteryList().add(weeklyDraw);
    }

}
