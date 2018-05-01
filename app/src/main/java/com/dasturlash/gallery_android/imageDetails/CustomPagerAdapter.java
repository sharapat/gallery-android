package com.dasturlash.gallery_android.imageDetails;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dasturlash.gallery_android.R;
import com.dasturlash.gallery_android.models.Photo;

import java.util.List;

/**
 * Created by QAREKEN on 4/28/2018.
 */

class CustomPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Photo> photoModels;

    CustomPagerAdapter(Context context, List<Photo> photoModels) {
        this.context = context;
        this.photoModels = photoModels;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_viewpager, container, false);
        final ImageView image = layout.findViewById(R.id.image);
        Glide.with(context)
                .load(photoModels.get(position).getUrl())
                .asBitmap()
                .error(R.drawable.ic_launcher_foreground)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        image.setImageBitmap(resource);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return photoModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return photoModels.get(position).getTitle();
    }
}
