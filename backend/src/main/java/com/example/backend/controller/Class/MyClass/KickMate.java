package com.example.backend.controller.Class.MyClass;

import com.example.backend.service.Class.MyClass.KickMateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class KickMate {
    @Autowired
    private KickMateService kickMateService;
    @PostMapping("/user/kick/")
    public Map<String,String> kickuser(@RequestParam Map<String,String> data) {
        return kickMateService.kickuser(data);
    }
}
