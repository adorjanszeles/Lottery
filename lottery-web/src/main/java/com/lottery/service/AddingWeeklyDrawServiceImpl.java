package com.lottery.service;

import com.lottery.model.Lottery;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawDTO;
import com.lottery.repository.WeeklyDrawJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link AddingWeeklyDrawService interfész implementációja.
 */

@Service
public class AddingWeeklyDrawServiceImpl implements AddingWeeklyDrawService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AddingWeeklyDrawServiceImpl.class);
    private Lottery lottery;
    private WeeklyDrawJPARepository weeklyDrawJPARepository;
    private WeeklyDrawDestinationMapper weeklyDrawDestinationMapper;

    @Autowired
    public AddingWeeklyDrawServiceImpl(Lottery lottery,
                                       WeeklyDrawJPARepository weeklyDrawJPARepository,
                                       WeeklyDrawDestinationMapper weeklyDrawDestinationMapper) {
        this.lottery = lottery;
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
        this.weeklyDrawDestinationMapper = weeklyDrawDestinationMapper;
    }

    @Override
    public void addNewWeeklyDraw(WeeklyDrawDTO sourceWeeklyDraw) {
        WeeklyDraw weeklyDraw = this.weeklyDrawDestinationMapper.sourceToDestination(sourceWeeklyDraw);
        AddingWeeklyDrawServiceImpl.LOGGER.debug(
                "addNewWeeklyDraw function: WeeklyDraw made from WeeklyDrawDTO object was successful.");
        this.lottery.getLotteryList().add(weeklyDraw);
        weeklyDrawJPARepository.save(weeklyDraw);
    }

}
