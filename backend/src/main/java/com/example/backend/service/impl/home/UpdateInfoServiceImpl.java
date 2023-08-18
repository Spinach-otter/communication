package com.example.backend.service.impl.home;

import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import com.example.backend.service.home.UpdateInfoService;
import com.example.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateInfoServiceImpl implements UpdateInfoService {
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
        String newname = data.get("newname");
        String fullname = data.get("fullname");
        String phone = data.get("phone");
        String sex = data.get("sex");
        Integer updatedid=userMapper.selectIdByUserName(username);
        User user1=userMapper.selectById(updatedid);
        String major=user1.getMajor();


        Map<String,String> map = new HashMap<>();//定义return格式
        //System.out.println(user);
        System.out.println(data);

        //语法检查
        if(newname == null || newname.length() == 0){
            map.put("error_message","用户名不能为空");
            return map;
        }


        //更新
        Integer s;
        if(sex.equals("0")) s=0;
        else s=1;
        User new_user = new User(
                updatedid,
                newname,
                user1.getPassword(),
                major,
                fullname,
                s,
                phone
        );
        userMapper.updateById(new_user);

        //System.out.println(userMapper.selectById(4));
        map.put("error_message","success");
        return map;
    }
}
