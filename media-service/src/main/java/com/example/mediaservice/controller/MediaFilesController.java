package com.example.mediaservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media")
public class MediaFilesController {

    @GetMapping
    public String hello(){
        return "Hello World";
    }
}
