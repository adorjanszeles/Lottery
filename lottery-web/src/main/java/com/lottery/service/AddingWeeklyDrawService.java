package com.lottery.service;

import com.lottery.model.WeeklyDrawDTO;

/**
 * interfész új weeklyDraw objektumok létrehozásához
 */
public interface AddingWeeklyDrawService {

    /**
     * Adds a new WeeklyDraw object to the LotteryList
     * @param weeklyDraw weeklyDraw DTO
     */
    void addNewWeeklyDraw(WeeklyDrawDTO weeklyDraw);
}
