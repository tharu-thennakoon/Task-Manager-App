package com.Backend.backend.services.auth;

import com.Backend.backend.dto.SignUprequest;
import com.Backend.backend.dto.UserDto;

public interface AuthService {

    UserDto signUpUser(SignUprequest signUprequest);

    boolean hasUserWithEmail(String email);
}
