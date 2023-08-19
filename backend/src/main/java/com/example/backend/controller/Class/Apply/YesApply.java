package com.example.backend.controller.Class.Apply;

import com.example.backend.service.Class.Apply.YesApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class YesApply {
    @Autowired
    private YesApplyService yesApplyService;
    @PostMapping("/apply/yes/")
    public Map<String,String> applyyes(@RequestParam Map<String,String> data) {
        return yesApplyService.applyyes(data);
    }
}
