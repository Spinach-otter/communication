package com.example.backend.service.impl.Class.Apply;

import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import com.example.backend.service.Class.Apply.ApplyListService;
import com.example.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyListServiceImpl implements ApplyListService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> applylist() {
        //同InfoServiceImpl一样 如果授权成功 则从上下文中将User信息提取出来
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        return userMapper.selectUsersApplyingToClassByUserId(user.getId());
    }
}
