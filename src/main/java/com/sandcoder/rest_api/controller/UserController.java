package com.sandcoder.rest_api.controller;

import com.sandcoder.rest_api.beans.domain.Error;
import com.sandcoder.rest_api.beans.domain.User;
import com.sandcoder.rest_api.service.UserService;
import com.sandcoder.rest_api.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping(path = "/find/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("userId") String userId){
        String transactionId = appUtils.getTraceId();
        if(!StringUtils.hasText(transactionId)) {
            transactionId = UUID.randomUUID().toString();
        }
        ResponseEntity<?> userResponseEntity;
        User user = userService.getUserById(userId);
        if(user == null){
            log.error("User not found.");
            Error error = new Error(transactionId, String.valueOf(HttpStatus.NOT_FOUND), "User not found.");
            userResponseEntity = new ResponseEntity<>(
                    error,
                    HttpStatus.NOT_FOUND);
        } else{
            userResponseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        }
        return userResponseEntity;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user){
        String transactionId = appUtils.getTraceId();
        if(!StringUtils.hasText(transactionId)) {
            transactionId = UUID.randomUUID().toString();
        }
        ResponseEntity<?> userResponseEntity;
        User user1 = userService.createUser(user);

        if(user1 == null){
            log.error("User could not be created.");
            Error error = new Error(transactionId, String.valueOf(HttpStatus.NOT_FOUND), "User not found.");
            userResponseEntity = new ResponseEntity<>(
                    error,
                    HttpStatus.NOT_FOUND);
        } else{
            userResponseEntity = new ResponseEntity<>(user1, HttpStatus.OK);
        }

        return userResponseEntity;
    }

}
