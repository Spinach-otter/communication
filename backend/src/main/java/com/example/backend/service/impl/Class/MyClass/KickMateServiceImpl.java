package com.example.backend.service.impl.Class.MyClass;

import com.example.backend.mapper.UserClassMapper;
import com.example.backend.service.Class.MyClass.KickMateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KickMateServiceImpl implements KickMateService {
    @Autowired
    private UserClassMapper userClassMapper;
    @Override
    public Map<String, String> kickuser(Map<String, String> data) {
        String username=data.get("username");
        int tmp=userClassMapper.deleteUserFromClassByUsername(username);
        System.out.println(tmp);
        Map<String,String> map =new HashMap<>();
        map.put("error_message","success");
        return map;
    }
}
