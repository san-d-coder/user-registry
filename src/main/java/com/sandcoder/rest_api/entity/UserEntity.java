package com.sandcoder.rest_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String userName;
    private String userPassword;
}
