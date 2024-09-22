package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.dto.UserSessionDto;
import com.ravn.challenge.movies_catalog_management.exception.UserNotFoundException;
import com.ravn.challenge.movies_catalog_management.model.User;
import com.ravn.challenge.movies_catalog_management.dto.AuthRequestDto;
import com.ravn.challenge.movies_catalog_management.security.JwtService;
import com.ravn.challenge.movies_catalog_management.utils.Constants;
import com.ravn.challenge.movies_catalog_management.utils.RestTokenResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    static final Logger logger = LogManager.getLogger(AuthenticationService.class);

    public void doAuthentication(AuthRequestDto authRequest, RestTokenResponse response) {

        Optional<User> users = userService.findByUserName(authRequest.getUsername());

        // Checks if user exist on the database
        if(users.isEmpty()){
            throw new UserNotFoundException(authRequest.getUsername());
        }

        // Checks if the password entered matches with the database password
        User userMatch = users.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(authRequest.getPassword(), userMatch.getPassword())) {
            // Saving users data to session
            UserSessionDto userSessionDto = new UserSessionDto();
            userSessionDto.setRole(userMatch.getRole());
            userSessionDto.setUserName(userMatch.getUserName());
            sessionDataService.setUserSession(userSessionDto);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if(authentication.isAuthenticated()){
                String token = jwtService.generateToken(userMatch.getUserName(), userMatch.getRole());

                response.setToken(token);
                response.setMessage("The user has been authenticated successfully...");
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatus(Constants.SUCCESS_RESPONSE);
            } else {
                response.setMessage("There was an issue while authenticating the user");
                response.setStatusCode(HttpStatus.FORBIDDEN.value());
                response.setStatus(Constants.FAIL_RESPONSE);
            }
        } else {
            response.setMessage("The password doesn't match with the database record");
            response.setStatus(Constants.SUCCESS_RESPONSE);
            response.setStatusCode(HttpStatus.FORBIDDEN.value());
        }
    }

    @Autowired
    public AuthenticationService(UserServiceImpl userService, SessionDataService sessionDataService,
                                 JwtService jwtService, AuthenticationManager authenticationManager){
        this.jwtService = jwtService;
        this.userService = userService;
        this.sessionDataService = sessionDataService;
        this.authenticationManager = authenticationManager;

    }

    private final UserServiceImpl userService;

    private final SessionDataService sessionDataService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
}
