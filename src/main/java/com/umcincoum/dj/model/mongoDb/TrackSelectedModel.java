package com.umcincoum.dj.model.mongoDb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TrackSelectedModel {

    @Id
    private String id;
    private String name;
    private String artist;
    private String photoUri;
    private UserModel userModel;
    private int likes;

    public TrackSelectedModel(String id, String name, String artist, String photoUri, UserModel userModel, int likes) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.photoUri = photoUri;
        this.userModel = userModel;
        this.likes = likes;
    }

    public TrackSelectedModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
