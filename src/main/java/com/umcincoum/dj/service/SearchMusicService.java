package com.umcincoum.dj.service;

import com.umcincoum.dj.model.ArtistsModel;
import com.umcincoum.dj.model.TracksModel;
import com.umcincoum.dj.model.postgre.TbJukeboxArtist;
import com.umcincoum.dj.model.postgre.TbJukeboxMusic;
import com.umcincoum.dj.restUtilsSpot.SpotifyCaller;
import com.umcincoum.dj.service.postgre.TbJukeboxArtistService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SearchMusicService {

    @Autowired
    SpotifyCaller spotifyCaller;

    @Autowired
    TbJukeboxArtistService tbJukeboxArtistService;

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
            tracksModel.setAlbum(tracksArrayInput.getJSONObject(i).getJSONObject("album").getString("name"));

            if(!tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").toString().equals("[]")){
                tracksModel.setPhotoUri(tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));
            }
            tracksArrayOutput.add(tracksModel);
        }

        return tracksArrayOutput;
    }

    public List<TracksModel> getTracksFromArtistWithTrack(String artist, String track) {

        List<TracksModel> tracksArrayOutput = new ArrayList<>();
        JSONArray tracksArrayInput = new JSONObject(spotifyCaller.getTracksFromArtistByNameSpotCaller(artist, track).getBody()).getJSONObject("tracks").getJSONArray("items");

        for (int i = 0; i < tracksArrayInput.length(); i++) {

            TracksModel tracksModel = new TracksModel();
            tracksModel.setId(tracksArrayInput.getJSONObject(i).getString("id"));
            tracksModel.setName(tracksArrayInput.getJSONObject(i).getString("name"));
            tracksModel.setAlbum(tracksArrayInput.getJSONObject(i).getJSONObject("album").getString("name"));
            if (!tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").toString().equals("[]")) {
                tracksModel.setPhotoUri(tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));
            }
            tracksArrayOutput.add(tracksModel);
        }

        return tracksArrayOutput;
    }



        public List<TbJukeboxMusic> getTracksFromPlaylist(String playlist, int offset){

            List<TbJukeboxMusic> tracksArrayOutput = new ArrayList<>();
            List<TbJukeboxArtist> tbJukeboxArtistList = new ArrayList<>();
            JSONArray tracksArrayInput = new JSONObject(spotifyCaller.getTracksFromPlaylist(playlist, offset).getBody()).getJSONObject("tracks").getJSONArray("items");

            for (int i = 0; i < tracksArrayInput.length(); i++){

                TbJukeboxMusic tracksModel = new TbJukeboxMusic();
                TbJukeboxArtist tbJukeboxArtist = new TbJukeboxArtist();

                tracksModel.setId(tracksArrayInput.getJSONObject(i).getJSONObject("track").getString("id"));
                tracksModel.setMusic(tracksArrayInput.getJSONObject(i).getJSONObject("track").getString("name").toUpperCase());
                tracksModel.setAlbum(tracksArrayInput.getJSONObject(i).getJSONObject("track").getJSONObject("album").getString("name").toUpperCase());
                tracksModel.setArtist(tracksArrayInput.getJSONObject(i).getJSONObject("track").getJSONObject("album").getJSONArray("artists").getJSONObject(0).getString("name").toUpperCase());

                tbJukeboxArtist.setName(tracksArrayInput.getJSONObject(i).getJSONObject("track").getJSONObject("album").getJSONArray("artists").getJSONObject(0).getString("name").toUpperCase());
                tbJukeboxArtist.setId(tracksArrayInput.getJSONObject(i).getJSONObject("track").getJSONObject("album").getJSONArray("artists").getJSONObject(0).getString("id").toUpperCase());

                tracksModel.setPlaylist(playlist);
                tbJukeboxArtist.setPlaylist(playlist);

                if(!tracksArrayInput.getJSONObject(i).getJSONObject("track").getJSONObject("album").getJSONArray("images").toString().equals("[]")){
                    tracksModel.setUriImage(tracksArrayInput.getJSONObject(i).getJSONObject("track").getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));
                    tbJukeboxArtist.setUriImage(tracksArrayInput.getJSONObject(i).getJSONObject("track").getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));

                }
                tracksArrayOutput.add(tracksModel);
                tbJukeboxArtistList.add(tbJukeboxArtist);
            }

            tbJukeboxArtistService.saveAll(tbJukeboxArtistList);

            return tracksArrayOutput;
        }





    public List<TracksModel> getTracks(String track){

        List<TracksModel> tracksArrayOutput = new ArrayList<>();
        JSONArray tracksArrayInput = new JSONObject(spotifyCaller.getTracks(track).getBody()).getJSONObject("tracks").getJSONArray("items");

        for (int i = 0; i < tracksArrayInput.length(); i++){

            TracksModel tracksModel = new TracksModel();
            tracksModel.setId(tracksArrayInput.getJSONObject(i).getString("id"));
            tracksModel.setName(tracksArrayInput.getJSONObject(i).getString("name"));
            tracksModel.setArtist(tracksArrayInput.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name"));

            if(!tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").toString().equals("[]")){
                tracksModel.setPhotoUri(tracksArrayInput.getJSONObject(i).getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"));
            }
            tracksArrayOutput.add(tracksModel);
        }

        List<TracksModel> distinctElements = tracksArrayOutput.stream()
                .filter( distinctByKey(p -> p.getArtist()) )
                .collect( Collectors.toList() );

        return distinctElements;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
