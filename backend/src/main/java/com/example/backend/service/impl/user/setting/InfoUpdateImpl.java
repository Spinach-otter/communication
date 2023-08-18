package com.example.backend.service.impl.user.setting;

import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import com.example.backend.service.impl.UserDetailsImpl;
import com.example.backend.service.user.setting.InfoUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoUpdateImpl implements InfoUpdate {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> update(Map<String, String> data) {
        //1. 获取User
        //同InfoServiceImpl一样 如果授权成功 则从上下文中将User信息提取出来
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        //2. 获取data内容
        String username = data.get("username");
        String major = data.get("major");
        String fullname = data.get("fullname");
        String phone = data.get("phone");
        String sex = data.get("sex");

        Map<String,String> map = new HashMap<>();//定义return格式

        //语法检查
        if(username == null || username.length() == 0){
            map.put("error_message","用户名不能为空");
            return map;
        }
        System.out.println(user);
        System.out.println(data);

        //更新
        Integer s;
        if(sex.equals("0")) s=0;
        else s=1;
        User new_user = new User(
                user.getId(),
                username,
                user.getPassword(),
                major,
                fullname,
                s,
                phone
        );
        userMapper.updateById(new_user);

        System.out.println(userMapper.selectById(4));
        map.put("error_message","success");
        return map;
    }
}
