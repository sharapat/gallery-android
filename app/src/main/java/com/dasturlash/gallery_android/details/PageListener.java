package com.dasturlash.gallery_android.details;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.dasturlash.gallery_android.ResponseHolder;

/**
 * Created by QAREKEN on 5/6/2018.
 */

public class PageListener extends ViewPager.SimpleOnPageChangeListener {
    private AppCompatActivity activity;

    PageListener(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(ResponseHolder.getInstance().getPhotosModel().getPhotos()
                    .getPhoto().get(position).getTitle());
        }
    }
}
