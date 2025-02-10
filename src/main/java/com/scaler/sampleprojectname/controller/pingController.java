package com.scaler.sampleprojectname.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pingController {

    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
}
