package com.springboot.rentcar.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Bean
    public TokenStore jdbcTokenStore(){
        return new JdbcTokenStore(this.dataSource);
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //.checkTokenAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetails) throws Exception {
        clientDetails
                .jdbc(this.dataSource)
                .passwordEncoder(this.passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoint) throws Exception {
        endpoint
                .tokenStore(jdbcTokenStore())
                .authenticationManager(this.authenticationManager);
    }
}
