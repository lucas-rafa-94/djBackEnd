package com.umcincoum.dj.service;

import com.umcincoum.dj.model.canonical.ResponseCall;
import com.umcincoum.dj.model.mongoDb.EventModel;
import com.umcincoum.dj.model.mongoDb.UserModel;
import com.umcincoum.dj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseCall insertUser(UserModel userModel){
        ResponseCall responseCall = new ResponseCall();
        try {
            if (userRepository.findByEmail(userModel.getEmail()) == null) {
                List<EventModel> listEvent = new ArrayList<>();

                userModel.setEvents(listEvent);

                userRepository.save(userModel);

                responseCall.setStatus("Success");
                responseCall.setDescription("Usuário criado com sucesso!");

            }else{

                responseCall.setStatus("Failed");
                responseCall.setDescription("Email já cadastrado!");

            }
        }catch (Exception e){
            e.printStackTrace();
            responseCall.setStatus("Failed");
            responseCall.setDescription("Falha Interna");
        }
        return responseCall;
    }

    public UserModel loginUser(UserModel userModel){
        UserModel userModelReceived = new UserModel();

        try {
            if (userRepository.findByEmailAndAndPassword(userModel.getEmail(), userModel.getPassword()) == null) {
                userModelReceived = null;
            }else{
                userModelReceived = userRepository.findByEmailAndAndPassword(userModel.getEmail(), userModel.getPassword());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return userModelReceived;
    }

    public UserModel getUserFromEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserModel updateEventUser(String email, EventModel event){

        UserModel userQuery = new UserModel();

        if (userRepository.findByEmail(email) != null) {

            List<EventModel> listEvents = new ArrayList<>();
            userQuery = userRepository.findByEmail(email);
            listEvents = userQuery.getEvents();
            listEvents.add(event);
            userQuery.setEvents(listEvents);

            userRepository.save(userQuery);

        }else{
            userQuery = null;
        }

        return userQuery;
    }
}
