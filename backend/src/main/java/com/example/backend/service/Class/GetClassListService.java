package com.example.backend.service.Class;

import com.example.backend.pojo.Classes;

import java.util.List;
import java.util.Map;

public interface GetClassListService {
    public List<Classes> getlist(Map<String,String> data);
}
