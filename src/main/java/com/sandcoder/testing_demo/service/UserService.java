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

    /**
     * find User by ID from DB.
     * @param userId userId to be searched with.
     * @return User identified by the userId.
     */
    public User getUserById(String userId){
        User user = null;
        StopWatch stopWatch = new StopWatch();
        if(StringUtils.hasText(userId)) {
            log.info("Retrieving User with user id - {}", userId);
            try {
                stopWatch.start();
                UserEntity userEntity = userRepository.findById(UUID.fromString(userId)).stream().findFirst().orElse(null);
                if(userEntity != null){
                    user = userMapper.mapToUser(userEntity);
                }
            } catch (Exception exception){
                log.error("Exception occurred trying to retrieve user - ", exception);
            } finally {
                if(stopWatch.isRunning()){
                    stopWatch.stop();
                    log.info("Time taken to retrieve user with user id - {} is {} ms", userId, stopWatch.getTotalTimeMillis());
                }
            }
        }
        return user;
    }

    /**
     * Create user entry in the DB
     * @param user User object sent in the request
     * @return Saved User object
     */
    public User createUser(User user){
        log.info("Creating User process started.");
        User user1 = null;
        StopWatch stopWatch = new StopWatch();
        if(user != null){
            try {
                stopWatch.start();
                UserEntity userEntity = userMapper.mapFromUser(user);
                UserEntity savedEntity = userRepository.save(userEntity);
                user1 = userMapper.mapToUser(savedEntity);
            } catch (Exception e) {
                log.error("Exception occurred while creating User object - ", e);
            } finally {
                if(stopWatch.isRunning()){
                    stopWatch.stop();
                    log.info("Create User method completed in {} seconds", stopWatch.getTotalTimeSeconds());
                }
            }
        }
        return user1;
    }

}
