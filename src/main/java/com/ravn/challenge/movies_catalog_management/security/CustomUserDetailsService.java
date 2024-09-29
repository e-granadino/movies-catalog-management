package com.ravn.challenge.movies_catalog_management.security;

import com.ravn.challenge.movies_catalog_management.exception.UserNotFoundException;
import com.ravn.challenge.movies_catalog_management.model.User;
import com.ravn.challenge.movies_catalog_management.service.UserServiceImpl;
import com.ravn.challenge.movies_catalog_management.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    static final Logger logger = LogManager.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUserName(username);
        if(user.isPresent()){
            User userFound = user.get();

            return org.springframework.security.core.userdetails.User.builder()
                    .username(userFound.getUserName())
                    .password(userFound.getPassword())
                    .roles(getRoles(userFound))
                    .build();

        } else {
            throw new UserNotFoundException(username);
        }
    }

    private String[] getRoles(User user) {
        if(user.getRole().equals(Constants.ADMIN_ROLE)){
            return new String[]{Constants.USER_ROLE, Constants.ADMIN_ROLE};
        }
        return new String[]{Constants.USER_ROLE};
    }

    @Autowired
    public CustomUserDetailsService(UserServiceImpl userService){
        this.userService = userService;
    }
    private final UserServiceImpl userService;
}
