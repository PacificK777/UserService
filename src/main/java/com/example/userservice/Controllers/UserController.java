package com.example.userservice.Controllers;

import com.example.userservice.DTOs.*;
import com.example.userservice.Services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    public LoginResponseDTO login(LoginRequestDTO requestDTO){
        return null;
    }
    public SignUpResponseDTO signUp(SignUpRequestDTO requestDTO){
        return null;
    }
    public UserDTO validateToken(ValidateTokenRequestDTO requestDTO){
        return null;
    }
    public LoginResponseDTO logout(LoginRequestDTO requestDTO){
        return null;
    }

}
