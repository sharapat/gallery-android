package com.dasturlash.gallery_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by QAREKEN on 5/1/2018.
 */

public class PhotosModel {

    @SerializedName("photo")
    @Expose
    private ArrayList<PhotoModel> photo = null;

    public PhotosModel(ArrayList<PhotoModel> photo) {
        this.photo = photo;
    }

    public ArrayList<PhotoModel> getPhoto() {
        return photo;
    }
}
