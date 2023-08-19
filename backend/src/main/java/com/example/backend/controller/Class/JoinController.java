package com.example.backend.controller.Class;

import com.example.backend.service.Class.JoinClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JoinController {
    @Autowired
    private JoinClassService joinClassService;
    @PostMapping("/class/join/")
    public Map<String,String> joinclass(@RequestParam Map<String,String> data) {
        return joinClassService.joinclass(data);
    }
}
