package com.springboot.rentcar.rent.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;

public class AccessTokenUtil {

    public static HttpHeaders createTokenHeader(){
        HttpHeaders header = new HttpHeaders();
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return header;
    }
}
