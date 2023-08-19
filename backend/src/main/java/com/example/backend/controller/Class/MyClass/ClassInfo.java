package com.example.backend.controller.Class.MyClass;

import com.example.backend.pojo.Classes;
import com.example.backend.service.Class.MyClass.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ClassInfo {
    @Autowired
    private ClassInfoService classInfoService;
    @GetMapping("/myclass/info/")
    public Map<String,String> myclassinfo(){
        //System.out.println(classInfoService.myclassinfo());
        return classInfoService.myclassinfo();
    }
}
