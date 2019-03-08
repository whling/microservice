package com.whl.microservice.configserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @GetMapping("/config")
    public ResponseEntity<String> config(){
        return ResponseEntity.ok("config server");
    }
}
