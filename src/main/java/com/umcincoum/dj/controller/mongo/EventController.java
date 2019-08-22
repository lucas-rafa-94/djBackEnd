package com.umcincoum.dj.controller.mongo;

import com.umcincoum.dj.model.mongoDb.EventModel;
import com.umcincoum.dj.service.mongo.EventService;
import com.umcincoum.dj.service.mongo.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    PlaylistService playlistService;

    @PostMapping
    public void createEvent(@RequestBody EventModel event){
        eventService.createEvent(event);
        playlistService.createPlaylistByEvent(event.getPlaylists(), event);
    }
    @GetMapping
    public List<EventModel> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{name}")
    public EventModel getEventByName(@PathVariable String name){
        return eventService.getByName(name);
    }
}
