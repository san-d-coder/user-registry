package com.sandcoder.testing_demo.beans.external;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostRequest {
    private String userId;
    private String id;
    private String title;
    private String body;
}
