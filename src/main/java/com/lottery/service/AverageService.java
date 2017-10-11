package com.lottery.service;

import com.lottery.model.AverageResult;

public interface AverageService {

    /**
     * rule futtatása
     *
     * @return result objektum
     */

    AverageResult executeRule();
}
