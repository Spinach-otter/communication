package com.example.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String fullname;
    private String classname;
    private String username;
    private Integer Sex;
    private String phonenumber;
}
