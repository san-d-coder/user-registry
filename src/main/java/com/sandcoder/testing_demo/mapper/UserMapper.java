package com.sandcoder.testing_demo.mapper;

import com.sandcoder.testing_demo.domain.User;
import com.sandcoder.testing_demo.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToUser(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity mapFromUser(User user);
}
