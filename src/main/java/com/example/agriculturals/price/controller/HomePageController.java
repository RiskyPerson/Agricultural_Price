package com.example.agriculturals.price.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomePageController {
    @GetMapping
    public ResponseEntity<String> sayHelloFromHomepage(){
        return ResponseEntity.ok("Hello from homepage");
    }

}
