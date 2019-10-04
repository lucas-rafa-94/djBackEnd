package com.umcincoum.dj.controller.mongo;

import com.umcincoum.dj.model.canonical.ResponseCall;
import com.umcincoum.dj.model.mongoDb.PlaylistModel;
import com.umcincoum.dj.model.mongoDb.TrackSelectedModel;
import com.umcincoum.dj.service.mongo.PlaylistService;
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
    public ResponseCall putMusicOnPlaylist(@RequestParam String playlist, @RequestBody TrackSelectedModel trackSelectedModel){
       return playlistService.putTrackOnPlaylist(playlist, trackSelectedModel);
    }

    @GetMapping("/{playlist}")
    public PlaylistModel getPlaylistsFromId(@PathVariable String playlist){
        return playlistService.getPlaylistFromId(playlist);
    }

    @GetMapping("/top-10")

    public PlaylistModel getPlaylistsFromIdTop(){
        return playlistService.getPlaylistFromIdTop("jukeboxpop");
    }

    @PostMapping("/{playlist}/vote")
    public void voteOnMusicByPlaylist(@PathVariable String playlist, @RequestParam String email, @RequestParam String music){
        playlistService.voteOnMusicByPlaylist(music,playlist,email);
    }
}
