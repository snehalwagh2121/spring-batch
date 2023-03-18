package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@Slf4j
public class SampleController {

    @Value("${message}")
    String defaultMsg;

    @GetMapping("/getmessage")
    public String loadMessage() {
        log.info("checking runtime config values: " + defaultMsg);
        return this.defaultMsg;
    }

}
