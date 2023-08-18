package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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

}
