package com.springboot.rentcar.config;

import com.springboot.rentcar.mapper.AccessToken;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {
    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        jwtAccessTokenConverter.setAccessTokenConverter(this);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
        AccessToken accessTokenMapper = new AccessToken();
        if(map.get("email") != null){
            accessTokenMapper.setEmail((String) map.get("email"));
        }
        if(map.get("user_name") != null){
            accessTokenMapper.setUser_name((String) map.get("user_name"));
        }
        if(map.get("client_id") != null){
            accessTokenMapper.setClient_id((String) map.get("client_id"));
        }
        if(map.get("scope") != null){
            accessTokenMapper.setScope((List<String>) map.get("scope"));
        }
        List<String> authorities = new ArrayList<>();
        oAuth2Authentication.getAuthorities().forEach(auth -> {
            authorities.add(auth.getAuthority());
        });
        accessTokenMapper.setAuthorities(authorities);
        accessTokenMapper.setExp((Long)map.get("exp"));
        accessTokenMapper.setEnabled((Boolean) map.get("isEnabled"));
        oAuth2Authentication.setDetails(accessTokenMapper);
        return oAuth2Authentication;
    }
}
