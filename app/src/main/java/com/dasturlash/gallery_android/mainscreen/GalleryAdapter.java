package com.dasturlash.gallery_android.mainscreen;

import android.support.annotation.NonNull;
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

    GalleryAdapter(GalleryListener listener) {
        this.listener = listener;
    }

    void updateModel(List<Photo> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
            holder.populateModel(models, position, listener, view);
    }

    @Override
    public int getItemCount() {
        return models == null ? 0 : models.size();
    }
}
