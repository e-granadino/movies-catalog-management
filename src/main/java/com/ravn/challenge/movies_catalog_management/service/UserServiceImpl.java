package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.dto.UserDto;
import com.ravn.challenge.movies_catalog_management.model.User;
import com.ravn.challenge.movies_catalog_management.repository.UserRepository;
import com.ravn.challenge.movies_catalog_management.utils.Constants;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    public Optional<User> findByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }

    public void createUser(UserDto userDto, GenericRestResponse<User> response) {
        Optional<User> user = userRepository.findByUserName(userDto.getUserName());

        if(user.isEmpty()){
            UserDto sessionUser = sessionDataService.getUserSession();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            ModelMapper mapper = new ModelMapper();

            User userToSave = mapper.map(userDto, User.class);
            userToSave.setPassword(encoder.encode(userDto.getPassword()));
            userToSave.setRole(Constants.USER_ROLE); // Admin role is not supported yet

            if(sessionUser != null){
                userToSave.setCreatedBy(sessionUser.getUserName());
            }

            User userSaved = userRepository.save(userToSave);

            response.setResponse(userSaved);
            response.setMessage("The user was created successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatus(Constants.SUCCESS_RESPONSE);
        } else {
            response.setMessage("The username already exist");
            response.setStatusCode(HttpStatus.CONFLICT.value());
            response.setStatus(Constants.FAIL_RESPONSE);
        }
    }

    @Autowired
    public UserServiceImpl(SessionDataService sessionDataService, UserRepository userRepository){
        this.sessionDataService = sessionDataService;
        this.userRepository = userRepository;
    }


    private final UserRepository userRepository;

    private final SessionDataService sessionDataService;

}
