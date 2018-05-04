package com.dasturlash.gallery_android.mainscreen;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.ResponseHolder;
import com.dasturlash.gallery_android.details.PhotoDetailActivity;
import com.dasturlash.gallery_android.models.PhotosModel;
import com.dasturlash.gallery_android.retrofit.ApiClient;
import com.dasturlash.gallery_android.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GalleryListener {
    public static final String EXTRA_CURRENT_ITEM = "CurrentItem";
    public static final String EXTRA_PHOTO_MODEL = "ExtraPhotoModel";

    private GalleryAdapter adapter;
    private PhotosModel photosModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView.LayoutManager manager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            manager = new GridLayoutManager(MainActivity.this, 2);
        } else {
            manager = new GridLayoutManager(MainActivity.this, 1 + 2);
        }
        adapter = new GalleryAdapter(MainActivity.this);
        RecyclerView photoList = findViewById(R.id.list_photo);
        photoList.setLayoutManager(manager);
        photoList.setAdapter(adapter);
        photoList.setHasFixedSize(true);
        if (ResponseHolder.getInstance().getPhotosModel() == null) {
            getData();
        } else {
            photosModel = ResponseHolder.getInstance().getPhotosModel();
            adapter.updateModel(photosModel.getPhotos().getPhoto());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnSearchClickListener(searchListener);
        return true;
    }

    View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "search clicked", Toast.LENGTH_LONG).show();
        }
    };

    public void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<PhotosModel> call = apiInterface.func();
        call.enqueue(new Callback<PhotosModel>() {
            @Override
            public void onResponse(@NonNull Call<PhotosModel> call, @NonNull Response<PhotosModel> response) {
                if (response.body() != null) {
                    photosModel = response.body();
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

    @Override
    public void onImageClicked(int position) {
        Intent intent = new Intent(MainActivity.this, PhotoDetailActivity.class);
        intent.putParcelableArrayListExtra(MainActivity.EXTRA_PHOTO_MODEL, photosModel.getPhotos().getPhoto());
        intent.putExtra(MainActivity.EXTRA_CURRENT_ITEM, position);
        startActivity(intent);
    }
}
