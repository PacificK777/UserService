package com.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.NestingKind;

@Getter
@Setter
public class ValidateTokenRequestDTO {
    private String token;
}
