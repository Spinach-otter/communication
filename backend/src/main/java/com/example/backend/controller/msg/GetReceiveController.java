package com.example.backend.controller.msg;

import com.example.backend.pojo.msg;
import com.example.backend.service.msg.ReceiveListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetReceiveController {
    @Autowired
    private ReceiveListService receiveListService;
    @GetMapping("/msg/getreceive/")
    public List<msg> getlist(){
        return receiveListService.getlist();
    }
}
