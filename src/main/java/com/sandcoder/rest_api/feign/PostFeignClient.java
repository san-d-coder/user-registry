package com.sandcoder.rest_api.feign;

import com.sandcoder.rest_api.beans.external.PostRequest;
import com.sandcoder.rest_api.beans.external.PostResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Feign client interface for interacting with the Post API.
 * Provides methods to retrieve and create posts via HTTP requests.
 */
@FeignClient(name = "postFeignClient", url = "${postFeignClient.api.url}")
public interface PostFeignClient {

    /**
     * Retrieves a post by its unique identifier.
     *
     * @param postId the ID of the post to retrieve
     * @return the {@link PostResponse} containing post details
     */
    @GetMapping("/posts/{id}")
    PostResponse getPostById(@PathVariable("id") String postId);

    /**
     * Retrieves all available posts.
     *
     * @return a list of {@link PostResponse} objects representing all posts
     */
    @GetMapping("/posts")
    List<PostResponse> getAllPosts();

    /**
     * Retrieves all posts created by a specific user.
     *
     * @param userId the ID of the user whose posts to retrieve
     * @return a list of {@link PostResponse} objects for the specified user
     */
    @GetMapping("/user/{userId}/posts")
    List<PostResponse> getPostByUserId(@PathVariable("userId") String userId);

    /**
     * Creates a new post using the provided request data.
     *
     * @param postRequest the {@link PostRequest} object containing post details
     * @return the {@link PostResponse} containing the created post details
     */
    @PostMapping("/posts")
    PostResponse createPost(@RequestBody PostRequest postRequest);

}

