package com.dasturlash.gallery_android.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by QAREKEN on 4/24/2018.
 */

public class PhotoModel implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("secret")
    @Expose
    private Integer secret;

    @SerializedName("server")
    @Expose
    private Integer server;

    @SerializedName("farm")
    @Expose
    private Integer farm;

    @SerializedName("title")
    @Expose
    private String title;
    private String url;

    private PhotoModel(Parcel parcel) {
        id = parcel.readInt();
        secret = parcel.readInt();
        server = parcel.readInt();
        farm = parcel.readInt();
        title = parcel.readString();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        url = "https://farm" + farm.toString() + ".staticflickr.com/" + server.toString() + "/"
                + id.toString() + "_" + secret.toString() + ".jpg";
        return url;
    }

    public static final Parcelable.Creator CREATOR = new Creator<PhotoModel>() {
        @Override
        public PhotoModel createFromParcel(Parcel parcel) {
            return new PhotoModel(parcel);
        }

        @Override
        public PhotoModel[] newArray(int i) {
            return new PhotoModel[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(secret);
        parcel.writeInt(server);
        parcel.writeInt(farm);
        parcel.writeString(title);
    }
}
