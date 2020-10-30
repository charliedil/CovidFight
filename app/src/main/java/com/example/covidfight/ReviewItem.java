package com.example.covidfight;

//Class ReviewItem: class for a review;

public class ReviewItem {
    private float starNumbers;
    private String comment;
    private String date;
    public ReviewItem() {}
    public ReviewItem(float starNumbers, String comment, String date) {
        this.starNumbers = starNumbers;
        this.comment = comment;
        this.date = date;
    }

    public float getStarNumbers() {
        return starNumbers;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }
}
