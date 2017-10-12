package com.lottery.service;

import com.lottery.model.AverageResult;

/**
 * interfész átlagot számító szabály futtatásához
 */
public interface AverageService {

    /**
     * Átlagot számító rule futtatása
     *
     * @return result objektum
     */

    AverageResult executeRule();
}
