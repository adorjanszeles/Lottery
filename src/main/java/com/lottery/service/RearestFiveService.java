package com.lottery.service;

import com.lottery.model.RearestFiveResult;

/**
 * interfész a legritkábban előforduló számok kiszámítására írt service-hez
 */
public interface RearestFiveService {

    /**
     * Legritkábban előforduló számok kiszámításához írt rule futtatása
     *
     * @return result objektum
     */

    RearestFiveResult executeRule();

}
