package com.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponseDTO {
    private ResponseStatus responseStatus;
    private String message;
}
