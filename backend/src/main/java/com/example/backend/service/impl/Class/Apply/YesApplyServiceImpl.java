package com.example.backend.service.impl.Class.Apply;

import com.example.backend.mapper.ApplicationsMapper;
import com.example.backend.mapper.UserClassMapper;
import com.example.backend.pojo.User;
import com.example.backend.service.Class.Apply.YesApplyService;
import com.example.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class YesApplyServiceImpl implements YesApplyService {
    @Autowired
    private ApplicationsMapper applicationsMapper;
    @Autowired
    private UserClassMapper userClassMapper;
    @Override
    public Map<String, String> applyyes(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        //System.out.println(data);
        //data
        String username=data.get("username");
        //加入班级
        userClassMapper.addUserToClassByUsername(username);
        //删除申请
        applicationsMapper.deleteApplicationsByUsername(username);
        Map<String,String> map = new HashMap<>();//定义return格式

        map.put("error_message","success");
        return map;
    }
}
