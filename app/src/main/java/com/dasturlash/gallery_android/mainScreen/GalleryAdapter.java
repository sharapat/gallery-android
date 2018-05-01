package com.dasturlash.gallery_android.mainScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.models.Photo;

import java.util.List;

/**
 * Created by QAREKEN on 4/24/2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {
    private GalleryListener listener;
    private List<Photo> models;
    private View view;

    GalleryAdapter(GalleryListener listener, List<Photo> models) {
        this.listener = listener;
        this.models = models;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
            holder.populateModel(models, position, listener, view);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
