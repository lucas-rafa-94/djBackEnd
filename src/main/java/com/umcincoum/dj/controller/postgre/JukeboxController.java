package com.umcincoum.dj.controller.postgre;


import com.umcincoum.dj.model.postgre.TbJukeboxArtist;
import com.umcincoum.dj.model.postgre.TbJukeboxMusic;
import com.umcincoum.dj.service.postgre.TbJukeboxArtistService;
import com.umcincoum.dj.service.postgre.TbJukeboxMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jukebox")
public class JukeboxController {
    @Autowired
    TbJukeboxMusicService tbJukeboxMusicService;

    @Autowired
    TbJukeboxArtistService tbJukeboxArtistService;

    @PostMapping
    public void saveMusic(List<TbJukeboxMusic> tbJukeboxMusic){
        tbJukeboxMusicService.saveAll(tbJukeboxMusic);
    }
    @GetMapping("/artist")
    public List<TbJukeboxArtist> getByArtistAndPlaylist(@RequestParam String name, @RequestParam String playlist){
        return tbJukeboxArtistService.findByPlaylistAndArtist(playlist,name.toUpperCase());
    }

    @GetMapping("/tracks")
    public List<TbJukeboxMusic> getTracksByArtist(@RequestParam String artist, @RequestParam String playlist){
        return tbJukeboxMusicService.queryByArtist(artist,playlist);
    }

    @GetMapping("/tracks/filter")
    public List<TbJukeboxMusic> getTracksFilterByArtist(@RequestParam String artist, @RequestParam String music, @RequestParam String playlist){
        return tbJukeboxMusicService.queryByArtistFilterByMusic(artist,music,playlist);
    }

    @GetMapping("/playlist/{playlist}/artists")
    public List<TbJukeboxArtist> getByArtistsByPlaylist(@PathVariable String playlist){
        return tbJukeboxArtistService.findArtistsByPlaylist(playlist);
    }

    @GetMapping("/playlist/{playlist}/musics")
    public List<TbJukeboxMusic> getByMusicsByPlaylist(@PathVariable String playlist){
        return tbJukeboxMusicService.queryByPlaylist(playlist);
    }

    @GetMapping("/playlist/{playlist}/filter")
    public List<TbJukeboxMusic> getByMusicsByPlaylist(@PathVariable String playlist, @RequestParam String music){
        return tbJukeboxMusicService.queryByPlaylistFilterMusic(playlist, music);
    }
}
