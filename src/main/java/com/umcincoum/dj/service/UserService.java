package com.umcincoum.dj.service;

import com.umcincoum.dj.model.canonical.ResponseCall;
import com.umcincoum.dj.model.mongoDb.EventModel;
import com.umcincoum.dj.model.mongoDb.UserModel;
import com.umcincoum.dj.repository.EventRepository;
import com.umcincoum.dj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventService eventService;

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

    public UserModel updateEventUserWithEventBody(String email, EventModel event){

        UserModel userQuery = new UserModel();

        if (userRepository.findByEmail(email) != null) {

            List<EventModel> listEvents = new ArrayList<>();

            userQuery = userRepository.findByEmail(email);

            if(userQuery.getEvents() != null){
                listEvents = userQuery.getEvents();
                listEvents.add(event);
                userQuery.setEvents(listEvents);
            }else{
                listEvents.add(event);
                userQuery.setEvents(listEvents);
            }
            userRepository.save(userQuery);

        }else{
            userQuery = null;
        }

        return userQuery;
    }

    public UserModel updateEventUserWithEventId(String email, String name){

        UserModel userQuery = new UserModel();
        EventModel eventQuery = new EventModel();
        boolean exists = false;


        if (userRepository.findByEmail(email) != null) {
            eventQuery = eventService.getByName(name);

            List<EventModel> listEvents = new ArrayList<>();
            userQuery = userRepository.findByEmail(email);

            if(userQuery.getEvents() != null){
                for(int i = 0; i < userQuery.getEvents().size(); i++ ){
                    if(userQuery.getEvents().get(i).getName().equals(eventQuery.getName())){
                        exists = true;
                    }
                }
                if(!exists){
                    listEvents.add(eventQuery);
                    userQuery.setEvents(listEvents);
                }
            }else{
                listEvents.add(eventQuery);
                userQuery.setEvents(listEvents);
            }
            userRepository.save(userQuery);

        }else{
            userQuery = null;
        }

        return userQuery;
    }


}
