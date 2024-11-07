package com.example.userservice.Services;

import com.example.userservice.Models.Token;
import com.example.userservice.Models.User;

public interface UserService {
    User signUp(String name, String email, String password);

    Token login(String email, String password);

    User validateToken(String token);

    void logout(String token);
}
