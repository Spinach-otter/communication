package com.example.backend.service.impl.home;

import com.example.backend.mapper.UserMapper;
import com.example.backend.service.home.DelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DelUserImpl implements DelUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> remove(Map<String, String> data) {
        Integer id=userMapper.selectIdByUserName(data.get("username"));
        userMapper.deleteById(id);
        Map<String,String> map=new HashMap<>();
        map.put("error_message","success");
        return map;
    }
}
