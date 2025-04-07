package com.Backend.backend.controller.auth;

import com.Backend.backend.dto.AuthenticationRequest;
import com.Backend.backend.dto.AuthenticationResponse;
import com.Backend.backend.dto.SignUprequest;
import com.Backend.backend.dto.UserDto;
import com.Backend.backend.entity.UserEntity;
import com.Backend.backend.repository.UserRepository;
import com.Backend.backend.services.auth.AuthService;
import com.Backend.backend.services.jwt.UserService;
import com.Backend.backend.utils.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
        final UserDetails userDetails = userService.userDetailService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<UserEntity> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwtToken = jwtUtil.genarateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isEmpty()) {
            throw new BadCredentialsException("Invalid username or password");
        }
        authenticationResponse.setJwt(jwtToken);
        authenticationResponse.setUserId(optionalUser.get().getId());
        authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        return authenticationResponse;
    }
}
