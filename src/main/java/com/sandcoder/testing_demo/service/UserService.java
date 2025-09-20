package com.sandcoder.testing_demo.service;

import com.sandcoder.testing_demo.domain.User;
import com.sandcoder.testing_demo.entity.UserEntity;
import com.sandcoder.testing_demo.mapper.UserMapper;
import com.sandcoder.testing_demo.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service @Slf4j @Data
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public User getUserById(String transactionId, String userId){
        User user = null;
        if(StringUtils.hasText(userId)) {
            log.info("Retrieving User with user id - {}", userId);
            try {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                UserEntity userEntity = userRepository.findById(UUID.fromString(userId)).stream().findFirst().orElse(null);
                if(userEntity != null){
                    user = userMapper.mapToUser(userEntity);
                }
            } catch (Exception exception){
                log.error("Exception occurred trying to retrieve user - ", exception);
            }
        }

        return user;
    }

}
