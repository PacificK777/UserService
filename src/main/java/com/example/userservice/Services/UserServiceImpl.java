package com.example.userservice.Services;

import com.example.userservice.Models.Token;
import com.example.userservice.Models.User;
import com.example.userservice.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public User signUp(String name, String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        User user = null;
        if (optionalUser.isPresent()){
            //Navigate user to the login
        } else{
            //Create a new user object
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setHashedPassword(bCryptPasswordEncoder.encode(password));

            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public Token login(String email, String password) {
        return null;
    }

    @Override
    public User validateToken(String token) {
        return null;
    }

    @Override
    public void logout(String token) {

    }
}
