package com.umcincoum.dj.model.postgre;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_JUKEBOX_MUSIC")
public class TbJukeboxMusic {

    @Id
    @Column(columnDefinition = "text")
    private String id;

    @Column(columnDefinition = "text")
    private String music;

    @Column(columnDefinition = "text")
    private String artist;

    @Column(columnDefinition = "text")
    private String album;

    @Column(columnDefinition = "text")
    private String uriImage;

    @Column(columnDefinition = "text")
    private String playlist;

    public TbJukeboxMusic(String id, String music, String artist, String album, String uriImage, String playlist) {
        this.id = id;
        this.music = music;
        this.artist = artist;
        this.album = album;
        this.uriImage = uriImage;
        this.playlist = playlist;
    }

    public TbJukeboxMusic() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
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

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }
}
