package com.example.backend.controller.Class;

import com.example.backend.pojo.Classes;
import com.example.backend.service.Class.GetClassListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetClassList {
    @Autowired
    private GetClassListService getClassListService;
    //获取所有班级信息或者查询信息
    @GetMapping("/class/getlist/")
    public List<Classes> getlist(@RequestParam Map<String,String> data) {
        return getClassListService.getlist(data);
    }
}
