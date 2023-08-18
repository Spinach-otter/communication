package com.example.backend.controller;

import com.example.backend.pojo.Messages;
import com.example.backend.pojo.msg;
import com.example.backend.service.msg.GetSendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetSendMsgListController {
    @Autowired
    private GetSendListService getSendListService;
    @GetMapping("/msg/getlist/")
    public List<msg> getlist() {
        return getSendListService.getlist();
    }
}
