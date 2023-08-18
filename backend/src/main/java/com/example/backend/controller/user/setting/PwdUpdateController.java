package com.example.backend.controller.user.setting;

import com.example.backend.service.user.setting.PwdUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PwdUpdateController {
    @Autowired
    private PwdUpdate pwdUpdate;
    @PostMapping ("/setting/pwd/")
    public Map<String, String> update(@RequestParam Map<String,String> data) {
        return pwdUpdate.update(data);
    }
}
