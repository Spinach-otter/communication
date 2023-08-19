package com.example.backend.service.impl.Class.MyClass;

import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import com.example.backend.service.Class.MyClass.MyClassUserListService;
import com.example.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyClassUserListServiceImpl implements MyClassUserListService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getlist() {
        //同InfoServiceImpl一样 如果授权成功 则从上下文中将User信息提取出来
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        return userMapper.getUsersInSameClassByUserID(user.getId());
    }
}
