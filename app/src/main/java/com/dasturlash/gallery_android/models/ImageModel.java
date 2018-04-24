package com.dasturlash.gallery_android.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by QAREKEN on 4/24/2018.
 */

public class ImageModel implements Parcelable {
    private String imageUrl;
    private String imageTitle;

    public ImageModel(String imageUrl, String imageTitle) {
        this.imageUrl = imageUrl;
        this.imageTitle = imageTitle;
    }

    protected ImageModel(Parcel in) {
        imageUrl = in.readString();
        imageTitle = in.readString();
    }

    public static final Parcelable.Creator<ImageModel> CREATOR = new Parcelable.Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel parcel) {
            return new ImageModel(parcel);
        }

        @Override
        public ImageModel[] newArray(int i) {
            return new ImageModel[i];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(imageTitle);
    }
}
