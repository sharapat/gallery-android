package com.dasturlash.gallery_android.details;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.ResponseHolder;
import com.dasturlash.gallery_android.mainscreen.MainActivity;

public class PhotoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ViewPager viewPager = findViewById(R.id.viewpager);

        int currentItem = getIntent().getIntExtra(MainActivity.EXTRA_CURRENT_ITEM, 0);
        getSupportActionBar().setTitle(ResponseHolder.getInstance().getPhotosModel().getPhotos().getPhoto().get(currentItem).getTitle());

        ViewPager.SimpleOnPageChangeListener pageListener = new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                ActionBar actionBar = PhotoDetailActivity.this.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(ResponseHolder.getInstance().getPhotosModel().getPhotos()
                            .getPhoto().get(position).getTitle());
                }
            }
        };
        PhotoDetailPagerAdapter adapter = new PhotoDetailPagerAdapter(this);

        viewPager.setAdapter(adapter);
        adapter.updateModel(ResponseHolder.getInstance().getPhotosModel().getPhotos().getPhoto());
        viewPager.setOnPageChangeListener(pageListener);
        viewPager.setCurrentItem(currentItem);
    }
}
