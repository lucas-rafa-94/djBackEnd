package com.umcincoum.dj.model;

public class TracksModel {

    private String artist;
    private String name;
    private String photoUri;
    private String id;

    public TracksModel(String artist, String name, String photoUri, String id) {
        this.artist = artist;
        this.name = name;
        this.photoUri = photoUri;
        this.id = id;
    }

    public TracksModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
