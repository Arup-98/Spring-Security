package com.bjitacademy.securityModule.service.impl;

import com.bjitacademy.securityModule.entity.UserEntity;
import com.bjitacademy.securityModule.model.UserRequestModel;
import com.bjitacademy.securityModule.model.UserResponseModel;
import com.bjitacademy.securityModule.repository.UserRepository;
import com.bjitacademy.securityModule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return null;
    }

    @Override
    public ResponseEntity<Object> register(UserRequestModel requestModel) {
        UserEntity userEntity = UserEntity.builder()
                .email(requestModel.getEmail())
                .userName(requestModel.getUserName())
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .password(requestModel.getPassword())
                .build();
        UserEntity savedUserEntity = userRepository.save(userEntity);
        UserResponseModel responseModel = UserResponseModel.builder()
                .email(requestModel.getEmail())
                .userName(requestModel.getUserName())
                .firstName(requestModel.getFirstName())
               // .lastName(requestModel.getLastName())
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }
    public ResponseEntity<Object> getAll() {
        List<UserEntity> userEntityList=userRepository.findAll();
        List<UserResponseModel> userResponseList=new  ArrayList<>();
        for (UserEntity u:userEntityList){
            UserResponseModel responseModel = UserResponseModel.builder()
                    .email(u.getEmail())
                    .userName(u.getUserName())
                    .firstName(u.getFirstName())
                   // .lastName(u.getLastName())
                    .build();
            userResponseList.add(responseModel);
        }
        return new ResponseEntity<>(userResponseList,HttpStatus.ACCEPTED);
    }
}
