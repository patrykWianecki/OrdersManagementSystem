package com.app.exception;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ExceptionInfo {

    private ExceptionCode exceptionCode;
    private String description;
    private LocalDateTime dateTime;

    public ExceptionInfo(ExceptionCode exceptionCode, String description) {
        this.exceptionCode = exceptionCode;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }
}
