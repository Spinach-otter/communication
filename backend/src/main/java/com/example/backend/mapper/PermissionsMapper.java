package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.Permissions;

import java.util.List;

public interface PermissionsMapper extends BaseMapper<Permissions> {
    List<String> selectPermsByUserId(Integer id);
}
