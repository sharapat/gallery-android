package com.dasturlash.gallery_android.imageDetails;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.mainScreen.MainActivity;
import com.dasturlash.gallery_android.models.PhotoModel;

import java.util.ArrayList;

public class PhotoDetailActivity extends AppCompatActivity {
    private ArrayList<PhotoModel> photoModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        photoModels = getIntent().getParcelableArrayListExtra(MainActivity.EXTRA_PHOTO_MODEL);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this, photoModels));
        viewPager.setCurrentItem(getIntent().getIntExtra(MainActivity.EXTRA_CURRENT_ITEM, 0));
    }
}