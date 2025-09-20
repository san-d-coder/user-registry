package com.sandcoder.testing_demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    private String userId;
    private String userName;
    @JsonIgnore
    private String userPassword;
}
