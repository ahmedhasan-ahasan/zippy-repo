package com.partnerstoreservice.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class PartnerStoreManagementSecurityConfigurer extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(PartnerStoreManagementSecurityConfigurer.class);
	@Autowired
	private Environment env;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String username = null;
		String password = null;

		username = env.getProperty("partnerstoreservice.api.security.username");
		password = env.getProperty("partnerstoreservice.api.security.password");
		logger.info("The password of the restful service is {}", password);
		auth.inMemoryAuthentication().withUser(username).password(password).authorities("apiuser");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		boolean csrf = true;

		csrf = Boolean.getBoolean(env.getProperty("partnerstoreservice.api.security.csrf"));
		// http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
		http.httpBasic().and().authorizeHttpRequests().anyRequest().authenticated();
		if (csrf == false) {
			http.csrf().disable();
		}
	}


}
