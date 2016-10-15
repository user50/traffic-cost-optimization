package com.yura;

public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException(String message) {
        super(message);
    }
}
