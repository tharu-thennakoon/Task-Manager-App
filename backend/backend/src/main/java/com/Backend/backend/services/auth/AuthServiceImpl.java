package com.Backend.backend.services.auth;

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
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
        Optional<UserEntity> optionalUser =userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalUser.isEmpty()){
            UserEntity user = new UserEntity();
            user.setEmail("admin@test.com");
            user.setUsername("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Admin account created successfully");
        }else{
            System.out.println("Admin account already exists");
        }
    }
}
