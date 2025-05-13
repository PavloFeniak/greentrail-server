package com.example.trekservice.controllers;

import com.example.trekservice.entity.models.Treks;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treks")
public class TrekController {
    @PostMapping
    public Treks getTreks(Treks treks) {
        return treks;
    }
}
