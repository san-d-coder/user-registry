package com.sandcoder.rest_api.controller;


import com.sandcoder.rest_api.beans.domain.Error;
import com.sandcoder.rest_api.beans.external.PostRequest;
import com.sandcoder.rest_api.beans.external.PostResponse;
import com.sandcoder.rest_api.service.PostService;
import com.sandcoder.rest_api.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for managing posts.
 * Provides endpoints to create a new post and retrieve a post by its ID.
 */
@RestController
@RequestMapping("/api/v1/post")
@Slf4j
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    AppUtils appUtils;

    /**
     * Creates a new post using the provided request data.
     *
     * @param postRequest the {@link PostRequest} object containing post details
     * @return a {@link ResponseEntity} containing the created post details or an error response
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostRequest postRequest) {
        String transactionId = appUtils.getTraceId();
        if (!StringUtils.hasText(transactionId)) {
            transactionId = UUID.randomUUID().toString();
        }
        ResponseEntity<?> postResponseEntity;
        PostResponse postResponse = postService.createPost(postRequest, transactionId);

        if (postResponse == null) {
            log.error("Post could not be created.");
            Error error = new Error(transactionId, String.valueOf(HttpStatus.NOT_FOUND), "User not found.");
            postResponseEntity = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            postResponseEntity = new ResponseEntity<>(postResponse, HttpStatus.OK);
        }

        return postResponseEntity;
    }

    /**
     * Retrieves a post by its unique identifier.
     *
     * @param postId the ID of the post to retrieve
     * @return a {@link ResponseEntity} containing the post details or an error response
     */
    @PostMapping("/find/{postId}")
    public ResponseEntity<?> findPostById(@PathVariable("postId") String postId) {
        String transactionId = appUtils.getTraceId();
        if (!StringUtils.hasText(transactionId)) {
            transactionId = UUID.randomUUID().toString();
        }
        ResponseEntity<?> postResponseEntity;
        PostResponse postResponse = postService.findById(postId, transactionId);

        if (postResponse == null) {
            log.error("Post with ID {} could not be retrieved.", postId);
            Error error = new Error(transactionId, String.valueOf(HttpStatus.NOT_FOUND), "User not found.");
            postResponseEntity = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            postResponseEntity = new ResponseEntity<>(postResponse, HttpStatus.OK);
        }

        return postResponseEntity;
    }


}
