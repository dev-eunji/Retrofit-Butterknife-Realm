package com.example.eunjilee.librarysampleapp;

import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

/**
 * Created by eunjilee on 26/01/2017.
 */

public class Repo extends RealmObject
{
    @SerializedName("name")
    String OwnerName;
    @SerializedName("full_name")
    String OwnerFullName;
    @SerializedName("owner")
    Owner owner;


    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getOwnerFullName() {
        return OwnerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        OwnerFullName = ownerFullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
