package com.umcincoum.dj.controller;

import com.umcincoum.dj.model.mongoDb.PlaylistModel;
import com.umcincoum.dj.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("playlist")
public class PlaylistController {

    @Autowired
    PlaylistService playlistService;

    @GetMapping()
    public List<PlaylistModel> getPlaylistsFromEvent(@RequestParam String event){
        return playlistService.getPlaylistsFromEvent(event);
    }
}
