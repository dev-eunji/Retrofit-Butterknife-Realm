package com.example.eunjilee.librarysampleapp;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by eunjilee on 26/01/2017.
 */

public class Owner extends RealmObject{
    @SerializedName("avatar_url")
    String OwnerImg;

    public Owner() {
    }

    public Owner(String ownerImg) {
        OwnerImg = ownerImg;
    }

    public String getOwnerImg() {
        return OwnerImg;
    }

    public void setOwnerImg(String ownerImg) {
        OwnerImg = ownerImg;
    }
}
