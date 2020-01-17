package com.springboot.rentcar.mapper;

import lombok.Data;

import java.util.List;

@Data
public class AccessToken {
    private String user_name;
    private List<String> scope;
    private boolean isEnabled;
    private long exp;
    private List<String> authorities;
    private String email;
    private String client_id;
}
