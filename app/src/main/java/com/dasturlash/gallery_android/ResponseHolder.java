package com.dasturlash.gallery_android;

import com.dasturlash.gallery_android.models.PhotosModel;

/**
 * Created by QAREKEN on 5/2/2018.
 */

public final class ResponseHolder {

    private static ResponseHolder instance = null;
    private PhotosModel photosModel = null;

    private ResponseHolder() {
    }

    public static ResponseHolder getInstance() {
        if (instance == null) {
            instance = new ResponseHolder();
        }
        return instance;
    }

    public PhotosModel getPhotosModel() {
        return photosModel;
    }

    public void setPhotosModel(PhotosModel photosModel) {
        this.photosModel = photosModel;
    }
}
