package com.lottery.service;


import com.lottery.model.RawWeeklyDraw;
import com.lottery.model.WeeklyDraw;

import java.io.FileNotFoundException;
import java.util.List;

public interface WeeklyDrawConverter {

    List<WeeklyDraw> convertRawsToWeeklyDraws(List<RawWeeklyDraw> rawWeeklyDraws) throws FileNotFoundException;

}
