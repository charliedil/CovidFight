package com.example.covidfight;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class YelpSearchResult {
    @SerializedName("total") int total;
    @SerializedName("businesses") ArrayList<YelpRestaurant> restaurants;
}

class YelpRestaurant implements Parcelable {
    String name;
    Double rating;
    String price;
    @SerializedName("review_count") int numReviews;
    @SerializedName("distance") Double distanceInMeters;
    @SerializedName("image_url") String imageUrl;
    ArrayList<YelpCategory> categories;
    YelpLocation location;

    public String displayDistance() {
        Double milesPerMeter = 0.000621371;
        String distanceInMiles = String.format("%.2f", distanceInMeters * milesPerMeter);
        return distanceInMiles+" miles";
    }

    /**
     * Constructor for the Business data model
     * @param name name of the business
     */
    public YelpRestaurant(String name, Double rating, String price, int numReviews, Double distanceInMeters, String imageUrl) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.numReviews = numReviews;
        this.distanceInMeters = distanceInMeters;
        this.imageUrl = imageUrl;
    }

    protected YelpRestaurant(Parcel in) {
        name = in.readString();
        rating = in.readDouble();
        price = in.readString();
        numReviews = in.readInt();
        distanceInMeters = in.readDouble();
        imageUrl = in.readString();
    }

    public static final Parcelable.Creator<YelpRestaurant> CREATOR = new Parcelable.Creator<YelpRestaurant>() {
        @Override
        public YelpRestaurant createFromParcel(Parcel in) {
            return new YelpRestaurant(in);
        }

        @Override
        public YelpRestaurant[] newArray(int size) {
            return new YelpRestaurant[size];
        }
    };

    public String getName() {
        return name;
    }
    public Double getRating() {
        return rating;
    }
    public String getPrice() { return price; }
    public int getNumReviews() { return numReviews; }
    public Double getDistanceInMeters() { return distanceInMeters; }
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(rating);
        parcel.writeString(price);
        parcel.writeInt(numReviews);
        parcel.writeDouble(distanceInMeters);
        parcel.writeString(imageUrl);
    }
}

class YelpCategory {
    String title;
}

class YelpLocation {
    @SerializedName("address1") String address;
}