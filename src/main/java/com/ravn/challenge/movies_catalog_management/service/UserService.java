package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.dto.UserDto;
import com.ravn.challenge.movies_catalog_management.model.User;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;

import java.util.Optional;


public interface UserService {
    Optional<User> findByUserName(String userName);

    GenericRestResponse<User> createUser(UserDto user);
}
