package com.example.jaimin.demo.model;

import java.io.Serializable;

/**
 * Created by jaimin on 10/18/2016.
 */

public class Media implements Serializable{

    String mediaId;
    String name;

    public Media(String mediaId, String name) {
        this.mediaId = mediaId;
        this.name = name;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
