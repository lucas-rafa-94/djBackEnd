package com.umcincoum.dj.service.mongo;

import com.umcincoum.dj.model.canonical.ResponseCall;
import com.umcincoum.dj.model.mongoDb.EventModel;
import com.umcincoum.dj.model.mongoDb.UserModel;
import com.umcincoum.dj.repository.mongo.UserRepository;
import com.umcincoum.dj.service.mongo.EventService;
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
            if (userRepository.findByEmail(userModel.getEmail().toLowerCase()) == null) {
                List<EventModel> listEvent = new ArrayList<>();

                userModel.setEvents(listEvent);

                UserModel userModel1 = new UserModel();
                userModel1.setEmail(userModel.getEmail().toLowerCase());
                userModel1.setPassword(userModel.getPassword());

                userRepository.save(userModel1);

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
            if (userRepository.findByEmailAndAndPassword(userModel.getEmail().toLowerCase(), userModel.getPassword()) == null) {
                userModelReceived = null;
            }else{
                userModelReceived = userRepository.findByEmailAndAndPassword(userModel.getEmail().toLowerCase(), userModel.getPassword());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return userModelReceived;
    }

    public UserModel getUserFromEmail(String email){
        return userRepository.findByEmail(email.toLowerCase());
    }

    public UserModel updateEventUserWithEventBody(String email, EventModel event){

        UserModel userQuery = new UserModel();

        if (userRepository.findByEmail(email.toLowerCase()) != null) {

            List<EventModel> listEvents = new ArrayList<>();

            userQuery = userRepository.findByEmail(email.toLowerCase());

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


        if (userRepository.findByEmail(email.toLowerCase()) != null) {
            eventQuery = eventService.getByName(name);

            List<EventModel> listEvents = new ArrayList<>();
            userQuery = userRepository.findByEmail(email.toLowerCase());

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

    public void updatePassword(UserModel userModel){
        UserModel userModelQuery = userRepository.findByEmail(userModel.getEmail().toLowerCase());
        userModelQuery.setPassword(userModel.getPassword());;
        userRepository.save(userModelQuery);
    }


}
