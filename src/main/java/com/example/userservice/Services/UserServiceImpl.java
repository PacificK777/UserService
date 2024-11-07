package com.example.userservice.Services;

import com.example.userservice.Exceptions.InvalidPasswordException;
import com.example.userservice.Exceptions.UserNotFoundException;
import com.example.userservice.Models.Token;
import com.example.userservice.Models.User;
import com.example.userservice.Repositories.TokenRepository;
import com.example.userservice.Repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
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
    public Token login(String email, String password) throws InvalidPasswordException, UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = null;
        if(optionalUser.isEmpty()){
            //call the signup method
            throw new UserNotFoundException("User not found with the provided email.");
        }else{
            user = optionalUser.get();
            if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
                //through exception
                throw new InvalidPasswordException("Invalid Password !!");
            }
            //generate the token
            Token token = createToken(user);
            token = tokenRepository.save(token);
            return token;
        }
    }
    private Token createToken(User user){
        Token token = new Token();
        token.setUser(user);
        //create random string of specific number of characters using library apache lang 3 commons
        token.setValue(RandomStringUtils.randomAlphabetic(50));
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysFromInputDate = today.plus(30, ChronoUnit.DAYS);

        //convert the local date into date format
        Date expiryAt = Date.from(thirtyDaysFromInputDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); //no need to remember, use copy paste
        token.setExpiryAt(expiryAt);
        return token;
    }

    @Override
    public User validateToken(String token) {
        return null;
    }

    @Override
    public void logout(String token) {

    }
}
