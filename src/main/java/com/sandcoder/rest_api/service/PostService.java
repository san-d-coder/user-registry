package com.sandcoder.rest_api.service;

import com.sandcoder.rest_api.beans.external.PostRequest;
import com.sandcoder.rest_api.beans.external.PostResponse;
import com.sandcoder.rest_api.feign.PostFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Slf4j
public class PostService {

    /**
     * Feign Client Implementation
     */
    @Autowired
    PostFeignClient postFeignClient;

    /**
     * Creates a new post using the provided request data.
     *
     * @param postRequest   the request object containing post details
     * @param transactionId the transaction identifier for logging
     * @return the response object containing created post details, or null if an error occurs
     */
    public PostResponse createPost(PostRequest postRequest, String transactionId) {
        PostResponse postResponse = null;
        StopWatch stopWatch = new StopWatch();
        try {
            log.info("Creating Post.");
            stopWatch.start();
            log.debug("Request to API - {}", postRequest);
            postResponse = postFeignClient.createPost(postRequest);
            log.debug("Response from API - {}", postResponse);
        } catch (Exception e) {
            log.error("An error occurred while creating Post - ", e);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
            log.info("Process ID - {} completed in {} seconds.", transactionId, totalTimeSeconds);
        }

        return postResponse;
    }


    /**
     * Retrieves a post by its ID.
     *
     * @param postId        the unique identifier of the post to retrieve
     * @param transactionId the transaction identifier for logging
     * @return the response object containing post details, or null if an error occurs
     */
    public PostResponse findById(String postId, String transactionId) {
        PostResponse postResponse = null;
        StopWatch stopWatch = new StopWatch();
        try {
            log.info("Retrieving Post with ID - {}.", postId);
            stopWatch.start();
            log.debug("Request to API - {}", postId);
            postResponse = postFeignClient.getPostById(postId);
            log.debug("Response from API - {}", postResponse);
        } catch (Exception e) {
            log.error("An error occurred while creating Post - ", e);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
            log.info("Process ID - {} completed in {} seconds.", transactionId, totalTimeSeconds);
        }

        return postResponse;
    }
}
