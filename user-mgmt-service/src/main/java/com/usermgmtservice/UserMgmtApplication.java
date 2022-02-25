package com.usermgmtservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ahmed
 */
@SpringBootApplication
public class UserMgmtApplication implements WebMvcConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(UserMgmtApplication.class);

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(UserMgmtApplication.class, args);
		logger.info("Spring applicaiton boot started successfully");
	}
}
