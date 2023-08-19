package com.example.backend.service.impl.Class;

import com.example.backend.mapper.ClassesMapper;
import com.example.backend.mapper.UserClassMapper;
import com.example.backend.mapper.UserRolesMapper;
import com.example.backend.pojo.Classes;
import com.example.backend.pojo.User;
import com.example.backend.pojo.userclasses;
import com.example.backend.pojo.userroles;
import com.example.backend.service.Class.AddClassService;
import com.example.backend.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddClassServiceImpl implements AddClassService {
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private UserClassMapper userClassMapper;
    @Override
    public Map<String, String> addclass(Map<String, String> data) {
        //同InfoServiceImpl一样 如果授权成功 则从上下文中将User信息提取出来
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        //拿data
        String classname=data.get("classname");
        String classinfo=data.get("classinfo");
        Integer createid=user.getId();

        Map<String,String> map=new HashMap<>();
        List<Classes> classes=classesMapper.selectClassByClassName(classname);
        if(!classes.isEmpty()) {
            map.put("error_message","班级已存在");
            return map;
        }
        Integer roleid=userRolesMapper.selectRoleIdByUserId(user.getId());
        if(roleid==2) {
            map.put("error_message","您已创建过班级，请勿再创建");
            return map;
        }

        //创建班级，升级Role，加入班级
        Classes classes1=new Classes(null,classname,classinfo, user.getId());
        classesMapper.insert(classes1);
        if(roleid==1) userRolesMapper.updateUserRoleToClassAdmin(user.getId());
        userclasses uc=new userclasses(null, user.getId(), classes1.getClassid());
        userClassMapper.insert(uc);
        map.put("error_message","success");
        return map;
    }
}
