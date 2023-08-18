package com.example.backend.controller.home;

import com.example.backend.service.home.DelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DelUser {
    @Autowired
    private DelUserService delUserService;
    @PostMapping("/user/del/")
    @PreAuthorize("hasAuthority('deleteUser')")
    public Map<String,String> remove (@RequestParam Map<String, String> data){
        //data中只保存dele_user_name的信息
        return delUserService.remove(data);
    }
}
