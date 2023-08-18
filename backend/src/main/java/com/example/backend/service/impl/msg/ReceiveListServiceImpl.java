package com.example.backend.service.impl.msg;

import com.example.backend.mapper.MessagesMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.Messages;
import com.example.backend.pojo.User;
import com.example.backend.pojo.msg;
import com.example.backend.service.impl.UserDetailsImpl;
import com.example.backend.service.msg.ReceiveListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReceiveListServiceImpl implements ReceiveListService {
    @Autowired
    private MessagesMapper messagesMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<msg> getlist() {
        //1. 获取User
        //同InfoServiceImpl一样 如果授权成功 则从上下文中将User信息提取出来
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        //System.out.println(user.getId());
        //System.out.println(messagesMapper.selectMsgBySendId(user.getId()));
        List<Messages> listmsg = messagesMapper.selectMsgByReceiveId(user.getId());
        List<msg> res=new ArrayList<>();
        for(Messages m:listmsg) {
            String txt=m.getMessagecontent();
            Date time=m.getTimestamp();
            String name=userMapper.selectById(m.getSenderid()).getUsername();
            msg x=new msg(name,txt,time);
            res.add(x);
        }
        return res;
    }
}
