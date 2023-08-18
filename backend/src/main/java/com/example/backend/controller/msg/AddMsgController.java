package com.example.backend.controller.msg;

import com.example.backend.service.msg.AddMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddMsgController {
    @Autowired
    private AddMsg  addMsg;
    @PostMapping("/add/msg/")
    public Map<String,String> addmsg(@RequestParam Map<String,String> data) {
        return addMsg.addmsg(data);
    }
}
