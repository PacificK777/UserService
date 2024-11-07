package com.example.userservice.Controllers;

import com.example.userservice.DTOs.*;
import com.example.userservice.Exceptions.InvalidPasswordException;
import com.example.userservice.Exceptions.UserNotFoundException;
import com.example.userservice.Models.Token;
import com.example.userservice.Models.User;
import com.example.userservice.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO requestDTO) throws UserNotFoundException, InvalidPasswordException {
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        try {
            Token token = userService.login(
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );
            responseDTO.setToken(token);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("Login Successful");
        }
        catch(UserNotFoundException e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage("User not found. Redirecting to singup.");
            responseDTO.setRedirectURL("/signup");
        } catch (InvalidPasswordException e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage("Invalid password");
        }
        return responseDTO;
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
            responseDTO.setMessage("User created successfully");
        } else{
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage("Email id might already be present");
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
