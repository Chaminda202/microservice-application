package com.springboot.rentcar.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableOAuth2Sso // need to authenticate from oauth server
public class WebPortalApplication extends WebSecurityConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(WebPortalApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.mvcMatchers("/")
				.permitAll()
				.anyRequest()
				.authenticated();
	}
}
