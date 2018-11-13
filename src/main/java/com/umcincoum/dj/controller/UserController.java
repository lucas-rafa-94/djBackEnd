package com.umcincoum.dj.controller;

import com.umcincoum.dj.model.canonical.ResponseCall;
import com.umcincoum.dj.model.mongoDb.EventModel;
import com.umcincoum.dj.model.mongoDb.UserModel;
import com.umcincoum.dj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseCall createUser(@RequestBody UserModel user){
        return userService.insertUser(user);
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel user){
        return userService.loginUser(user);
    }

    @PostMapping("/{email}/event")
    public UserModel updateEventUser (@PathVariable String email, @RequestBody EventModel event){
        return userService.updateEventUser(email, event);
    }

    @GetMapping("/{email}")
    public UserModel getUserFromEmail (@PathVariable String email){
        return userService.getUserFromEmail(email);
    }


}
