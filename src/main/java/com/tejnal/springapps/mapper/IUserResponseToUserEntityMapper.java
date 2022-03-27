package com.tejnal.springapps.mapper;

import com.tejnal.springapps.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface IUserResponseToUserEntityMapper {

  @Mappings({
    @Mapping(target = "user_id", source = "userResponse.userId"),
    @Mapping(target = "name", source = "userResponse.name"),
    @Mapping(target = "blog", source = "userResponse.blog")
  })
  com.tejnal.springapps.entity.User mapOrderResponseToOrderEntity(UserResponse userResponse);
}
