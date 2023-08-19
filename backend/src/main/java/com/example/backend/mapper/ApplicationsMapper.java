package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.Applications;

import java.util.List;

public interface ApplicationsMapper extends BaseMapper<Applications> {
    List<Applications> getApplicationsByUserID(Integer userId);
    int deleteApplicationsByUsername(String username);


}
