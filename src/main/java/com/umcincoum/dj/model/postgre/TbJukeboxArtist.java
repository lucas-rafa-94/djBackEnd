package com.umcincoum.dj.model.postgre;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_JUKEBOX_ARTIST")
public class TbJukeboxArtist {

    @Id
    @Column(columnDefinition = "text")
    private String id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String uriImage;

    @Column(columnDefinition = "text")
    private String playlist;

    public TbJukeboxArtist(String id, String name, String uriImage, String playlist) {
        this.id = id;
        this.name = name;
        this.uriImage = uriImage;
        this.playlist = playlist;
    }

    public TbJukeboxArtist() {
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
