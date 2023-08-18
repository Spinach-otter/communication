package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.userroles;

public interface UserRolesMapper extends BaseMapper<userroles> {
    public Integer selectRoleIdByUserId(Integer id);
}
