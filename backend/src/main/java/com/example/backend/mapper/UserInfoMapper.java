package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    List<UserInfo> selectUserInfo();
    List<UserInfo> selectUserInfoByName(String str);
    List<UserInfo> selectUserInfoByClass(String str);
    List<UserInfo> selectUserInfoByUserName(String str);
}
