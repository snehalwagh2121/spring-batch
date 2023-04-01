package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
@Slf4j
@RequestMapping("/service1")
public class SampleController {

    @Value("${message}")
    String defaultMsg;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getmessage")
    public String loadMessage() {
        log.info("checking runtime config values: " + defaultMsg);
        return this.defaultMsg;
    }

    @GetMapping("/getmessagefromservice2")
    public String loadMessageFromService2() {
        log.info("calling service 2 /message api");
        String msg= restTemplate.getForObject(
                "http://localhost:9011/service2/message/",
                String.class
        );
        return msg;
    }

}
