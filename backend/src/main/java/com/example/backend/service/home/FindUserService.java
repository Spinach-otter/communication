package com.example.backend.service.home;

import com.example.backend.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface FindUserService {
    List<UserInfo> getlist(Map<String, String> data);
}
