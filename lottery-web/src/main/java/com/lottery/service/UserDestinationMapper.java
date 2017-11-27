package com.lottery.service;

import com.lottery.model.User;
import com.lottery.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

/**
 * Ez alapján az interface alapján a MapStruct bean mapper implementációt gyárt. A WeeklyDraw osztályt map-peli.
 */
@Mapper
@Service
public interface UserDestinationMapper {

    @Mappings({@Mapping(target = "id", ignore = true)})
    User sourceToDestination(UserDTO source);

    @Mappings({@Mapping(target = "password", ignore = true)})
    UserDTO destinationToSource(User destination);
}
