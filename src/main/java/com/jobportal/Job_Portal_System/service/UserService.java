package com.jobportal.Job_Portal_System.service;

import com.jobportal.Job_Portal_System.dto.UserResponseDto;
import com.jobportal.Job_Portal_System.model.User;
import com.jobportal.Job_Portal_System.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        return userRepository.findAll(pageable);
    }

    public Page<UserResponseDto> getUsersDto(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return userRepository.findAll(pageable).
                map(user -> {
                    UserResponseDto dto = new UserResponseDto();
                    dto.setId(user.getId());
                    dto.setName(user.getName());
                    dto.setEmail(user.getEmail());
                    dto.setRole(user.getRole().name());

                    return dto;
                });
    }
}
