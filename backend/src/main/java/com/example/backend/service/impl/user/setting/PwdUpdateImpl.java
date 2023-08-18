package com.example.backend.service.impl.user.setting;

import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import com.example.backend.service.impl.UserDetailsImpl;
import com.example.backend.service.user.setting.PwdUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PwdUpdateImpl implements PwdUpdate {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> update(Map<String, String> data) {
        //1. 获取User
        //同InfoServiceImpl一样 如果授权成功 则从上下文中将User信息提取出来
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        //2. 获取data内容
        String pwd = data.get("pwd");
        String newpwd = data.get("newpwd");
        //System.out.println(data);

        Map<String,String> map = new HashMap<>();//定义return格式

        //语法检查
        if(pwd == null || pwd.length() == 0){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(!pwd.equals(newpwd)) {
            System.out.println("err");
            map.put("error_message","密码输入不一致");
            return map;
        }


        //更新
        User new_user = new User(
                user.getId(),
                user.getUsername(),
                passwordEncoder.encode(newpwd),
                user.getMajor(),
                user.getFullname(),
                user.getSex(),
                user.getPhonenumber()
        );
        userMapper.updateById(new_user);
        map.put("error_message","success");
        return map;

    }
}
