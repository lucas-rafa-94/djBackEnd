package com.umcincoum.dj.controller;

import com.umcincoum.dj.model.mongoDb.PlaylistModel;
import com.umcincoum.dj.model.mongoDb.TrackSelectedModel;
import com.umcincoum.dj.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public void putMusicOnPlaylist(@RequestParam String playlist, @RequestBody TrackSelectedModel trackSelectedModel){
        playlistService.putTrackOnPlaylist(playlist, trackSelectedModel);
    }

    @GetMapping("/{playlist}")
    public PlaylistModel getPlaylistsFromId(@PathVariable String playlist){
        return playlistService.getPlaylistFromId(playlist);
    }

    @PostMapping("/{playlist}/vote")
    public void voteOnMusicByPlaylist(@PathVariable String playlist, @RequestParam String email, @RequestParam String music){

    }
}
