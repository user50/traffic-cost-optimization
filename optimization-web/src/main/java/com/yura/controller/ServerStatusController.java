package com.yura.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yevhen
 */
@RestController
public class ServerStatusController {
    private static final String STATUS_RUNNING = "Server is working";

    @RequestMapping("/status")
    public String status() {
        return STATUS_RUNNING;
    }

}
