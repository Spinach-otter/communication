package com.example.backend;

import com.example.backend.pojo.UserInfo;
import com.example.backend.service.home.FindUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("stpass1"));
        System.out.println(passwordEncoder.encode("class_adminpass"));
        System.out.println(passwordEncoder.encode("adminpass"));
        System.out.println(passwordEncoder.encode("pa"));
    }

//    @Autowired
//    private FindUserService findUserService;
//    @Test
//    void getlist() {
//        Map<String,String> mp = new HashMap<>();
//        mp.put("op","");
//        mp.put("filter","");
//        List<Map<String,String>> list = findUserService.getlist(mp);
//        System.out.println(list);
//    }

}
