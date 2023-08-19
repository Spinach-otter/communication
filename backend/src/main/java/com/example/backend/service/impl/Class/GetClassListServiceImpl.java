package com.example.backend.service.impl.Class;

import com.example.backend.mapper.ClassesMapper;
import com.example.backend.pojo.Classes;
import com.example.backend.service.Class.GetClassListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetClassListServiceImpl implements GetClassListService {
    @Autowired
    private ClassesMapper classesMapper;
    @Override
    public List<Classes> getlist(Map<String, String> data) {
        String filter = data.get("filter");
        //没有条件,展示全部
        if(filter.equals("") || filter.length()==0)
            return classesMapper.selectList(null);
        else {
            return classesMapper.selectClassByClassName(filter);
        }

    }
}
