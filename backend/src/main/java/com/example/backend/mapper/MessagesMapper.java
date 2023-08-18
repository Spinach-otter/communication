package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.Messages;

import java.util.List;

public interface MessagesMapper extends BaseMapper<Messages> {
    public List<Messages> selectMsgBySendId(Integer id);
    public List<Messages> selectMsgByReceiveId(Integer id);
}
