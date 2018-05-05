package com.dasturlash.gallery_android.mainscreen;

import com.dasturlash.gallery_android.models.Photo;

import java.util.List;

/**
 * Created by QAREKEN on 4/24/2018.
 */

public interface GalleryListener {
    void onImageClicked(List<Photo> photoModels, int position);
}
