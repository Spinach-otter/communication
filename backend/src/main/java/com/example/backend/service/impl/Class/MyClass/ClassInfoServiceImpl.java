package com.example.backend.service.impl.Class.MyClass;

import com.example.backend.mapper.ClassesMapper;
import com.example.backend.pojo.Classes;
import com.example.backend.pojo.User;
import com.example.backend.service.Class.MyClass.ClassInfoService;
import com.example.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {
    @Autowired
    private ClassesMapper classesMapper;
    @Override
    public Map<String,String> myclassinfo() {
        //同InfoServiceImpl一样 如果授权成功 则从上下文中将User信息提取出来
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Classes classes=classesMapper.selectClassByUserID(user.getId());
        Map<String,String> map=new HashMap<>();
        map.put("classname",classes.getClassname());
        map.put("classinfo",classes.getClassinfo());
        return map;
    }
}
