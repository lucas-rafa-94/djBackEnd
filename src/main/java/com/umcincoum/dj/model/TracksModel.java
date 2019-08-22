package com.umcincoum.dj.model;

public class TracksModel {

    private String artist;
    private String name;
    private String photoUri;
    private String artist;
    private String album;
    private String id;

<<<<<<< HEAD
    public TracksModel(String name, String photoUri, String artist, String album, String id) {
=======
    public TracksModel(String artist, String name, String photoUri, String id) {
        this.artist = artist;
>>>>>>> 7e571caa7b9208d42d481d22e1a2f1b971e3ddcf
        this.name = name;
        this.photoUri = photoUri;
        this.artist = artist;
        this.album = album;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
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
