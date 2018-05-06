package com.dasturlash.gallery_android.mainscreen;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dasturlash.gallery_android.ResponseHolder;
import com.dasturlash.gallery_android.models.PhotosModel;
import com.dasturlash.gallery_android.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by QAREKEN on 5/5/2018.
 */

class GalleryPresenter {
    private Retrofit apiClient;
    private PhotosModel photosModel;
    private GalleryView galleryView;
    private String searchText;

    GalleryPresenter(GalleryView galleryView, Retrofit apiClient,
                     PhotosModel photosModel) {
        this.galleryView = galleryView;
        this.apiClient = apiClient;
        this.photosModel = photosModel;
    }

    void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    void getInterestings() {
        galleryView.resultNotFoundHide();
        galleryView.listHide();
        galleryView.startProgressBar();
        Call<PhotosModel> call = apiClient.create(ApiInterface.class).interesting();
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
                    galleryView.updateAdapter(photosModel);
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
        Call<PhotosModel> call = apiClient.create(ApiInterface.class).search(searchText);
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
                    galleryView.updateAdapter(photosModel);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosModel> call, @NonNull Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });
    }
}
