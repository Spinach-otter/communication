package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.Classes;

import java.util.List;

public interface ClassesMapper extends BaseMapper<Classes> {
    public List<Classes> selectClassByClassName(String str);
    void deleteClassByClassName(String className);
    Integer getClassIdByClassName(String className);
    Classes selectClassByUserID(Integer userID);

}
