package com.example.backend.service.impl.msg;

import com.example.backend.mapper.MessagesMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.Messages;
import com.example.backend.pojo.User;
import com.example.backend.service.impl.UserDetailsImpl;
import com.example.backend.service.msg.AddMsg;
import com.google.protobuf.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddMsgImpl implements AddMsg {
    @Autowired
    private MessagesMapper messagesMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> addmsg(Map<String, String> data) {
        //1. 获取User
        //同InfoServiceImpl一样 如果授权成功 则从上下文中将User信息提取出来
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        //2. 获取data内容
        String username = data.get("username");
        String messagecontent = data.get("messagecontent");
        //System.out.println(username);
        Map<String,String> map = new HashMap<>();//定义return格式

        //语法检查
        if(messagecontent== null || messagecontent.length() == 0){
            map.put("error_message","留言不能为空");
            return map;
        }
        if(messagecontent.length() > 100){
            map.put("error_message","长度不能大于100");
            return map;
        }

        //3. 创建Bot对象
        Date now = new Date();
        Integer receiveid=userMapper.selectIdByUserName(username);
        //System.out.println(receiveid);
        //id自增 因此不用传
        //创建时间和修改时间一致
        Messages messages = new Messages(null,user.getId(),receiveid,messagecontent,now);
        messagesMapper.insert(messages);
        map.put("error_message","success");
        return map;

    }
}
