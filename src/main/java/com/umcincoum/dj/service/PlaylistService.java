package com.umcincoum.dj.service;

import com.umcincoum.dj.model.mongoDb.EventModel;

import com.umcincoum.dj.model.mongoDb.PlaylistModel;
import com.umcincoum.dj.model.mongoDb.TrackSelectedModel;
import com.umcincoum.dj.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            playlist.setPlaylist(event.getId() + playlistList.get(i).trim());
            playlist.setTrackSelectedModelList(null);

            playlistRepository.insert(playlist);
        }
    }

    public List<PlaylistModel> getPlaylistsFromEvent(String event){
        return playlistRepository.findByEventModel_Id(event);
    }

    public void putTrackOnPlaylist(String playlist, TrackSelectedModel trackSelectedModel){
        PlaylistModel playlistModel = new PlaylistModel();
        playlistModel = playlistRepository.findByPlaylist(playlist);

        if(playlistModel != null){
            List<TrackSelectedModel> listTracks = new ArrayList<>();
            if (playlistModel.getTrackSelectedModelList() != null){
                listTracks = playlistModel.getTrackSelectedModelList();
                listTracks.add(trackSelectedModel);
            }else{
                listTracks.add(trackSelectedModel);
            }
            playlistModel.setTrackSelectedModelList(listTracks);
        }
        playlistRepository.save(playlistModel);
    }

    public PlaylistModel getPlaylistFromId(String playlist){
        return playlistRepository.findByPlaylist(playlist);
    }

//    public void voteOnMusicByPlaylist (String )
}
