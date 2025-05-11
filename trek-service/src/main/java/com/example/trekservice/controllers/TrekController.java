package com.example.trekservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treks")
public class TrekController {
    @PostMapping
    public String hello(){
        return "hello";
    }
}
