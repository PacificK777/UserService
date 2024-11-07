package com.example.userservice.DTOs;

import com.example.userservice.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {
    private User user;
    private ResponseStatus responseStatus;
    private String message;
}
