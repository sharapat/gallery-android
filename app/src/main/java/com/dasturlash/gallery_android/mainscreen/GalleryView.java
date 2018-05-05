package com.dasturlash.gallery_android.mainscreen;

/**
 * Created by QAREKEN on 5/5/2018.
 */

public interface GalleryView {
    void resultNotFoundShow();
    void resultNotFoundHide();
    void startProgressBar();
    void stopProgressBar();
    void listHide();
    void listShow();
    void setSearchForMessage();
}
