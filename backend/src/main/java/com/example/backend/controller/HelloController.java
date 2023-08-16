package com.example.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HelloController {
    @RequestMapping("hello/")
    @PreAuthorize("hasAuthority('test')")
    public String hello() {
        return "hello";
    }
}
