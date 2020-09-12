package com.tutorials.ar.localstack.exception;

public class BucketResourceException extends RuntimeException {

    public BucketResourceException(String message) {
        super(message);
    }

    public BucketResourceException(String message, Exception exception) {
        super(message, exception);
    }
}
