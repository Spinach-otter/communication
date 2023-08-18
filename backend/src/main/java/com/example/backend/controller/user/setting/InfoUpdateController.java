package com.example.backend.controller.user.setting;

import com.example.backend.service.user.setting.InfoUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoUpdateController {
    @Autowired
    private InfoUpdate infoUpdate;
    @PostMapping ("/setting/info/")
    public Map<String, String> update(@RequestParam Map<String,String> data) {
        return infoUpdate.update(data);
    }
}
