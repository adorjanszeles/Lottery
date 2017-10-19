package com.lottery.service;

import com.lottery.model.WeeklyDraw;

/**
 * interfész új weeklyDraw objektumok létrehozásához
 */
public interface AddingWeeklyDrawService {

    /**
     * Adds a new WeeklyDraw object to the LotteryList
     */
    void AddNewWeeklyDraw(WeeklyDraw weeklyDraw);
}
