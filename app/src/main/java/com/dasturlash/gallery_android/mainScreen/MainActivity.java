package com.dasturlash.gallery_android.mainScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.imageDetails.ImageDetailActivity;
import com.dasturlash.gallery_android.models.ImageModel;

public class MainActivity extends AppCompatActivity implements ImageGalleryListener {
    private RecyclerView imageList;
    private ImageGalleryAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageList = findViewById(R.id.list_image);
        adapter = new ImageGalleryAdapter(this, ImageModel.getImageModels());
        manager = new GridLayoutManager(this, 2);
        imageList.setAdapter(adapter);
        imageList.setLayoutManager(manager);
        imageList.setHasFixedSize(true);
    }

    @Override
    public void onImageClicked(int position) {
        //TODO here will be a function that opens the photo on the new activity
        ImageModel imageModel = ImageModel.getImageModels().get(position);
        Intent intent = new Intent(this, ImageDetailActivity.class);
        intent.putExtra(ImageDetailActivity.EXTRA_SPACE_IMAGE, imageModel);
        startActivity(intent);
    }
}
