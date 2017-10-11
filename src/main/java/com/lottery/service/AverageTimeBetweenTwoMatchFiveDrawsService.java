package com.lottery.service;

import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;

public interface AverageTimeBetweenTwoMatchFiveDrawsService {

    /**
     * rule futtatása
     *
     * @return result objektum
     */

    AverageTimeBetweenTwoMatchFiveDrawsResult executeRule();
}
