package com.example.covidfight;

//Class ReviewItem: class for a review;

public class ReviewItem {
    private float starNumbers;
    private int id;
    private String comment;
    private String restaurantName;

    public ReviewItem(float starNumbers, int id, String comment, String restaurantName) {
        this.starNumbers = starNumbers;
        this.id = id;
        this.comment = comment;
        this.restaurantName = restaurantName;
    }

    public float getStarNumbers() {
        return starNumbers;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
