package com.umcincoum.dj.controller;

import com.umcincoum.dj.model.mongoDb.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AccessController {

   @Autowired
   User

    @PostMapping
    public void createUser(@RequestBody UserModel user){

    }
}
