package com.lottery.service;

import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;

/**
 * interfész ötös találatok közti átlagos idő kiszámítására írt service-hez
 */
public interface AverageTimeBetweenTwoMatchFiveDrawsService {

    /**
     * Két ötös találat közti átlagos idő kiszámítására írt rule futtatása
     *
     * @return result objektum
     */

    AverageTimeBetweenTwoMatchFiveDrawsResult executeRule();
}
