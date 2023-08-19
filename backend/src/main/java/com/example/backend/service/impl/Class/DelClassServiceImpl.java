package com.example.backend.service.impl.Class;

import com.example.backend.mapper.ClassesMapper;
import com.example.backend.service.Class.DelClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DelClassServiceImpl implements DelClassService {
    @Autowired
    private ClassesMapper classesMapper;
    @Override
    public Map<String, String> delclass(Map<String, String> data) {
        String classname=data.get("classname");
        classesMapper.deleteClassByClassName(classname);
        Map<String,String> map =new HashMap<>();
        map.put("error_message","success");
        return map;
    }
}
