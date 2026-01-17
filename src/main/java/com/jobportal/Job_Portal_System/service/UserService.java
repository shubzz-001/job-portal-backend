package com.jobportal.Job_Portal_System.service;

import com.jobportal.Job_Portal_System.dto.UserResponseDto;
import com.jobportal.Job_Portal_System.model.Role;
import com.jobportal.Job_Portal_System.model.User;
import com.jobportal.Job_Portal_System.repository.UserRepository;
import com.jobportal.Job_Portal_System.specification.UserSpecification;

import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<UserResponseDto> searchUsers(
        String name,
        String email,
        Role role,
        int page,
        int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<User> spec = Specification
            .where(UserSpecification.hasName(name))
            .and(UserSpecification.hasEmail(email))
            .and(UserSpecification.hasRole(role));

        return userRepository.findAll(pageable)
            .map(user -> {
                UserResponseDto dto = new UserResponseDto();
                dto.setId(user.getId());
                dto.setName(user.getName());
                dto.setEmail(user.getEmail());
                dto.setRole(user.getRole().name());

                return dto;
            });
    }
}