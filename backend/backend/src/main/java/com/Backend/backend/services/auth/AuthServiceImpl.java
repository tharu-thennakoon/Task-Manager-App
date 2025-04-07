package com.Backend.backend.services.auth;

import com.Backend.backend.dto.SignUprequest;
import com.Backend.backend.dto.UserDto;
import com.Backend.backend.enums.UserRole;
import com.Backend.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import com.Backend.backend.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount() {
        Optional<UserEntity> optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalUser.isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail("admin@test.com");
            user.setUsername("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Admin account created successfully");
        } else {
            System.out.println("Admin account already exists");
        }
    }

    @Override
    public UserDto signUpUser(SignUprequest signUprequest) {
        UserEntity user = new UserEntity();
        user.setEmail(signUprequest.getEmail());
        user.setUsername(signUprequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signUprequest.getPassword()));
        user.setUserRole(UserRole.EMPLOYEE);
        UserEntity createdUser = userRepository.save(user);
        return convertToDto(createdUser);
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    private UserDto convertToDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getUsername());
        userDto.setUserRole(user.getUserRole());
        return userDto;
    }
}