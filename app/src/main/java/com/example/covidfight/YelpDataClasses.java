package com.example.covidfight;

import com.google.gson.annotations.SerializedName;
import java.util.List;

class YelpSearchResult {
    @SerializedName("total") int total;
    @SerializedName("businesses") List<YelpRestaurant> restaurants;
}

class YelpRestaurant {
    String name;
    Double rating;
    String price;
    @SerializedName("review_count") int numReviews;
    @SerializedName("distance") Double distanceInMeters;
    @SerializedName("image_url") String imageUrl;
    List<YelpCategory> categories;
    YelpLocation location;

    public String displayDistance() {
        Double milesPerMeter = 0.000621371;
        String distanceInMiles = "%.2f".format(String.valueOf(distanceInMeters * milesPerMeter));
        return distanceInMiles+" miles";
    }
}

class YelpCategory {
    String title;
}

class YelpLocation {
    @SerializedName("address1") String address;
}