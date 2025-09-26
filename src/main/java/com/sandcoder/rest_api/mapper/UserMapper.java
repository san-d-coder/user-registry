package com.sandcoder.rest_api.mapper;

import com.sandcoder.rest_api.beans.domain.User;
import com.sandcoder.rest_api.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToUser(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity mapFromUser(User user);
}
