package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.userclasses;

import java.util.List;

public interface UserClassMapper extends BaseMapper<userclasses> {
    List<userclasses> getUserClassesByUserID(Integer userId);
    int deleteUserFromClassByUsername(String username);
    int addUserToClassByUsername(String username);

}

