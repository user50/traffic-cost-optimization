package com.yura.logging;

public enum  Logger {
    LOGGER;

    public void log(String message){
        java.util.logging.Logger.getAnonymousLogger().info(message);
    }
}
