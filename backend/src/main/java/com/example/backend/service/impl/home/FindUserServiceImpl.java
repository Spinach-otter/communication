package com.example.backend.service.impl.home;

import com.example.backend.mapper.UserInfoMapper;
import com.example.backend.pojo.User;
import com.example.backend.pojo.UserInfo;
import com.example.backend.service.home.FindUserService;
import com.example.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FindUserServiceImpl implements FindUserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getlist(Map<String, String> data) {


        //获取data内容
        String op = data.get("op");
        String filter = data.get("filter");

        //没有条件,展示全部
        if(filter.equals("") || filter.length()==0)
        return userInfoMapper.selectUserInfo();

        if(op.equals("1")) {//如果按要求搜索
            return userInfoMapper.selectUserInfoByName(filter);
        } else if(op.equals("3")) {
            return userInfoMapper.selectUserInfoByClass(filter);
        } else if (op.equals("2")) {
            return userInfoMapper.selectUserInfoByUserName(filter);
        }
        return null;
    }
}
