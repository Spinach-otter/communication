package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    public Integer selectIdByUserName(String str);
    List<User> getUsersInSameClassByUserID(Integer userId);
    List<User> selectUsersApplyingToClassByUserId(Integer classID);
}
