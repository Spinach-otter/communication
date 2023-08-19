package com.example.backend.controller.Class;

import com.example.backend.service.Class.DelClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DelClass {
    @Autowired
    private DelClassService delClassService;
    @PostMapping("/class/del/")
    @PreAuthorize("hasAuthority('deleteUser')")
    public Map<String,String> delclass(@RequestParam Map<String,String> data) {
        return delClassService.delclass(data);
    }
}
