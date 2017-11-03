package com.lottery.service;

import com.lottery.model.User;
import com.lottery.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserDestinationMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    User sourceToDestination(UserDTO source);

    UserDTO destinationToSource(User destination);
}
