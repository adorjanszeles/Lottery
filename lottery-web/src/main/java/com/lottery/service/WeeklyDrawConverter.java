package com.lottery.service;

import com.lottery.model.RawWeeklyDraw;
import com.lottery.model.WeeklyDraw;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Interface a RawWeeklyDraw osztály instance-okból WeeklyDrawt gyártó folyamtot vezérlő osztályhoz.
 */
public interface WeeklyDrawConverter {

    List<WeeklyDraw> convertRawsToWeeklyDraws(List<RawWeeklyDraw> rawWeeklyDraws) throws FileNotFoundException;

}
