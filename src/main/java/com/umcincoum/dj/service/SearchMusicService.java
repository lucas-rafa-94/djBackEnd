package com.umcincoum.dj.service;

import com.umcincoum.dj.model.ArtistsModel;
import com.umcincoum.dj.model.TracksModel;
import com.umcincoum.dj.restUtilsSpot.SpotifyCaller;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchMusicService {

    @Autowired
    SpotifyCaller spotifyCaller;

    public List<ArtistsModel> getArtists (String artist){


        List<ArtistsModel> artistsArrayOutput = new ArrayList<>();

        JSONArray artistsArrayInput = new JSONObject(spotifyCaller.getArtistsSpotCaller(artist).getBody()).getJSONObject("artists").getJSONArray("items");


        for (int i = 0; i < artistsArrayInput.length();i++){

            ArtistsModel artistsModel = new ArtistsModel();
            artistsModel.setName(artistsArrayInput.getJSONObject(i).getString("name"));
            artistsModel.setId(artistsArrayInput.getJSONObject(i).getString("id"));
            if(!artistsArrayInput.getJSONObject(i).getJSONArray("images").toString().equals("[]")){
                artistsModel.setPhotoUri(artistsArrayInput.getJSONObject(i).getJSONArray("images").getJSONObject(0).getString("url"));
            }
            artistsArrayOutput.add(artistsModel);
        }

        return artistsArrayOutput;
    }

    public List<TracksModel> getTopTracksFromArtist(String id){
        List<TracksModel> tracksArrayOutput = new ArrayList<>();
        JSONArray tracksArrayInput = new JSONObject(spotifyCaller.getTopTracksFromArtistSpotCaller(id).getBody()).getJSONArray("tracks");

        System.out.println(tracksArrayInput.getJSONObject(0).toString());
        for (int i = 0; i < tracksArrayInput.length(); i++){

            TracksModel tracksModel = new TracksModel();
            tracksModel.setId(tracksArrayInput.getJSONObject(i).getString("id"));
            tracksModel.setName(tracksArrayInput.getJSONObject(i).getString("name"));

            if(!tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").toString().equals("[]")){
                tracksModel.setPhotoUri(tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));
            }
            tracksArrayOutput.add(tracksModel);
        }

        return tracksArrayOutput;
    }

    public List<TracksModel> getTracksFromArtistWithTrack(String artist, String track){

        List<TracksModel> tracksArrayOutput = new ArrayList<>();
        JSONArray tracksArrayInput = new JSONObject(spotifyCaller.getTracksFromArtistByNameSpotCaller(artist,track).getBody()).getJSONObject("tracks").getJSONArray("items");

        for (int i = 0; i < tracksArrayInput.length(); i++){

            TracksModel tracksModel = new TracksModel();
            tracksModel.setId(tracksArrayInput.getJSONObject(i).getString("id"));
            tracksModel.setName(tracksArrayInput.getJSONObject(i).getString("name"));

            if(!tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").toString().equals("[]")){
                tracksModel.setPhotoUri(tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));
            }
            tracksArrayOutput.add(tracksModel);
        }

        return tracksArrayOutput;
    }
}
