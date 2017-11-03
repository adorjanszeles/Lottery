package com.lottery.service;

import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawDTO;
import com.lottery.repository.WeeklyDrawJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link AddingWeeklyDrawService interfész implementációja.
 */

@Service
public class AddingWeeklyDrawServiceImpl implements AddingWeeklyDrawService {

    private Lottery lottery;
    private WeeklyDrawJPARepository weeklyDrawJPARepository;
    private WeeklyDrawDestinationMapper weeklyDrawDestinationMapper;

    @Autowired
    public AddingWeeklyDrawServiceImpl(Lottery lottery, WeeklyDrawJPARepository weeklyDrawJPARepository) {
        this.lottery = lottery;
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
    }

    @Override
    public void AddNewWeeklyDraw(WeeklyDrawDTO sourceWeeklyDraw) {
        weeklyDrawDestinationMapper = new WeeklyDrawDestinationMapperImpl();
        WeeklyDraw weeklyDraw = this.weeklyDrawDestinationMapper.sourceToDestination(sourceWeeklyDraw);
        System.out.println(weeklyDraw);
        this.lottery.getLotteryList().add(weeklyDraw);
        weeklyDrawJPARepository.save(weeklyDraw);
    }

}
