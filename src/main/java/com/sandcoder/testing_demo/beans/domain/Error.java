package com.sandcoder.testing_demo.beans.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Error {
    String transactionId;
    String errorCode;
    String errorMessage;
}
