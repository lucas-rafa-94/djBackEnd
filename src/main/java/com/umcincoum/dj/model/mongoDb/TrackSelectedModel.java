package com.umcincoum.dj.model.mongoDb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class TrackSelectedModel {

    @Id
    private String music;
    private String name;
    private String artist;
    private String photoUri;
    private UserModel userModel;
    private List<String> usersVoted;
    private int likes;

    public TrackSelectedModel(String music, String name, String artist, String photoUri, UserModel userModel, List<String> usersVoted, int likes) {
        this.music = music;
        this.name = name;
        this.artist = artist;
        this.photoUri = photoUri;
        this.userModel = userModel;
        this.usersVoted = usersVoted;
        this.likes = likes;
    }

    public TrackSelectedModel() {
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<String> getUsersVoted() {
        return usersVoted;
    }

    public void setUsersVoted(List<String> usersVoted) {
        this.usersVoted = usersVoted;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
