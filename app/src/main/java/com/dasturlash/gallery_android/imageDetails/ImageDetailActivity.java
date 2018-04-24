package com.dasturlash.gallery_android.imageDetails;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.models.ImageModel;

public class ImageDetailActivity extends AppCompatActivity {
    public static final String EXTRA_SPACE_IMAGE = "EXTRA_SPACE_IMAGE";

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        image = findViewById(R.id.image);
        ImageModel model = getIntent().getParcelableExtra(EXTRA_SPACE_IMAGE);

        Glide.with(this)
                .load(model.getImageUrl())
                .asBitmap()
                .error(R.drawable.ic_launcher_foreground)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        image.setImageBitmap(resource);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);
    }
}
