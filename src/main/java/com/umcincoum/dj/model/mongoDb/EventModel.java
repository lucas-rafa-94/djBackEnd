package com.umcincoum.dj.model.mongoDb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class EventModel {

    @Id
    private String id;
    private String name;
    private Date date;
    private String description;
    private String photoUri;

    public EventModel(String id, String name, Date date, String description, String photoUri) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.photoUri = photoUri;
    }

    public EventModel() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
