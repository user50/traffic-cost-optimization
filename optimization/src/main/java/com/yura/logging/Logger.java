package com.yura.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public enum  Logger {
    LOGGER;

    public void info(String message){
//        log(message);
    }

    public void error(String message)
    {
        log(message);
    }

    private void log(String message)
    {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("info.txt", true ));

            printWriter.println(message);

            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            java.util.logging.Logger.getAnonymousLogger().info(e.getMessage());
        }
    }
}
