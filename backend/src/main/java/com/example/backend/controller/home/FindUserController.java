package com.example.backend.controller.home;

import com.example.backend.pojo.UserInfo;
import com.example.backend.service.home.FindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FindUserController {
    @Autowired
    private FindUserService findUserService;
    @GetMapping("/user/getlist/")
    public List<UserInfo> find(@RequestParam Map<String,String> data) {
        return findUserService.getlist(data);
    }
}
