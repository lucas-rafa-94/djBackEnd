package com.umcincoum.dj.service;

import com.umcincoum.dj.model.mongoDb.EventModel;
import com.umcincoum.dj.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public EventModel getByName(String name){
        return eventRepository.findByName(name);
    }

    public void createEvent(EventModel eventModel){
        eventRepository.insert(eventModel);
    }

    public List<EventModel> getAllEvents(){
        return eventRepository.findAll();
    }
}
