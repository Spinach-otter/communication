package com.example.backend.controller.Class;

import com.example.backend.service.Class.AddClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddClassController {
    @Autowired
    private AddClassService addClassService;
    @PostMapping("/class/add/")
    public Map<String,String> addclass(@RequestParam Map<String,String> data) {
        return addClassService.addclass(data);
    }
}
