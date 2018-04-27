package com.dasturlash.gallery_android.imageDetails;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dasturlash.gallery_android.R;

public class ImageDetailActivity extends AppCompatActivity {
    public static final String EXTRA_SPACE_IMAGE = "EXTRA_SPACE_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));

    }
}
