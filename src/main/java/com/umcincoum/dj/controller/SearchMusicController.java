package com.umcincoum.dj.controller;

import com.umcincoum.dj.model.ArtistsModel;
import com.umcincoum.dj.model.TracksModel;
import com.umcincoum.dj.service.SearchMusicService;
import com.umcincoum.dj.service.postgre.TbJukeboxMusicService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/search-music")
public class SearchMusicController {

    @Autowired
    SearchMusicService searchMusicService;

    @Autowired
    TbJukeboxMusicService tbJukeboxMusicService;

    @GetMapping
    public List<ArtistsModel> getArtists(@RequestParam("artists") String artist){
        return searchMusicService.getArtists(artist);
    }

    @GetMapping("/top-tracks/{id}")
    public List<TracksModel> getTopTracksFromArtist(@PathVariable("id") String id){
        return searchMusicService.getTopTracksFromArtist(id);
    }

    @GetMapping("/playlist/{id}")
    public void loadPlaylist(@PathVariable("id") String id, @RequestParam int offset){
        tbJukeboxMusicService.loadPlaylist(id, offset);
    }

    @GetMapping("/artist")
    public List<TracksModel> getTracksFromArtist(@RequestParam("name") String name, @RequestParam("track") String track){
        return searchMusicService.getTracksFromArtistWithTrack(name,track);
    }

    @GetMapping("/by-track")
    public List<TracksModel> getTracksFromArtist(@RequestParam("track") String track){
        return searchMusicService.getTracks(track);
    }



}
