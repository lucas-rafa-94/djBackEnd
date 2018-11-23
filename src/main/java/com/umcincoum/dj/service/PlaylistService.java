package com.umcincoum.dj.service;

import com.umcincoum.dj.model.canonical.ResponseCall;
import com.umcincoum.dj.model.mongoDb.EventModel;

import com.umcincoum.dj.model.mongoDb.PlaylistModel;
import com.umcincoum.dj.model.mongoDb.TrackSelectedModel;
import com.umcincoum.dj.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public ResponseCall putTrackOnPlaylist(String playlist, TrackSelectedModel trackSelectedModel){
        ResponseCall responseCall = new ResponseCall();
        PlaylistModel playlistModel = new PlaylistModel();
        playlistModel = playlistRepository.findByPlaylist(playlist);

        if(playlistModel != null){
            List<TrackSelectedModel> listTracks = new ArrayList<>();
            if (playlistModel.getTrackSelectedModelList() != null){
                for(int i = 0; i<playlistModel.getTrackSelectedModelList().size(); i++){
                    if(playlistModel.getTrackSelectedModelList().get(i).getMusic().equals(trackSelectedModel.getMusic())){
                        responseCall.setStatus("ERRO");
                        responseCall.setDescription("JA EXISTENTE");
                        return responseCall;
                    }
                }
                listTracks = playlistModel.getTrackSelectedModelList();
                listTracks.add(trackSelectedModel);
            }else{
                listTracks.add(trackSelectedModel);
            }
            playlistModel.setTrackSelectedModelList(listTracks);
        }
        playlistRepository.save(playlistModel);

        responseCall.setStatus("SUCESSO");
        responseCall.setDescription("SUCESSO");

        return responseCall;
    }

    public PlaylistModel getPlaylistFromId(String playlist){

        PlaylistModel playlistModel = new PlaylistModel();
        playlistModel = playlistRepository.findByPlaylist(playlist);
    if(playlistModel.getTrackSelectedModelList() != null){
        List<TrackSelectedModel> trackSelectedModelList = playlistModel.getTrackSelectedModelList();

        Collections.sort(trackSelectedModelList,
                Comparator.comparingInt(TrackSelectedModel::getLikes).reversed());

        playlistModel.setTrackSelectedModelList(trackSelectedModelList);
    }


        return playlistModel;
    }

    public void voteOnMusicByPlaylist (String music, String playlist, String email ){
        PlaylistModel playlistModel = new PlaylistModel();
        playlistModel = playlistRepository.findByPlaylist(playlist);

        for(int i = 0; i < playlistModel.getTrackSelectedModelList().size(); i++){
            if (playlistModel.getTrackSelectedModelList().get(i).getMusic().equals(music)){
                int votes = playlistModel.getTrackSelectedModelList().get(i).getLikes();
                playlistModel.getTrackSelectedModelList().get(i).setLikes(votes + 1);

                List<String> emails = new ArrayList<>();
                if(playlistModel.getTrackSelectedModelList().get(i).getUsersVoted() != null){
                    emails = playlistModel.getTrackSelectedModelList().get(i).getUsersVoted();
                    emails.add(email);

                }else{
                    emails.add(email);
                }
                playlistModel.getTrackSelectedModelList().get(i).setUsersVoted(emails);
                playlistRepository.save(playlistModel);
            }
        }
    }
}
