package com.annztech.rightcareApi.ENUM;


public enum StatusCode {
    firstNameEmpty(1001, "First name cannot be empty"),
    firstNameCannotBeANumber(1002, "First name cannot have number"),
    firstNameonlyAlphabets(1003 , "First name can have only alphabets"),
    invalidUserUuid(1004 , "please enter a valid userUuid"),
    userNotFound(1005 , "user with the given Uuid not found"),
    ERROR(1001, "Error"),
    NOT_FOUND(1002, "Not Found");

    private final int code;
    private final String message;


    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

