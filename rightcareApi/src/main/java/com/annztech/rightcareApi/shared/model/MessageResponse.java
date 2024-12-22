package com.annztech.rightcareApi.shared.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MessageResponse {
    private Status status;

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public static class Status {
        private int code;
        private String message;
    }
}

