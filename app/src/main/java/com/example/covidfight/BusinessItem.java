package com.example.covidfight;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Data model for each card in the RecyclerView
 */
public class BusinessItem implements Parcelable {

    private String title;
    private String info;
    private int imageResource;
    private int rating;

    /**
     * Constructor for the Business data model
     * @param title name of the business
     * @param info  information about the business
     */
    public BusinessItem(String title, String info, int imageResource, int rating) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.rating = rating;
    }

    protected BusinessItem(Parcel in) {
        title = in.readString();
        info = in.readString();
        imageResource = in.readInt();
        rating = in.readInt();
    }

    public static final Creator<BusinessItem> CREATOR = new Creator<BusinessItem>() {
        @Override
        public BusinessItem createFromParcel(Parcel in) {
            return new BusinessItem(in);
        }

        @Override
        public BusinessItem[] newArray(int size) {
            return new BusinessItem[size];
        }
    };

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(info);
        parcel.writeInt(imageResource);
        parcel.writeInt(rating);
    }
}