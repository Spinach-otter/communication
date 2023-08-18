package com.example.backend.controller.home;

import com.example.backend.service.home.UpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateInfoController {
    @Autowired
    private UpdateInfoService updateInfoService;
    @PostMapping("/update/info/")
    @PreAuthorize("hasAuthority('alterOtherInfo')")
    public Map<String, String> update (@RequestParam Map<String,String> data){
        return updateInfoService.update(data);
    }
}
