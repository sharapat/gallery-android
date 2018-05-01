package com.dasturlash.gallery_android.retrofit;

import com.dasturlash.gallery_android.models.PhotosModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by QAREKEN on 5/1/2018.
 */

public interface ApiInterface {
    @GET("/services/rest/?method=flickr.interestingness.getList&"
            + "api_key=5cd6479a3000bf6e31b0e320a1ae8fe5&date=&extras=url_o&per_page=20&"
            + "page=1&format=json&nojsoncallback=1")
    Call<PhotosModel> func();
}
