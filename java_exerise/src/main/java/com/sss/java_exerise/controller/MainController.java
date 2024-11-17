package com.sss.java_exerise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/v1")
public class MainController {


    @GetMapping("/time")
    public ResponseEntity<String> getTime() {
        String url = "http://worldtimeapi.org/api/timezone/Asia/Karachi";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
