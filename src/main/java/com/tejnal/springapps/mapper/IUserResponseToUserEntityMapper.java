package com.tejnal.springapps.mapper;

import com.tejnal.springapps.entity.Person;
import com.tejnal.springapps.model.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;



@Mapper
public interface IUserResponseToUserEntityMapper {

  @Mappings({
          @Mapping(target = "personId", source = "personResponse.personId"),
          @Mapping(target = "name", source = "personResponse.name"),
          @Mapping(target = "blog", source = "personResponse.blog")
  })
  Person mapOrderResponseToOrderEntity(PersonResponse personResponse);

}
