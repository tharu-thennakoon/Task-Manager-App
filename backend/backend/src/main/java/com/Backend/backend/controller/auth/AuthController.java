package com.Backend.backend.controller.auth;

import com.Backend.backend.dto.AuthenticationResponse;
import com.Backend.backend.dto.SignUprequest;
import com.Backend.backend.dto.UserDto;
import com.Backend.backend.repository.UserRepository;
import com.Backend.backend.services.auth.AuthService;
import com.Backend.backend.services.jwt.UserService;
import com.Backend.backend.utils.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final UserRepository userRepository;

    private final JWTUtil jwtUtil;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, UserRepository userRepository, JWTUtil jwtUtil, UserService userService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpRequest(@RequestBody SignUprequest signUprequest) {
        if (authService.hasUserWithEmail(signUprequest.getEmail()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exist with this email");
            UserDto createdUserDto = authService.signUpUser(signUprequest);

        if (createdUserDto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User creation failed");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);

    }

    public AuthenticationResponse login(@RequestBody)

}
