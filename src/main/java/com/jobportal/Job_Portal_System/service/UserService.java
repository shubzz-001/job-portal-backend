package com.jobportal.Job_Portal_System.service;

import com.jobportal.Job_Portal_System.dto.UserResponseDto;
import com.jobportal.Job_Portal_System.model.Role;
import com.jobportal.Job_Portal_System.model.User;
import com.jobportal.Job_Portal_System.repository.UserRepository;
import com.jobportal.Job_Portal_System.specification.UserSpecification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    public UserService(UserRepository userRepository, FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
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

        Specification<User> spec = Specification.allOf(
            UserSpecification.hasName(name),
            UserSpecification.hasEmail(email),
            UserSpecification.hasRole(role)
        );

        return userRepository.findAll(spec, pageable)
            .map(user -> {
                UserResponseDto dto = new UserResponseDto();
                dto.setId(user.getId());
                dto.setName(user.getName());
                dto.setEmail(user.getEmail());
                dto.setRole(user.getRole().name());

                return dto;
            });
    }

    public void uploadProfileImage(Long userId, MultipartFile file) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String filePath = fileStorageService.saveFile(file);
        user.setProfileToken(filePath);

        userRepository.save(user);
    }
}