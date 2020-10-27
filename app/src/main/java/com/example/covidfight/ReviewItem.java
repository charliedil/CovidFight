package com.example.covidfight;

//Class ReviewItem: class for a review;

public class ReviewItem {
    private float starNumbers;
    private String comment;

    public ReviewItem(float starNumbers, String comment) {
        this.starNumbers = starNumbers;
        this.comment = comment;
    }

    public float getStarNumbers() {
        return starNumbers;
    }

    public String getComment() {
        return comment;
    }
}
