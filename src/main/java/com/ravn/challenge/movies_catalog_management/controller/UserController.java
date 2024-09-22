package com.ravn.challenge.movies_catalog_management.controller;

import com.ravn.challenge.movies_catalog_management.dto.UserDto;
import com.ravn.challenge.movies_catalog_management.model.User;
import com.ravn.challenge.movies_catalog_management.service.AuthenticationService;
import com.ravn.challenge.movies_catalog_management.dto.AuthRequestDto;
import com.ravn.challenge.movies_catalog_management.service.UserServiceImpl;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;
import com.ravn.challenge.movies_catalog_management.utils.RestTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/users")
@Tag(name = "User Management", description = "API for managing users")
public class UserController {

    @Operation(summary = "User Authentication", description = "Authenticate user with username and password")
    @ApiResponse(responseCode = "200", description = "The user has been authenticated successfully", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "403", description = "The user's password doesn't match with the database", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "404", description = "The user was not found in the database", content = {@Content(mediaType = "application/json")})
    @PostMapping("/auth")
    public RestTokenResponse authUser(@Valid @RequestBody AuthRequestDto authRequest) {

        RestTokenResponse response = new RestTokenResponse();
        authenticationService.doAuthentication(authRequest, response);

        return response;
    }

    @Operation(summary = "Create User", description = "Creates a new user with role `user`")
    @ApiResponse(responseCode = "200", description = "The user has been created successfully", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "409", description = "The username is already taken", content = {@Content(mediaType = "application/json")})
    @PostMapping("create")
    public GenericRestResponse<User> createUser(@Valid @RequestBody UserDto user){
        GenericRestResponse<User> response = new GenericRestResponse<>();
        userService.createUser(user, response);

        return response;
    }

    @Autowired
    public UserController(AuthenticationService authenticationService, UserServiceImpl userService){
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    private final AuthenticationService authenticationService;
    private final UserServiceImpl userService;
}
