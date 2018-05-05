package com.dasturlash.gallery_android.mainscreen;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dasturlash.gallery_android.ResponseHolder;
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
    private GalleryAdapter adapter;

    GalleryPresenter(GalleryView galleryView, Call<PhotosModel> call,
                     PhotosModel photosModel, GalleryAdapter adapter) {
        this.galleryView = galleryView;
        this.call = call;
        this.photosModel = photosModel;
        this.adapter = adapter;
    }

    void getInterestings() {
        galleryView.resultNotFoundHide();
        galleryView.listHide();
        galleryView.startProgressBar();
        call.enqueue(new Callback<PhotosModel>() {
            @Override
            public void onResponse(@NonNull Call<PhotosModel> call, @NonNull Response<PhotosModel> response) {
                if (response.body() != null) {
                    galleryView.stopProgressBar();
                    photosModel = response.body();
                    assert photosModel != null;
                    if (photosModel.getPhotos().getPhoto().isEmpty()) {
                        galleryView.resultNotFoundShow();
                    } else {
                        galleryView.listShow();
                    }
                    ResponseHolder.getInstance().setPhotosModel(photosModel);
                    adapter.updateModel(photosModel.getPhotos().getPhoto());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosModel> call, @NonNull Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });
    }

    void getSearch() {
        galleryView.resultNotFoundHide();
        galleryView.listHide();
        galleryView.startProgressBar();
        call.enqueue(new Callback<PhotosModel>() {
            @Override
            public void onResponse(@NonNull Call<PhotosModel> call, @NonNull Response<PhotosModel> response) {
                if (response.body() != null) {
                    galleryView.stopProgressBar();
                    photosModel = response.body();
                    assert photosModel != null;
                    if (photosModel.getPhotos().getPhoto().isEmpty()) {
                        galleryView.resultNotFoundShow();
                    } else {
                        galleryView.listShow();
                        galleryView.setSearchForMessage();
                    }
                    ResponseHolder.getInstance().setPhotosModel(photosModel);
                    adapter.updateModel(photosModel.getPhotos().getPhoto());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosModel> call, @NonNull Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });
    }
}
