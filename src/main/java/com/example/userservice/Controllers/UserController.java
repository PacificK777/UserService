package com.example.userservice.Controllers;

import com.example.userservice.DTOs.*;
import com.example.userservice.Models.User;
import com.example.userservice.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    public LoginResponseDTO login(LoginRequestDTO requestDTO){
        return null;
    }
    @PostMapping("/signup")
    public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO requestDTO){
        User user = userService.signUp(
                requestDTO.getName(),
                requestDTO.getEmail(),
                requestDTO.getPassword()
        );
        SignUpResponseDTO responseDTO = new SignUpResponseDTO();
        if(user != null) {
            responseDTO.setUser(user);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } else{
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
    public UserDTO validateToken(ValidateTokenRequestDTO requestDTO){
        return null;
    }
    public LoginResponseDTO logout(LoginRequestDTO requestDTO){
        return null;
    }

}
