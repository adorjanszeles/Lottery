package com.lottery.service;

import com.lottery.model.WeeklyDrawDTO;

/**
 * interfész új weeklyDraw objektumok létrehozásához
 */
public interface AddingWeeklyDrawService {

    /**
     * Új weeklyDraw objektumokat ad a lottery listához
     *
     * @param weeklyDraw weeklyDraw DTO
     */
    void addNewWeeklyDraw(WeeklyDrawDTO weeklyDraw);
}
