package com.example.covidfight;

//Class ReviewItem: class for a review;

public class ReviewItem {
    private float starNumbers;
    private int id;
    private String comment;

    public ReviewItem(float starNumbers, int id, String comment) {
        this.starNumbers = starNumbers;
        this.id = id;
        this.comment = comment;
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
}
