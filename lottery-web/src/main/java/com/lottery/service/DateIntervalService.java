package com.lottery.service;

import java.util.Date;

/**
 * interfész dátum végpontok kiszámításához
 */
public interface DateIntervalService {
    /**
     * a legkorábbi húzás dátumát számolja ki
     *
     * @return weeklyDraw objektum
     */
    Date getStart();

    /**
     * a legújabb húzás dátumát számolja ki
     *
     * @return weeklyDraw objektum
     */
    Date getEnd();
}
