package com.dasturlash.gallery_android.mainscreen;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dasturlash.gallery_android.models.PhotosModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by QAREKEN on 5/5/2018.
 */

class GalleryPresenter {
    private Call<PhotosModel> call;
    private PhotosModel photosModel;
    private GalleryView galleryView;

    GalleryPresenter(GalleryView galleryView, Call<PhotosModel> call,
                     PhotosModel photosModel) {
        this.galleryView = galleryView;
        this.call = call;
        this.photosModel = photosModel;
    }

    void getInterestings() {
        call.enqueue(new Callback<PhotosModel>() {
            @Override
            public void onResponse(@NonNull Call<PhotosModel> call, @NonNull Response<PhotosModel> response) {
                if (response.body() != null) {
                    photosModel = response.body();
                    galleryView.updateModel(photosModel);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosModel> call, @NonNull Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });
    }

    void getSearch() {
        call.enqueue(new Callback<PhotosModel>() {
            @Override
            public void onResponse(@NonNull Call<PhotosModel> call, @NonNull Response<PhotosModel> response) {
                if (response.body() != null) {
                    photosModel = response.body();
                    galleryView.updateModel(photosModel);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosModel> call, @NonNull Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });
    }
}
