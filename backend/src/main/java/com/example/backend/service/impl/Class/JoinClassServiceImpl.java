package com.example.backend.service.impl.Class;

import com.example.backend.mapper.ApplicationsMapper;
import com.example.backend.mapper.ClassesMapper;
import com.example.backend.mapper.UserClassMapper;
import com.example.backend.pojo.Applications;
import com.example.backend.pojo.User;
import com.example.backend.pojo.userclasses;
import com.example.backend.service.Class.JoinClassService;
import com.example.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JoinClassServiceImpl implements JoinClassService {
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private UserClassMapper userClassMapper;
    @Autowired
    private ApplicationsMapper applicationsMapper;
    @Override
    public Map<String, String> joinclass(Map<String, String> data) {
        //同InfoServiceImpl一样 如果授权成功 则从上下文中将User信息提取出来
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String classname=data.get("classname");

        //err
        Map<String,String> map =new HashMap<>();
        List<userclasses> uc=userClassMapper.getUserClassesByUserID(user.getId());
        if(!uc.isEmpty()){
            map.put("error_message","您已有班级");
            return map;
        }
        List<Applications> applications=applicationsMapper.getApplicationsByUserID(user.getId());
        if(!applications.isEmpty()){
            map.put("error_message","您已申请班级");
            return map;
        }

        Integer classid=classesMapper.getClassIdByClassName(classname);
        Applications applications1=new Applications(null, user.getId(), classid);
        applicationsMapper.insert(applications1);
        map.put("error_message","success");
        return map;
    }
}
