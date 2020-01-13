package com.springboot.rentcar.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test")
                .password(this.passwordEncoder.encode("password"))
                .roles("USER", "ADMIN")
                .authorities("READ", "WRITE", "DELETE", "APPROVE");

    }
}
