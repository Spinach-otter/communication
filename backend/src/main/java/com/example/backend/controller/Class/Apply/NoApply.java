package com.example.backend.controller.Class.Apply;

import com.example.backend.pojo.User;
import com.example.backend.service.Class.Apply.ApplyNoService;
import com.example.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NoApply {
    @Autowired
    private ApplyNoService applyNoService;
    @PostMapping("/apply/no/")
    public Map<String,String> applyno(@RequestParam Map<String,String> data) {
        return applyNoService.applyno(data);
    }
}
