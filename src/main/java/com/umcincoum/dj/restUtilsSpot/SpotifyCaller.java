package com.umcincoum.dj.restUtilsSpot;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;

@Service
public class SpotifyCaller {

    private static String user = "12154043033";

    public static String tokenSpotCaller(){

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.add("Authorization", "Basic ZjZhYmZmZDBmN2VmNDBiMzliODIzZTk5NjgyNGY2NDM6MGVlOWNmOWE3OWQyNDBkMDg3NzcyMTRjYTc1MGRhY2Y=");
        headers.add("Content-Type", "application/x-www-form-urlencoded");


        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        JSONObject jsonObject = new JSONObject(restTemplate.postForEntity("https://accounts.spotify.com/api/token",request, String.class ).getBody());

        return jsonObject.getString("access_token");
    }

    public ResponseEntity<String> getArtistsSpotCaller(String artist){
        String newArtist = "";
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.add("Authorization", "Bearer " + tokenSpotCaller());

        if(artist.contains(" ")){
            newArtist = artist.replace(" ","+");
        }else if(artist.contains("%20")){
            newArtist = artist.replace("%20","+");
        }else {
            newArtist = artist;
        }

        System.out.println(newArtist);
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("https://api.spotify.com/v1/search")
                .queryParam("q", newArtist + "*")
                .queryParam("type", "artist");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity<String> getTopTracksFromArtistSpotCaller(String id){

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.add("Authorization", "Bearer " + tokenSpotCaller());

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("https://api.spotify.com/v1/artists/" + id + "/top-tracks")
                .queryParam("country", "BR");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity<String> getTracksFromArtistByNameSpotCaller(String artist, String track){
        String finalUri = "";

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.add("Authorization", "Bearer " + tokenSpotCaller());

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("https://api.spotify.com/v1/search")
                .queryParam("q", "artist:" + artist + " track:" + track + "*")
                .queryParam("type", "track")
                .queryParam("market", "BR");

        try{
            finalUri = URLDecoder.decode(builder.toUriString(), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return restTemplate.exchange(finalUri, HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity<String> getTracksFromPlaylist(String playlist, int offset){
        String finalUri = "";

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.add("Authorization", "Bearer " + tokenSpotCaller());

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("https://api.spotify.com/v1/users/" + user + "/playlists/" + playlist)
                .queryParam("fields", "tracks.items(artist,track(id,name,album(artists,name,images)))")
                .queryParam("offset", Integer.toString(offset));



        try{
            finalUri = URLDecoder.decode(builder.toUriString(), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return restTemplate.exchange(finalUri, HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity<String> getTracks(String track){
        String finalUri = "";

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.add("Authorization", "Bearer " + tokenSpotCaller());

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("https://api.spotify.com/v1/search")
                .queryParam("q", "track:" + track + "*")
                .queryParam("type", "track")
                .queryParam("market", "BR");

        try{
            finalUri = URLDecoder.decode(builder.toUriString(), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return restTemplate.exchange(finalUri, HttpMethod.GET, entity, String.class);
    }

}
