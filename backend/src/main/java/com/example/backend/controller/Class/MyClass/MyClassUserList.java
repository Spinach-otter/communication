package com.example.backend.controller.Class.MyClass;

import com.example.backend.pojo.User;
import com.example.backend.service.Class.MyClass.MyClassUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MyClassUserList {
    @Autowired
    private MyClassUserListService myClassUserListService;
    @GetMapping("/myclass/userlist/")
    public List<User> getlist(){
        return myClassUserListService.getlist();
    }
}
