package com.dasturlash.gallery_android.details;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.mainscreen.MainActivity;
import com.dasturlash.gallery_android.models.Photo;

import java.util.ArrayList;

public class PhotoDetailActivity extends AppCompatActivity {
    private ArrayList<Photo> photoModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        photoModels = getIntent().getParcelableArrayListExtra(MainActivity.EXTRA_PHOTO_MODEL);
        Log.d("model size", String.valueOf(photoModels.size()));
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this, photoModels));
        viewPager.setCurrentItem(getIntent().getIntExtra(MainActivity.EXTRA_CURRENT_ITEM, 0));
    }
}
