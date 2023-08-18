package com.example.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.mapper.UserRolesMapper;
import com.example.backend.pojo.User;
import com.example.backend.pojo.userroles;
import com.example.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if(username == null){
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if(password == null || confirmedPassword == null){
            map.put("error_message","密码不能为空");
            return map;
        }
        username = username.trim();//trim() 方法用于删除字符串的头尾空白符
        if(username.length() == 0){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password.length() == 0 || confirmedPassword.length() == 0){
            map.put("error_message","密码不能为空");
            return map;
        }
        if(username.length() > 100){
            map.put("error_message","用户名长度过长");
            return map;
        }
        if(password.length() > 100 || confirmedPassword.length() > 100){
            map.put("error_message","密码长度过长");
            return map;
        }
        if(!password.equals(confirmedPassword)){
            map.put("error_message","两次输入的密码不一致");
            return map;
        }

        //查询用户是否已经存在 如果存在则不允许注册
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            map.put("error_message","用户名已存在");
            return map;
        }

        //满足以上的条件 添加新用户,同时设置权限为普通用户
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, username, encodedPassword,null,null,null,null);
        userMapper.insert(user);
        Integer userid=user.getId();
        userroles userRoles=new userroles(null,userid,1);
        userRolesMapper.insert(userRoles);
        map.put("error_message","success");
        return map;
    }
}