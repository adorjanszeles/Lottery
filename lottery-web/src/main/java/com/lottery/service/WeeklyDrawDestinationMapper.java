package com.lottery.service;

import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * This interface make auto-generated implementation for itself(you can find it in Target dict).
 * Its a bean mapper interface using MapSturct.
 */
@Mapper
public interface WeeklyDrawDestinationMapper {
    @Mappings({
            @Mapping(target = "redisId", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    WeeklyDraw sourceToDestination(WeeklyDrawDTO source);

    WeeklyDrawDTO destinationToSource(WeeklyDraw destination);
}