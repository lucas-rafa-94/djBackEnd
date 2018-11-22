package com.umcincoum.dj.model.mongoDb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class PlaylistModel {

    @Id
    private String playlist;
    private EventModel eventModel;
    private String genre;
    private List<TrackSelectedModel> trackSelectedModelList;

    public PlaylistModel(String playlist, EventModel eventModel, String genre, List<TrackSelectedModel> trackSelectedModelList) {
        this.playlist = playlist;
        this.eventModel = eventModel;
        this.genre = genre;
        this.trackSelectedModelList = trackSelectedModelList;
    }

    public PlaylistModel() {
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<TrackSelectedModel> getTrackSelectedModelList() {
        return trackSelectedModelList;
    }

    public void setTrackSelectedModelList(List<TrackSelectedModel> trackSelectedModelList) {
        this.trackSelectedModelList = trackSelectedModelList;
    }
}
