package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.models.TestModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PostMapping
    private ResponseEntity<String> save(@Valid @RequestBody TestModel testModel) {
        return ResponseEntity.ok("ok");
    }
}
