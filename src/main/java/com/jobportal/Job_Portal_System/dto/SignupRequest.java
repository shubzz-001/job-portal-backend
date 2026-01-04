package com.jobportal.Job_Portal_System.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import com.jobportal.Job_Portal_System.model.Role;



@Getter
@Setter
public class SignupRequest {

    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Role role;
}