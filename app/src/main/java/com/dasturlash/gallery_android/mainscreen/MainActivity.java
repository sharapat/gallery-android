package com.dasturlash.gallery_android.mainscreen;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.ResponseHolder;
import com.dasturlash.gallery_android.details.PhotoDetailActivity;
import com.dasturlash.gallery_android.models.Photo;
import com.dasturlash.gallery_android.models.PhotosModel;
import com.dasturlash.gallery_android.retrofit.ApiClient;
import com.dasturlash.gallery_android.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements GalleryListener, GalleryView {
    public static final String EXTRA_CURRENT_ITEM = "CurrentItem";

    private GalleryAdapter adapter;
    private GalleryPresenter galleryPresenter;
    private PhotosModel photosModel;
    private ApiInterface apiInterface;

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
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<PhotosModel> call = apiInterface.interesting();
        galleryPresenter = new GalleryPresenter(this, call, photosModel);

        RecyclerView photoList = findViewById(R.id.list_photo);
        photoList.setLayoutManager(manager);
        photoList.setAdapter(adapter);
        photoList.setHasFixedSize(true);

        if (ResponseHolder.getInstance().getPhotosModel() == null) {
            galleryPresenter.getInterestings();
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
        searchView.setOnQueryTextListener(onQueryTextListener);
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();
        return true;
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            Call<PhotosModel> call = apiInterface.search(s);
            galleryPresenter = new GalleryPresenter(MainActivity.this, call, photosModel);
            galleryPresenter.getSearch();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };

    @Override
    public void onImageClicked(List<Photo> photos, int position) {
        Intent intent = new Intent(MainActivity.this, PhotoDetailActivity.class);
        intent.putExtra(MainActivity.EXTRA_CURRENT_ITEM, position);
        startActivity(intent);
    }

    @Override
    public void updateModel(PhotosModel model) {
        ResponseHolder.getInstance().setPhotosModel(model);
        adapter.updateModel(model.getPhotos().getPhoto());
    }
}
