package com.umcincoum.dj.service;

import com.umcincoum.dj.model.mongoDb.UserModel;
import com.umcincoum.dj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void insertUser(UserModel userModel){
        userRepository.save(userModel);
    }
}
