package com.jobportal.Job_Portal_System.controller;

import com.jobportal.Job_Portal_System.dto.UserResponseDto;
import com.jobportal.Job_Portal_System.model.User;
import com.jobportal.Job_Portal_System.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "id")String sortBy
    ) {
        return ResponseEntity.ok(userService.getUsers(page, size, sortBy));
    }

    @GetMapping("/dto")
    public ResponseEntity<Page<UserResponseDto>> getUsersDto(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "id")String sortBy
    ) {
        return ResponseEntity.ok(userService.getUsersDto(page, size, sortBy));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserResponseDto>> searchUsers(
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String email,
            @RequestParam(required = false)String role,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size
    ) {
        return ResponseEntity.ok(
            userService.searchUsers(name, email, null, page, size)
        );
    }

}
