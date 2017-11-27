package com.lottery.service;

import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Ez alapján az interface alapján a MapStruct bean mapper implementációt gyárt. A WeeklyDraw osztályt map-peli.
 */
@Mapper
public interface WeeklyDrawDestinationMapper {
    @Mappings({@Mapping(target = "redisId", ignore = true), @Mapping(target = "id", ignore = true)})
    WeeklyDraw sourceToDestination(WeeklyDrawDTO source);

    WeeklyDrawDTO destinationToSource(WeeklyDraw destination);
}