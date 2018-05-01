package com.dasturlash.gallery_android.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by QAREKEN on 5/1/2018.
 */

public class ApiClient {
    private static Retrofit retrofit = null;

    private static Gson gson = null;
    public static Retrofit getClient() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        if (retrofit == null) {
            String API_BASE_URL = "https://api.flickr.com/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
