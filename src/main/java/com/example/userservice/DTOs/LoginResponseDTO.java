package com.example.userservice.DTOs;

import com.example.userservice.Models.Token;
import com.example.userservice.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private Token token;
    private ResponseStatus responseStatus;
    private String message;
    private String redirectURL;
}
