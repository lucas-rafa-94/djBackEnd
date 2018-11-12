package com.umcincoum.dj.model.mongoDb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class PlaylistModel {

    @Id
    private String id;
    private EventModel eventModel;
    private String genre;
    private List<TrackSelectedModel> trackSelectedModelList;

    public PlaylistModel(String id, EventModel eventModel, String genre, List<TrackSelectedModel> trackSelectedModelList) {
        this.id = id;
        this.eventModel = eventModel;
        this.genre = genre;
        this.trackSelectedModelList = trackSelectedModelList;
    }

    public PlaylistModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
