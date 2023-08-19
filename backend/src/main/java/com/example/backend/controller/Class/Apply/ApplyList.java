package com.example.backend.controller.Class.Apply;

import com.example.backend.pojo.User;
import com.example.backend.service.Class.Apply.ApplyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplyList {
    @Autowired
    private ApplyListService applyListService;
    @GetMapping("/apply/list/")
    public List<User> applylist(){
        return applyListService.applylist();
    }
}
