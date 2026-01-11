package com.jobportal.Job_Portal_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class JobPortalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalSystemApplication.class, args);
	}

}
