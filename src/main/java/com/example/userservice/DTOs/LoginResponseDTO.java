package com.example.userservice.DTOs;

import com.example.userservice.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String token;
    private ResponseStatus responseStatus;
}
