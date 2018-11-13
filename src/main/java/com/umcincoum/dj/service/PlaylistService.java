package com.umcincoum.dj.service;

import com.umcincoum.dj.model.mongoDb.EventModel;
import com.umcincoum.dj.model.mongoDb.PlaylistModel;
import com.umcincoum.dj.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    PlaylistRepository playlistRepository;

    public void createPlaylistByEvent(List<String> playlistList, EventModel event){

        for(int i = 0; i < playlistList.size(); i++){
            PlaylistModel playlist = new PlaylistModel();

            playlist.setEventModel(event);
            playlist.setGenre(playlistList.get(i));
            playlist.setId(event.getId() + playlistList.get(i).trim());
            playlist.setTrackSelectedModelList(null);

            playlistRepository.insert(playlist);
        }
    }

    public List<PlaylistModel> getPlaylistsFromEvent(String event){
        return playlistRepository.findByEventModel_Id(event);
    }
}
