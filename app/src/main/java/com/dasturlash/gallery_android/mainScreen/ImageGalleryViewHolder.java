package com.dasturlash.gallery_android.mainScreen;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.models.ImageModel;

/**
 * Created by QAREKEN on 4/24/2018.
 */

class ImageGalleryViewHolder extends RecyclerView.ViewHolder {
    private ImageView photo;

    ImageGalleryViewHolder(View itemView) {
        super(itemView);
        photo = itemView.findViewById(R.id.item_image);
    }

    void populateModel(final int position, final ImageGalleryListener listener, View view) {
        Glide.with(view.getContext())
                .load(ImageModel.getImageModels().get(position).getImageUrl())
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
