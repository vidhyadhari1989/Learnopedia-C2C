package com.c2c.learnopedia.model;

/**
 * Created by prakash on 6/29/2016.
 */
public class Generics {

    private String genericId;

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getGenericId() {
        return genericId;
    }

    public void setGenericId(String genericId) {
        this.genericId = genericId;
    }

    private String genericName;

    public Generics(String genericId, String genericName) {
        this.genericId = genericId;
        this.genericName = genericName;
    }
}
