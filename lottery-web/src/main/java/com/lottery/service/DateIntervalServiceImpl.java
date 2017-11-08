package com.lottery.service;

import com.lottery.model.Lottery;
import com.lottery.repository.WeeklyDrawJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * {@link DateIntervalService interfész implementációja.
 */

@Service
public class DateIntervalServiceImpl extends LotteryService implements DateIntervalService {
    private WeeklyDrawJPARepository weeklyDrawJPARepository;

    @Autowired
    public DateIntervalServiceImpl(Lottery lottery, WeeklyDrawJPARepository weeklyDrawJPARepository) {
        super(lottery);
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
    }

    @Override
    public Date getStart() {
        DateIntervalServiceImpl.LOGGER.debug("Legkorábbi dátum query-je elkezdődött...");
        Date min = weeklyDrawJPARepository.minDate();
        DateIntervalServiceImpl.LOGGER.debug("Legkorábbi dátum query-je befejeződött...");
        return min;
    }

    @Override
    public Date getEnd() {
        DateIntervalServiceImpl.LOGGER.debug("Legújabb dátum query-je elkezdődött...");
        Date max = weeklyDrawJPARepository.maxDate();
        DateIntervalServiceImpl.LOGGER.debug("Legújabb dátum query-je befejeződött...");
        return max;
    }
}
