package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * ErrorMessage
 */
public class ErrorMessage {
    private int errorCode;
    private HttpStatus errorStatus;
    private String errorMessage;

    public ErrorMessage() {
    }

    public ErrorMessage(int errorCode, HttpStatus errorStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(HttpStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
