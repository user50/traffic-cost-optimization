package com.yura.http;

public class Not200OkException extends RuntimeException{

    public Not200OkException(String message) {
        super(message);
    }
}
