package com.dasturlash.gallery_android.mainScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.models.ImageModel;

import java.util.ArrayList;

/**
 * Created by QAREKEN on 4/24/2018.
 */

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryViewHolder> {
    private ImageGalleryListener listener;
    private ArrayList<ImageModel> models;
    private View view;

    ImageGalleryAdapter(ImageGalleryListener listener, ArrayList<ImageModel> models) {
        this.listener = listener;
        this.models = models;
    }

    @Override
    public ImageGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageGalleryViewHolder holder, int position) {
            holder.populateModel(position, listener, view);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
