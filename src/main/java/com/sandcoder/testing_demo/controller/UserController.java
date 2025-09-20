package com.sandcoder.testing_demo.controller;

import com.sandcoder.testing_demo.domain.Error;
import com.sandcoder.testing_demo.domain.User;
import com.sandcoder.testing_demo.service.UserService;
import com.sandcoder.testing_demo.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("userId") String userId){
        String transactionId = appUtils.getTraceId();
        if(!StringUtils.hasText(transactionId)) {
            transactionId = UUID.randomUUID().toString();
        }
        ResponseEntity<?> userResponseEntity;
        User user = userService.getUserById(transactionId, userId);
        if(user == null){
            Error error = new Error(transactionId, String.valueOf(HttpStatus.NOT_FOUND), "User not found.");
            userResponseEntity = new ResponseEntity<>(
                    error,
                    HttpStatus.NOT_FOUND);
        } else{
            userResponseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        }
        return userResponseEntity;
    }

}
