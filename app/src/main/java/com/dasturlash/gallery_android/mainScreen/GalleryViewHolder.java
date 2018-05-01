package com.dasturlash.gallery_android.mainScreen;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.models.PhotoModel;

import java.util.ArrayList;

/**
 * Created by QAREKEN on 4/24/2018.
 */

class GalleryViewHolder extends RecyclerView.ViewHolder {
    private ImageView photo;

    GalleryViewHolder(View itemView) {
        super(itemView);
        photo = itemView.findViewById(R.id.item_image);
    }

    void populateModel(ArrayList<PhotoModel> photoModels, final int position, final GalleryListener listener, View view) {
        Glide.with(view.getContext())
                .load(photoModels.get(position).getUrl())
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(photo);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onImageClicked(position);
                }
            }
        });
    }
}
